<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div id="page-wrapper">
    <div class="container-fluid">
        <div class="row">
            <div class="alert alert-success" role="alert">
                <span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span>
                Choose report type and press generate button:
            </div>
            <div class="col-lg-12">
                <div class="col-lg-6">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                        <span class="panel-title">
                            Project report
                        </span>
                        </div>
                        <div class="panel-body">
                            <form action="project/report" method="GET">
                                <div class="form-group col-lg-12">
                                    <label for="selectedProject">Select project: </label>
                                    <select class="form-control" id="selectedProject" name="name">
                                        <c:forEach var="project" items="${projects}">
                                            <option value="${project.name}">${project.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group col-lg-12">
                                    <p>Select the contents of the report:</p>
                                    <div class="checkbox">
                                        <label><input type="checkbox" value="">Employees</label>
                                    </div>
                                    <div class="checkbox">
                                        <label><input type="checkbox" value="">Sprints</label>
                                    </div>
                                    <div class="checkbox">
                                        <label><input type="checkbox" value="">Tasks</label>
                                    </div>
                                </div>
                                <button type="submit" class="btn btn-primary center-block">Generate</button>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                        <span class="panel-title">
                            Project report
                        </span>
                        </div>
                        <div class="panel-body">
                            <form action="employee/report" method="GET">
                                <div class="form-group col-lg-12">
                                    <label for="selectedProject">Select employee: </label>
                                    <select class="form-control" id="selectedEmployee" name="username">
                                        <c:forEach var="employee" items="${employees}">
                                            <option value="${employee.username}">${employee.firstName} ${employee.lastName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group col-lg-6">
                                    <label for="fromDate">From:</label>
                                    <input type="date" name="fromDate" class="form-control" id="fromDate" required>
                                </div>
                                <div class="form-group col-lg-6">
                                    <label for="toDate">To:</label>
                                    <input type="date" name="toDate" class="form-control" id="toDate" required>
                                </div>
                                <button type="submit" class="btn btn-primary center-block">Generate</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>