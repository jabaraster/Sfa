package jabara.sfa.web.ui.page;

import jabara.general.Empty;
import jabara.sfa.model.FailAuthentication;
import jabara.sfa.web.ui.AppSession;
import jabara.wicket.ErrorClassAppender;

import java.io.Serializable;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.pages.RedirectPage;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.string.StringValue;

/**
 * 
 */
@SuppressWarnings("synthetic-access")
public class LoginPage extends WebPageBase {
    private static final long serialVersionUID = 1925170327965147328L;

    private final Handler     handler          = new Handler();

    private FeedbackPanel     feedback;
    private StatelessForm<?>  form;
    private TextField<String> userId;
    private PasswordTextField password;
    private Button            submitter;

    /**
     * 
     */
    public LoginPage() {
        this.add(getFeedback());
        this.add(getForm());
    }

    /**
     * @see jabara.sfa.web.ui.page.WebPageBase#renderHead(org.apache.wicket.markup.head.IHeaderResponse)
     */
    @Override
    public void renderHead(final IHeaderResponse pResponse) {
        super.renderHead(pResponse);
        addPageCssReference(pResponse, getPageClass());
        pResponse.render(OnDomReadyHeaderItem.forScript(JavaScriptUtil.getFocusScript(getUserId())));
    }

    /**
     * @see jabara.sfa.web.ui.page.WebPageBase#getTitleLabelModel()
     */
    @Override
    protected IModel<String> getTitleLabelModel() {
        return Model.of(getString("pageTitle")); //$NON-NLS-1$
    }

    StatelessForm<?> getForm() {
        if (this.form == null) {
            this.form = new StatelessForm<>("form"); //$NON-NLS-1$
            this.form.add(getUserId());
            this.form.add(getPassword());
            this.form.add(getSubmitter());
        }
        return this.form;
    }

    PasswordTextField getPassword() {
        if (this.password == null) {
            this.password = new PasswordTextField("password", Model.of(Empty.STRING)); //$NON-NLS-1$
        }
        return this.password;
    }

    @SuppressWarnings("serial")
    Button getSubmitter() {
        if (this.submitter == null) {
            this.submitter = new Button("submitter") { //$NON-NLS-1$
                @Override
                public void onError() {
                    LoginPage.this.handler.onSubmitterError();
                }

                @Override
                public void onSubmit() {
                    LoginPage.this.handler.tryLogin();
                }
            };
        }
        return this.submitter;
    }

    TextField<String> getUserId() {
        if (this.userId == null) {
            this.userId = new TextField<>("userId", Model.of(Empty.STRING)); //$NON-NLS-1$
            this.userId.setRequired(true);
        }
        return this.userId;
    }

    private FeedbackPanel getFeedback() {
        if (this.feedback == null) {
            this.feedback = new FeedbackPanel("feedback"); //$NON-NLS-1$
        }
        return this.feedback;
    }

    private class Handler implements Serializable {
        private static final long        serialVersionUID   = 6317461189636878176L;

        private final ErrorClassAppender errorClassAppender = new ErrorClassAppender(Model.of("error")); //$NON-NLS-1$

        private void onSubmitterError() {
            this.errorClassAppender.addErrorClass(getForm());
        }

        private void tryLogin() {
            try {
                AppSession.get().login(getUserId().getModelObject(), getPassword().getModelObject());

                final StringValue u = getPageParameters().get("u"); //$NON-NLS-1$
                if (u.isNull() || u.isEmpty()) {
                    setResponsePage(getApplication().getHomePage());
                } else {
                    setResponsePage(new RedirectPage(u.toString()));
                }
            } catch (final FailAuthentication e) {
                error(getString("message.failLogin")); //$NON-NLS-1$
                this.errorClassAppender.addErrorClass(getForm());
            }
        }
    }

}
