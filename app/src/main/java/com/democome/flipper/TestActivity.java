package com.democome.flipper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class TestActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, TestActivity.class);
        context.startActivity(starter);
    }

    public void getContext(TestActivity activity) {
        Log.e("xx", activity.getClass().getSimpleName());
    }

    private Handler handler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();

                handler = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        while (true) {
                            try {
                                Thread.sleep(5000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            if (msg.what == 0) {
                                getContext((TestActivity) msg.obj);
                                break;
                            }
                        }
                    }
                };

                Looper.loop();
            }
        }).start();


        while (handler == null) {

        }

        Message message = Message.obtain();
        message.obj = this;
        message.what = 0;
        handler.sendMessage(message);
    }
}
