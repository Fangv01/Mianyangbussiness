package com.example.message.my;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.widget.Toolbar;

import com.example.message.R;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class My_Document extends Activity {
    private ListView mListView;
    private EditText pathText;
    private MyFileAdapter mAdapter;
    private Context mContext;
    private File currentFile;
    private String sdRootPath;
    private Toolbar toolbar;
    private ArrayList<FileEntity> mList;
    private Handler mHandler;
    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_documentlist);
        pathText=findViewById(R.id.path_text);
        toolbar=findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.add_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.add_floder:
                        final EditText foldername=new EditText(mContext);
                        AlertDialog.Builder builder=new AlertDialog.Builder(mContext);
                        builder.setTitle("输入文件夹名").setView(foldername).setNegativeButton("Cancel",null)
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        createFolder(foldername.getText().toString());
                                        getData(currentFile.toString());
                                    }
                                });
                        builder.show();
                        break;
                    case R.id.add_file:
                        final EditText filename=new EditText(mContext);
                        AlertDialog.Builder build=new AlertDialog.Builder(mContext);
                        build.setTitle("输入文件名").setView(filename).setNegativeButton("Cancel",null)
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        createFile(filename.getText().toString());
                                        getData(currentFile.toString());
                                    }
                                });
                        build.show();
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
        mHandler=new Handler(){
            @Override
            public void handleMessage(Message msg){
                super.handleMessage(msg);
                switch (msg.what){
                    case 1:if(mAdapter==null){
                                mAdapter=new MyFileAdapter(mContext,mList);
                                mListView.setAdapter(mAdapter);
                            }else mAdapter.notifyDataSetChanged();
                        break;
                    case 2:break;
                    default:break;
                }
            }
        };
        mContext=this;
        mList=new ArrayList<>();
        sdRootPath="/data/user/0/com.example.message";
        currentFile=new File(sdRootPath);
        pathText.setText(currentFile.toString().substring(sdRootPath.length()));
        System.out.println(sdRootPath);
        initView();
        getData(sdRootPath);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_menu, menu);
        return true;
    }

    @Override
    public void onBackPressed(){
        System.out.println("onBackPressed");
        if(sdRootPath.equals(currentFile.getAbsolutePath())){
            System.out.println("当前为根目录");
            super.onBackPressed();
            return;
        }
        String parentPath = currentFile.getParent();
        if (parentPath != null) {
            currentFile=new File(parentPath);
            pathText.setText(currentFile.toString().substring(sdRootPath.length()));
            getData(parentPath);
        }else super.onBackPressed();
    }

    private void initView(){
        mListView=findViewById(R.id.docList);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                final FileEntity entity=mList.get(position);
                if(entity.getFileType()== FileEntity.Type.FLODER){
                    currentFile=new File(entity.getFilePath());
                    pathText.setText(currentFile.toString().substring(sdRootPath.length()));
                    getData(entity.getFilePath());
                }else if(entity.getFileType()== FileEntity.Type.FILE){

                }
            }
        });
    }
    private void getData(final String path){
        findAllFiles(path);
    }
    public void findAllFiles(String path){
        mList.clear();
        if(path==null||path.equals(""))return;
        File fatherFile = new File(path);
        File[] files=fatherFile.listFiles();
        System.out.println(this.getFilesDir().getAbsolutePath()+" OK");
        if (files != null && files.length > 0) {
            for (int i = 0; i < files.length; i++) {
                FileEntity entity = new FileEntity();
                boolean isDirectory = files[i].isDirectory();
                if(isDirectory ==true){
                    entity.setFileType(FileEntity.Type.FLODER);
                }else{
                    entity.setFileType(FileEntity.Type.FILE);
                }
                entity.setFileName(files[i].getName());
                entity.setFilePath(files[i].getAbsolutePath());
                entity.setFileSize(files[i].length()+"");
                mList.add(entity);
                System.out.println(entity.toString());
            }
        }
        mHandler.sendEmptyMessage(1);
    }

    private void createFile(String filename){
        File newFile=new File(currentFile.toString()+"/"+filename+".txt");
        if(newFile.exists()){
            newFile.delete();
        }
        try {
            newFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void createFolder(String foldername){
        File newfolder=new File(currentFile.toString()+"/"+foldername);
        if(newfolder.exists())Toast.makeText(mContext,"文件夹已存在",Toast.LENGTH_LONG);
        else {
            newfolder.mkdir();
            if(newfolder.isDirectory()||newfolder.mkdirs())Toast.makeText(mContext,"创建成功",Toast.LENGTH_LONG);
           else Toast.makeText(mContext,"创建失败",Toast.LENGTH_LONG);
        }
    }
    @Override
    public void onStart(){
        super.onStart();
    }

    class MyFileAdapter extends BaseAdapter {
        private Context mAContext;
        private ArrayList<FileEntity> mAList;
        private LayoutInflater mInflater;

        public MyFileAdapter(Context mContext, ArrayList<FileEntity> mList) {
            super();
            this.mAContext = mContext;
            this.mAList = mList;
            mInflater = LayoutInflater.from(mAContext);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return mAList.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return mAList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public int getItemViewType(int position) {
            if(mAList.get(position).getFileType() == FileEntity.Type.FLODER)return 0;
            else return 1;
        }
        @Override
        public int getViewTypeCount() {
            return 2;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            int type = getItemViewType(position);
            FileEntity entity = mAList.get(position);

            if(convertView == null){
                holder = new ViewHolder();
                switch (type) {
                    case 0://folder
                        convertView = mInflater.inflate(R.layout.item, parent, false);
                        holder.iv = convertView.findViewById(R.id.docImage);
                        holder.tv = convertView.findViewById(R.id.docText);
                        break;
                    case 1://file
                        convertView = mInflater.inflate(R.layout.item, parent, false);
                        holder.iv = convertView.findViewById(R.id.docImage);
                        holder.tv = convertView.findViewById(R.id.docText);
                        break;
                    default:
                        break;
                }
                convertView.setTag(holder);
            }else {
                holder = (ViewHolder) convertView.getTag();
            }

            switch (type) {
                case 0:
                    holder.iv.setImageResource(R.drawable.floder);
                    holder.tv.setText(entity.getFileName());
                    break;
                case 1:
                    holder.iv.setImageResource(R.drawable.file);
                    holder.tv.setText(entity.getFileName());
                    break;
                default:
                    break;
            }
            return convertView;
        }
    }
    class ViewHolder {
        ImageView iv;
        TextView tv;
    }
}
