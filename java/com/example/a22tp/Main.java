package com.example.a22tp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class Main extends Activity {

    private String s = null;
    private String ss = null;
    private String sss = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.ll);

        fetch("https://belatar.name/rest/profile.php?login=LOGIN&passwd=PASSWRD&id=abc",false);
    }




    public void fetch(String url,Boolean use){
        final TextView tv = ((TextView)findViewById(R.id.message));
        final ImageView iv = ((ImageView)findViewById(R.id.iv));
        new Thread(){
            @Override
            public void run() {
                super.run();
                //s = j.getJSONObject("debug").getString("id");
                JSONObject j = null;
                if(use) {
                    HttpData hd = new HttpData(url,iv);
                    j = hd.getData();
                }
                try {
                    if(!use)
                        j = new JSONObject(getString(R.string.url));
                    ss = j.getJSONObject("debug").getString("id");
                    sss = j.getJSONObject("debug").getString("login");
                    s = j.getString("error");

                } catch (JSONException e) {throw new RuntimeException(e);}

                tv.post(new Runnable() {
                    @Override
                    public void run() {
                        tv.setText(ss+"\n"+sss+"\n"+s);
                    }
                });
            }
        }.start();
    }

    // https://belatar.name/rest/profile.php?login=test&passwd=test&id=xxxx

}







