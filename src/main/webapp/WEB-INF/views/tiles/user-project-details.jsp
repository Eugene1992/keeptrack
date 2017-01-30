<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<div id="page-wrapper">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12">
                <!-- Project Summary Panel -->
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">
                            <i class="glyphicon glyphicon-info-sign"></i> Project summary
                        </h3>
                    </div>
                    <div class="panel-body">
                        <table class="table table-bordered">
                            <tbody>
                            <tr>
                                <td><b>Project name:</b></td>
                                <td>${user.project.name}</td>
                            </tr>
                            <tr>
                                <td><b>Project managerId:</b></td>
                                <td>${user.project.manager.firstName} ${user.project.manager.lastName}</td>
                            </tr>
                            <tr>
                                <td><b>Status</b></td>
                                <td>${user.project.status}</td>
                            </tr>
                            <tr>
                                <td><b>Start date</b></td>
                                <td><c:out value="${user.project.startDate == null ? 'Not started': user.project.startDate}"/></td>
                            </tr>
                            <tr>
                                <td><b>End date</b></td>
                                <td><c:out value="${user.project.endDate == null ? 'Not ended': user.project.startDate}"/></td>
                            </tr>
                            <tr>
                                <td><b>Employees</b></td>
                                <td>${fn:length(user.project.users)}</td>
                            </tr>
                            <tr>
                                <td><b>Sprints</b></td>
                                <td>${fn:length(user.project.sprints)}</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <!-- Project Employees Panel -->
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">
                            <i class="fa fa-users fa-fw"></i> Project employees
                        </h3>
                    </div>
                    <div class="panel-body">
                        <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <th>First name</th>
                                    <th>Last name</th>
                                    <th>Position</th>
                                    <th class="text-right" style="width: 40px;">Details</th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${user.project.users}" var="employee">
                                <tr>
                                    <td>${employee.firstName}</td>
                                    <td>${employee.lastName}</td>
                                    <td>${employee.role}</td>
                                    <td>
                                        <button class="btn btn-info btn-xs pull-right">Details</button>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
                <!-- Project Sprints Panel -->
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">
                            <i class="glyphicon glyphicon-info-sign"></i> Project Sprints
                        </h3>
                    </div>
                    <div class="panel-body">
                        <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <th>Title</th>
                                    <th>Description</th>
                                    <th>Status</th>
                                    <security:authorize access="hasRole('PM')">
                                    <th class="text-right" style="width: 40px;">Update</th>
                                    </security:authorize>
                                    <th class="text-right" style="width: 40px;">Details</th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${user.project.sprints}" var="sprint">
                                <tr>
                                    <td>${sprint.name}</td>
                                    <td>${sprint.description}</td>
                                    <td>${sprint.status}</td>
                                    <security:authorize access="hasRole('PM')">
                                    <td>
                                        <button class="btn btn-warning btn-xs pull-right">Update</button>
                                    </td>
                                    </security:authorize>
                                    <td>
                                        <button class="btn btn-info btn-xs pull-right">Details</button>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <security:authorize access="hasRole('PM')">
                        <button class="btn btn-success pull-right" data-toggle="modal" data-target="#addSprintModal">
                            Add Sprint
                        </button>
                        </security:authorize>
                        <div class="modal fade" role="dialog" id="addSprintModal">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <form:form action="project/sprints/add" method="POST" modelAttribute="sprint" >
                                            <form:hidden path="projectId" value="${user.project.id}"/>
                                            <form:hidden path="status" value="CREATED"/>
                                            <div class="form-group col-lg-12 outer">
                                                <div class="form-group col-lg-6">
                                                    <label for="name">Title:</label>
                                                    <form:input path="name" type="text" class="form-control"/>
                                                    <form:errors path="name" cssClass="label label-danger"/>
                                                </div>
                                            </div>
                                            <div class="form-group col-lg-12 outer">
                                                <div class="form-group col-lg-6">
                                                    <label for="startDate">Start date:</label>
                                                    <form:input path="startDate" type="date" class="form-control"/>
                                                    <form:errors path="startDate" cssClass="label label-danger"/>
                                                </div>
                                                <div class="form-group col-lg-6">
                                                    <label for="endDate">End date:</label>
                                                    <form:input path="endDate" type="date" class="form-control"/>
                                                    <form:errors path="endDate" cssClass="label label-danger"/>
                                                </div>
                                            </div>
                                            <div class="form-group col-lg-12 outer">
                                                <div class="form-group col-lg-12">
                                                    <p><b>Description: </b></p>
                                                    <form:textarea path="description" rows="5" cssClass="text-area-margin"/>
                                                    <form:errors path="description" cssClass="label label-danger"/>
                                                </div>
                                            </div>
                                            <div class="form-group text-center">
                                                <button type="submit" class="btn btn-primary">Create sprint</button>
                                                <button type="button" data-dismiss="modal" class="btn btn-sm btn-danger"><i class="fa fa-times" aria-hidden="true"></i> No, cancel</button>
                                            </div>
                                        </form:form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Project Tasks Panel -->
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">
                            <i class="glyphicon glyphicon-info-sign"></i> Project Tasks
                        </h3>
                    </div>
                    <div class="panel-body">
                        <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <th>Title</th>
                                    <th>Assigned to</th>
                                    <th>Created by</th>
                                    <th>Status</th>
                                    <th>Estimate</th>
                                    <security:authorize access="hasRole('PM')">
                                    <th class="text-right" style="width: 40px;">Update</th>
                                    </security:authorize>
                                    <th class="text-right" style="width: 40px;">Details</th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${user.project.sprints}" var="sprint">
                                <c:forEach items="${sprint.tasks}" var="dto">
                                <tr>
                                    <td>${dto.name}</td>
                                    <td>${dto.assigner.firstName} ${dto.assigner.lastName}</td>
                                    <td>${dto.creator.firstName} ${dto.creator.lastName}</td>
                                    <td>${dto.status}</td>
                                    <td>${dto.estimate}</td>
                                    <security:authorize access="hasRole('PM')">
                                    <td><button class="btn btn-warning btn-xs pull-right">Update</button></td>
                                    </security:authorize>
                                    <td>
                                        <button class="btn btn-info btn-xs pull-right">Details</button>
                                    </td>
                                </tr>
                                </c:forEach>
                            </c:forEach>
                            </tbody>
                        </table>
                        <security:authorize access="hasRole('PM')">
                        <button class="btn btn-success pull-right" data-toggle="modal" data-target="#addTaskModal">
                            Add Task
                        </button>
                        </security:authorize>
                    </div>
                    <security:authorize access="hasRole('PM')">
                    <div class="modal fade" role="dialog" id="addTaskModal">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <form:form action="/project/tasks/add" method="POST" modelAttribute="task" >
                                        <div class="form-group col-lg-12 outer">
                                            <form:hidden path="creatorId" value="${user.id}" class="form-control"/>
                                            <form:hidden path="status" value="CREATED" class="form-control"/>
                                            <div class="form-group col-lg-6">
                                                <label for="name">Title:</label>
                                                <form:input path="name" type="text" class="form-control"/>
                                                <form:errors path="name" cssClass="label label-danger"/>
                                            </div>
                                            <div class="form-group form-group-sm col-lg-6">
                                                <label for="sprintId">Sprint:</label>
                                                <form:select path="sprintId" class="form-control custom-select-height">
                                                    <c:forEach var="sprint" items="${user.project.sprints}">
                                                        <form:option value="${sprint.id}" label="${sprint.name}"/>
                                                    </c:forEach>
                                                </form:select>
                                                <form:errors path="sprintId" cssClass="label label-danger"/>
                                            </div>
                                        </div>
                                        <div class="form-group col-lg-12 outer">
                                            <div class="form-group col-lg-6">
                                                <label for="estimate">Estimate:</label>
                                                <form:input path="estimate" min="1" type="number" class="form-control"/>
                                                <form:errors path="estimate" cssClass="label label-danger"/>
                                            </div>
                                            <div class="form-group col-lg-6">
                                                <label for="assignerId">Assigner:</label>
                                                <form:select path="assignerId" class="form-control custom-select-height">
                                                    <c:forEach var="employee" items="${user.project.users}">
                                                        <form:option value="${employee.id}" label="${employee.firstName} ${employee.lastName}"/>
                                                    </c:forEach>
                                                </form:select>
                                                <form:errors path="assignerId" cssClass="label label-danger"/>
                                            </div>
                                        </div>
                                        <div class="form-group col-lg-12 outer">
                                            <div class="form-group col-lg-6">
                                                <label for="startDate">Start date:</label>
                                                <form:input path="startDate" type="date" class="form-control"/>
                                                <form:errors path="startDate" cssClass="label label-danger"/>
                                            </div>
                                            <div class="form-group col-lg-6">
                                                <label for="endDate">End date:</label>
                                                <form:input path="endDate" type="date" class="form-control"/>
                                                <form:errors path="endDate" cssClass="label label-danger"/>
                                            </div>
                                        </div>
                                        <div class="form-group col-lg-12 outer">
                                            <div class="form-group col-lg-12">
                                                <p><b>Description: </b></p>
                                                <form:textarea path="description" rows="5" cssClass="text-area-margin"/>
                                                <form:errors path="description" cssClass="label label-danger"/>
                                            </div>
                                        </div>
                                        <div class="form-group text-center">
                                            <button type="submit" class="btn btn-primary">Create task</button>
                                            <button type="button" class="btn btn-danger" data-dismiss="modal">Close menu</button>
                                        </div>
                                    </form:form>
                                </div>
                            </div>
                        </div>
                    </div>
                    </security:authorize>
                </div>
            </div>
        </div>
    </div>
</div>