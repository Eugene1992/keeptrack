<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
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
        <a class="navbar-brand" href="/"><i class="fa fa-clock-o"></i> KeepTrack</a>
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
                <i class="fa fa-user"></i> ${user.firstName} ${user.lastName} <b class="caret"></b>
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
                    <a href="j_spring_security_logout"><i class="fa fa-fw fa-power-off"></i> Log Out</a>
                </li>
            </ul>
        </li>
    </ul>
    <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
    <div class="collapse navbar-collapse navbar-ex1-collapse">
        <ul class="nav navbar-nav side-nav">
            <security:authorize access="hasAnyRole('EMPLOYEE', 'CUSTOMER', 'PM')">
                <li class="active">
                    <a href="/dashboards"><i class="fa fa-fw fa-dashboard"></i> Dashboard</a>
                </li>
            </security:authorize>
            <security:authorize access="hasAnyRole('EMPLOYEE', 'CUSTOMER', 'PM')">
                <li>
                    <a href="/project"><i class="fa fa-fw fa-edit"></i> Project</a>
                </li>
            </security:authorize>
            <security:authorize access="hasRole('ADMIN')">
            <li>
                <a href="/projects"><i class="fa fa-fw fa-edit"></i> Projects</a>
            </li>
            <li>
                <a href="/users"><i class="fa fa-fw fa-user"></i> Users</a>
            </li>
            <li>
                <a href="/sprints"><i class="fa fa-fw fa-tasks"></i> Sprints</a>
            </li>
            <li>
                <a href="/tasks"><i class="fa fa-fw fa-wrench"></i> Tasks</a>
            </li>
            </security:authorize>
        </ul>
    </div>
</nav>