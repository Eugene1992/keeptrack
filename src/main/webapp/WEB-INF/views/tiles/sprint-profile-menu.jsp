<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div id="page-wrapper">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-offset-2 col-lg-8"><br>
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">${sprint.name}</h3>

                    </div>
                    <div class="panel-body">
                        <table class="table table-user-information ">
                            <tbody>
                            <tr>
                                <td><b>Name:</b></td>
                                <td>${sprint.name}</td>
                            </tr>
                            <tr>
                                <td><b>Project:</b></td>
                                <td>${sprint.project.name}</td>
                            </tr>
                            <tr>
                                <td><b>Project managerId:</b></td>
                                <td>${sprint.project.manager.firstName} ${sprint.project.manager.lastName}</td>
                            </tr>
                            <tr>
                                <td><b>Status:</b></td>
                                <td>${sprint.status}</td>
                            </tr>
                            <tr>
                            <tr>
                                <td><b>Start date:</b></td>
                                <td>${sprint.startDate}</td>
                            </tr>
                            <tr>
                                <td><b>End date:</b></td>
                                <td>${sprint.endDate}</td>
                            </tr>
                            <tr>
                                <td><b>Description:</b></td>
                                <td>${sprint.description}</td>
                            </tr>
                            <tr>
                                <td><b>Tasks</b></td>
                                <td>
                                    ${fn:length(sprint.tasks)}
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>