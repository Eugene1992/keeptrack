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
                                <td><b>Project manager:</b></td>
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
                                        <button class="btn btn-info btn-xs">Details</button>
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
                                    <th>Update</th>
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
                                        <button class="btn btn-warning btn-xs">Update</button>
                                    </td>
                                    <td>
                                        <button class="btn btn-info btn-xs">Details</button>
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
                                        <form action="/project/sprints/add" method="POST" >
                                            <input type="hidden" name="projectName" value="${currentProject.name}" class="form-control" required>
                                            <div class="form-group col-lg-6">
                                                <label for="title">Title:</label>
                                                <input type="text" name="sprintName" class="form-control" id="title" required>
                                            </div>
                                            <div class="form-group col-lg-6">
                                                <label for="title">End date:</label>
                                                <input type="date" name="sprintEndDate" class="form-control" id="endDate" required>
                                            </div>
                                            <div class="form-group col-lg-12">
                                                <label for="description">Description:</label>
                                                <textarea name="sprintDescription" class="form-control" id="description" rows="5" required></textarea>
                                            </div>
                                            <div class="form-group col-lg-12">
                                                <p><b>Note:</b> New Sprint can start only after the previous one.
                                                    The current sprint can be queued and will start after the completion of the previous sprint.</p>
                                            </div>
                                            <div class="form-group col-lg-12 text-center">
                                                <button type="submit" class="btn btn-sm btn-success"><i class="fa fa-check" aria-hidden="true"></i> Add sprint</button>
                                                <button type="button" data-dismiss="modal" class="btn btn-sm btn-danger"><i class="fa fa-times" aria-hidden="true"></i> No, cancel</button>
                                            </div>
                                        </form>
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
                                <c:forEach items="${sprint.tasks}" var="task">
                                <tr>
                                    <td>${task.name}</td>
                                    <td>${task.assigner.firstName} ${task.assigner.lastName}</td>
                                    <td>${task.creator.firstName} ${task.creator.lastName}</td>
                                    <td>${task.status}</td>
                                    <td>${task.estimate}</td>
                                    <td>
                                        <button class="btn btn-info btn-xs">Details</button>
                                    </td>
                                    <td>
                                        <button class="btn btn-danger btn-xs" data-toggle="modal" data-target="#confirm-drop-task-${task.id}">
                                            <i class="glyphicon glyphicon-remove"></i>
                                        </button>
                                    </td>
                                </tr>
                                    <div class="modal fade" role="dialog" id="confirm-drop-task-${task.id}">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-body text-center">
                                                    <h4>Do you really want delete ${task.name} form ${currentProject.name} project?</h4>
                                                </div>
                                                <div class="modal-footer">
                                                    <form action="/project/tasks/delete" method="POST" >
                                                        <input type="hidden" class="form-control" name="projectName" value="${currentProject.name}" />
                                                        <input type="hidden" class="form-control" name="taskId" value="${task.id}" />
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
                        <button class="btn btn-warning pull-left">Edit Task</button>
                        <button class="btn btn-success pull-right" data-toggle="modal" data-target="#addTaskModal">Add
                            Task
                        </button>
                    </div>
                    <div class="modal fade" role="dialog" id="addTaskModal">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <form action="/project/task/add" method="POST" >
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
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>