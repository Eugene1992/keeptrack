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
                            Add new sprint
                        </span>
                    </div>
                    <div class="panel-body">
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
                                    <form:textarea path="description" rows="5" cssClass="text-area-margin"/>
                                    <form:errors path="description" cssClass="label label-danger"/>
                                </div>
                            </div>
                            <div class="form-group text-center">
                                <button type="submit" class="btn btn-primary">Create sprint</button>
                                <a href="/sprints"><button type="button" class="btn btn-danger">Cancel</button></a>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>