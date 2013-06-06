/**
 * 
 */
package jabara.sfa.web.ui.page;

import jabara.general.Empty;
import jabara.sfa.WebStarter;
import jabara.sfa.service.IMemberService;

import javax.naming.NamingException;

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
    public final WicketRule tester = WicketRule.newInstance(LoginPage.class);

    /**
     * 
     */
    @SuppressWarnings("nls")
    @Test
    public void _パスワード欄が入力必須であること() {
        final LoginPage ids = new LoginPage();

        this.tester.newFormTester(ids.getForm().getId()) //
                .setValue(ids.getUserId().getId(), "xxx") //
                .setValue(ids.getPassword().getId(), null) //
                .submit(ids.getSubmitter().getId());
        this.tester.assertErrorMessages("パスワードは必須入力です。");

        this.tester.newFormTester(ids.getForm().getId()) //
                .setValue(ids.getUserId().getId(), "xxx") //
                .setValue(ids.getPassword().getId(), Empty.STRING) //
                .submit(ids.getSubmitter().getId());
        this.tester.assertErrorMessages("パスワードは必須入力です。");
    }

    /**
     * 
     */
    @SuppressWarnings("nls")
    @Test
    public void _ユーザID欄が入力必須であること() {
        final LoginPage ids = new LoginPage();

        this.tester.newFormTester(ids.getForm().getId()) //
                .setValue(ids.getUserId().getId(), null) //
                .setValue(ids.getPassword().getId(), "xxx") //
                .submit(ids.getSubmitter().getId());
        this.tester.assertErrorMessages("ユーザIDは必須入力です。");

        this.tester.newFormTester(ids.getForm().getId()) //
                .setValue(ids.getUserId().getId(), Empty.STRING) //
                .setValue(ids.getPassword().getId(), "xxx") //
                .submit(ids.getSubmitter().getId());
        this.tester.assertErrorMessages("ユーザIDは必須入力です。");
    }

    /**
     * 
     */
    @Test
    public void _管理者ユーザでログイン可能であること() {
        final LoginPage ids = new LoginPage();

        this.tester.newFormTester(ids.getForm().getId()) //
                .setValue(ids.getUserId().getId(), IMemberService.DEFAULT_ADMINISTRATOR_USER_ID) //
                .setValue(ids.getPassword().getId(), IMemberService.DEFAULT_ADMINISTRATOR_USER_PASSWORD) //
                .submit(ids.getSubmitter().getId());
        this.tester.assertRenderedPage(this.tester.getApplication().getHomePage());
    }

    /**
     * 
     */
    @Test
    public void _初期画面になり得ること() {
        this.tester.assertRenderedPage(LoginPage.class);
    }

    /**
     * @throws NamingException -
     */
    @BeforeClass
    public static void beforeClass() throws NamingException {
        WebStarter.initializeDataSource();
    }
}
