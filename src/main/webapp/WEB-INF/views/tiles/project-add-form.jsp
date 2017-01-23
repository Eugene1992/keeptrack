<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div id="page-wrapper">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-offset-2 col-lg-8">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <span class="panel-title">
                            Create new project
                        </span>
                    </div>
                    <div class="panel-body">
                        <form:form action="/projects/add" method="POST" modelAttribute="project">
                            <div class="form-group col-lg-12 outer">
                                <div class="form-group col-lg-6">
                                    <label for="name">Title:</label>
                                    <form:input path="name" type="text" class="form-control" id="name" />
                                    <form:errors path="name" cssClass="label label-danger"/>
                                </div>
                                <div class="form-group col-lg-6">
                                    <label for="managerId">Project manager:</label>
                                    <form:select class="form-control" path="managerId" id="managerId">
                                        <c:forEach var="manager" items="${freeManagers}">
                                            <form:option value="${manager.id}" label="${manager.firstName} ${manager.lastName}"/>
                                        </c:forEach>
                                    </form:select>
                                    <form:errors path="managerId" cssClass="label label-danger"/>
                                </div>
                            </div>
                            <div class="form-group col-lg-12 outer">
                                <div class="form-group col-lg-6">
                                    <label for="name">Start date:</label>
                                    <form:input path="startDate" type="date" class="form-control" id="name" />
                                    <form:errors path="startDate" cssClass="label label-danger"/>
                                </div>
                                <div class="form-group col-lg-6">
                                    <label for="name">End date:</label>
                                    <form:input path="endDate" type="date" class="form-control" id="name" />
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
                                        <c:forEach var="employee" items="${freeEmployees}">
                                            <form:option value="${employee.id}" label="${employee.firstName} ${employee.lastName}"/>
                                        </c:forEach>
                                    </form:select>
                                    <form:errors path="employees" cssClass="label label-danger"/>
                                </div>
                            </div>
                            <div class="form-group col-lg-12 outer">
                                <div class="form-group col-lg-12">
                                    <p><b>Note:</b> You can add sprints, tasks after project creation</p>
                                </div>
                            </div>
                            <div class="form-group text-center">
                                <button type="submit" class="btn btn-primary">Create project</button>
                                <a href="/projects"><button type="button" class="btn btn-danger">Back to projects</button></a>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>