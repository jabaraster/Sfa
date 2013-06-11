/**
 * 
 */
package jabara.sfa.entity;

import jabara.bean.annotation.Hidden;
import jabara.bean.annotation.Localized;
import jabara.bean.annotation.Order;
import jabara.jpa.entity.EntityBase;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    @Size(min = 1, max = MAX_CHAR_COUNT_USER_ID)
    protected String          userId;

    /**
     * 
     */
    @Column(nullable = false)
    protected boolean         administrator          = false;

    /**
     * @see jabara.jpa.entity.EntityBase#getCreated()
     */
    @Override
    @Localized
    @Order(100)
    public Date getCreated() {
        return super.getCreated();
    }

    /**
     * @see jabara.jpa.entity.EntityBase#getId()
     */
    @Override
    @Order(0)
    public Long getId() {
        return super.getId();
    }

    /**
     * @see jabara.jpa.entity.EntityBase#getUpdated()
     */
    @Override
    @Localized
    @Order(200)
    public Date getUpdated() {
        return super.getUpdated();
    }

    /**
     * @return nameを返す.
     */
    @Localized
    @Order(10)
    public String getUserId() {
        return this.userId;
    }

    /**
     * @return administratorを返す.
     */
    @Localized
    @Order(20)
    public boolean isAdministrator() {
        return this.administrator;
    }

    /**
     * @see jabara.jpa.entity.EntityBase#isPersisted()
     */
    @Override
    @Hidden
    public boolean isPersisted() {
        return super.isPersisted();
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
