<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div id="page-wrapper">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12"><br>
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <span class="panel-title">
                            Projects list
                        </span>
                        <a href="/projects/new"><button type="button" class="btn btn-success btn-sm pull-right">New Project</button></a>
                    </div>
                    <div class="panel-body">
                        <table class="table table-bordered table-hover" id="users-table">
                            <thead>
                            <tr>
                                <th>Title</th>
                                <th>Start date</th>
                                <th>End date</th>
                                <th>Status</th>
                                <th>Manager</th>
                                <th>Employees</th>
                                <th>Sprints</th>
                                <th>Update</th>
                                <th>Delete</th>
                                <th>Details</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="project" items="${projects}">
                                <tr>
                                    <td>${project.name}</td>
                                    <td>${project.startDate}</td>
                                    <td>${project.endDate}</td>
                                    <td>${project.status}</td>
                                    <td>${project.manager.firstName} ${project.manager.lastName}</td>
                                    <td>${fn:length(project.users)}
                                    <td>${fn:length(project.sprints)}</td>
                                    <td>
                                        <button type="button" class="btn btn-warning btn-xs" data-toggle="modal"
                                                data-target="#updEmpModal">Update</button>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-danger btn-xs">Delete</button>
                                    </td>
                                    <td>
                                        <a href="/project/${project.id}"><button type="button" class="btn btn-success btn-xs">Details</button></a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <!-- New Project Modal -->
        <div class="modal fade" id="newProjectModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header" style="background-color: #337AB7">
                        <h4 class="modal-title">Add new Employee</h4>
                    </div>
                    <div class="modal-body">
                        <form:form action="/addProject" method="POST" modelAttribute="project">
                            <div class="form-group col-lg-12 outer">
                                <div class="form-group col-lg-6">
                                    <label for="name">Title:</label>
                                    <form:input path="name" type="text" class="form-control" id="name" pattern="[a-zA-Z]{2,20}" />
                                </div>
                                <div class="form-group col-lg-6">
                                    <label for="manager">Project manager:</label>
                                    <form:select path="manager" id="manager">
                                        <c:forEach var="employee" items="${freeEmployees}">
                                            <form:option value="${employee.firstName} ${employee.lastName}"/>
                                        </c:forEach>
                                    </form:select>
                                </div>
                            </div>
                            <div class="form-group col-lg-12 outer">
                                <div class="form-group col-lg-12">
                                    <p><b>Description: </b></p>
                                    <form:textarea path="description" id="description" rows="5" cssStyle="width: 100%"/>
                                </div>
                            </div>
                            <div class="form-group col-lg-12 outer">
                                <div class="form-group col-lg-12">
                                    <label for="employees">Project manager:</label>
                                    <form:select class="form-control" path="employees" id="employees">
                                        <c:forEach var="employee" items="${freeEmployees}">
                                            <form:option value="${employee.id}" label="${employee.firstN}"/>
                                        </c:forEach>
                                    </form:select>
                                </div>
                            </div>
                            <div class="form-group col-lg-12 outer">
                                <div class="form-group col-lg-12">
                                    <p><b>Note:</b> You can add employees, sprints, tasks after project creation</p>
                                </div>
                            </div>
                            <div class="form-group text-center">
                                <button type="submit" class="btn btn-primary">Create project</button>
                                <button type="button" class="btn btn-danger" data-dismiss="modal">Close menu</button>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
        <!-- Update User Modal -->
        <div class="modal fade" id="updEmpModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header" style="background-color: #337AB7">
                        <h4 class="modal-title">Update Employee</h4>
                    </div>
                    <div class="modal-body">
                        <form name="updEmpForm" novalidate>
                            <div class="col-lg-12 outer">
                                <div class="form-group col-lg-6">
                                    <label for="ufirstName">First name:</label>
                                    <input type="text" class="form-control" id="ufirstName" pattern="/^[a-zA-Z]{2,10}$/" required/>
                                </div>
                                <div class="form-group col-lg-6">
                                    <label for="ulastName">Last name:</label>
                                    <input type="text" class="form-control" pattern="/^[a-zA-Z]{2,10}$/" id="ulastName" required>
                                </div>
                            </div>
                            <div class="col-lg-12 outer">
                                <div class="form-group col-lg-3">
                                    <label for="usalary">Salary:</label>
                                    <input type="text" class="form-control" id="usalary" pattern="/^[0-9]+$/" required>
                                </div>
                                <div class="form-group form-group-sm col-md-3">
                                    <label for="ugender">Gender:</label>
                                    <select class="form-control custom-select-height" id="ugender" required>
                                        <option>Male</option>
                                        <option>Female</option>
                                    </select>
                                </div>
                                <div class="form-group col-lg-6">
                                    <label for="uemail">Email:</label>
                                    <input type="email" class="form-control" id="uemail" required>
                                </div>
                            </div>
                            <div class="col-lg-12 outer">
                                <div class="form-group col-lg-6">
                                    <label for="ubirthday">Birthday:</label>
                                    <input type="date" class="form-control" id="ubirthday" required>
                                </div>
                                <div class="form-group col-lg-6">
                                    <label for="uhiredate">Hiredate:</label>
                                    <input type="date" class="form-control" id="uhiredate" required>
                                </div>
                            </div>
                            <div class="form-group col-lg-12 outer">
                                <div class="form-group form-group-sm col-md-6" id="upm">
                                    <label for="upm">Assign as PM for the project:</label>
                                    <select class="form-control custom-select-height" required>
                                        <option>Minerva</option>
                                    </select>
                                </div>
                            </div>
                            <div class="text-center">
                                <button type="submit" class="btn btn-primary">Save changes</button>
                                <button type="button" class="btn btn-danger" data-dismiss="modal">Close menu</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>