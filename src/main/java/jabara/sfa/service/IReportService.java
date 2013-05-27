/**
 * 
 */
package jabara.sfa.service;

import jabara.sfa.entity.EReport;
import jabara.sfa.service.impl.ReportServiceImpl;

import java.util.List;

import com.google.inject.ImplementedBy;

/**
 * @author jabaraster
 */
@ImplementedBy(ReportServiceImpl.class)
public interface IReportService {

    /**
     * @return -
     */
    long countAll();

    /**
     * @param pFirst -
     * @param pCount -
     * @return -
     */
    List<EReport> get(long pFirst, long pCount);

    /**
     * @return
     */
    /**
     * @param pOffset -
     * @param pCount -
     * @return -
     */
    List<EReport> getAll(int pOffset, int pCount);

    /**
     * @param pReport -
     */
    void insert(EReport pReport);
}
