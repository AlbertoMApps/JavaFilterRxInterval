package development.alberto.com.javacodingtest.dataTest;

import org.junit.Test;

import development.alberto.com.javacodingtest.presenter.PresenterMainActivity;
import development.alberto.com.javacodingtest.view.MainActivity;

import static junit.framework.Assert.assertNotNull;

/**
 * Created by alber on 13/11/2016.
 */

public class MainTest {

    MainActivity main = new MainActivity();
    PresenterMainActivity presenter = new PresenterMainActivity(main);

    @Test
    public void testRetrievingInfo()
    {
        assertNotNull(presenter.getPeople());
        assertNotNull(presenter.getTextList());
        assertNotNull(presenter.getFilterAPI());
        assertNotNull(presenter.getFilterText());
    }
}
