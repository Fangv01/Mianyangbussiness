package com.example.message.my;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
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


import com.example.message.R;

import java.io.File;
import java.util.ArrayList;

public class My_Document extends Activity implements View.OnClickListener {
    private ListView mListView;
    private Button mOK;
    private EditText pathText;
    private MyFileAdapter mAdapter;
    private Context mContext;
    private File currentFile;
    private String sdRootPath;

    private ArrayList<FileEntity> mList;
    private Handler mHandler;
    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_documentlist);
        pathText=findViewById(R.id.path_text);
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
        pathText.setText(currentFile.toString().substring(sdRootPath.length()));
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
            pathText.setText(currentFile.toString().substring(sdRootPath.length()));
            getData(parentPath);
        }else super.onBackPressed();
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
                    pathText.setText(currentFile.toString().substring(sdRootPath.length()));
                    getData(entity.getFilePath());
                }else if(entity.getFileType()== FileEntity.Type.FILE){
                    openFile(entity.getFilePath());
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

    private void openFile(String path) {
        File file=new File(path);
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        String type = getMIMEType(file);
        intent.setDataAndType(Uri.fromFile(file), type);
        try {
            startActivity(intent);
        }
        catch (Exception e) {
            Toast.makeText(this, "未知类型，不能打开", Toast.LENGTH_SHORT).show();
        }
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

    private String getMIMEType(File file) {
        String type = "*/*";
        String fileName = file.getName();
        int dotIndex = fileName.indexOf('.');
        if(dotIndex < 0) return type;
        String end = fileName.substring(dotIndex, fileName.length()).toLowerCase();
        if(end == "")return type;
        for(int i=0; i<MIME_MapTable.length; i++) {
            if(end == MIME_MapTable[i][0]) type = MIME_MapTable[i][1] ;
        }
        return type;
    }
    private final String[][] MIME_MapTable = {
            // {后缀名， MIME类型}
            { ".3gp", "video/3gpp" },
            { ".apk", "application/vnd.android.package-archive" },
            { ".asf", "video/x-ms-asf" },
            { ".avi", "video/x-msvideo" },
            { ".bin", "application/octet-stream" },
            { ".bmp", "image/bmp" },
            { ".c", "text/plain" },
            { ".class", "application/octet-stream" },
            { ".conf", "text/plain" },
            { ".cpp", "text/plain" },
            { ".doc", "application/msword" },
            { ".docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document" },
            { ".xls", "application/vnd.ms-excel" },
            { ".xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" },
            { ".exe", "application/octet-stream" },
            { ".gif", "image/gif" },
            { ".gtar", "application/x-gtar" },
            { ".gz", "application/x-gzip" },
            { ".h", "text/plain" },
            { ".htm", "text/html" },
            { ".html", "text/html" },
            { ".jar", "application/java-archive" },
            { ".java", "text/plain" },
            { ".jpeg", "image/jpeg" },
            { ".jpg", "image/jpeg" },
            { ".js", "application/x-javascript" },
            { ".log", "text/plain" },
            { ".m3u", "audio/x-mpegurl" },
            { ".m4a", "audio/mp4a-latm" },
            { ".m4b", "audio/mp4a-latm" },
            { ".m4p", "audio/mp4a-latm" },
            { ".m4u", "video/vnd.mpegurl" },
            { ".m4v", "video/x-m4v" },
            { ".mov", "video/quicktime" },
            { ".mp2", "audio/x-mpeg" },
            { ".mp3", "audio/x-mpeg" },
            { ".mp4", "video/mp4" },
            { ".mpc", "application/vnd.mpohun.certificate" },
            { ".mpe", "video/mpeg" },
            { ".mpeg", "video/mpeg" },
            { ".mpg", "video/mpeg" },
            { ".mpg4", "video/mp4" },
            { ".mpga", "audio/mpeg" },
            { ".msg", "application/vnd.ms-outlook" },
            { ".ogg", "audio/ogg" },
            { ".pdf", "application/pdf" },
            { ".png", "image/png" },
            { ".pps", "application/vnd.ms-powerpoint" },
            { ".ppt", "application/vnd.ms-powerpoint" },
            { ".pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation" },
            { ".prop", "text/plain" },
            { ".rc", "text/plain" },
            { ".rmvb", "audio/x-pn-realaudio" },
            { ".rtf", "application/rtf" },
            { ".sh", "text/plain" },
            { ".tar", "application/x-tar" },
            { ".tgz", "application/x-compressed" },
            { ".txt", "text/plain" },
            { ".wav", "audio/x-wav" },
            { ".wma", "audio/x-ms-wma" },
            { ".wmv", "audio/x-ms-wmv" },
            { ".wps", "application/vnd.ms-works" },
            { ".xml", "text/plain" },
            { ".z", "application/x-compress" },
            { ".zip", "application/x-zip-compressed" },
            { "", "*/*" }
    };
}
