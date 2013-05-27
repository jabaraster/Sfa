/**
 * 
 */
package jabara.sfa.service;

import jabara.sfa.model.FailAuthentication;
import jabara.sfa.service.impl.AuthenticationServiceImpl;

import com.google.inject.ImplementedBy;

/**
 * @author jabaraster
 */
@ImplementedBy(AuthenticationServiceImpl.class)
public interface IAuthenticationService {

    /**
     * @param pUserId
     * @param pPassword
     * @return -
     * @throws FailAuthentication
     */
    AuthenticatedAs login(String pUserId, String pPassword) throws FailAuthentication;

    /**
     * @author jabaraster
     */
    public enum AuthenticatedAs {

        /**
         * 通常ユーザとして認証済み.
         */
        NORMAL_USER,

        /**
         * 管理者ユーザとして認証済み.
         */
        ADMINISTRATOR, ;
    }

}
