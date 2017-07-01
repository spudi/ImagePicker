package com.nguyenhoanglam.imagepicker.helper;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hoanglam on 7/31/16.
 */
public class ImageUtils {

    private static final String TAG = "ImageUtils";

    public static File createImageFileInMedia(String directory) {
        // External sdcard location
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), directory);
        return createImageFileInternal(mediaStorageDir);
    }

    public static File createImageFileInFolder(String folderPath) {
        File folder = new File(folderPath);
        return createImageFileInternal(folder);
    }

    private static File createImageFileInternal(File folder) {

        // Create the storage directory if it does not exist
        if (!folder.exists()) {
            if (!folder.mkdirs()) {
                Log.d(TAG, "Oops! Failed create " + folder.getAbsolutePath() + " directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "IMG_" + timeStamp;

        File imageFile = null;
        try {
            imageFile = File.createTempFile(imageFileName, ".jpg", folder);
        } catch (IOException e) {
            Log.d(TAG, "Oops! Failed create " + imageFileName + " file");
        }
        return imageFile;
    }
}
