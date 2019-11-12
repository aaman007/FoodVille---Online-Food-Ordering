package app.aaman007.com.tablayout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ProgressBar;

public class WelcomeScreen extends AppCompatActivity {

    private ProgressBar progressBar;
    private int progress;
    public static int lastActivity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //ActionBar actionBar = getSupportActionBar();
        //if(actionBar != null)
            //actionBar.hide();
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        progressBar = findViewById(R.id.progressBarId);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                progresser();
        }});
        thread.start();
    }

    private void progresser()
    {
        for(progress=1;progress<=100;progress++)
        {
            try {
                Thread.sleep(30);
                progressBar.setProgress(progress);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        if(lastActivity == 1)
        {
            Intent intent = new Intent(WelcomeScreen.this,MainActivity.class);
            startActivity(intent);
        }
        else if(lastActivity == 2){
            Intent intent = new Intent(WelcomeScreen.this, AdminLayout.class);
            startActivity(intent);
        }
        else
        {
            Intent intent = new Intent(WelcomeScreen.this,logIn.class);
            startActivity(intent);
        }
        finish();
    }
}
