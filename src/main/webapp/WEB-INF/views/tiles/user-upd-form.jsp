<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div id="page-wrapper">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-offset-3 col-lg-6">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <span class="panel-title">
                            Update user
                        </span>
                    </div>
                    <div class="panel-body">
                        <form:form action="/users/update" method="POST" modelAttribute="user" id="newUserModal">
                            <form:hidden path="id" value="${updUser.id}" class="form-control"/>
                            <div class="form-group col-lg-12 outer">
                                <div class="form-group col-lg-6">
                                    <label for="firstName">First name:</label>
                                    <form:input path="firstName" type="text" class="form-control" value="${updUser.firstName}"/>
                                    <form:errors path="firstName" cssClass="label label-danger"/>
                                </div>
                                <div class="form-group col-lg-6">
                                    <label for="lastName">Last name:</label>
                                    <form:input path="lastName" type="text" class="form-control" value="${updUser.lastName}"/>
                                    <form:errors path="lastName" cssClass="label label-danger"/>
                                </div>
                            </div>
                            <div class="form-group col-lg-12 outer">
                                <div class="form-group col-lg-6">
                                    <label for="username">Username:</label>
                                    <form:input path="username" type="text" class="form-control" value="${updUser.username}"/>
                                    <form:errors path="username" cssClass="label label-danger"/>
                                </div>
                                <div class="form-group col-lg-6">
                                    <label for="password">Password:</label>
                                    <form:input path="password" type="text" class="form-control" value="${updUser.password}"/>
                                    <form:errors path="password" cssClass="label label-danger"/>
                                </div>
                            </div>
                            <div class="form-group col-lg-12 outer">
                                <div class="form-group form-group-sm col-md-4">
                                    <label for="gender">Gender:</label>
                                    <form:select path="gender" class="form-control custom-select-height">
                                        <c:choose>
                                            <c:when test="${updUser.gender eq 'MALE'}"><option selected>MALE</option></c:when>
                                            <c:when test="${updUser.gender eq 'FEMALE'}"><option selected>FEMALE</option></c:when>
                                        </c:choose>
                                    </form:select>
                                    <form:errors path="gender" cssClass="label label-danger"/>
                                </div>
                                <div class="form-group form-group-sm col-md-4">
                                    <label for="role">Role:</label>
                                    <form:select path="role" class="form-control custom-select-height">
                                        <c:choose>
                                            <c:when test="${updUser.role eq 'EMPLOYEE'}"><option selected>EMPLOYEE</option></c:when>
                                            <c:when test="${updUser.role eq 'CUSTOMER'}"><option selected>CUSTOMER</option></c:when>
                                            <c:when test="${updUser.role eq 'PM'}"><option selected>PM</option></c:when>
                                            <c:when test="${updUser.role eq 'ADMIN'}"><option selected>ADMIN</option></c:when>
                                        </c:choose>
                                    </form:select>
                                    <form:errors path="role" cssClass="label label-danger"/>
                                </div>
                                <div class="form-group form-group-sm col-lg-4">
                                    <label for="projectId">Project:</label>
                                    <form:select path="projectId" class="form-control custom-select-height">
                                        <c:forEach var="project" items="${allProjects}">
                                            <c:choose>
                                                <c:when test="${updUser.project.id eq project.id}">
                                                    <option value="${project.id}" selected>${project.name}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${project.id}">${project.name}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                        <form:errors path="projectId" cssClass="label label-danger"/>
                                    </form:select>
                                </div>
                            </div>
                            <div class="form-group col-lg-12 outer">
                                <div class="form-group col-lg-6">
                                    <label for="birthday">Birthday:</label>
                                    <form:input path="birthday" type="date" class="form-control" value="${updUser.birthday}"/>
                                    <form:errors path="birthday" cssClass="label label-danger"/>
                                </div>
                                <div class="form-group col-lg-6">
                                    <label for="hireDay">Hire day:</label>
                                    <form:input path="hireDay" type="date" class="form-control" value="${updUser.hireDay}"/>
                                    <form:errors path="hireDay" cssClass="label label-danger"/>
                                </div>
                            </div>
                            <div class="form-group col-lg-12 outer">
                                <div class="form-group col-lg-6">
                                    <label for="email">Email:</label>
                                    <form:input path="email" type="email" class="form-control" value="${updUser.email}"/>
                                    <form:errors path="email" cssClass="label label-danger"/>
                                </div>
                                <div class="form-group col-lg-6">
                                    <label for="salary">Salary:</label>
                                    <form:input path="salary" type="number" min="0" class="form-control" value="${updUser.salary}"/>
                                    <form:errors path="salary" cssClass="label label-danger"/>
                                </div>
                            </div>
                            <div class="form-group text-center">
                                <button type="submit" class="btn btn-primary">Update user</button>
                                <a href="/users"><button type="button" class="btn btn-danger">Back to users</button></a>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>