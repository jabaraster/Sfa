package jabara.sfa.web.ui;

import jabara.general.ArgUtil;
import jabara.sfa.service.IAuthenticationService;
import jabara.sfa.web.ui.page.LoginPage;
import jabara.sfa.web.ui.page.LogoutPage;
import jabara.sfa.web.ui.page.ProtedtedPageBase;
import jabara.sfa.web.ui.page.RestrictedPageBase;
import jabara.sfa.web.ui.page.TopPage;
import jabara.wicket.LoginPageInstantiationAuthorizer;
import jabara.wicket.MarkupIdForceOutputer;

import org.apache.wicket.Page;
import org.apache.wicket.Session;
import org.apache.wicket.guice.GuiceComponentInjector;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.util.IProvider;

import com.google.inject.Injector;

/**
 *
 */
public class WicketApplication extends WebApplication {

    private static final String       ENC = "UTF-8";   //$NON-NLS-1$

    private final IProvider<Injector> injectorProvider;

    /**
     * @param pInjectorProvider Guiceの{@link Injector}を供給するオブジェクト. DI設定に使用します.
     */
    public WicketApplication(final IProvider<Injector> pInjectorProvider) {
        ArgUtil.checkNull(pInjectorProvider, "pInjectorProvider"); //$NON-NLS-1$
        this.injectorProvider = pInjectorProvider;
    }

    /**
     * @see org.apache.wicket.Application#getHomePage()
     */
    @Override
    public Class<? extends Page> getHomePage() {
        return TopPage.class;
    }

    /**
     * @see org.apache.wicket.protocol.http.WebApplication#newSession(org.apache.wicket.request.Request, org.apache.wicket.request.Response)
     */
    @Override
    public Session newSession(final Request pRequest, @SuppressWarnings("unused") final Response pResponse) {
        return new AppSession(pRequest, this.injectorProvider.get().getInstance(IAuthenticationService.class));
    }

    /**
     * @see org.apache.wicket.protocol.http.WebApplication#init()
     */
    @Override
    protected void init() {
        super.init();

        mountPages();
        initializeEncoding();
        initializeInjection();
        initializeSecurity();
        initializeOther();
    }

    private void initializeEncoding() {
        getMarkupSettings().setDefaultMarkupEncoding(ENC);
        getRequestCycleSettings().setResponseRequestEncoding(getMarkupSettings().getDefaultMarkupEncoding());
    }

    private void initializeInjection() {
        getComponentInstantiationListeners().add(new GuiceComponentInjector(this, this.injectorProvider.get()));
    }

    private void initializeOther() {
        getComponentInstantiationListeners().add(new MarkupIdForceOutputer());
    }

    private void initializeSecurity() {
        getSecuritySettings().setAuthorizationStrategy(new LoginPageInstantiationAuthorizer() {

            @Override
            protected Class<? extends Page> getFirstPageType() {
                return TopPage.class;
            }

            @Override
            protected Class<? extends Page> getLoginPageType() {
                return LoginPage.class;
            }

            @Override
            protected Class<? extends Page> getRestictedPageType() {
                if (AppSession.get().currentUserIsAdministrator()) {
                    return RestrictedPageBase.class;
                } else {
                    return ProtedtedPageBase.class;
                }
            }

            @Override
            protected boolean isAuthenticated() {
                final AppSession session = AppSession.get();
                return session.isAuthenticated();
            }
        });
    }

    private void mountPages() {
        this.mountPage("login", LoginPage.class); //$NON-NLS-1$
        this.mountPage("logout", LogoutPage.class); //$NON-NLS-1$
    }
}
