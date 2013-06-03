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
     * @return -
     */
    List<EMember> getAll();

    /**
     * 
     */
    void insertAdministratorIfNotExists();
}
