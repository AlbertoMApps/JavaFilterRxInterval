package development.alberto.com.javacodingtest;

import android.app.Application;

import development.alberto.com.javacodingtest.di.components.DaggerNetComponent;
import development.alberto.com.javacodingtest.di.components.NetComponent;
import development.alberto.com.javacodingtest.di.modules.AppModule;
import development.alberto.com.javacodingtest.di.modules.NetModule;

/**
 * Created by alber on 12/11/2016.
 */

public class MyApp extends Application {
    private NetComponent mNetComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mNetComponent = DaggerNetComponent
                .builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule())
                .build();
    }

    public NetComponent getmNetComponent(){
        return mNetComponent;
    }


}

