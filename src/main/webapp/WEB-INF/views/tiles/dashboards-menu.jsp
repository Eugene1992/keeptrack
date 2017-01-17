<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="page-wrapper">
    <div class="container-fluid">
        <br>
        <!-- Page Heading -->
        <div class="row">
            <div class="col-lg-12">
                <ol class="breadcrumb">
                    <li class="active">
                        <i class="fa fa-dashboard"></i> Dashboards
                    </li>
                </ol>
            </div>
        </div>
        <!-- /.row -->

        <div class="row">
            <div class="col-lg-3 col-md-6">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <div class="row">
                            <div class="col-xs-3">
                                <i class="fa fa-money fa-5x"></i>
                            </div>
                            <div class="col-xs-9 text-right">
                                <div class="huge">${totalCustomers}</div>
                                <div>Customers</div>
                            </div>
                        </div>
                    </div>
                    <a href="/customers">
                        <div class="panel-footer">
                            <span class="pull-left">View Details</span>
                            <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                            <div class="clearfix"></div>
                        </div>
                    </a>
                </div>
            </div>
            <div class="col-lg-3 col-md-6">
                <div class="panel panel-green">
                    <div class="panel-heading">
                        <div class="row">
                            <div class="col-xs-3">
                                <i class="fa fa-folder-open fa-5x"></i>
                            </div>
                            <div class="col-xs-9 text-right">
                                <div class="huge">${totalProjects}</div>
                                <div>Projects</div>
                            </div>
                        </div>
                    </div>
                    <a href="#">
                        <div class="panel-footer">
                            <span class="pull-left">View Details</span>
                            <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                            <div class="clearfix"></div>
                        </div>
                    </a>
                </div>
            </div>
            <div class="col-lg-3 col-md-6">
                <div class="panel panel-yellow">

                    <div class="panel-heading">
                        <div class="row">
                            <div class="col-xs-3">
                                <i class="fa fa-users fa-5x"></i>
                            </div>
                            <div class="col-xs-9 text-right">
                                <div class="huge">${totalEmployees}</div>
                                <div>Employees</div>
                            </div>
                        </div>
                    </div>
                    <a href="#">
                        <div class="panel-footer">
                            <span class="pull-left">View Details</span>
                            <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                            <div class="clearfix"></div>
                        </div>
                    </a>
                </div>
            </div>
            <div class="col-lg-3 col-md-6">
                <div class="panel panel-red">
                    <div class="panel-heading">
                        <div class="row">
                            <div class="col-xs-3">
                                <i class="fa fa-tasks fa-5x"></i>
                            </div>
                            <div class="col-xs-9 text-right">
                                <div class="huge">${totalTasks}</div>
                                <div>Tasks</div>
                            </div>
                        </div>
                    </div>
                    <a href="#">
                        <div class="panel-footer">
                            <span class="pull-left">View Details</span>
                            <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                            <div class="clearfix"></div>
                        </div>
                    </a>
                </div>
            </div>
        </div>
        <!-- /.row -->

        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title"><i class="fa fa-bar-chart-o fa-fw"></i> Area Chart</h3>
                    </div>
                    <div class="panel-body">
                        <div id="morris-area-chart"></div>
                    </div>
                </div>
            </div>
        </div>
        <!-- /.row -->

        <div class="row">
            <div class="col-lg-4">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title"><i class="fa fa-long-arrow-right fa-fw"></i> Donut Chart</h3>
                    </div>
                    <div class="panel-body">
                        <div id="morris-donut-chart"></div>
                        <div class="text-right">
                            <a href="#">View Details <i class="fa fa-arrow-circle-right"></i></a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-4">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title"><i class="fa fa-long-arrow-right fa-fw"></i> Donut Chart</h3>
                    </div>
                    <div class="panel-body">
                        <div id="morris-donut-chart"></div>
                        <div class="text-right">
                            <a href="#">View Details <i class="fa fa-arrow-circle-right"></i></a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-4">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title"><i class="fa fa-long-arrow-right fa-fw"></i> Donut Chart</h3>
                    </div>
                    <div class="panel-body">
                        <div id="morris-donut-chart"></div>
                        <div class="text-right">
                            <a href="#">View Details <i class="fa fa-arrow-circle-right"></i></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-6">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title"><i class="fa fa-clock-o fa-fw"></i> Tasks Panel</h3>
                    </div>
                    <div class="panel-body">
                        <div class="list-group">
                            <a href="#" class="list-group-item">
                                <span class="badge">just now</span>
                                <i class="fa fa-fw fa-calendar"></i> Calendar updated
                            </a>
                            <a href="#" class="list-group-item">
                                <span class="badge">4 minutes ago</span>
                                <i class="fa fa-fw fa-comment"></i> Commented on a post
                            </a>
                            <a href="#" class="list-group-item">
                                <span class="badge">23 minutes ago</span>
                                <i class="fa fa-fw fa-truck"></i> Order 392 shipped
                            </a>
                            <a href="#" class="list-group-item">
                                <span class="badge">46 minutes ago</span>
                                <i class="fa fa-fw fa-money"></i> Invoice 653 has been paid
                            </a>
                            <a href="#" class="list-group-item">
                                <span class="badge">1 hour ago</span>
                                <i class="fa fa-fw fa-user"></i> A new user has been added
                            </a>
                            <a href="#" class="list-group-item">
                                <span class="badge">2 hours ago</span>
                                <i class="fa fa-fw fa-check"></i> Completed task: "pick up dry cleaning"
                            </a>
                            <a href="#" class="list-group-item">
                                <span class="badge">yesterday</span>
                                <i class="fa fa-fw fa-globe"></i> Saved the world
                            </a>
                            <a href="#" class="list-group-item">
                                <span class="badge">two days ago</span>
                                <i class="fa fa-fw fa-check"></i> Completed task: "fix error on sales page"
                            </a>
                        </div>
                        <div class="text-right">
                            <a href="#">View All Activity <i class="fa fa-arrow-circle-right"></i></a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-6">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title"><i class="fa fa-money fa-fw"></i> Latest Tasks</h3>
                    </div>
                    <div class="panel-body">
                        <div class="table-responsive">
                            <table class="table table-bordered table-hover table-striped">
                                <thead>
                                <tr>
                                    <th>Name</th>
                                    <th>Status</th>
                                    <th>Project</th>
                                    <th>Created</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="task" items="${latestTasks}">
                                    <tr>
                                        <td>${task.name}</td>
                                        <td>${task.status}</td>
                                        <td>${task.sprint.project.name}</td>
                                        <td>${task.startDate}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <div class="text-right">
                            <a href="/tasks">View All Tasks <i class="fa fa-arrow-circle-right"></i></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>