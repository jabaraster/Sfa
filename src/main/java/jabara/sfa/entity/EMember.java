/**
 * 
 */
package jabara.sfa.entity;

import jabara.bean.annotation.Localized;
import jabara.jpa.entity.EntityBase;
import jabara.sfa.beanvalidation.RequireMaxCharCount;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * @author jabaraster
 */
@Entity
public class EMember extends EntityBase<EMember> {
    private static final long serialVersionUID       = -6160695120326005348L;

    private static final int  MAX_CHAR_COUNT_USER_ID = 100;
    /**
     * 
     */
    @Column(nullable = false, length = MAX_CHAR_COUNT_USER_ID * 3, unique = true)
    @NotNull
    @RequireMaxCharCount(MAX_CHAR_COUNT_USER_ID)
    protected String          userId;

    /**
     * 
     */
    @Column(nullable = false)
    protected boolean         administrator          = false;

    /**
     * @return nameを返す.
     */
    @Localized
    public String getUserId() {
        return this.userId;
    }

    /**
     * @return administratorを返す.
     */
    @Localized
    public boolean isAdministrator() {
        return this.administrator;
    }

    /**
     * @param pAdministrator administratorを設定.
     */
    public void setAdministrator(final boolean pAdministrator) {
        this.administrator = pAdministrator;
    }

    /**
     * @param pName nameを設定.
     */
    public void setUserId(final String pName) {
        this.userId = pName;
    }

}
