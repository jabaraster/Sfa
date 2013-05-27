/**
 * 
 */
package jabara.sfa.service.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import jabara.sfa.WebStarter;
import jabara.sfa.entity.ELoginPassword;
import jabara.sfa.entity.EMember;
import jabara.sfa.model.FailAuthentication;
import jabara.sfa.service.IAuthenticationService.AuthenticatedAs;
import jabara.sfa.service.impl.AuthenticationServiceImpl;

import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

/**
 * @author jabaraster
 */
@RunWith(Enclosed.class)
public class AuthenticationServiceImplTest {

    /**
     * @throws NamingException
     */
    @BeforeClass
    public static void beforeClass() throws NamingException {
        WebStarter.initializeDataSource();
    }

    /**
     * @author jabaraster
     */
    public static class UserCountIsTwo {
        /**
         * 
         */
        @Rule
        public final JpaDaoRule<AuthenticationServiceImpl> tool = new JpaDaoRule<AuthenticationServiceImpl>() {
                                                                    @Override
                                                                    protected AuthenticationServiceImpl createService(
                                                                            final EntityManagerFactory pEntityManagerFactory) {
                                                                        return new AuthenticationServiceImpl(pEntityManagerFactory);
                                                                    }
                                                                };

        /**
         * @throws FailAuthentication
         */
        @SuppressWarnings("nls")
        @Test
        public void 管理者としてログイン() throws FailAuthentication {
            insertAdministrator();
            insertNormalUser();

            final AuthenticatedAs actual = this.tool.getSut().login("admin", "password");
            assertThat(actual, is(AuthenticatedAs.ADMINISTRATOR));
        }

        /**
         * @throws FailAuthentication
         */
        @SuppressWarnings("nls")
        @Test
        public void 通常ユーザとしてログイン() throws FailAuthentication {
            insertAdministrator();
            insertNormalUser();

            final AuthenticatedAs actual = this.tool.getSut().login("normal", "password");
            assertThat(actual, is(AuthenticatedAs.NORMAL_USER));
        }

        @SuppressWarnings("nls")
        private void insertAdministrator() {
            final EntityManager em = this.tool.getEntityManager();

            final EMember member = new EMember();
            member.setAdministrator(true);
            member.setUserId("admin");
            em.persist(member);

            final ELoginPassword password = new ELoginPassword();
            password.setPassword("password");
            password.setUser(member);
            em.persist(password);
        }

        @SuppressWarnings("nls")
        private void insertNormalUser() {
            final EntityManager em = this.tool.getEntityManager();

            final EMember member = new EMember();
            member.setAdministrator(false);
            member.setUserId("normal");
            em.persist(member);

            final ELoginPassword password = new ELoginPassword();
            password.setPassword("password");
            password.setUser(member);
            em.persist(password);
        }
    }

    /**
     * @author jabaraster
     */
    public static class UserIsEmpty {
        /**
         * 
         */
        @Rule
        public final JpaDaoRule<AuthenticationServiceImpl> tool = new JpaDaoRule<AuthenticationServiceImpl>() {
                                                                    @Override
                                                                    protected AuthenticationServiceImpl createService(
                                                                            final EntityManagerFactory pEntityManagerFactory) {
                                                                        return new AuthenticationServiceImpl(pEntityManagerFactory);
                                                                    }
                                                                };

        /**
         * @throws FailAuthentication
         */
        @SuppressWarnings("nls")
        @Test(expected = FailAuthentication.class)
        public void ログインに失敗したら例外がスローされる() throws FailAuthentication {
            this.tool.getSut().login("jabara", "password");
        }
    }
}
