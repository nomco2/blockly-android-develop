package com.google.blockly.android.demo.Coding_app_activity.demo;

import android.app.Activity;
import android.os.Environment;

import java.io.File;

/**
 * Created by Jean on 2016/7/21.
 */
public class RequestPermission {

    public int WRITE_EXTERNAL_STORAGE = 1;
    public void requestWriteSettings(Activity _activity) {
        //ActivityCompat.requestPermissions(_activity,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_EXTERNAL_STORAGE);
    }

    /**
     * CreateSDCardDir
     */
    public void createSDCardDir(String path)
    {
        File sdcardDir = Environment.getExternalStorageDirectory();
        String pathcat=sdcardDir.getPath()+path;
        File path1 = new File(pathcat);
        if (!path1.exists())
        {
            path1.mkdirs();
        }
//        VideoPlay.photofile_path=sdcardDir.getPath()+ MainActivity.RAKVideo_Photo;//路径已经到Photo文件夹
//        VideoPlay.videofile_path=sdcardDir.getPath()+ MainActivity.RAKVideo_Video;//路径已经到Video文件夹
//        VideoPlay.voicefile_path=sdcardDir.getPath()+ MainActivity.RAKVideo_Voice;//路径已经到Voice文件夹
//        VideoPlay.voicefile_path=sdcardDir.getPath()+ MainActivity.RAKVideo_PlayBack;//路径已经到PlayBack文件夹
    }
}
