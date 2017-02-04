<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<div id="page-wrapper">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12"><br>
                <div class="panel panel-default" style="margin-bottom: 0">
                    <div class="panel-body" style="padding: 0">
                        <ul class="nav nav-pills" role="tablist">
                            <security:authorize access="hasAnyRole('PM')">
                            <li class="nav-item active">
                                <a class="nav-link" data-toggle="tab" href="#assigned" role="tab"><b>Opened</b></a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" data-toggle="tab" href="#inProgress" role="tab"><b>Accepted</b></a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" data-toggle="tab" href="#rejected" role="tab"><b>Rejected</b></a>
                            </li>
                            </security:authorize>
                            <security:authorize access="hasAnyRole('EMPLOYEE')">
                            <li class="nav-item active">
                                <a class="nav-link" data-toggle="tab" href="#created" role="tab"><b>Created</b></a>
                            </li>
                            </security:authorize>
                        </ul>
                    </div>
                </div>
                <div class="tab-content">
                    <security:authorize access="hasAnyRole('PM')">
                    <div class="tab-pane active" id="assigned" role="tabpanel">
                        <c:choose>
                            <c:when test="${not empty openedRequests}">
                                <div class="col-lg-12"><br>
                                    <div class="panel panel-primary">
                                        <div class="panel-heading">
                                            <h3 class="panel-title">Opened requests</h3>
                                        </div>
                                        <div class="panel-body">
                                            <table class="table table-bordered">
                                                <thead>
                                                <tr>
                                                    <th>Tittle</th>
                                                    <th>Creator</th>
                                                    <th>Status</th>
                                                    <th>Sprint</th>
                                                    <th>Task</th>
                                                    <th>Task estimate</th>
                                                    <th>Requested estimate</th>
                                                    <th class="text-right" style="width: 40px;">Accept</th>
                                                    <th class="text-right" style="width: 40px;">Reject</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <c:forEach var="request" items="${openedRequests}">
                                                    <c:choose>
                                                        <c:when test="${request.status == 'OPENED'}">
                                                            <tr>
                                                                <td>${request.tittle}</td>
                                                                <td>${request.creator.firstName} ${request.creator.lastName}</td>
                                                                <td><span class="label label-info">${request.status}</span></td>
                                                                <td>${request.task.sprint.name}</td>
                                                                <td>${request.task.name}</td>
                                                                <td>${request.task.estimate}</td>
                                                                <td>${request.reqEstimate}</td>
                                                                <td>
                                                                    <button type="button" class="btn btn-sm btn-success" data-toggle="modal" data-target="#accept-request-${request.id}">
                                                                        <span class="glyphicon glyphicon-ok"></span>
                                                                        Accept
                                                                    </button>
                                                                </td>
                                                                <td>
                                                                    <button type="button" class="btn btn-sm btn-danger" data-toggle="modal" data-target="#reject-request-${request.id}">
                                                                        <span class="glyphicon glyphicon-remove"></span>
                                                                        Reject
                                                                    </button>
                                                                </td>
                                                            </tr>
                                                            <div class="modal fade" role="dialog"
                                                                 id="accept-request-${request.id}">
                                                                <div class="modal-dialog">
                                                                    <div class="modal-content">
                                                                        <div class="modal-body text-center">
                                                                            <h4>Do you really want accept request?</h4>
                                                                        </div>
                                                                        <div class="modal-footer">
                                                                            <form action="/task/request/accept" method="POST">
                                                                                <input type="hidden" class="form-control" name="requestId" value="${request.id}"/>
                                                                                <div class="text-center">
                                                                                    <button type="submit" class="btn btn-sm btn-success">
                                                                                        <i class="fa fa-check" aria-hidden="true"></i>
                                                                                        Yes, accept
                                                                                    </button>
                                                                                    <button type="button" data-dismiss="modal" class="btn btn-sm btn-danger">
                                                                                        <i class="fa fa-times" aria-hidden="true"></i>
                                                                                        No, cancel
                                                                                    </button>
                                                                                </div>
                                                                            </form>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="modal fade" role="dialog"
                                                                 id="reject-request-${request.id}">
                                                                <div class="modal-dialog">
                                                                    <div class="modal-content">
                                                                        <div class="modal-body text-center">
                                                                            <h4>Do you really want reject request?</h4>
                                                                        </div>
                                                                        <div class="modal-footer">
                                                                            <form action="/task/request/reject" method="POST">
                                                                                <input type="hidden" class="form-control" name="requestId" value="${request.id}"/>
                                                                                <div class="text-center">
                                                                                    <button type="submit" class="btn btn-sm btn-success">
                                                                                        <i class="fa fa-check" aria-hidden="true"></i>
                                                                                        Yes, reject
                                                                                    </button>
                                                                                    <button type="button" data-dismiss="modal" class="btn btn-sm btn-danger">
                                                                                        <i class="fa fa-times" aria-hidden="true"></i>
                                                                                        No, cancel
                                                                                    </button>
                                                                                </div>
                                                                            </form>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </c:when>
                                                    </c:choose>
                                                </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="col-lg-12"><br>
                                    <div class="alert alert-success" role="alert">
                                        <p>No opened requests found.</p>
                                    </div>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="tab-pane" id="inProgress" role="tabpanel">
                        <c:choose>
                            <c:when test="${not empty acceptedRequests}">
                                <div class="col-lg-12"><br>
                                    <div class="panel panel-primary">
                                        <div class="panel-heading">
                                            <h3 class="panel-title">Accepted requests</h3>
                                        </div>
                                        <div class="panel-body">
                                            <table class="table table-bordered">
                                                <thead>
                                                <tr>
                                                    <th>Tittle</th>
                                                    <th>Creator</th>
                                                    <th>Status</th>
                                                    <th>Sprint</th>
                                                    <th>Task</th>
                                                    <th>Task estimate</th>
                                                    <th>Requested estimate</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <c:forEach var="request" items="${acceptedRequests}">
                                                    <tr>
                                                        <td>${request.tittle}</td>
                                                        <td>${request.creator.firstName} ${request.creator.lastName}</td>
                                                        <td><span class="label label-success">${request.status}</td>
                                                        <td>${request.task.sprint.name}</td>
                                                        <td>${request.task.name}</td>
                                                        <td>${request.task.estimate}</td>
                                                        <td>${request.reqEstimate}</td>
                                                    </tr>
                                                </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>

                            </c:when>
                            <c:otherwise>
                                <div class="col-lg-12"><br>
                                    <div class="alert alert-success" role="alert">
                                        <p>No assigned requests found.</p>
                                    </div>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="tab-pane" id="rejected" role="tabpanel">
                        <c:choose>
                            <c:when test="${not empty rejectedRequests}">
                                <div class="col-lg-12"><br>
                                    <div class="panel panel-primary">
                                        <div class="panel-heading">
                                            <h3 class="panel-title">Rejected requests</h3>
                                        </div>
                                        <div class="panel-body">
                                            <table class="table table-bordered">
                                                <thead>
                                                <tr>
                                                    <th>Tittle</th>
                                                    <th>Creator</th>
                                                    <th>Status</th>
                                                    <th>Sprint</th>
                                                    <th>Task</th>
                                                    <th>Task estimate</th>
                                                    <th>Requested estimate</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <c:forEach var="request" items="${rejectedRequests}">
                                                    <tr>
                                                        <td>${request.tittle}</td>
                                                        <td>${request.creator.firstName} ${request.creator.lastName}</td>
                                                        <td><span class="label label-danger">${request.status}</span></td>
                                                        <td>${request.task.sprint.name}</td>
                                                        <td>${request.task.name}</td>
                                                        <td>${request.task.estimate}</td>
                                                        <td>${request.reqEstimate}</td>
                                                    </tr>
                                                </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="col-lg-12"><br>
                                    <div class="alert alert-success" role="alert">
                                        <p>No rejected requests found.</p>
                                    </div>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    </security:authorize>
                    <security:authorize access="hasAnyRole('EMPLOYEE')">
                    <div class="tab-pane active" id="created" role="tabpanel">
                        <c:choose>
                            <c:when test="${not empty user.createdRequests}">
                                <div class="col-lg-12"><br>
                                    <div class="panel panel-primary">
                                        <div class="panel-heading">
                                            <h3 class="panel-title">Created requests</h3>
                                        </div>
                                        <div class="panel-body">
                                            <table class="table table-bordered">
                                                <thead>
                                                <tr>
                                                    <th>Tittle</th>
                                                    <th>Creator</th>
                                                    <th>Status</th>
                                                    <th>Sprint</th>
                                                    <th>Task</th>
                                                    <th>Task estimate</th>
                                                    <th>Requested estimate</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <c:forEach var="request" items="${user.createdRequests}">
                                                    <tr>
                                                        <td>${request.tittle}</td>
                                                        <td>${request.creator.firstName} ${request.creator.lastName}</td>
                                                        <td>
                                                            <c:choose>
                                                                <c:when test="${request.status == 'OPENED'}">
                                                                    <span class="label label-info">${request.status}</span>
                                                                </c:when>
                                                                <c:when test="${request.status == 'ACCEPTED'}">
                                                                    <span class="label label-success">${request.status}</span>
                                                                </c:when>
                                                                <c:when test="${request.status == 'REJECTED'}">
                                                                    <span class="label label-danger">${request.status}</span>
                                                                </c:when>
                                                            </c:choose>
                                                        </td>
                                                        <td>${request.task.sprint.name}</td>
                                                        <td>${request.task.name}</td>
                                                        <td>${request.task.estimate}</td>
                                                        <td>${request.reqEstimate}</td>
                                                    </tr>
                                                </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="col-lg-12"><br>
                                    <div class="alert alert-success" role="alert">
                                        <p>No created requests found.</p>
                                    </div>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    </security:authorize>
                </div>
            </div>
        </div>
    </div>
</div>