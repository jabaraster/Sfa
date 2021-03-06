/**
 * 
 */
package jabara.sfa.web.ui.page;

import jabara.sfa.service.IMemberService;
import jabara.sfa.web.WebInitializer;
import jabara.sfa.web.ui.WicketApplication;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.util.IProvider;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import com.google.inject.Injector;

/**
 * @author jabaraster
 */
public class WicketRule extends WicketTester implements TestRule {

    WicketRule() {
        super(createApplication());
    }

    /**
     * @see org.junit.rules.TestRule#apply(org.junit.runners.model.Statement, org.junit.runner.Description)
     */
    @Override
    public Statement apply(final Statement pBase, @SuppressWarnings("unused") final Description pDescription) {
        return pBase;
    }

    /**
     * @return -
     */
    @Override
    public WicketApplication getApplication() {
        return (WicketApplication) super.getApplication();
    }

    /**
     * @param pStartPageType
     * @return ログインまで済ませ、pStartPageTypeを表示した状態のテスタ.
     */
    @SuppressWarnings("nls")
    public static WicketRule loggedin(final Class<? extends WebPage> pStartPageType) {
        final WicketRule ret = new WicketRule();
        ret.startPage(LoginPage.class);

        final LoginPage ids = new LoginPage();

        final FormTester formTester = ret.newFormTester("form");
        formTester.setValue(ids.getUserId(), IMemberService.DEFAULT_ADMINISTRATOR_USER_ID);
        formTester.setValue(ids.getPassword(), IMemberService.DEFAULT_ADMINISTRATOR_USER_PASSWORD);
        formTester.submit(ids.getSubmitter().getId());

        ret.startPage(pStartPageType);
        return ret;
    }

    /**
     * @param pStartPageType
     * @return -
     */
    public static WicketRule newInstance(final Class<? extends WebPage> pStartPageType) {
        final WicketRule ret = new WicketRule();
        ret.startPage(pStartPageType);
        return ret;
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