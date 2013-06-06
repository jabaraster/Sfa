/**
 * 
 */
package jabara.sfa.service;

import jabara.sfa.entity.EMember;
import jabara.sfa.service.impl.MemberServiceImpl;

import java.util.List;

import com.google.inject.ImplementedBy;

/**
 * @author jabaraster
 */
@ImplementedBy(MemberServiceImpl.class)
public interface IMemberService {

    /**
     * 
     */
    final String DEFAULT_ADMINISTRATOR_USER_ID       = "administrator"; //$NON-NLS-1$
    /**
     * 
     */
    final String DEFAULT_ADMINISTRATOR_USER_PASSWORD = "password";     //$NON-NLS-1$

    /**
     * @return -
     */
    List<EMember> getAll();

    /**
     * 
     */
    void insertAdministratorIfNotExists();
}
