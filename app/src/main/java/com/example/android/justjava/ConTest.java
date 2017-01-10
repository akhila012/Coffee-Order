package com.example.android.justjava;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by nic on 28-12-2016.
 */
public class ConTest extends AsyncTask<String,Void,String> {
    Context ctx;
    AlertDialog alertDialog;
    //AlertDialog alertDialog;
    ConTest(Context ctx){
        this.ctx=ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        String e_url="http://192.168.0.5/mail.php";
        try {
            String email_id=params[0];
            String msg=params[1];
            URL url=new URL(e_url);
            HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream=httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String post_data = URLEncoder.encode("email_id", "UTF-8")+"="+URLEncoder.encode(email_id,"UTF-8")+"&"+URLEncoder.encode("msg", "UTF-8")+"="+URLEncoder.encode(msg,"UTF-8");
            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
            String result="";
            String line="";
            while((line = bufferedReader.readLine())!= null)
                result += line;

            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return result;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return "server not working";
    }

  /*  @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }
    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }*/
    @Override
    protected void onPostExecute(String result) {
        alertDialog=new AlertDialog.Builder(ctx).create();
        alertDialog.setTitle("progress");
        alertDialog.setMessage(result);
        alertDialog.show();

    }

}
