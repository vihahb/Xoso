package com.xtelsolution.xoso.sdk.common;

import android.content.Context;

import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;

public class SocketSingleton {
    private static SocketSingleton instance;
    private static final String SERVER_ADDRESS = "http://124.158.4.190:3000/";
    private Socket mSocket;
    private Context context;

    public SocketSingleton(Context context, String area) {
        this.context = context;
        this.mSocket = getServerSocket(area);
    }

    public static SocketSingleton get(Context context, String area){
        if(instance == null){
            instance = getSync(context, area);
        }
        instance.context = context;
        return instance;
    }

    private static synchronized SocketSingleton getSync(Context context, String area) {
        if(instance == null){
            instance = new SocketSingleton(context, area);
        }
        return instance;
    }

    public Socket getSocket(){
        return this.mSocket;
    }

    public Socket getServerSocket(String area) {
        try {
            IO.Options opts = new IO.Options();
            opts.forceNew = true;
            opts.reconnection = true;
            mSocket = IO.socket(SERVER_ADDRESS + area, opts);
            return mSocket;
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}