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
                            Update task
                        </span>
                    </div>
                    <div class="panel-body">
                        <form:form action="/tasks/update" method="POST" modelAttribute="task" >
                            <div class="form-group col-lg-12 outer">
                                <form:hidden path="id" value="${updTask.id}" class="form-control"/>
                                <div class="form-group col-lg-6">
                                    <label for="name">Title:</label>
                                    <form:input path="name" type="text" class="form-control" value="${updTask.name}"/>
                                    <form:errors path="name" cssClass="label label-danger"/>
                                </div>
                                <div class="form-group form-group-sm col-lg-6">
                                    <label for="sprintId">Sprint/Project:</label>
                                    <form:select path="sprintId" class="form-control custom-select-height">
                                        <c:forEach var="sprint" items="${allSprints}">
                                            <c:choose>
                                                <c:when test="${sprint.id eq updTask.sprint.id}">
                                                    <option value="${sprint.id}" selected>${sprint.name} / ${sprint.project.name}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${sprint.id}">${sprint.name} / ${sprint.project.name}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </form:select>
                                    <form:errors path="sprintId" cssClass="label label-danger"/>
                                </div>
                            </div>
                            <div class="form-group col-lg-12 outer">
                                <div class="form-group col-lg-6">
                                    <label for="startDate">Start date:</label>
                                    <form:input path="startDate" type="date" class="form-control" value="${updTask.startDate}"/>
                                    <form:errors path="startDate" cssClass="label label-danger"/>
                                </div>
                                <div class="form-group col-lg-6">
                                    <label for="endDate">End date:</label>
                                    <form:input path="endDate" type="date" class="form-control" value="${updTask.endDate}"/>
                                    <form:errors path="endDate" cssClass="label label-danger"/>
                                </div>

                            </div>
                            <div class="form-group col-lg-12 outer">
                                <div class="form-group col-lg-6">
                                    <label for="estimate">Estimate:</label>
                                    <form:input path="estimate" type="number" min="1" class="form-control" value="${updTask.estimate}"/>
                                    <form:errors path="estimate" cssClass="label label-danger"/>
                                </div>
                                <div class="form-group form-group-sm col-md-6">
                                    <label for="status">Status:</label>
                                    <form:select path="status" class="form-control custom-select-height">
                                        <form:option value="CREATED" />
                                        <form:option value="OPEN" />
                                        <form:option value="IN_PROGRESS" />
                                        <form:option value="RESOLVED" />
                                        <form:option value="CLOSED" />
                                    </form:select>
                                    <form:errors path="status" cssClass="label label-danger"/>
                                </div>
                            </div>
                            <div class="form-group col-lg-12 outer">
                                <div class="form-group col-lg-6">
                                    <label for="creatorId">Creator:</label>
                                    <form:select path="creatorId" class="form-control custom-select-height">
                                        <c:forEach var="manager" items="${allManagers}">
                                            <c:choose>
                                                <c:when test="${manager.id eq updTask.creator.id}">
                                                    <option value="${manager.id}" selected>${manager.firstName} ${manager.lastName}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${manager.id}">${manager.firstName} ${manager.lastName}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </form:select>
                                    <form:errors path="creatorId" cssClass="label label-danger"/>
                                </div>
                                <div class="form-group col-lg-6">
                                    <label for="assignerId">Assigner:</label>
                                    <form:select path="assignerId" class="form-control custom-select-height">
                                        <c:forEach var="employee" items="${allEmployees}">
                                            <c:choose>
                                                <c:when test="${employee.id eq updTask.assigner.id}">
                                                    <option value="${employee.id}" selected>${employee.firstName} ${employee.lastName}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${employee.id}">${employee.firstName} ${employee.lastName}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </form:select>
                                    <form:errors path="assignerId" cssClass="label label-danger"/>
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
                                <button type="submit" class="btn btn-primary">Update task</button>
                                <a href="/tasks"><button type="button" class="btn btn-danger" data-dismiss="modal">Back to tasks</button></a>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>