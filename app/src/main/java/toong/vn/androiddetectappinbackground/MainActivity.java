package toong.vn.androiddetectappinbackground;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity implements ApplicationLifeCycleClient {
    private String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.start_second_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });
        ((MyApp) getApplication()).addApplicationLifeCycleClients(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ((MyApp) getApplication()).removeApplicationLifeCycleClient(this);
    }

    @Override
    public void onBackground() {
        Log.i(TAG, "onBackground");
    }

    @Override
    public void onForeground() {
        Log.i(TAG, "onForeground");
    }
}
