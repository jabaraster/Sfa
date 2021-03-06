/**
 * 
 */
package jabara.sfa.web.ui.page;

import jabara.sfa.entity.EMember;
import jabara.sfa.service.IMemberService;
import jabara.wicket.ErrorClassAppender;
import jabara.wicket.beaneditor.BeanEditor;

import java.io.Serializable;

import javax.inject.Inject;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * @author jabaraster
 */
@SuppressWarnings("synthetic-access")
public class MemberEditorPage extends WebPageBase {
    private static final long   serialVersionUID = 8777145479105283339L;

    @Inject
    private IMemberService      memberService;

    private final Handler       handler          = new Handler();

    private Form<?>             form;
    private BeanEditor<EMember> editor;
    private Button              submitter;

    /**
     * 
     */
    public MemberEditorPage() {
        this.add(getForm());
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

    private BeanEditor<EMember> getEditor() {
        if (this.editor == null) {
            this.editor = new BeanEditor<>("editor", this.memberService.getAll().get(0)); //$NON-NLS-1$
        }
        return this.editor;
    }

    private Form<?> getForm() {
        if (this.form == null) {
            this.form = new Form<>("form"); //$NON-NLS-1$
            this.form.add(getEditor());
            this.form.add(getSubmitter());
        }
        return this.form;
    }

    @SuppressWarnings("serial")
    private Button getSubmitter() {
        if (this.submitter == null) {
            this.submitter = new Button("submitter") { //$NON-NLS-1$
                @Override
                public void onError() {
                    MemberEditorPage.this.handler.onError();
                }

                @Override
                public void onSubmit() {
                    MemberEditorPage.this.handler.onSubmit();
                }
            };
        }
        return this.submitter;
    }

    private class Handler implements Serializable {
        private static final long        serialVersionUID   = -6538659030796470744L;

        private final ErrorClassAppender errorClassAppender = new ErrorClassAppender(Model.of("error")); //$NON-NLS-1$

        void onError() {
            this.errorClassAppender.addErrorClass(getForm());
        }

        void onSubmit() {
            this.errorClassAppender.addErrorClass(getForm());
        }
    }
}
