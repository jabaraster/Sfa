/**
 * 
 */
package jabara.sfa.web.ui.page;

import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * @author jabaraster
 */
public abstract class ProtecdtedPageBase extends RestrictedPageBase {
    private static final long serialVersionUID = 4974019219251588135L;

    /**
     * 
     */
    protected ProtecdtedPageBase() {
        super();
    }

    /**
     * @param pParameters
     */
    protected ProtecdtedPageBase(final PageParameters pParameters) {
        super(pParameters);
    }

}
