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
                            Projects list
                        </span>
                        <button type="button" class="btn btn-success btn-sm pull-right" data-toggle="modal"
                                data-target="#newProjectModal">New Project</button>
                    </div>
                    <div class="panel-body">
                        <table class="table table-bordered table-hover" id="projects-table">
                            <thead>
                            <tr>
                                <th>Title</th>
                                <th>Start date</th>
                                <th>End date</th>
                                <th>Status</th>
                                <th>Manager</th>
                                <th>Employees</th>
                                <th>Sprints</th>
                                <th>Update</th>
                                <th>Delete</th>
                                <th>Details</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="project" items="${projects}">
                                <tr>
                                    <td>${project.name}</td>
                                    <td>${project.startDate}</td>
                                    <td>${project.endDate}</td>
                                    <td>${project.status}</td>
                                    <td>${project.manager.firstName} ${project.manager.lastName}</td>
                                    <td>${fn:length(project.users)}
                                    <td>${fn:length(project.sprints)}</td>
                                    <td>
                                        <form action="/projects/update/${project.id}" method="POST">
                                            <button type="submit" class="btn btn-warning btn-xs">Update</button>
                                        </form>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-danger btn-xs" data-toggle="modal" data-target="#confirm-drop-project-${project.id}">Delete</button>
                                    </td>
                                    <td>
                                        <a href="/project/${project.name}"><button type="button" class="btn btn-success btn-xs">Details</button></a>
                                    </td>
                                </tr>
                                <div class="modal fade" role="dialog" id="confirm-drop-project-${project.id}">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-body text-center">
                                                <h4>Do you really want delete ${project.name}?</h4>
                                            </div>
                                            <div class="modal-footer">
                                                <form action="/projects/delete" method="POST" >
                                                    <input type="hidden" class="form-control" name="id" value="${project.id}" />
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
        <!-- New Project Modal -->
        <div class="modal fade" id="newProjectModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header" style="background-color: #337AB7">
                        <h4 class="modal-title">Create new project</h4>
                    </div>
                    <div class="modal-body">
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
                                <div class="form-group col-lg-4">
                                    <label for="name">Start date:</label>
                                    <form:input path="startDate" type="date" class="form-control" id="name" />
                                    <form:errors path="startDate" cssClass="label label-danger"/>
                                </div>
                                <div class="form-group col-lg-4">
                                    <label for="name">End date:</label>
                                    <form:input path="endDate" type="date" class="form-control" id="name" />
                                    <form:errors path="endDate" cssClass="label label-danger"/>
                                </div>
                                <div class="form-group col-lg-4">
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
                                    <form:textarea path="description" id="description" rows="5" cssStyle="width: 100%"/>
                                    <form:errors path="description" cssClass="label label-danger"/>
                                </div>
                            </div>
                            <div class="form-group col-lg-12 outer">
                                <div class="form-group col-lg-12">
                                    <label for="employees">Employees:</label>
                                    <form:select class="form-control" path="employees" id="employees" size="6">
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
                                <button type="button" class="btn btn-danger" data-dismiss="modal">Close menu</button>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>