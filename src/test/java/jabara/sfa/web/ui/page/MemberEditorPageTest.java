/**
 * 
 */
package jabara.sfa.web.ui.page;

import jabara.sfa.WebStarter;

import javax.naming.NamingException;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author jabaraster
 */
public class MemberEditorPageTest {

    /**
     * 
     */
    @Rule
    public final WicketRule tool = WicketRule.newWithLogin();

    /**
     * 
     */
    @Test
    public void _test() {
        final WicketTester tester = this.tool.getTester();
        tester.startPage(MemberEditorPage.class);
        tester.assertRenderedPage(MemberEditorPage.class);
    }

    /**
     * @throws NamingException
     */
    @BeforeClass
    public static void beforeClass() throws NamingException {
        WebStarter.initializeDataSource();
    }
}
