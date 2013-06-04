/**
 * 
 */
package jabara.sfa.web.ui.page;

import jabara.sfa.WebStarter;
import jabara.sfa.web.ui.WicketRule;

import javax.naming.NamingException;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author jabaraster
 */
public class LoginPageTest {

    /**
     * 
     */
    @Rule
    public final WicketRule tool = WicketRule.newInstance();

    /**
     * 
     */
    @Test
    public void _test() {
        final WicketTester tester = this.tool.getTester();
        tester.startPage(LoginPage.class);
        tester.assertRenderedPage(LoginPage.class);
    }

    /**
     * @throws NamingException -
     */
    @BeforeClass
    public static void beforeClass() throws NamingException {
        WebStarter.initializeDataSource();
    }

}
