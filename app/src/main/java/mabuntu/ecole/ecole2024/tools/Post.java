package mabuntu.ecole.ecole2024.tools;

import java.io.*;
import java.net.*;
import android.os.*;

public class Post extends AsyncTask<String, Void, String>{

    protected String link;

    public Post(String url){
        link = url;
    }

    @Override protected String doInBackground(String[] tab){
        int len = tab.length;
        String data = "";

        try{
            for (int i=0; i<len; i=i+2)
                data = data + URLEncoder.encode(tab[i], "UTF-8") + "=" + URLEncoder.encode(tab[i+1], "UTF-8") + "&";
            URL url = new URL(link);
            URLConnection con = url.openConnection();
            con.setDoOutput(true);

            OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
            wr.write(data);
            wr.flush();

            BufferedReader reader = new BufferedReader( new InputStreamReader(con.getInputStream()));
            StringBuilder str = new StringBuilder();
            String line;
            while( (line = reader.readLine()) != null)
                str.append(line + "\n" );
            return str.toString();

        }catch (Exception e){
            return null;
        }
    }

    @Override protected void onPostExecute(String resulte) { }

}
