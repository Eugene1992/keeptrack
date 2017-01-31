<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<div id="page-wrapper">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-offset-2 col-lg-8"><br>
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">${user.firstName} ${user.lastName}</h3>

                    </div>
                    <div class="panel-body">
                        <table class="table table-user-information ">
                            <tbody>
                            <tr>
                                <td><b>First name:</b></td>
                                <td>${user.firstName}</td>
                            </tr>
                            <tr>
                                <td><b>Last name:</b></td>
                                <td>${user.lastName}</td>
                            </tr>
                            <tr>
                                <td><b>Gender:</b></td>
                                <td>${user.gender}</td>
                            </tr>
                            <tr>
                                <td><b>Role:</b></td>
                                <td>${user.role}</td>
                            </tr>
                            <tr>
                                <td><b>Project:</b></td>
                                <td>
                                    ${user.project.name}
                                    <security:authorize access="hasRole('ADMIN')">
                                    <a href="/project/${user.project.name}">
                                        <button class="btn btn-xs btn-success pull-right">
                                            <i class="glyphicon glyphicon-share"></i>
                                        </button>
                                    </a>
                                    </security:authorize>
                                </td>
                            </tr>
                            <security:authorize access="hasRole('ADMIN')">
                            <tr>
                                <td><b>Tasks:</b></td>
                                <td>
                                    <c:choose>
                                        <c:when test="${user.role eq 'PM'}">${fn:length(user.createdTasks)}</c:when>
                                        <c:when test="${user.role eq 'EMPLOYEE'}">${fn:length(user.assignedTasks)}</c:when>
                                    </c:choose>
                                </td>
                            </tr>
                            <tr>
                                <td><b>Username:</b></td>
                                <td>${user.username}</td>
                            </tr>
                            <tr>
                                <td><b>Password:</b></td>
                                <td>${user.password}</td>
                            </tr>
                            <tr>
                                <td><b>Email:</b></td>
                                <td>${user.email}</td>
                            </tr>
                            <tr>
                                <td><b>Birthday:</b></td>
                                <td>${user.birthday}</td>
                            </tr>
                            <tr>
                                <td><b>Hire day:</b></td>
                                <td>${user.hireDay}</td>
                            </tr>
                            </security:authorize>
                            </tbody>
                        </table>
                        <security:authorize access="hasRole('ADMIN')">
                        <a href="/users"><button class="btn btn-sm btn-primary pull-right">Back to users</button></a>
                        </security:authorize>
                        <p><i>Tip: full information about the user is available only to the administrator.</i></p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>