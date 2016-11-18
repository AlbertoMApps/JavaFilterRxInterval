package development.alberto.com.javacodingtest.di.components;


import javax.inject.Singleton;

import dagger.Component;
import development.alberto.com.javacodingtest.api.Service;
import development.alberto.com.javacodingtest.di.modules.AppModule;
import development.alberto.com.javacodingtest.di.modules.NetModule;
import development.alberto.com.javacodingtest.view.MainActivity;


@Singleton
@Component(modules={NetModule.class, AppModule.class})
public interface NetComponent {
    Service.IContactsAPI  getPeopleAPI();
    NetComponent getmNetComponent();
    void inject(MainActivity main);
}
