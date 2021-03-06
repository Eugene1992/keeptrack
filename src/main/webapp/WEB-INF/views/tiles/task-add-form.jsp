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
                    <div class="panel-body">
                        <form:form action="/tasks/add" method="POST" modelAttribute="task" >
                            <div class="form-group col-lg-12 outer">
                                <div class="form-group col-lg-6">
                                    <label for="name">Title:</label>
                                    <form:input path="name" type="text" class="form-control"/>
                                    <form:errors path="name" cssClass="label label-danger"/>
                                </div>
                                <div class="form-group form-group-sm col-lg-6">
                                    <label for="sprintId">Sprint/Project:</label>
                                    <form:select path="sprintId" class="form-control custom-select-height">
                                        <c:forEach var="sprint" items="${allSprints}">
                                            <form:option value="${sprint.id}" label="${sprint.name} / ${sprint.project.name}"/>
                                        </c:forEach>
                                    </form:select>
                                    <form:errors path="sprintId" cssClass="label label-danger"/>
                                </div>
                            </div>
                            <div class="form-group col-lg-12 outer">
                                <div class="form-group col-lg-6">
                                    <label for="startDate">Start date:</label>
                                    <form:input path="startDate" type="date" class="form-control"/>
                                    <form:errors path="startDate" cssClass="label label-danger"/>
                                </div>
                                <div class="form-group col-lg-6">
                                    <label for="endDate">End date:</label>
                                    <form:input path="endDate" type="date" class="form-control"/>
                                    <form:errors path="endDate" cssClass="label label-danger"/>
                                </div>

                            </div>
                            <div class="form-group col-lg-12 outer">
                                <div class="form-group col-lg-6">
                                    <label for="estimate">Estimate:</label>
                                    <form:input path="estimate" min="1" type="number" class="form-control"/>
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
                                    <label for="assignerId">Assigner:</label>
                                    <form:select path="assignerId" class="form-control custom-select-height">
                                        <c:forEach var="employee" items="${allEmployees}">
                                            <form:option value="${employee.id}" label="${employee.firstName} ${employee.lastName}"/>
                                        </c:forEach>
                                    </form:select>
                                    <form:errors path="assignerId" cssClass="label label-danger"/>
                                </div>
                                <div class="form-group col-lg-6">
                                    <label for="creatorId">Creator:</label>
                                    <form:select path="creatorId" class="form-control custom-select-height">
                                        <c:forEach var="manager" items="${allManagers}">
                                            <form:option value="${manager.id}" label="${manager.firstName} ${manager.lastName}"/>
                                        </c:forEach>
                                    </form:select>
                                    <form:errors path="creatorId" cssClass="label label-danger"/>
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