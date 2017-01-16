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
                                        <button class="btn btn-danger btn-xs"><i class="glyphicon glyphicon-remove"></i></button>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <button class="btn btn-success pull-right" data-toggle="modal" data-target="#addSprintModal">Add
                            Sprint
                        </button>
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
                                        <button class="btn btn-danger btn-xs">
                                            <i class="glyphicon glyphicon-remove"></i>
                                        </button>
                                    </td>
                                </tr>
                                </c:forEach>
                            </c:forEach>
                            </tbody>
                        </table>
                        <button class="btn btn-warning pull-left">Edit Task</button>
                        <button class="btn btn-success pull-right" data-toggle="modal" data-target="#addTaskModal">Add
                            Task
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>