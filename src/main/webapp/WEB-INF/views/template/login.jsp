<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>KeepTrack</title>

    <!-- Bootstrap Core CSS -->
    <link href="${param.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="${param.contextPath}/resources/css/sb-admin.css" rel="stylesheet">

    <!-- isteven multi select -->
    <link href="${param.contextPath}/resources/css/isteven-multi-select.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="${param.contextPath}/resources/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- Morris Charts CSS -->
    <link href="${param.contextPath}/resources/css/plugins/morris.css" rel="stylesheet">
</head>
<body>
<div class="container" style="margin-top:40px">
    <div class="row">
        <div class="col-sm-6 col-md-4 col-md-offset-4">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <strong> Sign in to continue</strong>
                </div>
                <div class="panel-body">
                    <form role="form" action="j_spring_security_check" method="POST">
                        <fieldset>
                            <div class="row">
                                    <img class="profile-img center-block"
                                         src="https://ssl.gstatic.com/images/icons/material/product/1x/avatar_circle_blue_120dp.png" alt=""><br>
                            </div>
                            <div class="row">
                                <div class="col-sm-12 col-md-10  col-md-offset-1 ">
                                    <div class="form-group">
                                        <div class="input-group">
												<span class="input-group-addon">
													<i class="glyphicon glyphicon-user"></i>
												</span>
                                            <input class="form-control" placeholder="Username" name="user_name" type="text" autofocus>
                                        </div>
                                        <c:if test="${not empty error}">
                                            <span class="label label-danger">${error}</span>
                                        </c:if>
                                    </div>
                                    <div class="form-group">
                                        <div class="input-group">
												<span class="input-group-addon">
													<i class="glyphicon glyphicon-lock"></i>
												</span>
                                            <input class="form-control" placeholder="Password" name="user_password" type="password">
                                        </div>
                                        <c:if test="${not empty error}">
                                            <span class="label label-danger">${error}</span>
                                        </c:if>
                                    </div>
                                    <div class="checkbox">
                                        <label><input type="checkbox" name="_spring_security_remember_me" >Remember me</label>
                                    </div>
                                    <div class="form-group">
                                        <input type="submit" class="btn btn-lg btn-primary btn-block" value="Sign in">
                                    </div>
                                </div>
                            </div>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
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
