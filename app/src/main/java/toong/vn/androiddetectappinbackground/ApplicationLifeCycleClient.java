package toong.vn.androiddetectappinbackground;

interface ApplicationLifeCycleClient {

        void onBackground();

        void onForeground();
    }