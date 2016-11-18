package development.alberto.com.javacodingtest.di.modules;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import development.alberto.com.javacodingtest.api.Constant.Constant;
import development.alberto.com.javacodingtest.api.Service;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetModule {

    @Provides
    @Singleton
    Service.IContactsAPI providePeopleAPI() {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constant.BASE_URL)
                .build();

        return retrofit.create(Service.IContactsAPI.class);

    }
}
