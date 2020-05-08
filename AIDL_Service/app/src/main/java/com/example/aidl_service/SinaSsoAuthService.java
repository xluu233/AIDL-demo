package com.example.aidl_service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.aidl_client.SsoAuth;


public class SinaSsoAuthService extends Service {

    SinaSsoImpl mBinder = new SinaSsoImpl();

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return mBinder;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(getApplicationContext(),"start",Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy() {
        Toast.makeText(getApplicationContext(),"stop",Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }


    class SinaSsoImpl extends SsoAuth.Stub{

        @Override
        public void ssoAuth(String username,String pwd) throws RemoteException {
            Log.e("xixixixixi","SSo登录啦："+username+"mima:"+pwd);
        }

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }
    }
}
