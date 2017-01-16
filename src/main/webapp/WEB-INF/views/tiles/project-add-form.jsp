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
                            Add new project
                        </span>
                    </div>
                    <div class="panel-body">
                        <form:form action="/addProject" method="POST" modelAttribute="project">
                            <div class="form-group col-lg-12 outer">
                                <div class="form-group col-lg-6">
                                    <label for="name">Title:</label>
                                    <form:input path="name" type="text" class="form-control" id="name" pattern="[a-zA-Z]{2,20}" />
                                    <form:errors path="name" cssClass="label label-danger"/>
                                </div>
                                <div class="form-group col-lg-6">
                                    <label for="manager">Project manager:</label>
                                    <form:select class="form-control" path="manager" id="manager">
                                        <c:forEach var="manager" items="${freeManagers}">
                                            <form:option value="${manager.id}" label="${manager.firstName} ${manager.lastName}"/>
                                        </c:forEach>
                                    </form:select>
                                    <form:errors path="manager" cssClass="label label-danger"/>
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
                                    <p><b>Note:</b> You can add employees, sprints, tasks after project creation</p>
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