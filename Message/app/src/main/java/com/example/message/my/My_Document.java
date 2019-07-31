package com.example.message.my;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.message.R;

import java.io.File;
import java.util.ArrayList;

public class My_Document extends Activity implements View.OnClickListener {
    private ListView mListView;
    private Button mOK;
    private MyFileAdapter mAdapter;
    private Context mContext;
    private File currentFile;
    String sdRootPath;

    private ArrayList<FileEntity> mList;
    private Handler mHandler;
    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_documentlist);
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
        //sdRootPath= Environment.getExternalStorageDirectory().getAbsolutePath();
        sdRootPath="/data/user/0/com.example.message";
        currentFile=new File(sdRootPath);
        System.out.println(sdRootPath);
        initView();
        getData(sdRootPath);
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
            getData(parentPath);
        }

    }

    private void initView(){
        mListView=findViewById(R.id.docList);
        mOK=findViewById(R.id.OK);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                final FileEntity entity=mList.get(position);
                if(entity.getFileType()== FileEntity.Type.FLODER){
                    currentFile=new File(entity.getFilePath());
                    getData(entity.getFilePath());
                }else if(entity.getFileType()== FileEntity.Type.FILE){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //Toast.makeText(mContext,"OK",1);
                            Toast.makeText(mContext,entity.getFilePath()+" "+entity.getFileName(),1).show();
                        }
                    });
                }
            }
        });
    }
    private void getData(final String path){
        new Thread(){
            @Override
            public void run(){
                super.run();
                findAllFiles(path);
            }
        }.start();
    }
    @Override
    public void onStart(){
        super.onStart();
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.OK){
            setResult(100);
            finish();
        }
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
//          System.out.println("position-->"+position+"  ---convertView--"+convertView);
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
