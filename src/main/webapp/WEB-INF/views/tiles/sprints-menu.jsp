<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div id="page-wrapper">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12"><br>
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <span class="panel-title">
                            Sprints list
                        </span>
                        <button type="button" class="btn btn-success btn-sm pull-right" data-toggle="modal"
                                data-target="#newSprintModal">New Sprint
                        </button>
                    </div>
                    <div class="panel-body">
                        <table class="table table-bordered table-hover" id="sprints-table">
                            <thead>
                            <tr>
                                <th>Name</th>
                                <th>Project</th>
                                <th>Status</th>
                                <th>Start date</th>
                                <th>End date</th>
                                <th>Description</th>
                                <th>Tasks</th>
                                <th>Update</th>
                                <th>Delete</th>
                                <th>Details</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${sprintsList}" var="sprint">
                                <jsp:useBean id="sprint" scope="page" type="com.netcracker.keeptrack.model.Sprint"/>
                                <tr>
                                    <td>${sprint.name}</td>
                                    <td>${sprint.project.name}</td>
                                    <td>${sprint.status}</td>
                                    <td>${sprint.startDate}</td>
                                    <td>${sprint.endDate}</td>
                                    <td>${sprint.description}</td>
                                    <td>${fn:length(sprint.tasks)}</td>
                                    <td>
                                        <form action="/sprints/update/${sprint.id}" method="POST">
                                            <button type="submit" class="btn btn-warning btn-xs">Update</button>
                                        </form>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-danger btn-xs" data-toggle="modal" data-target="#confirm-drop-sprint-${sprint.id}">Delete</button>                                    </td>
                                    </td>
                                    <td>
                                        <a href="sprint/${sprint.name}"><button type="button" class="btn btn-success btn-xs">Profile</button></a>
                                    </td>
                                </tr>
                                <div class="modal fade" role="dialog" id="confirm-drop-sprint-${sprint.id}">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-body text-center">
                                                <h4>Do you really want delete ${sprint.name}?</h4>
                                            </div>
                                            <div class="modal-footer">
                                                <form action="/sprints/delete" method="POST" >
                                                    <input type="hidden" class="form-control" name="id" value="${sprint.id}" />
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
        <div class="modal fade" id="newSprintModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header" style="background-color: #337AB7">
                        <h4 class="modal-title">Create new sprint</h4>
                    </div>
                    <div class="modal-body">
                        <form:form action="/sprints/add" method="POST" modelAttribute="sprint" >
                            <div class="form-group col-lg-12 outer">
                                <div class="form-group col-lg-4">
                                    <label for="name">Title:</label>
                                    <form:input path="name" type="text" class="form-control"/>
                                    <form:errors path="name" cssClass="label label-danger"/>
                                </div>
                                <div class="form-group form-group-sm col-md-4">
                                    <label for="status">Status:</label>
                                    <form:select path="status" class="form-control custom-select-height">
                                        <form:option value="CREATED" />
                                        <form:option value="IN_PROGRESS" />
                                        <form:option value="CLOSED" />
                                    </form:select>
                                    <form:errors path="status" cssClass="label label-danger"/>
                                </div>
                                <div class="form-group form-group-sm col-lg-4">
                                    <label for="projectId">Project:</label>
                                    <form:select path="projectId" class="form-control custom-select-height">
                                        <c:forEach var="project" items="${projects}">
                                            <form:option value="${project.id}" label="${project.name}"/>
                                        </c:forEach>
                                    </form:select>
                                    <form:errors path="projectId" cssClass="label label-danger"/>
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
                                <div class="form-group col-lg-12">
                                    <p><b>Description: </b></p>
                                    <form:textarea path="description" rows="5" cssStyle="width: 100%"/>
                                    <form:errors path="description" cssClass="label label-danger"/>
                                </div>
                            </div>
                            <div class="form-group text-center">
                                <button type="submit" class="btn btn-primary">Create sprint</button>
                                <button type="button" class="btn btn-danger" data-dismiss="modal">Close menu</button>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>