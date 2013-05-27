/**
 * 
 */
package jabara.sfa.service;

import jabara.sfa.service.impl.MemberServiceImpl;

import com.google.inject.ImplementedBy;

/**
 * @author jabaraster
 */
@ImplementedBy(MemberServiceImpl.class)
public interface IMemberService {

    /**
     * 
     */
    void insertAdministratorIfNotExists();

}
