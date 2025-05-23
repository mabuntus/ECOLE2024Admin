package mabuntu.ecole.ecole2024.tools;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;

import mabuntu.tombolia.pressing.R;

public class TImg {

    public static Bitmap scale(Bitmap bitmap){
        int width = 100;
        int height = 100;
        return Bitmap.createScaledBitmap(bitmap, width, height, true);
    }

    public static Bitmap eleve_img(Context context, String codeElv){
        try {
            String fname = String.format("%s/%s", FILE.images, codeElv);
            File file = new File(fname);
            return BitmapFactory.decodeFile(file.getPath());
        } catch (Exception e){
            return BitmapFactory.decodeResource(context.getResources(), R.mipmap.profile_white_foreground);
        }
    }

}
