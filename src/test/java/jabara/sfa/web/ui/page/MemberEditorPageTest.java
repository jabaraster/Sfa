/**
 * 
 */
package jabara.sfa.web.ui.page;

import jabara.sfa.WebStarter;

import javax.naming.NamingException;

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
    public final WicketRule tester = WicketRule.loggedin(MemberEditorPage.class);

    /**
     * 
     */
    @Test
    public void _test() {
        this.tester.assertRenderedPage(MemberEditorPage.class);
    }

    /**
     * @throws NamingException -
     */
    @BeforeClass
    public static void beforeClass() throws NamingException {
        WebStarter.initializeDataSource();
    }
}
