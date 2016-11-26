package com.team19.controller.repository;
import com.team19.controller.model.RowResourceReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
//import utils.SQLUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Booker on 11/24/16.
 */

@Repository
public class ResourceReportRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<RowResourceReport> getResourceReport(String userName) {

        List<RowResourceReport> resourceReports = new ArrayList<RowResourceReport>();

        StringBuilder builder = new StringBuilder();
        builder.append(SQLUtils.SELECT);
        builder.append(
                " Description AS PrimaryEmergencySupportFunction," +
                        " Count(ResourceId) AS TotalResources," +
                        " Sum(IF(Username='%s',1,0)) AS ResourcesInUse ");
        builder.append(SQLUtils.FROM);
        builder.append(" ESF " +
                " LEFT JOIN Primary_ESF ON Primary_ESF.Number = ESF.Number "+
                " LEFT JOIN Resource ON Primary_ESF.ResourceId = Resource.ID "+
                " GROUP BY "+
                " ESF.Description"
        );

        String sql = String.format(builder.toString(), userName);

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

        int rowNumber=0;
        int sumTotalResources=0;
        int sumResourcesInUse=0;
        for (Map<String, Object> row : rows) {
            RowResourceReport rowResourceReport = new RowResourceReport();
            rowResourceReport.setRowNumber(++rowNumber);
            rowResourceReport.setPrimaryEmergencySupportFunction(row.get("PrimaryEmergencySupportFunction").toString());

            int totalResources=Integer.parseInt(row.get("TotalResources").toString());
            rowResourceReport.setTotalResources(totalResources);

            int resourcesInUse=Integer.parseInt(row.get("ResourcesInUse").toString());
            rowResourceReport.setResourcesInUse(resourcesInUse);

            sumTotalResources+=totalResources;
            sumResourcesInUse+=resourcesInUse;

            resourceReports.add(rowResourceReport);
        }

        RowResourceReport sumResourceReports = new RowResourceReport();
        sumResourceReports.setRowNumber(0);
        sumResourceReports.setPrimaryEmergencySupportFunction("TOTALS");
        sumResourceReports.setTotalResources(sumTotalResources);
        sumResourceReports.setResourcesInUse(sumResourcesInUse);

        resourceReports.add(sumResourceReports);
        return resourceReports;

    }

}
