package development.alberto.com.javacodingtest.api;


import development.alberto.com.javacodingtest.api.Constant.Constant;
import development.alberto.com.javacodingtest.api.Models.People;
import retrofit2.http.GET;

/**
 * Created by alber
 */

public class Service {
    public interface IContactsAPI {
        @GET(Constant.GET_CONTACTS_URL)
        rx.Observable<People> getContacts();
    }
}
