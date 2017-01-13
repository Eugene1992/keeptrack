<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div id="page-wrapper">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12"><br>
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <span class="panel-title">
                            Employee list
                        </span>
                        <button type="button" class="btn btn-success btn-sm pull-right" data-toggle="modal"
                                data-target="#newEmpModal">New Employee
                        </button>
                    </div>
                    <div class="panel-body">
                        <table class="table table-bordered table-hover" id="employees-table">
                            <thead>
                                <tr>
                                    <th>First Name</th>
                                    <th>Last Name</th>
                                    <th>Birthday</th>
                                    <th>Hire Date</th>
                                    <th>Salary</th>
                                    <th>Gender</th>
                                    <th>Email</th>
                                    <th>Projects</th>
                                    <th>Update</th>
                                    <th>Delete</th>
                                    <th>Profile</th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${employeesList}" var="employee">
                            <jsp:useBean id="employee" scope="page" type="com.netcracker.keeptrack.model.Employee"/>
                                <tr>
                                    <td>${employee.firstName}</td>
                                    <td>${employee.lastName}</td>
                                    <td>${employee.birthday}</td>
                                    <td>${employee.birthday}</td>
                                    <td>${employee.salary}</td>
                                    <td>${employee.gender}</td>
                                    <td>${employee.email}</td>
                                    <td>${employeeProjects}</td>
                                    <td>
                                        <button type="button" class="btn btn-warning btn-xs" data-toggle="modal"
                                                data-target="#updEmpModal">Update</button>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-danger btn-xs">Delete</button>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-success btn-xs">Profile</button>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <!-- New Employee Modal -->
        <div class="modal fade" id="newEmpModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header" style="background-color: #337AB7">
                        <h4 class="modal-title">Add new Employee</h4>
                    </div>
                    <div class="modal-body">
                        <form:form action="/addEmployee" method="POST" modelAttribute="employee" id="newEmpForm" name="newEmpForm">
                            <div class="form-group col-lg-12 outer">
                                <div class="form-group col-lg-6">
                                    <label for="firstName">First name:</label>
                                    <form:input path="firstName" type="text" class="form-control" id="firstName" name="firstName" pattern="[a-zA-Z]{2,10}" />
                                </div>
                                <div class="form-group col-lg-6">
                                    <label for="lastName">Last name:</label>
                                    <form:input path="lastName" type="text" class="form-control" id="lastName" name="lastName" pattern="[a-zA-Z]{2,10}" />
                                </div>
                            </div>
                            <div class="form-group col-lg-12 outer">
                                <div class="form-group col-lg-3">
                                    <label for="salary">Salary:</label>
                                    <form:input path="salary" type="text" class="form-control" id="salary" name="salary" />
                                </div>
                                <div class="form-group form-group-sm col-md-3">
                                    <label for="gender">Gender:</label>
                                    <form:select path="gender" class="form-control custom-select-height" id="gender" name="gender">
                                        <form:option value="MALE" />
                                        <form:option value="FEMALE" />
                                    </form:select>
                                </div>
                                <div class="form-group col-lg-6">
                                    <label for="email">Email:</label>
                                    <form:input path="email" type="email" class="form-control" id="email" name="email"/>
                                </div>
                            </div>
                            <div class="form-group col-lg-12 outer">
                                <div class="form-group col-lg-6">
                                    <label for="birthday">Birthday:</label>
                                    <form:input path="birthday" type="date" class="form-control" id="birthday" name="birthday"/>
                                </div>

                                <div class="form-group col-lg-6">
                                    <label for="hiredate">Hiredate:</label>
                                    <form:input path="hireDay" type="date" class="form-control" id="hiredate" name="hiredate"/>
                                </div>
                            </div>
                            <%--<div class="form-group col-lg-12 outer">
                                <div class="form-group form-group-sm col-md-6" id="npm">
                                    <label for="npm">Assign as PM for the project:</label>
                                    <form:select path="" class="form-control custom-select-height">
                                        <form:option value="Minerva" />
                                    </form:select>
                                </div>
                            </div>--%>
                            <div class="form-group text-center">
                                <button type="submit" class="btn btn-primary">Save changes</button>
                                <button type="button" class="btn btn-danger" data-dismiss="modal">Close menu</button>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
        <!-- Update Employee Modal -->
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