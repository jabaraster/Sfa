/**
 * 
 */
package jabara.sfa.web.ui.component;

import jabara.jpa.entity.IEntity;
import jabara.wicket.beaneditor.BeanEditor;

import org.apache.wicket.markup.html.panel.Panel;

/**
 * @param <E>
 * @author jabaraster
 */
public class EntityEditor<E extends IEntity> extends Panel {
    private static final long serialVersionUID = -4429154358982463446L;

    /**
     * @param pId
     * @param pEntity
     */
    public EntityEditor(final String pId, final E pEntity) {
        super(pId);
        this.add(new BeanEditor<E>("editor", pEntity)); //$NON-NLS-1$
    }
}
