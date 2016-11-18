package development.alberto.com.javacodingtest;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import development.alberto.com.javacodingtest.api.Models.People;
import development.alberto.com.javacodingtest.api.Models.Person;
import development.alberto.com.javacodingtest.utils.Reader;
import development.alberto.com.javacodingtest.view.MainActivity;
import rx.functions.Action1;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * Created by alber on 13/11/2016.
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule public final ActivityRule<MainActivity> activityRule = new ActivityRule<>(MainActivity.class);


    @Test
    public void shouldLaunchMainScreen() throws Exception {

        final List<String> textListAPI = new ArrayList<>();
        List<String> textListText = new ArrayList<>();

        activityRule.get().getiContactsAPI().getContacts().subscribe(new Action1<People>() {
            @Override
            public void call(People people) {
                for (Person person : people.getPeople()) {
                    textListAPI.add( person.getFirstName() );
                }
            }
        });

        final InputStream inputStream = activityRule.get().getResources().openRawResource(R.raw.file);
        textListText = Reader.read("file.txt", inputStream);

        onView(withId(R.id.search_filter)).check(matches(isDisplayed()));
        onView(withId(R.id.list)).check(matches(isEnabled()));

        assertTrue(textListAPI.get(0), true);
        assertNotNull(textListAPI.get(0));
        assertNotNull(textListAPI.get(1));
        assertNotNull(textListAPI.get(2));
        assertTrue(textListText.get(0), true);
        assertNotNull(textListText.get(0));
        assertNotNull(textListText.get(1));
        assertNotNull(textListText.get(2));
    }
}
