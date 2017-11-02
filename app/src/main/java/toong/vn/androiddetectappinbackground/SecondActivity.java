package toong.vn.androiddetectappinbackground;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class SecondActivity extends AppCompatActivity implements ApplicationLifeCycleClient {
    private String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
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
