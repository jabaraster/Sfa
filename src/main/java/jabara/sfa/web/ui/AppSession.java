package jabara.sfa.web.ui;

import jabara.general.ArgUtil;
import jabara.sfa.model.FailAuthentication;
import jabara.sfa.service.IAuthenticationService;
import jabara.sfa.service.IAuthenticationService.AuthenticatedAs;

import java.util.concurrent.atomic.AtomicReference;

import javax.servlet.http.HttpServletRequest;

import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.cycle.RequestCycle;

/**
 * 
 */
public class AppSession extends WebSession {
    private static final long                      serialVersionUID = -5522467353190211133L;

    private final AtomicReference<AuthenticatedAs> authenticated    = new AtomicReference<>();

    private final IAuthenticationService           authenticationService;

    /**
     * @param pRequest
     * @param pAuthenticationService
     */
    public AppSession(final Request pRequest, final IAuthenticationService pAuthenticationService) {
        super(pRequest);
        ArgUtil.checkNull(pAuthenticationService, "pAuthenticationService"); //$NON-NLS-1$
        this.authenticationService = pAuthenticationService;
    }

    /**
     * @return 管理者ユーザとしてログイン済みならtrue.
     */
    public boolean currentUserIsAdministrator() {
        if (!isAuthenticatedCore()) {
            return false;
        }
        switch (this.authenticated.get()) {
        case NORMAL_USER:
            return false;
        case ADMINISTRATOR:
            return true;
        default:
            throw new IllegalStateException();
        }
    }

    /**
     * @see org.apache.wicket.protocol.http.WebSession#invalidate()
     */
    @Override
    public void invalidate() {
        super.invalidate();
        invalidateHttpSession();
    }

    /**
     * @see org.apache.wicket.Session#invalidateNow()
     */
    @Override
    public void invalidateNow() {
        super.invalidateNow();
        invalidateHttpSession();
    }

    /**
     * @return 認証済みあればtrue.
     */
    public boolean isAuthenticated() {
        return isAuthenticatedCore();
    }

    /**
     * @param pUser
     * @param pPassword
     * @throws FailAuthentication 認証NGの場合にスローして下さい.
     */
    public void login(final String pUser, final String pPassword) throws FailAuthentication {
        this.authenticated.set(this.authenticationService.login(pUser, pPassword));
    }

    private boolean isAuthenticatedCore() {
        return this.authenticated.get() != null;
    }

    /**
     * @return 現在のコンテキスト中の{@link AppSession}.
     */
    public static AppSession get() {
        return (AppSession) Session.get();
    }

    private static void invalidateHttpSession() {
        // Memcahcedによるセッション管理を行なっていると、Session.get()ではセッションが破棄されない.
        ((HttpServletRequest) RequestCycle.get().getRequest().getContainerRequest()).getSession().invalidate();
    }
}
