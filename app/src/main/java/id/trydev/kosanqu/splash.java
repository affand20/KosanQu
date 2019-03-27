package id.trydev.kosanqu;

import android.content.Intent;
import android.os.Build;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (Build.VERSION.SDK_INT > 9){
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        final int welcomeScreenDisplay = 3000; //3000 = 3 detik
        Thread welcomeThread = new Thread(){
            int wait =0;

            @Override
            public void run() {
                try{
                    super.run();
                    while (wait < welcomeScreenDisplay){
                        sleep(100);
                        wait += 100;
                    }
                } catch (Exception e){
                    System.out.println("EXc=" + e);
                } finally {
                    Intent intent = new Intent(splash.this, FilterActivity.class);
                    finish();
                    startActivity(intent);

                }
            }
        };
        welcomeThread.start();

    }
}
