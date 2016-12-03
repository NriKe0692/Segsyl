package com.enrique.segsyl;

        import android.app.Application;

        import com.enrique.segsyl.Network.RequestManager;
        import com.enrique.segsyl.Network.SegsylServices;

/**
 * Created by USUARIO on 26/11/2016.
 */

public class SegsylApp extends Application {
    private SegsylServices services;
    private static SegsylApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        services = new RequestManager().getWebServices();
    }

    public static SegsylApp getInstance(){
        return instance;
    }

    public SegsylServices getServices() {
        return services;
    }
}
