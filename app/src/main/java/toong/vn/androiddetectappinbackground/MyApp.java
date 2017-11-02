package toong.vn.androiddetectappinbackground;

import android.app.Application;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.arch.lifecycle.ProcessLifecycleOwner;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PhanVanLinh on 02/11/2017.
 * phanvanlinh.94vn@gmail.com
 */

public class MyApp extends Application {
    List<ApplicationLifeCycleClient> mApplicationLifeCycleClients = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        ProcessLifecycleOwner.get().getLifecycle().addObserver(new AppLifecycleListener());
    }

    public class AppLifecycleListener implements LifecycleObserver {

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        public void onMoveToForeground() {
            Toast.makeText(getApplicationContext(), "onMoveToForeground", Toast.LENGTH_SHORT)
                    .show();
            notifyAppInForegroundToClient();
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        public void onMoveToBackground() {
            Toast.makeText(getApplicationContext(), "onMoveToBackground", Toast.LENGTH_SHORT)
                    .show();
            notifyAppInBackgroundToClient();
        }
    }

    public void addApplicationLifeCycleClients(ApplicationLifeCycleClient client) {
        mApplicationLifeCycleClients.add(client);
    }

    public synchronized void removeApplicationLifeCycleClient(ApplicationLifeCycleClient client) {
        mApplicationLifeCycleClients.remove(client);
    }

    private void notifyAppInBackgroundToClient() {
        for (ApplicationLifeCycleClient client : mApplicationLifeCycleClients) {
            client.onForeground();
        }
    }

    private void notifyAppInForegroundToClient() {
        for (ApplicationLifeCycleClient client : mApplicationLifeCycleClients) {
            client.onForeground();
        }
    }
}
