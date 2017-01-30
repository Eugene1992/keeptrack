<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div id="page-wrapper">
    <div class="container-fluid"><br>
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <span class="panel-title">
                            ${report.project.name} summary
                        </span>
                    </div>
                    <div class="panel-body">
                        <div class="col-lg-12">
                            <table class="table table-bordered">
                                <tbody>
                                <tr>
                                    <td><b>Project name:</b></td>
                                    <td>${report.project.name}</td>
                                </tr>
                                <tr>
                                    <td><b>Project manager:</b></td>
                                    <td>${report.project.manager.firstName} ${report.project.manager.lastName}</td>
                                </tr>
                                <tr>
                                    <td><b>Status:</b></td>
                                    <td>${report.project.status}</td>
                                </tr>
                                <tr>
                                    <td><b>Start date:</b></td>
                                    <td><c:out value="${report.project.startDate}"/></td>
                                </tr>
                                <tr>
                                    <td><b>End date:</b></td>
                                    <td><c:out value="${report.project.endDate}"/></td>
                                </tr>
                                <tr>
                                    <td><b>Employees:</b></td>
                                    <td>${fn:length(report.project.users)}</td>
                                </tr>
                                <tr>
                                    <td><b>Sprints:</b></td>
                                    <td>${fn:length(report.project.sprints)}</td>
                                </tr>
                                <tr>
                                    <td><b>Tasks:</b></td>
                                    <td>${fn:length(report.dtoTasks)}</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="panel-heading">
                        <span class="panel-title">
                            ${report.project.name} tasks & sprints statistic
                        </span>
                    </div>
                    <div class="panel-body">
                        <div class="col-lg-6">
                            <div id="tasksChart" style="height: 300px; width: 100%;"></div>
                        </div>
                        <div class="col-lg-6">
                            <div id="sprintsChart" style="height: 300px; width: 100%;"></div>
                        </div>
                        <hr>
                    </div>
                    <div class="panel-heading">
                        <span class="panel-title">
                            ${report.project.name} sprints
                        </span>
                    </div>
                    <div class="panel-body">
                        <div class="col-lg-12">
                            <table class="table table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>Name</th>
                                    <th>Status</th>
                                    <th>Start date</th>
                                    <th>End date</th>
                                    <th>Human-hours<br>(in hours)</th>
                                    <th>Deviation<br>(in days)</th>
                                    <th>Duration<br>(in hours)</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="dto" items="${report.dtoSprints}">
                                    <tr style="background-color: ${dto.deviation < 0 ? '#F2DEDE' : '#DFF0D8'}">
                                        <td>${dto.sprint.name}</td>
                                        <td>${dto.sprint.status}</td>
                                        <td>${dto.sprint.startDate}</td>
                                        <td>${dto.sprint.endDate}</td>
                                        <td>${dto.humanHours}</td>
                                        <td>${dto.deviation}</td>
                                        <td>${dto.duration}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="panel-heading">
                        <span class="panel-title">
                            ${report.project.name} tasks
                        </span>
                    </div>
                    <div class="panel-body">
                        <div class="col-lg-12">
                            <table class="table table-bordered table-hover" id="tasks-table">
                                <thead>
                                <tr>
                                    <th>Name</th>
                                    <th>Status</th>
                                    <th>Assigner</th>
                                    <th>Sprint</th>
                                    <th>Start date</th>
                                    <th>End date</th>
                                    <th>Estimate</th>
                                    <th>Human-hours<br>(in hours)</th>
                                    <th>Deviation<br>(in hours)</th>
                                    <th>Duration<br>(in hours)</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="dto" items="${report.dtoTasks}">
                                    <tr style="background-color: ${dto.deviation < 0 ? '#F2DEDE' : '#DFF0D8'}">
                                        <td>${dto.task.name}</td>
                                        <td>${dto.task.status}</td>
                                        <td>${dto.task.assigner.firstName} ${dto.task.assigner.lastName}</td>
                                        <td>${dto.task.sprint.name}</td>
                                        <td>${dto.task.startDate}</td>
                                        <td>${dto.task.endDate}</td>
                                        <td>${dto.task.estimate}</td>
                                        <td>${dto.humanHours}</td>
                                        <td>${dto.deviation}</td>
                                        <td>${dto.duration}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>