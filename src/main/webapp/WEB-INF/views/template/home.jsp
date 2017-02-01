<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
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
<body>
<div id="wrapper">
    <tiles:insertAttribute name="navigation" />
    <div class="text-center" style="color: #9d9d9d; margin-top: 200px">
        <h1><i class="fa fa-clock-o fa-3x"></i></h1>
        <h1>Welcome to KeepTrack, ${user.firstName}!</h1>
        <h3>Click on the menu items for the further work.</h3>
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
