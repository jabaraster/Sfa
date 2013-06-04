/**
 * 
 */
package jabara.sfa.web.ui.page;

import jabara.sfa.service.IMemberService;
import jabara.wicket.beaneditor.BeanEditor;

import javax.inject.Inject;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;

/**
 * @author jabaraster
 */
public class MemberEditorPage extends ProtectedPageBase {
    private static final long serialVersionUID = 8777145479105283339L;

    @Inject
    private IMemberService    memberService;

    /**
     * 
     */
    public MemberEditorPage() {
        this.add(new BeanEditor<>("editor", this.memberService.getAll().get(0))); //$NON-NLS-1$
    }

    /**
     * @see jabara.sfa.web.ui.page.WebPageBase#renderHead(org.apache.wicket.markup.head.IHeaderResponse)
     */
    @Override
    public void renderHead(final IHeaderResponse pResponse) {
        super.renderHead(pResponse);
        addPageCssReference(pResponse, this.getClass());
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
