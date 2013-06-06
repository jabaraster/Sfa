/**
 * 
 */
package jabara.sfa.service.impl;

import jabara.jpa.JpaDaoBase;
import jabara.sfa.entity.ELoginPassword;
import jabara.sfa.entity.EMember;
import jabara.sfa.service.IMemberService;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

/**
 * @author jabaraster
 */
public class MemberServiceImpl extends JpaDaoBase implements IMemberService {
    private static final long serialVersionUID = 7579772724649072761L;

    /**
     * @param pEntityManagerFactory -
     */
    @Inject
    public MemberServiceImpl(final EntityManagerFactory pEntityManagerFactory) {
        super(pEntityManagerFactory);
    }

    /**
     * @see jabara.sfa.service.IMemberService#getAll()
     */
    @Override
    public List<EMember> getAll() {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<EMember> query = builder.createQuery(EMember.class);
        query.from(EMember.class);
        return em.createQuery(query).getResultList();
    }

    /**
     * @see jabara.sfa.service.IMemberService#insertAdministratorIfNotExists()
     */
    @Override
    public void insertAdministratorIfNotExists() {
        if (existsAministrator()) {
            return;
        }

        final EntityManager em = getEntityManager();

        final EMember member = new EMember();
        member.setAdministrator(true);
        member.setUserId(DEFAULT_ADMINISTRATOR_USER_ID);
        em.persist(member);

        final ELoginPassword password = new ELoginPassword();
        password.setPassword(DEFAULT_ADMINISTRATOR_USER_PASSWORD);
        password.setUser(member);
        em.persist(password);
    }

    private boolean existsAministrator() {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<String> query = builder.createQuery(String.class);
        query.from(EMember.class);

        final String DUMMY = "X"; //$NON-NLS-1$
        query.select(builder.literal(DUMMY).alias(DUMMY));

        return !em.createQuery(query).setMaxResults(1).getResultList().isEmpty();
    }
}
