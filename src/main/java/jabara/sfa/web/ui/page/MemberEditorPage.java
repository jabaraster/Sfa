/**
 * 
 */
package jabara.sfa.web.ui.page;

import jabara.sfa.entity.EMember;
import jabara.wicket.beaneditor.BeanEditor;

import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;

/**
 * @author jabaraster
 */
public class MemberEditorPage extends ProtecdtedPageBase {
    private static final long serialVersionUID = 8777145479105283339L;

    /**
     * 
     */
    public MemberEditorPage() {
        this.add(new BeanEditor<>("editor", new EMember())); //$NON-NLS-1$
    }

    /**
     * @see jabara.sfa.web.ui.page.WebPageBase#getTitleLabelModel()
     */
    @SuppressWarnings("serial")
    @Override
    protected IModel<String> getTitleLabelModel() {
        return new AbstractReadOnlyModel<String>() {
            @Override
            public String getObject() {
                return "メンバ編集"; //$NON-NLS-1$
            }
        };
    }

}
