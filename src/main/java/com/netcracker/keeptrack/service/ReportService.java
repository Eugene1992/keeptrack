package com.netcracker.keeptrack.service;

import com.netcracker.keeptrack.web.dto.EmployeeReportDTO;
import com.netcracker.keeptrack.web.dto.ProjectReportDTO;

/**
 * Project interface that describes methods for reports generation.
 */
public interface ReportService {

    /**
     * Generates a report about the project.
     * @param projectName name of the selected project
     * @return project report data transfer object
     */
    ProjectReportDTO generateProjectReport(String projectName);

    /**
     * Generates a report about the employee.
     * @param username name of the selected employee
     * @param fromDate start date of report
     * @param toDate end date of the report
     * @return employee report data transfer object
     */
    EmployeeReportDTO generateEmployeeReport(String username, String fromDate, String toDate);
}
