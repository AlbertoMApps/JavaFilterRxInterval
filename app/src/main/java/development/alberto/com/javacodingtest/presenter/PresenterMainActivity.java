package development.alberto.com.javacodingtest.presenter;

import android.util.Log;
import android.widget.Filter;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import development.alberto.com.javacodingtest.api.Models.People;
import development.alberto.com.javacodingtest.api.Models.Person;
import development.alberto.com.javacodingtest.api.interactor.DefaultSubscriber;
import development.alberto.com.javacodingtest.contract.ContractActions;
import development.alberto.com.javacodingtest.utils.Reader;
import development.alberto.com.javacodingtest.view.MainActivity;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by alber on 12/11/2016.
 */

public class PresenterMainActivity implements ContractActions.ActionsListener {

    private ContractActions.View mView;
    private List<Person> peopleList = new ArrayList<>();
    private List<String> textList = new ArrayList<>();
    private Observable<People> peopleObservable;
    private Observable<String> listObservable;
    private Subscription subscription;
    private InputStream inputStream ;

    public PresenterMainActivity(MainActivity mainActivity){
        this.mView = mainActivity;
    }


    public void executeAPI() {
        peopleObservable = mView.getiContactsAPI().getContacts();
        subscription = peopleObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new GetObserver().getPeopleObserver());
    }

    public void executeTimer() {
        Observable<Long> interval = Observable.interval(5, TimeUnit.SECONDS);
        subscription = interval
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new GetTimerObserver().getListObserver());
    }

    public void executeFile () {
        textList = Reader.read("file.txt", inputStream);
    }

    public void unsubscribe() {
        subscription.unsubscribe();
    }

    private class GetObserver extends DefaultSubscriber<People> {
        Observer<People> myObserver = new Observer<People>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.i("API error: ", e.getMessage());
            }

            @Override
            public void onNext(People people) {
                peopleList = people.getPeople();
                for (Person person : peopleList) {
                    Log.i("Person API: " , person.getFirstName());
                }
            }
        };
        public Observer<People> getPeopleObserver(){
            return myObserver;
        }

    }

    private class GetTimerObserver extends DefaultSubscriber<Long> {
        Observer<Long> myObserver = new Observer<Long>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.i("List error: ", e.getMessage());
            }

            @Override
            public void onNext(Long timer) {
                Log.i("Checking API  ",   " ------------ " );
                    executeAPI();
            }
        };
        public Observer<Long> getListObserver(){
            return myObserver;
        }

    }

    @Override
    public Filter getFilterAPI() {

        Filter filter = new Filter() {

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                ArrayList<Person> personFiltered = (ArrayList<Person>) results.values;
                if (personFiltered != null && personFiltered.size() > 0) {
                    for (Person person : personFiltered) {
                        Log.i("TAG  ",   " ------------ " );
                        Log.i("Input filtered : ", person.getFirstName() +" : " + person.getLastName() );
                    }
                    /**
                     * Uncomment whenever used for the API
                     */
//                    mView.getmAdapter().update(personFiltered);
//                    mView.getmAdapter().notifyDataSetChanged();
                }
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                ArrayList<Person> filterPeople = new ArrayList<Person>();

                constraint = constraint.toString().toLowerCase();
                for (int i = 0; i < peopleList.size(); i++) {
                    Person person = peopleList.get(i);
                    if (person.getFirstName().toLowerCase().contains(constraint.toString()) ||
                            person.getLastName().toLowerCase().contains(constraint.toString())) {
                        filterPeople.add(person);
                    }
                }

                results.count = filterPeople.size();
                results.values = filterPeople;

                return results;
            }
        };

        return filter;
    }

    @Override
    public Filter getFilterText() {
        Filter filter = new Filter() {

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                ArrayList<String> wordFiltered = (ArrayList<String>) results.values;
                if (wordFiltered != null && wordFiltered.size() > 0) {
                    for (String word : wordFiltered) {
                        Log.i("TAG  ",   " ------------ " );
                        Log.i("Input text filtered : ", word);
                    }
                    /**
                     * Uncomment whenever used for the text file
                     */
                    mView.getmAdapter().update(wordFiltered);
                    mView.getmAdapter().notifyDataSetChanged();
                }
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                ArrayList<String> filterText = new ArrayList<String>();

                constraint = constraint.toString().toLowerCase();
                for (int i = 0; i < textList.size(); i++) {
                    String word = textList.get(i);
                    if ( word.toLowerCase().contains( constraint.toString() ) ) {
                        filterText.add(word);
                    }
                }

                results.count = filterText.size();
                results.values = filterText;

                return results;
            }
        };

        return filter;
    }

    public List<String> getTextList() {
        return this.textList;
    }

    public List<Person> getPeople() {
        return this.peopleList;
    }

    public void sendInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }
}
