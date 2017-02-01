<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
                                <td>${currentProject.name}</td>
                            </tr>
                            <tr>
                                <td><b>Project managerId:</b></td>
                                <td>${projectManager.firstName} ${projectManager.lastName}</td>
                            </tr>
                            <tr>
                                <td><b>Status</b></td>
                                <td>${currentProject.status}</td>
                            </tr>
                            <tr>
                                <td><b>Start date</b></td>
                                <td><c:out value="${currentProject.startDate == null ? 'Not started': currentProject.startDate}"/></td>
                            </tr>
                            <tr>
                                <td><b>End date</b></td>
                                <td><c:out value="${currentProject.endDate == null ? 'Not ended': currentProject.startDate}"/></td>
                            </tr>
                            <tr>
                                <td><b>Employees</b></td>
                                <td>${totalProjectEmployees}</td>
                            </tr>
                            <tr>
                                <td><b>Sprints</b></td>
                                <td>${totalProjectSprints}</td>
                            </tr>
                            <tr>
                                <td><b>Tasks</b></td>
                                <td></td>
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
                                    <th>Details</th>
                                    <th class="th-del-btn">
                                        <i class="glyphicon glyphicon-remove"></i>
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${currentProject.users}" var="employee">
                                <tr>
                                    <td>${employee.firstName}</td>
                                    <td>${employee.lastName}</td>
                                    <td>${employee.role}</td>
                                    <td>
                                        <a href="/user/${employee.username}">
                                            <button class="btn btn-info btn-xs">Details</button>
                                        </a>
                                    </td>
                                    <td>
                                        <button class="btn btn-danger btn-xs" data-toggle="modal" data-target="#confirm-drop-employee-${employee.id}">
                                            <i class="glyphicon glyphicon-remove"></i>
                                        </button>
                                    </td>
                                </tr>
                                <div class="modal fade" role="dialog" id="confirm-drop-employee-${employee.id}">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-body text-center">
                                                <h4>Do you really want delete ${employee.firstName} ${employee.lastName} form ${employee.project.name} project?</h4>
                                            </div>
                                            <div class="modal-footer">
                                                <form action="/project/employees/delete" method="POST" >
                                                    <input type="hidden" class="form-control" name="employeeId" value="${employee.id}" />
                                                    <input type="hidden" class="form-control" name="projectName" value="${employee.project.name}" />
                                                    <div class="text-center">
                                                        <button type="submit" class="btn btn-sm btn-success"><i class="fa fa-check" aria-hidden="true"></i> Yes, delete</button>
                                                        <button type="button" data-dismiss="modal" class="btn btn-sm btn-danger"><i class="fa fa-times" aria-hidden="true"></i> No, cancel</button>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                            </tbody>
                        </table>
                        <button class="btn btn-success pull-right" data-toggle="modal" data-target="#add-employee">
                            Add Employee
                        </button>
                        <div class="modal fade" role="dialog" id="add-employee">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-body text-center">
                                        <h4>Select the employee you want to add to the project</h4>
                                    </div>
                                    <div class="modal-footer">
                                        <form action="/project/employees/add" method="POST" >
                                            <div class="form-group col-lg-offset-2 col-lg-8">
                                                <input type="hidden" class="form-control" name="projectName" value="${currentProject.name}">
                                                <select class="form-control" id="employees" name="employeeId">
                                                    <c:forEach var="employee" items="${freeEmployees}">
                                                        <option value="${employee.id}">${employee.firstName} ${employee.lastName}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <br>
                                            <div class="text-center">
                                                <button type="submit" class="btn btn-sm btn-success"><i class="fa fa-check" aria-hidden="true"></i> Add employee</button>
                                                <button type="button" data-dismiss="modal" class="btn btn-sm btn-danger"><i class="fa fa-times" aria-hidden="true"></i> No, cancel</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
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
                                    <th>Details</th>
                                    <th class="th-del-btn">
                                        <i class="glyphicon glyphicon-remove"></i>
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${currentProject.sprints}" var="sprint">
                                <tr>
                                    <td>${sprint.name}</td>
                                    <td>${sprint.description}</td>
                                    <td>${sprint.status}</td>
                                    <td>
                                        <a href="/sprint/${sprint.name}">
                                            <button class="btn btn-info btn-xs">Details</button>
                                        </a>
                                    </td>
                                    <td>
                                        <button class="btn btn-danger btn-xs" data-toggle="modal" data-target="#confirm-drop-sprint-${sprint.id}"><i class="glyphicon glyphicon-remove"></i></button>
                                    </td>
                                </tr>
                                <div class="modal fade" role="dialog" id="confirm-drop-sprint-${sprint.id}">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-body text-center">
                                                <h4>Do you really want delete ${sprint.name} form ${currentProject.name} project?</h4>
                                            </div>
                                            <div class="modal-footer">
                                                <form action="/project/sprints/delete" method="POST" >
                                                    <input type="hidden" class="form-control" name="sprintId" value="${sprint.id}" />
                                                    <input type="hidden" class="form-control" name="projectName" value="${currentProject.name}" />
                                                    <div class="text-center">
                                                        <button type="submit" class="btn btn-sm btn-success"><i class="fa fa-check" aria-hidden="true"></i> Yes, delete</button>
                                                        <button type="button" data-dismiss="modal" class="btn btn-sm btn-danger"><i class="fa fa-times" aria-hidden="true"></i> No, cancel</button>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                            </tbody>
                        </table>
                        <button class="btn btn-success pull-right" data-toggle="modal" data-target="#addSprintModal">Add
                            Sprint
                        </button>
                        <div class="modal fade" role="dialog" id="addSprintModal">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <form:form action="/sprints/add" method="POST" modelAttribute="sprint" >
                                            <form:hidden path="projectId" value="${currentProject.id}"/>
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
                                    <th>Details</th>
                                    <th class="th-del-btn">
                                        <i class="glyphicon glyphicon-remove"></i>
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${currentProject.sprints}" var="sprint">
                                <c:forEach items="${sprint.tasks}" var="dto">
                                <tr>
                                    <td>${dto.name}</td>
                                    <td>${dto.assigner.firstName} ${dto.assigner.lastName}</td>
                                    <td>${dto.creator.firstName} ${dto.creator.lastName}</td>
                                    <td>${dto.status}</td>
                                    <td>${dto.estimate}</td>
                                    <td>
                                        <a href="/task/${dto.name}">
                                            <button class="btn btn-info btn-xs">Details</button>
                                        </a>
                                    </td>
                                    <td>
                                        <button class="btn btn-danger btn-xs" data-toggle="modal" data-target="#confirm-drop-task-${dto.id}">
                                            <i class="glyphicon glyphicon-remove"></i>
                                        </button>
                                    </td>
                                </tr>
                                    <div class="modal fade" role="dialog" id="confirm-drop-task-${dto.id}">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-body text-center">
                                                    <h4>Do you really want delete ${dto.name} form ${currentProject.name} project?</h4>
                                                </div>
                                                <div class="modal-footer">
                                                    <form action="/project/tasks/delete" method="POST" >
                                                        <input type="hidden" class="form-control" name="projectName" value="${currentProject.name}" />
                                                        <input type="hidden" class="form-control" name="taskId" value="${dto.id}" />
                                                        <div class="text-center">
                                                            <button type="submit" class="btn btn-sm btn-success"><i class="fa fa-check" aria-hidden="true"></i> Yes, delete</button>
                                                            <button type="button" data-dismiss="modal" class="btn btn-sm btn-danger"><i class="fa fa-times" aria-hidden="true"></i> No, cancel</button>
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:forEach>
                            </tbody>
                        </table>
                        <button class="btn btn-success pull-right" data-toggle="modal" data-target="#addTaskModal">Add
                            Task
                        </button>
                    </div>
                    <div class="modal fade" role="dialog" id="addTaskModal">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <%--<form action="/project/task/add" method="POST" >
                                        <input class="form-control" type="hidden" name="projectName" value="${currentProject.name}" required>
                                        <div class="form-group col-lg-6">
                                            <label for="task-title">Title:</label>
                                            <input class="form-control" type="text" name="taskName" id="task-title" required>
                                        </div>
                                        <div class="form-group col-lg-6">
                                            <label for="assigner">Assign for:</label>
                                            <select class="form-control" id="assigner" name="assignerId">
                                                <c:forEach var="employee" items="${currentProject.users}">
                                                    <option value="${employee.id}">${employee.firstName} ${employee.lastName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="form-group col-lg-6">
                                            <label for="assigner">Sprint:</label>
                                            <select class="form-control" name="sprintId" required>
                                                <c:forEach var="sprint" items="${currentProject.sprints}">
                                                    <option value="${sprint.id}">${sprint.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="form-group col-lg-6">
                                            <label for="taskEstimate">Estimate:</label>
                                            <input class="form-control" type="number" name="taskEstimate" id="taskEstimate" required>
                                        </div>
                                        <div class="form-group col-lg-offset-3 col-lg-6">
                                            <label for="taskEndDate">End date:</label>
                                            <input type="date" name="taskEndDate" class="form-control" id="taskEndDate" required>
                                        </div>
                                        <div class="form-group col-lg-12">
                                            <label for="taskDescription">Description:</label>
                                            <textarea class="form-control" name="taskDescription" id="taskDescription" rows="5" required></textarea>
                                        </div>
                                        <div class="form-group col-lg-12 text-center">
                                            <button type="submit" class="btn btn-sm btn-success"><i class="fa fa-check" aria-hidden="true"></i> Add task</button>
                                            <button type="button" data-dismiss="modal" class="btn btn-sm btn-danger"><i class="fa fa-times" aria-hidden="true"></i> No, cancel</button>
                                        </div>
                                    </form>--%>
                                    <form:form action="/project/tasks/add" method="POST" modelAttribute="task" >
                                        <div class="form-group col-lg-12 outer">
                                            <div class="form-group col-lg-6">
                                                <label for="name">Title:</label>
                                                <form:input path="name" type="text" class="form-control"/>
                                                <form:errors path="name" cssClass="label label-danger"/>
                                            </div>
                                            <div class="form-group form-group-sm col-lg-6">
                                                <label for="sprintId">Sprint:</label>
                                                <form:select path="sprintId" class="form-control custom-select-height">
                                                    <c:forEach var="sprint" items="${currentProject.sprints}">
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
                                                    <c:forEach var="employee" items="${currentProject.users}">
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
                </div>
            </div>
        </div>
    </div>
</div>