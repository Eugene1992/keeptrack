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

    <script type="text/javascript">
        window.onload = function () {
            var inProgressTasksNum = ${projectStats.inProgressTasksNum};
            var assignedTasksNum = ${projectStats.assignedTasksNum};
            var closedTasksNum = ${projectStats.closedTasksNum};
            var rejectedTasksNum = ${projectStats.rejectedTasksNum};

            var inProgressSprintsNum = ${projectStats.inProgressSprintsNum};
            var closedSprintsNum = ${projectStats.closedSprintsNum};

            var tasksChart = new CanvasJS.Chart("tasksChart",
                {
                    title:{
                        text: "Tasks:",
                        fontFamily: "arial black",
                        horizontalAlign: "left",
                        fontSize: 16
                    },
                    animationEnabled: true,
                    legend: {
                        verticalAlign: "center",
                        horizontalAlign: "left"
                    },
                    theme: "theme1",
                    data: [
                        {
                            type: "pie",
                            indexLabelFontFamily: "Garamond",
                            indexLabelFontSize: 16,
                            indexLabelFontWeight: "bold",
                            startAngle:0,
                            indexLabelFontColor: "MistyRose",
                            indexLabelLineColor: "darkgrey",
                            indexLabelPlacement: "inside",
                            toolTipContent: "{name}: {y} tasks",
                            showInLegend: true,
                            indexLabel: "#percent%",
                            dataPoints: [
                                {  y: inProgressTasksNum, name: "In Progress", color: "#337AB7"},
                                {  y: assignedTasksNum, name: "Assigned", color: "#5BC0DE"},
                                {  y: closedTasksNum, name: "Closed", color: "#5CB85C"},
                                {  y: rejectedTasksNum, name: "Rejected", color: "#C24642"}
                            ]
                        }
                    ]
                });
            tasksChart.render();

            var sprintsChart = new CanvasJS.Chart("sprintsChart",
                {
                    title:{
                        text: "Sprints:",
                        fontFamily: "arial black",
                        horizontalAlign: "left",
                        fontSize: 16
                    },
                    animationEnabled: true,
                    legend: {
                        verticalAlign: "center",
                        horizontalAlign: "left"
                    },
                    theme: "theme1",
                    data: [
                        {
                            type: "pie",
                            indexLabelFontFamily: "Garamond",
                            indexLabelFontSize: 16,
                            indexLabelFontWeight: "bold",
                            startAngle:0,
                            indexLabelFontColor: "MistyRose",
                            indexLabelLineColor: "darkgrey",
                            indexLabelPlacement: "inside",
                            toolTipContent: "{name}: {y} sprints",
                            showInLegend: true,
                            indexLabel: "#percent%",
                            dataPoints: [
                                {  y: inProgressSprintsNum, name: "In progress", color: "#337AB7"},
                                {  y: closedSprintsNum, name: "Closed", color: "#5CB85C"}
                            ]
                        }
                    ]
                });
            sprintsChart.render();
        }
    </script>
</head>
<body>
<div id="wrapper">
    <tiles:insertAttribute name="navigation"/>
    <tiles:insertAttribute name="project-dashboards-menu"/>
</div>

<script type="text/javascript" src="../../../resources/js/canvasjs.min.js"></script>

<!-- jQuery -->
<script src="../../../resources/js/jquery.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="../../../resources/js/bootstrap.min.js"></script>

</body>
</html>

