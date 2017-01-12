
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" ng-app="mainApp">
<head>
    <meta charset="UTF-8">
    <title>KeepTrack</title>

    <!-- Bootstrap Core CSS -->
    <link href="../../../resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="../../../resources/css/sb-admin.css" rel="stylesheet">

    <!-- isteven multi select -->
    <link href="../../../resources/css/isteven-multi-select.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="../../../resources/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- Morris Charts CSS -->
    <link href="../../../resources/css/plugins/morris.css" rel="stylesheet">
</head>
<body ng-controller="MainCtrl">
<div id="wrapper">
    <!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="index.html"><i class="fa fa-clock-o"></i> KeepTrack</a>
        </div>
        <!-- Top Menu Items -->
        <ul class="nav navbar-right top-nav">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    <i class="fa fa-bell"></i> <b class="caret"></b>
                </a>
                <ul class="dropdown-menu alert-dropdown">
                    <li>
                        <a href="#">Alert Name <span class="label label-default">Alert Badge</span></a>
                    </li>
                    <li>
                        <a href="#">Alert Name <span class="label label-primary">Alert Badge</span></a>
                    </li>
                    <li>
                        <a href="#">Alert Name <span class="label label-success">Alert Badge</span></a>
                    </li>
                    <li>
                        <a href="#">Alert Name <span class="label label-info">Alert Badge</span></a>
                    </li>
                    <li>
                        <a href="#">Alert Name <span class="label label-warning">Alert Badge</span></a>
                    </li>
                    <li>
                        <a href="#">Alert Name <span class="label label-danger">Alert Badge</span></a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a href="#">View All</a>
                    </li>
                </ul>
            </li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    <i class="fa fa-user"></i> Evgeniy Deyneka <b class="caret"></b>
                </a>
                <ul class="dropdown-menu">
                    <li>
                        <a href="#"><i class="fa fa-fw fa-user"></i> Profile</a>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-fw fa-envelope"></i> Inbox</a>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-fw fa-gear"></i> Settings</a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a href="#"><i class="fa fa-fw fa-power-off"></i> Log Out</a>
                    </li>
                </ul>
            </li>
        </ul>
        <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
        <div class="collapse navbar-collapse navbar-ex1-collapse">
            <ul class="nav navbar-nav side-nav">
                <li class="active">
                    <a href="index.html"><i class="fa fa-fw fa-dashboard"></i> Dashboard</a>
                </li>
                <li>
                    <a href="#"><i class="fa fa-fw fa-bar-chart-o"></i> Statistic</a>
                </li>
                <li>
                    <a href="#"><i class="fa fa-fw fa-money"></i> Customers</a>
                </li>
                <li ng-click="changeView('projects')">
                    <a href="#"><i class="fa fa-fw fa-edit"></i> Projects</a>
                </li>
                <li ng-click="changeView('employees')">
                    <a href="#"><i class="fa fa-fw fa-user"></i> Employees</a>
                </li>
                <li>
                    <a href="#"><i class="fa fa-fw fa-wrench"></i> Tasks</a>
                </li>
                <li>
                    <a data-toggle="collapse" data-target="#demo">
                        <i class="fa fa-fw fa-arrows-v"></i> Dropdown <i class="fa fa-fw fa-caret-down"></i>
                    </a>
                    <ul id="demo" class="collapse">
                        <li>
                            <a href="#">Dropdown Item</a>
                        </li>
                        <li>
                            <a href="#">Dropdown Item</a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </nav>
</div>

<!-- isteven multi select -->
<%--<script src="../../../resources/js/libs/isteven-multi-select.js"></script>--%>

<!-- jQuery -->
<script src="../../../resources/js/jquery.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="../../../resources/js/bootstrap.min.js"></script>

<!-- Morris Charts JavaScript -->
<%--<script src="../../../resources/js/plugins/morris/raphael.min.js"></script>
<script src="../../../resources/js/plugins/morris/morris.min.js"></script>
<script src="../../../resources/js/plugins/morris/morris-data.js"></script>--%>
</body>
</html>
