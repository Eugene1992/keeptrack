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
            <tiles:insertAttribute name="task-add-form"/>
        </div>

        <!-- jQuery -->
        <script src="../../../resources/js/jquery.js"></script>

        <!-- Bootstrap Core JavaScript -->
        <script src="../../../resources/js/bootstrap.min.js"></script>

        <script>
            var textAreaData = '${updProject.description}';
            $('#description').val(textAreaData);
        </script>
    </body>
</html>
