package com.example.aidl_client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    SsoAuth mSsoAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void login(View view) {
        if (mSsoAuth == null){
            bindService();
        }else {
            doSsoAuth();
        }
    }


    private  void bindService(){
        Intent intent=new Intent();
        intent.setComponent(new ComponentName("com.example.aidl_service","com.example.aidl_service.SinaSsoAuthService"));
        bindService(intent,mConnection, Context.BIND_AUTO_CREATE);
    }

    ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mSsoAuth = SsoAuth.Stub.asInterface(service);
            doSsoAuth();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mSsoAuth = null;
        }
    };

    private void doSsoAuth() {
        try {
            mSsoAuth.ssoAuth("test","caonima");
        }catch (RemoteException e){
            e.printStackTrace();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mConnection);
    }
}
