package development.alberto.com.javacodingtest.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.io.InputStream;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import development.alberto.com.javacodingtest.MyApp;
import development.alberto.com.javacodingtest.R;
import development.alberto.com.javacodingtest.adapters.TextAdapter;
import development.alberto.com.javacodingtest.api.Models.Person;
import development.alberto.com.javacodingtest.api.Service;
import development.alberto.com.javacodingtest.contract.ContractActions;
import development.alberto.com.javacodingtest.presenter.PresenterMainActivity;

public class MainActivity extends AppCompatActivity implements ContractActions.View {

    private PresenterMainActivity presenter;
    private Unbinder unbinder;
    @BindView(R.id.search_filter)
    EditText searchText;
    @BindView(R.id.list)
    RecyclerView mRecyclerView;
    @Inject Service.IContactsAPI iContactsAPI;
    //Uncomment one or the other adapter
    private TextAdapter mAdapter;
//    private PeopleAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        presenter = new PresenterMainActivity(this);
        this.iContactsAPI = ((MyApp) getApplicationContext()).getmNetComponent().getPeopleAPI();
        //Read from a file.txt and uncomment to filter data from there
        final InputStream inputStream = this.getResources().openRawResource(R.raw.file);
        presenter.sendInputStream(inputStream);
        presenter.executeFile();
        //Interval to check every 5 seconds the API data output
        presenter.executeTimer();
        initRecyclerView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        searchText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //Uncomment any of the filters to check from file.text or APIContacts
                presenter.getFilterText().filter(editable);
//                presenter.getFilterAPI().filter(editable);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.unsubscribe();
    }

    public Service.IContactsAPI getiContactsAPI(){
        return this.iContactsAPI;
    }

    /**
     * Uncomment one or the other adapter to see how it works with the text file
     * @return TextAdapter
     */
    public TextAdapter getmAdapter(){
        return this.mAdapter;
    }

    /**
     * Uncomment one or the other adapter to see how it works with the Contacts API
     * @return PeopleAdapter
     */
//        public PeopleAdapter getmAdapter() {
//            return this.mAdapter;
//    }

    /**
     * Recycler view for the text reader and the ContactsAPI
     * Uncomment code for one or another adapter rto work with one or the other
     */
    public void initRecyclerView() {

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        List<String> textList = presenter.getTextList();
        List<Person> textListPeople = presenter.getPeople();
        mAdapter = new TextAdapter(textList, R.layout.list_row, getBaseContext());
//        mAdapter = new PeopleAdapter(textListPeople, R.layout.list_row, getBaseContext());
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }
}
