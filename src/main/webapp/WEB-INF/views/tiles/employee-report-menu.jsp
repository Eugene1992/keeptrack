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
                            ${report.reportedUser.firstName} ${report.reportedUser.lastName} summary
                        </span>
                    </div>
                    <div class="panel-body">
                        <div class="col-lg-6">
                            <table class="table table-user-information ">
                                <tbody>
                                <tr>
                                    <td><b>First name:</b></td>
                                    <td>${report.reportedUser.firstName}</td>
                                </tr>
                                <tr>
                                    <td><b>Last name:</b></td>
                                    <td>${report.reportedUser.lastName}</td>
                                </tr>
                                <tr>
                                    <td><b>Gender:</b></td>
                                    <td>${report.reportedUser.gender}</td>
                                </tr>
                                <tr>
                                    <td><b>Role:</b></td>
                                    <td>${report.reportedUser.role}</td>
                                </tr>
                                <tr>
                                    <td><b>Tasks:</b></td>
                                    <td>${fn:length(report.dtoTasks)}</td>
                                </tr>
                                <tr>
                                    <td><b>Username:</b></td>
                                    <td>${report.reportedUser.username}</td>
                                </tr>
                                <tr>
                                    <td><b>Email:</b></td>
                                    <td>${report.reportedUser.email}</td>
                                </tr>
                                <tr>
                                    <td><b>Birthday:</b></td>
                                    <td>${report.reportedUser.birthday}</td>
                                </tr>
                                <tr>
                                    <td><b>Hire day:</b></td>
                                    <td>${report.reportedUser.hireDay}</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="col-lg-6">
                            <div id="tasksChart" style="height: 300px; width: 100%;"></div>
                        </div>
                    </div>
                    <div class="panel-heading">
                        <span class="panel-title">
                            ${report.reportedUser.firstName} ${report.reportedUser.lastName} projects
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
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="project" items="${report.projects}">
                                    <tr>
                                        <td>${project.name}</td>
                                        <td>${project.status}</td>
                                        <td>${project.startDate}</td>
                                        <td>${project.endDate}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="panel-heading">
                        <span class="panel-title">
                            ${report.reportedUser.firstName} ${report.reportedUser.lastName} sprints
                        </span>
                    </div>
                    <div class="panel-body">
                        <div class="col-lg-12">
                            <table class="table table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th>Name</th>
                                        <th>Project</th>
                                        <th>Status</th>
                                        <th>Start date</th>
                                        <th>End date</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="sprint" items="${report.sprints}">
                                    <tr>
                                        <td>${sprint.name}</td>
                                        <td>${sprint.project.name}</td>
                                        <td>${sprint.status}</td>
                                        <td>${sprint.startDate}</td>
                                        <td>${sprint.endDate}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="panel-heading">
                        <span class="panel-title">
                            ${report.reportedUser.firstName} ${report.reportedUser.lastName} tasks
                        </span>
                    </div>
                    <div class="panel-body">
                        <div class="col-lg-12">
                            <table class="table table-bordered table-hover" id="tasks-table">
                                <thead>
                                    <tr>
                                        <th>Name</th>
                                        <th>Project</th>
                                        <th>Sprint</th>
                                        <th>Status</th>
                                        <th>Start date</th>
                                        <th>End date</th>
                                        <th>Deviation<br>(in hours)</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="dto" items="${report.dtoTasks}">
                                    <tr style="background-color: ${dto.deviation > 0 ? '#F2DEDE' : '#DFF0D8'}">
                                        <td>${dto.task.name}</td>
                                        <td>${dto.task.sprint.project.name}</td>
                                        <td>${dto.task.sprint.name}</td>
                                        <td>${dto.task.status}</td>
                                        <td>${dto.task.startDate}</td>
                                        <td>${dto.task.endDate}</td>
                                        <td>${dto.deviation}</td>
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