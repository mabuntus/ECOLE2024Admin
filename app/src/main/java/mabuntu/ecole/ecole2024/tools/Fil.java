package mabuntu.ecole.ecole2024.tools;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.itextpdf.html2pdf.HtmlConverter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import mabuntu.ecole.ecole2024.tvi.Tvi;

public class Fil {

    Context context;

    public Fil(Context context){
        this.context = context;
    }

    public void setContext(Context context){ this.context = context; }

    public boolean exists(String fname){
        try {
            FileInputStream fis = context.openFileInput(fname);
            fis.close();
            return true;
        }catch (Exception e){ return false; }
    }
    public static boolean exists(Context context, String fname){
        try {
            FileInputStream fis = context.openFileInput(fname);
            fis.close();
            return true;
        }catch (Exception e){ return false; }
    }

    public static boolean put(Context context, String fname, String data){
        try {
            FileOutputStream fos = context.openFileOutput(fname, Context.MODE_PRIVATE);
            fos.write(data.getBytes());
            fos.close();
            return true;
        } catch (Exception e){ return false; }
    }

    public boolean put(String fname, String data){
        try {
            FileOutputStream fos = context.openFileOutput(fname, Context.MODE_PRIVATE);
            fos.write(data.getBytes());
            fos.close();
            return true;
        } catch (Exception e){ return false; }
    }

    public static boolean append(Context context, String fname, String data){
        try {
            FileOutputStream fos = context.openFileOutput(fname, Context.MODE_APPEND);
            fos.write(data.getBytes());
            fos.close();
            return true;
        } catch (Exception e){ return false; }
    }

    public boolean append(String fname, String data){
        try {
            FileOutputStream fos = context.openFileOutput(fname, Context.MODE_APPEND);
            fos.write(data.getBytes());
            fos.close();
            return true;
        } catch (Exception e){ return false; }
    }

    public String get(String fname){
        try {
            FileInputStream fis = context.openFileInput(fname);
            byte[] bit = new byte[fis.available()];
            fis.read(bit);
            return new String(bit);
        } catch (Exception e){ return null; }
    }

    public static String get(Context context, String fname){
        try {
            FileInputStream fis = context.openFileInput(fname);
            byte[] bit = new byte[fis.available()];
            fis.read(bit);
            fis.close();
            return new String(bit);
        } catch (Exception e){ return null; }
    }

    public static String get_bit_b64(String fname, Context context){
        try {
            FileInputStream fis = context.openFileInput(fname);
            byte[] bit = new byte[fis.available()];
            fis.read(bit);
            fis.close();
            return Base64.encodeToString(bit, Base64.DEFAULT);
        } catch (Exception e){
            Log.println(Log.DEBUG, "get-from-b64", e.getMessage());
            return " ";
        }
    }
    public static boolean put_from_b64_str(Context context, String fname, String data_b64){
        try {
            FileOutputStream fos = context.openFileOutput(fname, Context.MODE_PRIVATE);
            byte[] bit = Base64.decode(data_b64, Base64.DEFAULT);
            fos.write(bit);
            fos.close();
            return true;
        } catch (Exception e){
            Log.println(Log.DEBUG, "put-from-b64", e.getMessage());
            return false;
        }
    }

    public String getE(String fname){
        try {
            File file = new File(fname);
            if (!file.exists()) return null;
            FileInputStream fis = new FileInputStream(file);
            byte[] byt = new byte[fis.available()];
            fis.read(byt);
            fis.close();
            return new String(byt);
        }catch (Exception e){ return null; }
    }

    public boolean putE(String fname, String data){
        try{
            FileOutputStream fos = new FileOutputStream(fname);
            fos.write(data.getBytes());
            fos.close();
            return true;
        }catch (Exception e){ return false; }
    }

    public static String bitmap2Str(Bitmap bitmap){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] bite = baos.toByteArray();
        return Base64.encodeToString(bite, Base64.DEFAULT);
    }
    public static String bitmap2base64(Bitmap bitmap){
        return bitmap2Str(bitmap);
    }
    public static Bitmap file2bitmap(Context context, String fname) {
        try {
            FileInputStream fiStream    = context.openFileInput(fname);
            Bitmap bitmap      = BitmapFactory.decodeStream(fiStream);
            fiStream.close();
            return bitmap;
        } catch (Exception e) {
            return null;
        }
    }

    public static Bitmap res2bitmap(Context context, int resId){
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resId);
        return bitmap;
    }
    public static boolean bitmap2file(Context context, Bitmap bitmap, String fname) {
        try {
            FileOutputStream foStream = context.openFileOutput(fname, Context.MODE_PRIVATE);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, foStream);
            foStream.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean bitmap2file(Bitmap bitmap, String fname) {
        try {
            FileOutputStream foStream = context.openFileOutput(fname, Context.MODE_PRIVATE);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, foStream);
            foStream.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public static String file2base64(Context context, String fname){
        try {
            FileInputStream fis = context.openFileInput(fname);
            byte[] bit = new byte[fis.available()];
            fis.read(bit);
            return Base64.encodeToString(bit, Base64.DEFAULT);
        }
        catch (Exception e){
            Log.println(Log.DEBUG, "file_2_base64", e.getMessage());
            return null;
        }
    }
    public Bitmap file2bitmap_elv(String fname, String classe) {
        try {
            FileInputStream fiStream    = context.openFileInput(fname);
            Log.println(Log.DEBUG, "synchronise-fil", fname);

            Bitmap bitmap      = BitmapFactory.decodeStream(fiStream);
            fiStream.close();
            return bitmap;
        } catch (Exception e) {
            classe = classe.replace(" ", "");
            fname = fname + "-" + classe;

            Log.println(Log.DEBUG, "synchronise-fil-catch", fname);

            return file2bitmap(fname);
        }
    }

    public Bitmap file2bitmap(String fname) {
        try {
            FileInputStream fiStream    = context.openFileInput(fname);
            Bitmap bitmap      = BitmapFactory.decodeStream(fiStream);
            fiStream.close();
            return bitmap;
        } catch (Exception e) {
            return null;
        }
    }

    public static String rawFile2Str(Context context, int resId){
        try {
            int size = 0;
            //InputStream is = getResources().openRawResource(R.raw.html_bulletin_head);
            InputStream is = context.getResources().openRawResource(resId);
            ByteArrayOutputStream outputStream=new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            while((size = is.read(buffer,0,1024)) >= 0){
                outputStream.write(buffer,0,size);
            }
            is.close();
            buffer=outputStream.toByteArray();
            return new String(buffer);

        } catch (Exception e){
            Log.println(Log.DEBUG, "file-connexion", e.getMessage());
            return null;
        }
    }


    public static void save2phone(String content, String dirname, String fname){
        try {
            File root = android.os.Environment.getExternalStorageDirectory();
            File dir = new File(String.format("%s/%s", root.getAbsolutePath(), dirname));
            dir.mkdirs();

            dir = new File(String.format("%s/%s/%s", root.getAbsolutePath(), dirname, fname));
            OutputStream os = new FileOutputStream(dir);
            os.write(content.getBytes());
            os.close();
        } catch (Exception e){
            Log.println(Log.DEBUG, "html_to_pdf", e.getMessage());
        }
    }
}

