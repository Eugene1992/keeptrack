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

    <link rel="stylesheet" href="${param.contextPath}/resources/css/dataTables.bootstrap.min.css">

    <script type="text/javascript">
        window.onload = function () {
            var numOfNonDelayedTasks = ${report.numOfNonDelayedTasks};
            var numOfDelayedTasks = ${report.numOfDelayedTasks};

            var tasksChart = new CanvasJS.Chart("tasksChart",
                {
                    title:{
                        text: "Employee tasks:",
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
                                {  y: numOfDelayedTasks, name: "Done with delay", color: "#C24642"},
                                {  y: numOfNonDelayedTasks, name: "Done without delay", color: "#337AB7"}
                            ]
                        }
                    ]
                });
            tasksChart.render();
        }
    </script>
</head>
<body>
<div id="wrapper">
    <tiles:insertAttribute name="navigation" />
    <tiles:insertAttribute name="employee-report-menu" />
</div>

<script type="text/javascript" src="../../../resources/js/canvasjs.min.js"></script>

<!-- jQuery -->
<script src="../../../resources/js/jquery.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="../../../resources/js/bootstrap.min.js"></script>

<script type="text/javascript" src="${param.contextPath}/resources/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${param.contextPath}/resources/js/dataTables.bootstrap.min.js"></script>

<script>
    $(document).ready(function() {
        $('#tasks-table').DataTable( {
            "pagingType": "full_numbers"
        } );
    } );
</script>

</body>
</html>
