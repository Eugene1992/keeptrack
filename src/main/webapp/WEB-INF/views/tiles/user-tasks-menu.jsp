<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div id="page-wrapper">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12"><br>
                <div class="panel panel-default" style="margin-bottom: 0">
                    <div class="panel-body" style="padding: 0">
                        <ul class="nav nav-pills" role="tablist">
                            <li class="nav-item active">
                                <a class="nav-link active" data-toggle="tab" href="#created" role="tab"><b>Created</b></a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" data-toggle="tab" href="#assigned" role="tab"><b>Assigned</b></a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" data-toggle="tab" href="#inProgress" role="tab"><b>In
                                    progress</b></a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" data-toggle="tab" href="#closed" role="tab"><b>Closed</b></a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" data-toggle="tab" href="#rejected" role="tab"><b>Rejected</b></a>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="tab-content">
                    <div class="tab-pane active" id="created" role="tabpanel">
                        <c:choose>
                            <c:when test="${not empty createdTasks}">
                                <c:forEach var="task" items="${createdTasks}">
                                    <div class="col-lg-6"><br>
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
                                                        <td><span class="label label-info">${task.status}</span></td>
                                                    </tr>
                                                    <tr>
                                                        <td><b>Project:</b></td>
                                                        <td>${task.sprint.project.name}</td>
                                                    </tr>
                                                    <tr>
                                                        <td><b>Sprint:</b></td>
                                                        <td>${task.sprint.name}</td>
                                                    </tr>
                                                    <tr>
                                                        <td><b>Creator:</b></td>
                                                        <td>${task.creator.firstName} ${task.creator.lastName}</td>
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
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <div class="col-lg-12"><br>
                                    <div class="alert alert-success" role="alert">
                                        <p>No created tasks found.</p>
                                    </div>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="tab-pane" id="assigned" role="tabpanel">
                        <c:choose>
                            <c:when test="${not empty assignedTasks}">
                                <c:forEach var="task" items="${assignedTasks}">
                                    <div class="col-lg-6"><br>
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
                                                        <td><span class="label label-info">${task.status}</span></td>
                                                    </tr>
                                                    <tr>
                                                        <td><b>Project:</b></td>
                                                        <td>${task.sprint.project.name}</td>
                                                    </tr>
                                                    <tr>
                                                        <td><b>Sprint:</b></td>
                                                        <td>${task.sprint.name}</td>
                                                    </tr>
                                                    <tr>
                                                        <td><b>Creator:</b></td>
                                                        <td>${task.creator.firstName} ${task.creator.lastName}</td>
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
                                                <div class="text-center">
                                                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#accept-task-${task.id}">
                                                        <span class="glyphicon glyphicon-ok"></span> Accept
                                                    </button>
                                                    <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#reject-task-${task.id}">
                                                        <span class="glyphicon glyphicon-remove"></span> Reject
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="modal fade" role="dialog" id="accept-task-${task.id}">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-body text-center">
                                                    <h4>Do you really want accept ${task.name}?</h4>
                                                </div>
                                                <div class="modal-footer">
                                                    <form action="/project/task/accept" method="POST" >
                                                        <input type="hidden" class="form-control" name="taskId" value="${task.id}" />
                                                        <div class="text-center">
                                                            <button type="submit" class="btn btn-sm btn-success"><i class="fa fa-check" aria-hidden="true"></i> Yes, accept</button>
                                                            <button type="button" data-dismiss="modal" class="btn btn-sm btn-danger"><i class="fa fa-times" aria-hidden="true"></i> No, cancel</button>
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="modal fade" role="dialog" id="reject-task-${task.id}">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-body text-center">
                                                    <h4>Do you really want reject ${task.name}?</h4>
                                                </div>
                                                <div class="modal-footer">
                                                    <form action="/project/task/reject" method="POST" >
                                                        <input type="hidden" class="form-control" name="taskId" value="${task.id}" />
                                                        <div class="text-center">
                                                            <button type="submit" class="btn btn-sm btn-success"><i class="fa fa-check" aria-hidden="true"></i> Yes, reject</button>
                                                            <button type="button" data-dismiss="modal" class="btn btn-sm btn-danger"><i class="fa fa-times" aria-hidden="true"></i> No, cancel</button>
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <div class="col-lg-12"><br>
                                    <div class="alert alert-success" role="alert">
                                        <p>No assigned tasks found.</p>
                                    </div>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="tab-pane" id="inProgress" role="tabpanel">
                        <c:choose>
                            <c:when test="${not empty inProgressTasks}">
                                <c:forEach var="task" items="${inProgressTasks}">
                                    <div class="col-lg-6"><br>
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
                                                        <td><span class="label label-success">${task.status}</span></td>
                                                    </tr>
                                                    <tr>
                                                        <td><b>Project:</b></td>
                                                        <td>${task.sprint.project.name}</td>
                                                    </tr>
                                                    <tr>
                                                        <td><b>Sprint:</b></td>
                                                        <td>${task.sprint.name}</td>
                                                    </tr>
                                                    <tr>
                                                        <td><b>Creator:</b></td>
                                                        <td>${task.creator.firstName} ${task.creator.lastName}</td>
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
                                                <button type="button" class="btn btn-success center-block" data-toggle="modal" data-target="#complete-task-${task.id}">
                                                    <span class="glyphicon glyphicon-ok"></span> Complete
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="modal fade" role="dialog" id="complete-task-${task.id}">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-body text-center">
                                                    <h4>Do you really want complete ${task.name}?</h4>
                                                </div>
                                                <div class="modal-footer">
                                                    <form action="/project/task/complete" method="POST" >
                                                        <input type="hidden" class="form-control" name="taskId" value="${task.id}" />
                                                        <div class="text-center">
                                                            <button type="submit" class="btn btn-sm btn-success"><i class="fa fa-check" aria-hidden="true"></i> Yes, complete</button>
                                                            <button type="button" data-dismiss="modal" class="btn btn-sm btn-danger"><i class="fa fa-times" aria-hidden="true"></i> No, cancel</button>
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <div class="col-lg-12"><br>
                                    <div class="alert alert-success" role="alert">
                                        <p>No performed tasks found.</p>
                                    </div>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="tab-pane" id="closed" role="tabpanel">
                        <c:choose>
                            <c:when test="${not empty closedTasks}">
                                <c:forEach var="task" items="${closedTasks}">
                                    <div class="col-lg-6"><br>
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
                                                        <td><span class="label label-danger">${task.status}</span></td>
                                                    </tr>
                                                    <tr>
                                                        <td><b>Project:</b></td>
                                                        <td>${task.sprint.project.name}</td>
                                                    </tr>
                                                    <tr>
                                                        <td><b>Sprint:</b></td>
                                                        <td>${task.sprint.name}</td>
                                                    </tr>
                                                    <tr>
                                                        <td><b>Creator:</b></td>
                                                        <td>${task.creator.firstName} ${task.creator.lastName}</td>
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
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <div class="col-lg-12"><br>
                                    <div class="alert alert-success" role="alert">
                                        <p>No closed tasks found.</p>
                                    </div>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="tab-pane" id="rejected" role="tabpanel">
                        <c:choose>
                            <c:when test="${not empty rejectedTasks}">
                                <c:forEach var="task" items="${rejectedTasks}">
                                    <div class="col-lg-6"><br>
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
                                                        <td><span class="label label-warning">${task.status}</span></td>
                                                    </tr>
                                                    <tr>
                                                        <td><b>Project:</b></td>
                                                        <td>${task.sprint.project.name}</td>
                                                    </tr>
                                                    <tr>
                                                        <td><b>Sprint:</b></td>
                                                        <td>${task.sprint.name}</td>
                                                    </tr>
                                                    <tr>
                                                        <td><b>Creator:</b></td>
                                                        <td>${task.creator.firstName} ${task.creator.lastName}</td>
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
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <div class="col-lg-12"><br>
                                    <div class="alert alert-success" role="alert">
                                        <p>No rejected tasks found.</p>
                                    </div>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>