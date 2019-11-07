package com.sjq.githubapp.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;

import com.sjq.githubapp.BuildConfig;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * 文件相关工具类
 * 使用{@link #getInstance()} 调用
 */
public class UtilsFile {

    private static UtilsFile instance = null;
    /**
     * 数据库备份位置
     */
    public String DB_FILE_BACKUP_PATH = getSDFilePath() + "/backup";
    /**
     * 异常日志位置
     */
    public String FILE_ERROR_PATH = getSDFilePath() + "/error_log";
    /**
     * 新版本升级安装包位置
     */
    public String NEW_VERSION = getSDFilePath() + "/new_version/githubapp.apk";
    private int LOGMAXKEEPTIME = 7;//删除7天前数据
    private String TAG = "info";

    /**
     * 私有的构造方法
     */
    private UtilsFile() {
    }

    /**
     * UtilsFile提供静态的获取实例
     *
     * @return UtilsFile的实例
     */
    public static UtilsFile getInstance() {
        if (instance == null) {
            instance = new UtilsFile();
        }
        return instance;
    }

    /**
     * 判断当前应用是否是debug状态
     */
    public static boolean isApkInDebug(Context context) {
        try {
            ApplicationInfo info = context.getApplicationInfo();
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {
            return false;
        }
    }

    //获取指定目录下一级文件
    public static List<File> getDirAllFile(File file) {
        List<File> fileList = new ArrayList<>();
        File[] fileArray = file.listFiles();
        if (fileArray == null)
            return fileList;
        for (File f : fileArray) {
            fileList.add(f);
        }
        fileSortByTime(fileList);
        return fileList;
    }

    //对文件进行时间排序,从前到后
    public static void fileSortByTime(List<File> fileList) {
        Collections.sort(fileList, new Comparator<File>() {
            public int compare(File p1, File p2) {
                if (p1.lastModified() < p2.lastModified()) {
                    return -1;
                }
                return 1;
            }
        });
    }

    public static String getDB_FILE_PATH() {
        if (BuildConfig.OFFICIAL) {
            //伊利正式版数据库位置
            return Environment.getDataDirectory() + "/data/com.sjq.githubapp/databases/github.db";
        } else {
            //测试版通用数据库位置
            return getSDFilePath() + "/data/github";
        }
    }

    /**
     * SD卡是否存在
     */
    public boolean isSDcardExists() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取sd卡剩余空间
     *
     * @return
     */
    public int freeSpaceOnSd() {
        double sdFreeKB;
        StatFs stat = new StatFs(Environment.getExternalStorageDirectory()
                .getPath());
        // 由于API18（Android4.3）以后getBlockSize过时并且改为了getBlockSizeLong
        // 因此这里需要根据版本号来使用那一套API
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            sdFreeKB = ((double) stat.getAvailableBlocksLong() * (double) stat
                    .getBlockSizeLong()) / 1024;// KB;
        } else {
            sdFreeKB = ((double) stat.getAvailableBlocks() * (double) stat
                    .getBlockSize()) / 1024;// KB;
        }
        return (int) sdFreeKB;
    }

    /**
     * 获取本地文件路径
     *
     * @return
     */
    public static String getSDFilePath() {
//        return Environment.getExternalStorageDirectory().toString() + "/Github";
        return Environment.getExternalStorageDirectory().getPath() + "/Github";
    }

    /**
     * 删除单个文件
     *
     * @param file 被删除文件
     * @return 文件删除成功返回true，否则返回false
     */
    public boolean deleteFile(File file) {
        if (file.isFile() && file.exists()) {
            return file.delete();
        }
        return false;
    }

    /**
     * 递归删除文件和文件夹
     *
     * @param file 要删除的根目录
     */
    public void recursionDeleteFile(File file) {
        if (file.isFile()) {
            file.delete();
            return;
        }
        if (file.isDirectory()) {
            File[] childFile = file.listFiles();
            if (childFile == null || childFile.length == 0) {
                file.delete();
                return;
            }
            for (File f : childFile) {
                recursionDeleteFile(f);
            }
            file.delete();
        }
    }

    //移除文件，获取文件时间与当前时间对比，时间定位7天，删除七天前的文件
    public void removeFileByTime(String dirPath) {
        //获取目录下所有文件
        List<File> allFile = getDirAllFile(new File(dirPath));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        //获取当前时间
        Date end = new Date(System.currentTimeMillis());
        try {
            end = dateFormat.parse(dateFormat.format(new Date(System.currentTimeMillis())));
        } catch (Exception e) {
            Log.d(TAG, "dataformat exeption e " + e.toString());
        }
        Log.d(TAG, "getNeedRemoveFile  dirPath = " + dirPath);
        for (File file : allFile) {//ComDef
            try {
                //文件时间减去当前时间
                Date start = dateFormat.parse(dateFormat.format(new Date(file.lastModified())));
                long diff = end.getTime() - start.getTime();//这样得到的差值是微秒级别
                long days = diff / (1000 * 60 * 60 * 24);
                if (LOGMAXKEEPTIME <= days) {
                    deleteFile(file);
                }

            } catch (Exception e) {
                Log.d(TAG, "dataformat exeption e " + e.toString());
            }
        }
    }

    /**
     * 从sd卡获取图片资源
     *
     * @return
     */
    public List<File> getImagePathFromSD(String filePath) {
        // 图片列表
        List<File> imagePathList = new ArrayList<>();
        // 得到该路径文件夹下所有的文件
        File fileAll = new File(filePath);
        File[] files = fileAll.listFiles();
        // 将所有的文件存入ArrayList中,并过滤所有图片格式的文件
        if (files != null && files.length > 0) {
            for (int i = 0; i < files.length; i++) {
                File file = files[i];
                if (checkIsImageFile(file.getPath())) {
                    imagePathList.add(file);
                }
            }
        }
        // 返回得到的图片列表
        return imagePathList;
    }

    /**
     * 检查扩展名，得到图片格式的文件
     *
     * @param fName 文件名
     * @return
     */
    @SuppressLint("DefaultLocale")
    private boolean checkIsImageFile(String fName) {
        boolean isImageFile;
        // 获取扩展名
        String FileEnd = fName.substring(fName.lastIndexOf(".") + 1,
                fName.length()).toLowerCase();
        if (FileEnd.equals("jpg") || FileEnd.equals("png") || FileEnd.equals("gif")
                || FileEnd.equals("jpeg") || FileEnd.equals("bmp")) {
            isImageFile = true;
        } else {
            isImageFile = false;
        }
        return isImageFile;
    }

    //判断文件是否存在
    public boolean fileIsExists(File file) {
        try {
            if (!file.exists()) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }



}
