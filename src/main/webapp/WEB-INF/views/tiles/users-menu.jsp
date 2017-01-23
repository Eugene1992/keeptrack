<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div id="page-wrapper">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12"><br>
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <span class="panel-title">
                            Users list
                        </span>
                        <button type="button" class="btn btn-success btn-sm pull-right" data-toggle="modal"
                                data-target="#newUserModal">New User
                        </button>
                    </div>
                    <div class="panel-body">
                        <table class="table table-bordered table-hover" id="users-table">
                            <thead>
                                <tr>
                                    <th>First Name</th>
                                    <th>Last Name</th>
                                    <th>Username</th>
                                    <th>Role</th>
                                    <th>Birthday</th>
                                    <th>Hire Date</th>
                                    <th>Salary</th>
                                    <th>Gender</th>
                                    <th>Project</th>
                                    <th>Update</th>
                                    <th>Delete</th>
                                    <th>Profile</th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="user" items="${allUsers}">
                            <jsp:useBean id="user" scope="page" type="com.netcracker.keeptrack.model.User"/>
                                <tr>
                                    <td>${user.firstName}</td>
                                    <td>${user.lastName}</td>
                                    <td>${user.username}</td>
                                    <td>${user.role}</td>
                                    <td>${user.birthday}</td>
                                    <td>${user.hireDay}</td>
                                    <td>${user.salary}</td>
                                    <td>${user.gender}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${user.role eq 'EMPLOYEE' || 'CUSTOMER'}">
                                                ${user.project.name}
                                            </c:when>
                                            <c:otherwise>
                                                ${user.managedProject.name}
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <form action="/users/update/${user.id}" method="POST">
                                            <button type="submit" class="btn btn-warning btn-xs">Update</button>
                                        </form>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-danger btn-xs" data-toggle="modal" data-target="#confirm-drop-user-${user.id}">Delete</button>                                    </td>
                                    <td>
                                        <a href="/user/${user.username}"><button type="button" class="btn btn-success btn-xs">Profile</button></a>
                                    </td>
                                </tr>
                                <div class="modal fade" role="dialog" id="confirm-drop-user-${user.id}">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-body text-center">
                                                <h4>Do you really want delete ${user.firstName} ${user.lastName}?</h4>
                                            </div>
                                            <div class="modal-footer">
                                                <form action="/users/delete" method="POST" >
                                                    <input type="hidden" class="form-control" name="id" value="${user.id}" />
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
                    </div>
                </div>
            </div>
        </div>
        <!-- New User Modal -->
        <div class="modal fade" id="newUserModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header" style="background-color: #337AB7">
                        <h4 class="modal-title">Create new User</h4>
                    </div>
                    <div class="modal-body">
                        <form:form action="/users/add" method="POST" modelAttribute="user" id="newUserModal">
                            <div class="form-group col-lg-12 outer">
                                <div class="form-group col-lg-6">
                                    <label for="firstName">First name:</label>
                                    <form:input path="firstName" type="text" class="form-control" />
                                </div>
                                <div class="form-group col-lg-6">
                                    <label for="lastName">Last name:</label>
                                    <form:input path="lastName" type="text" class="form-control" />
                                </div>
                            </div>
                            <div class="form-group col-lg-12 outer">
                                <div class="form-group col-lg-6">
                                    <label for="username">Username:</label>
                                    <form:input path="username" type="text" class="form-control" />
                                </div>
                                <div class="form-group col-lg-6">
                                    <label for="password">Password:</label>
                                    <form:input path="password" type="text" class="form-control" />
                                </div>
                            </div>
                            <div class="form-group col-lg-12 outer">
                                <div class="form-group form-group-sm col-md-4">
                                    <label for="gender">Gender:</label>
                                    <form:select path="gender" class="form-control custom-select-height">
                                        <form:option value="MALE" />
                                        <form:option value="FEMALE" />
                                    </form:select>
                                </div>
                                <div class="form-group form-group-sm col-md-4">
                                    <label for="role">Role:</label>
                                    <form:select path="role" class="form-control custom-select-height">
                                        <form:option value="EMPLOYEE" />
                                        <form:option value="CUSTOMER" />
                                        <form:option value="PM" />
                                        <form:option value="ADMIN" />
                                    </form:select>
                                </div>
                                <div class="form-group form-group-sm col-lg-4">
                                    <label for="projectId">Project:</label>
                                    <form:select path="projectId" class="form-control custom-select-height">
                                        <c:forEach var="project" items="${allProjects}">
                                            <form:option value="${project.id}" label="${project.name}"/>
                                        </c:forEach>
                                    </form:select>
                                    <form:errors path="projectId" cssClass="label label-danger"/>
                                </div>
                            </div>
                            <div class="form-group col-lg-12 outer">
                                <div class="form-group col-lg-6">
                                    <label for="birthday">Birthday:</label>
                                    <form:input path="birthday" type="date" class="form-control"/>
                                </div>

                                <div class="form-group col-lg-6">
                                    <label for="hireDay">Hire day:</label>
                                    <form:input path="hireDay" type="date" class="form-control"/>
                                </div>
                            </div>
                            <div class="form-group col-lg-12 outer">
                                <div class="form-group col-lg-6">
                                    <label for="email">Email:</label>
                                    <form:input path="email" type="email" class="form-control"/>
                                </div>
                                <div class="form-group col-lg-6">
                                    <label for="salary">Salary:</label>
                                    <form:input path="salary" type="number" min="0" class="form-control"/>
                                </div>
                            </div>

                            <div class="form-group text-center">
                                <button type="submit" class="btn btn-primary">Create user</button>
                                <button type="button" class="btn btn-danger" data-dismiss="modal">Close menu</button>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>