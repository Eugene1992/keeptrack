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
                        <h3 class="panel-title">${task.name}</h3>
                    </div>
                    <div class="panel-body">
                        <table class="table table-user-information ">
                            <tbody>
                            <tr>
                                <td><b>Name:</b></td>
                                <td>${task.name}</td>
                            </tr>
                            <tr>
                                <td><b>Status:</b></td>
                                <td>${task.status}</td>
                            </tr>
                            <tr>
                                <td><b>Project:</b></td>
                                <td>
                                    ${task.sprint.project.name}
                                    <security:authorize access="hasRole('ADMIN')">
                                    <a href="/project/${task.sprint.project.name}">
                                        <button class="btn btn-xs btn-success pull-right">
                                            <i class="glyphicon glyphicon-share"></i>
                                        </button>
                                    </a>
                                    </security:authorize>
                                </td>
                            </tr>
                            <tr>
                                <td><b>Sprint:</b></td>
                                <td>
                                    ${task.sprint.name}
                                    <a href="/sprint/${task.sprint.name}">
                                        <button class="btn btn-xs btn-success pull-right">
                                            <i class="glyphicon glyphicon-share"></i>
                                        </button>
                                    </a>
                                </td>
                            </tr>
                            <tr>
                                <td><b>Creator:</b></td>
                                <td>
                                    ${task.creator.firstName} ${task.creator.lastName}
                                    <a href="/user/${task.creator.username}">
                                        <button class="btn btn-xs btn-success pull-right">
                                            <i class="glyphicon glyphicon-share"></i>
                                        </button>
                                    </a>
                                </td>
                            </tr>
                            <tr>
                                <td><b>Assigner:</b></td>
                                <td>
                                    ${task.assigner.firstName} ${task.assigner.lastName}
                                    <a href="/user/${task.assigner.username}">
                                        <button class="btn btn-xs btn-success pull-right">
                                            <i class="glyphicon glyphicon-share"></i>
                                        </button>
                                    </a>
                                </td>
                            </tr>
                            <tr>
                                <td><b>Start date:</b></td>
                                <td>${task.startDate}</td>
                            </tr>
                            <tr>
                                <td><b>End date:</b></td>
                                <td>${task.endDate}</td>
                            </tr>
                            <tr>
                                <td><b>Estimate:</b></td>
                                <td>${task.estimate}</td>
                            </tr>
                            <tr>
                                <td><b>Description:</b></td>
                                <td>${task.description}</td>
                            </tr>
                            </tbody>
                        </table>
                        <security:authorize access="hasRole('ADMIN')">
                        <a href="/tasks"><button class="btn btn-sm btn-primary pull-right">Back to tasks</button></a>
                        </security:authorize>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>