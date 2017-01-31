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
                            Create new task
                        </span>
                    </div>
                    <c:set var="project" value="${user.role == 'PM' ? user.managedProject : user.project}"/>
                    <div class="panel-body">
                        <form:form action="/project/tasks/add" method="POST" modelAttribute="task" >
                            <div class="form-group col-lg-12 outer">
                                <form:hidden path="creatorId" value="${user.id}" class="form-control"/>
                                <form:hidden path="status" value="CREATED" class="form-control"/>
                                <div class="form-group col-lg-6">
                                    <label for="name">Title:</label>
                                    <form:input path="name" type="text" class="form-control"/>
                                    <form:errors path="name" cssClass="label label-danger"/>
                                </div>
                                <div class="form-group form-group-sm col-lg-6">
                                    <label for="sprintId">Sprint:</label>
                                    <form:select path="sprintId" class="form-control custom-select-height">
                                        <c:forEach var="sprint" items="${project.sprints}">
                                            <c:choose>
                                                <c:when test="${sprint.status != 'CLOSED'}">
                                                    <form:option value="${sprint.id}" label="${sprint.name}"/>                                                </c:when>
                                            </c:choose>
                                        </c:forEach>
                                    </form:select>
                                    <form:errors path="sprintId" cssClass="label label-danger"/>
                                </div>
                            </div>
                            <div class="form-group col-lg-12 outer">
                                <div class="form-group col-lg-6">
                                    <label for="assignerId">Assigner:</label>
                                    <form:select path="assignerId" class="form-control custom-select-height">
                                        <c:forEach var="employee" items="${project.users}">
                                            <c:choose>
                                                <c:when test="${employee.role == 'EMPLOYEE'}">
                                                    <form:option value="${employee.id}" label="${employee.firstName} ${employee.lastName}"/>
                                                </c:when>
                                            </c:choose>
                                        </c:forEach>
                                    </form:select>
                                    <form:errors path="assignerId" cssClass="label label-danger"/>
                                </div>
                                <div class="form-group col-lg-6">
                                    <label for="estimate">Estimate:</label>
                                    <form:input path="estimate" min="1" type="number" class="form-control"/>
                                    <form:errors path="estimate" cssClass="label label-danger"/>
                                </div>
                            </div>
                            <div class="form-group col-lg-12 outer">
                                <div class="form-group col-lg-6">
                                    <label for="startDate">Start date:</label>
                                    <form:input path="startDate" type="date" class="form-control"/>
                                    <form:errors path="startDate" cssClass="label label-danger"/>
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