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
                            Update project
                        </span>
                    </div>
                    <div class="panel-body">
                        <form:form action="/projects/update" method="POST" modelAttribute="project">
                            <form:hidden path="id" class="form-control" value="${updProject.id}"/>
                            <div class="form-group col-lg-12 outer">
                                <div class="form-group col-lg-6">
                                    <label for="name">Title:</label>
                                    <form:input path="name" type="text" class="form-control" id="name" value="${updProject.name}"/>
                                    <form:errors path="name" cssClass="label label-danger"/>
                                </div>
                                <div class="form-group col-lg-6">
                                    <label for="managerId">Project manager:</label>
                                    <form:select class="form-control" path="managerId" id="managerId">
                                        <c:forEach var="manager" items="${allManagers}">
                                            <c:choose>
                                                <c:when test="${updProject.manager.id eq manager.id}">
                                                    <option value="${manager.id}" selected>${manager.firstName} ${manager.lastName}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${manager.id}">${manager.firstName} ${manager.lastName}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </form:select>
                                    <form:errors path="managerId" cssClass="label label-danger"/>
                                </div>
                            </div>
                            <div class="form-group col-lg-12 outer">
                                <div class="form-group col-lg-6">
                                    <label for="name">Start date:</label>
                                    <form:input path="startDate" type="date" class="form-control" value="${updProject.startDate}"/>
                                    <form:errors path="startDate" cssClass="label label-danger"/>
                                </div>
                                <div class="form-group col-lg-6">
                                    <label for="name">End date:</label>
                                    <form:input path="endDate" type="date" class="form-control" value="${updProject.endDate}"/>
                                    <form:errors path="endDate" cssClass="label label-danger"/>
                                </div>
                            </div>
                            <div class="form-group col-lg-12 outer">
                                <div class="form-group col-lg-6">
                                    <label for="status">Status:</label>
                                    <form:select class="form-control" path="status" id="status">
                                        <form:option value="CREATED" label="CREATED"/>
                                        <form:option value="IN_PROGRESS" label="IN_PROGRESS"/>
                                        <form:option value="CLOSED" label="CLOSED"/>
                                    </form:select>
                                    <form:errors path="status" cssClass="label label-danger"/>
                                </div>
                            </div>
                            <div class="form-group col-lg-12 outer">
                                <div class="form-group col-lg-12">
                                    <p><b>Description: </b></p>
                                    <form:textarea path="description" id="description" rows="5" cssClass="text-area-margin"/>
                                    <form:errors path="description" cssClass="label label-danger"/>
                                </div>
                            </div>
                            <div class="form-group col-lg-12 outer">
                                <div class="form-group col-lg-12">
                                    <label for="employees">Employees:</label>
                                    <form:select class="form-control" path="employees" id="employees" size="10">
                                        <c:forEach var="selectedEmployee" items="${updProject.users}">
                                            <c:forEach var="employee" items="${allEmployees}">
                                                <c:choose>
                                                    <c:when test="${selectedEmployee.id eq employee.id}">
                                                        <option value="${employee.id}" selected>${employee.firstName} ${employee.lastName}</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="${employee.id}">${employee.firstName} ${employee.lastName}</option>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </c:forEach>
                                    </form:select>
                                    <form:errors path="employees" cssClass="label label-danger"/>
                                </div>
                            </div>
                            <div class="form-group col-lg-12 outer">
                                <div class="form-group col-lg-12">
                                    <p><b>Note:</b> You can add employees, sprints, tasks after project creation</p>
                                </div>
                            </div>
                            <div class="form-group text-center">
                                <button type="submit" class="btn btn-primary">Update project</button>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>