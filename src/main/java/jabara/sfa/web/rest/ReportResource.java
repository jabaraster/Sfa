package jabara.sfa.web.rest;

import jabara.general.ArgUtil;
import jabara.sfa.entity.EReport;
import jabara.sfa.service.IReportService;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 */
@Path("employee")
public class ReportResource {

    private final IReportService reportService;

    /**
     * @param pReportService -
     */
    @Inject
    public ReportResource(final IReportService pReportService) {
        ArgUtil.checkNull(pReportService, "pReportService"); //$NON-NLS-1$
        this.reportService = pReportService;
    }

    /**
     * @return 従業員情報全件.
     */
    @Path("all")
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public List<EReport> getAll() {
        return this.reportService.getAll(0, 100);
    }
}
