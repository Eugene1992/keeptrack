<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<div id="page-wrapper">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-offset-2 col-lg-8"><br>
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">${sprint.name}</h3>

                    </div>
                    <div class="panel-body">
                        <table class="table table-user-information ">
                            <tbody>
                            <tr>
                                <td><b>Name:</b></td>
                                <td>${sprint.name}</td>
                            </tr>
                            <tr>
                                <td><b>Project:</b></td>
                                <td>
                                    ${sprint.project.name}
                                    <a href="/project/${sprint.project.name}">
                                        <button class="btn btn-xs btn-success pull-right">
                                            <i class="glyphicon glyphicon-share"></i>
                                        </button>
                                    </a>
                                </td>
                            </tr>
                            <tr>
                                <td><b>Project managerId:</b></td>
                                <td>
                                    ${sprint.project.manager.firstName} ${sprint.project.manager.lastName}
                                    <a href="/user/${sprint.project.manager.username}">
                                        <button class="btn btn-xs btn-success pull-right">
                                            <i class="glyphicon glyphicon-share"></i>
                                        </button>
                                    </a>
                                </td>
                            </tr>
                            <tr>
                                <td><b>Status:</b></td>
                                <td>${sprint.status}</td>
                            </tr>
                            <tr>
                            <tr>
                                <td><b>Start date:</b></td>
                                <td>${sprint.startDate}</td>
                            </tr>
                            <tr>
                                <td><b>End date:</b></td>
                                <td>${sprint.endDate}</td>
                            </tr>
                            <tr>
                                <td><b>Description:</b></td>
                                <td>${sprint.description}</td>
                            </tr>
                            <tr>
                                <td><b>Tasks</b></td>
                                <td>
                                    ${fn:length(sprint.tasks)}
                                </td>
                            </tr>
                            </tbody>
                        </table>
                        <security:authorize access="hasRole('ADMIN')">
                        <a href="/sprints"><button class="btn btn-sm btn-primary pull-right">Back to sprints</button></a>
                        </security:authorize>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>