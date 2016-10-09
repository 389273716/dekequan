package com.tc.dekequan.util;

import android.content.Context;

import com.tomtop.ttutil.FileUtil;

import java.io.File;

import static com.tomtop.ttutil.FileUtil.isSDCardAvailable;

/**
 * author：   tc
 * date：     2016/10/9 & 15:05
 * version    1.0
 * description
 * modify by
 */

public class FilePathUtil {
    public static String getVoiceFilePath(Context context, String name) {
        return getFilePath(context, name, "voice");

    }

    public static String getImageFilePath(Context context, String name) {
        return getFilePath(context, name, "image");

    }

    public static String getFilePath(Context context, String name, String fileType) {
        if (!isSDCardAvailable()) {
            return null;
        }
        File file = new File(FileUtil.getAppCachePath(context) + "/" + fileType);
        boolean flag;
        if (!file.exists()) {
            flag = file.mkdir();
        } else {
            flag = true;
        }
        if (!flag) return FileUtil.getAppCachePath(context);
        String storagePath = FileUtil.getAppCachePath(context) + "/" + fileType + "/" + name;
        return storagePath;
    }
}
