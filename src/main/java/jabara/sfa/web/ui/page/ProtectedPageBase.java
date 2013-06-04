/**
 * 
 */
package jabara.sfa.web.ui.page;

import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * @author jabaraster
 */
public abstract class ProtectedPageBase extends RestrictedPageBase {
    private static final long serialVersionUID = 4974019219251588135L;

    /**
     * 
     */
    protected ProtectedPageBase() {
        super();
    }

    /**
     * @param pParameters -
     */
    protected ProtectedPageBase(final PageParameters pParameters) {
        super(pParameters);
    }

}
