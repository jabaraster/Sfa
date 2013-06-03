/**
 * 
 */
package jabara.sfa.web.ui.page;

import jabara.sfa.web.WebInitializer;
import jabara.sfa.web.ui.WicketApplication;

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
public class WicketRule implements TestRule {

    private final WicketTester tester;

    WicketRule() {
        this.tester = new WicketTester(createApplication());
    }

    /**
     * @see org.junit.rules.TestRule#apply(org.junit.runners.model.Statement, org.junit.runner.Description)
     */
    @Override
    public Statement apply(final Statement pBase, @SuppressWarnings("unused") final Description pDescription) {
        return pBase;
    }

    /**
     * @return {@link WicketTester}オブジェクト.
     */
    public WicketTester getTester() {
        return this.tester;
    }

    /**
     * @return ログインまで済ませたオブジェクト.
     */
    @SuppressWarnings("nls")
    public static WicketRule newWithLogin() {
        final WicketRule ret = new WicketRule();
        ret.tester.startPage(LoginPage.class);
        final FormTester formTester = ret.tester.newFormTester("form");
        formTester.setValue("user", "administrator");
        formTester.setValue("password", "password");
        formTester.submit("submitter");
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