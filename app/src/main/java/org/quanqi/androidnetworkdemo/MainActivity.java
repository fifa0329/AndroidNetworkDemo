package org.quanqi.androidnetworkdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity {

    private TextView responseContentTextView;

    private String url = "http://wwww.baidu.com";
    private String url12306 = "https://kyfw.12306.cn/otn/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        responseContentTextView = (TextView) findViewById(R.id.textViewResponseContent);

//        StringRequest request = new StringRequest(
//                Request.Method.GET,
//                url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        responseContentTextView.setText(response);
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        responseContentTextView.setText(error.toString());
//                    }
//                });
//        RequestManager.getInstance(this).addRequest(request, this);
        Thread run = new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client1 = new OkHttpClient();
                okhttp3.Request request1 = new okhttp3.Request.Builder()
                        .url("http://publicobject.com/helloworld.txt")
                        .build();

                okhttp3.Response response;
                try {
                    response = client1.newCall(request1).execute();
                    if (response.isSuccessful()) {
                        Headers responseHeaders = response.headers();
                        for (int i = 0; i < responseHeaders.size(); i++) {
                            Log.d("wlx", responseHeaders.name(i) + ": " + responseHeaders.value(i));
                        }
                        Log.d("wlx", response.body().string());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        run.start();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RequestManager.getInstance(this).cancelAll(this);
    }
}
