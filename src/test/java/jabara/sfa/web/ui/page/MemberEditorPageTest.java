/**
 * 
 */
package jabara.sfa.web.ui.page;

import jabara.sfa.WebStarter;
import jabara.sfa.web.WebInitializer;
import jabara.sfa.web.ui.WicketApplication;

import javax.naming.NamingException;

import org.apache.wicket.util.IProvider;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.inject.Injector;

/**
 * @author jabaraster
 */
public class MemberEditorPageTest {

    private WicketTester tester;

    /**
     * 
     */
    @Test
    public void _test() {
        this.tester.startPage(MemberEditorPage.class);
        this.tester.assertRenderedPage(MemberEditorPage.class);
    }

    /**
     * 
     */
    @Before
    public void setUp() {
        final WicketApplication application = createApplication();
        this.tester = new WicketTester(application);
    }

    /**
     * @throws NamingException
     */
    @BeforeClass
    public static void beforeClass() throws NamingException {
        WebStarter.initializeDataSource();
    }

    private static WicketApplication createApplication() {
        final Injector injector = new WebInitializer() {
            @Override
            public Injector getInjector() {
                return super.getInjector();
            }
        }.getInjector();
        final WicketApplication application = new WicketApplication(new IProvider<Injector>() {
            @Override
            public Injector get() {
                return injector;
            }
        });
        return application;
    }
}
