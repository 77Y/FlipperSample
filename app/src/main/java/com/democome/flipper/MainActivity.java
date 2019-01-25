package com.democome.flipper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharePreferenceUtils.getInstance(this).set("name", "hello");

        findViewById(R.id.shared).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String str = SharePreferenceUtils.getInstance(MainActivity.this).get("name", "");
                Log.e(TAG, str);
            }
        });

        findViewById(R.id.network).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Request request = new Request.Builder().get().url("http://t.weather.sojson.com/api/weather/city/101030100").build();
                Call call = FlipperApplication.sOkHttpClient.newCall(request);

                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        Log.d(TAG, response.body().string());
                    }
                });
            }
        });


        findViewById(R.id.activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TestActivity.start(MainActivity.this);
            }
        });
    }
}
