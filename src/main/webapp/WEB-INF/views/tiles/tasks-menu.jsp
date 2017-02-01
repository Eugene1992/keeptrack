<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div id="page-wrapper">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12"><br>
                <div class="panel panel-primary">
                    <div class="panel-heading" style="height: 42px;">
                        <span class="panel-title">
                            Tasks list
                        </span>
                        <button type="button" class="btn btn-success btn-sm pull-right" data-toggle="modal"
                                data-target="#newTaskModal">New Task
                        </button>
                    </div>
                    <div class="panel-body">
                        <table class="table table-bordered table-hover" id="tasks-table">
                            <thead>
                            <tr>
                                <th>Name</th>
                                <th>Project</th>
                                <th>Sprint</th>
                                <th>Status</th>
                                <th>Start date</th>
                                <th>End date</th>
                                <th>Estimate</th>
                                <th>Creator</th>
                                <th>Assigner</th>
                                <th>Update</th>
                                <th>Delete</th>
                                <th>Details</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${tasksList}" var="dto">
                                <tr>
                                    <td>${dto.name}</td>
                                    <td>${dto.sprint.project.name}</td>
                                    <td>${dto.sprint.name}</td>
                                    <td>${dto.status}</td>
                                    <td>${dto.startDate}</td>
                                    <td>${dto.endDate}</td>
                                    <td>${dto.estimate}</td>
                                    <td>${dto.creator.firstName} ${dto.creator.lastName}</td>
                                    <td>${dto.assigner.firstName} ${dto.assigner.lastName}</td>
                                    <td>
                                        <form action="/tasks/update/${dto.id}" method="POST">
                                            <button type="submit" class="btn btn-warning btn-xs">Update</button>
                                        </form>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-danger btn-xs" data-toggle="modal" data-target="#confirm-drop-task-${dto.id}">Delete</button>
                                    </td>
                                    <td>
                                        <a href="task/${dto.name}"><button type="button" class="btn btn-success btn-xs">Profile</button></a>
                                    </td>
                                </tr>
                                <div class="modal fade" role="dialog" id="confirm-drop-task-${dto.id}">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-body text-center">
                                                <h4>Do you really want delete ${dto.name}?</h4>
                                            </div>
                                            <div class="modal-footer">
                                                <form action="/tasks/delete" method="POST" >
                                                    <input type="hidden" class="form-control" name="id" value="${dto.id}" />
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
        <!-- New Sprint Modal -->
        <div class="modal fade" id="newTaskModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header" style="background-color: #337AB7">
                        <h4 class="modal-title">Create new task</h4>
                    </div>
                    <div class="modal-body">
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
                                    <form:input path="estimate" type="number" min="1" class="form-control"/>
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