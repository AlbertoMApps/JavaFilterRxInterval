package development.alberto.com.javacodingtest.contract;

import android.widget.Filter;

import development.alberto.com.javacodingtest.adapters.TextAdapter;
import development.alberto.com.javacodingtest.api.Service;

/**
 * Created by alber on 12/11/2016.
 */

public interface ContractActions {
    interface View {
        Service.IContactsAPI getiContactsAPI();
        TextAdapter getmAdapter();
//        PeopleAdapter getmAdapter();
    }
    interface ActionsListener{
        Filter getFilterAPI();
        Filter getFilterText();
    }
}
