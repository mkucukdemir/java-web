<%-- 
    Document   : dashboard
    Created on : Dec 23, 2021, 12:39:40 PM
    Author     : mehmet.kucukdemir
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/jquery/3.6.0/jquery.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/jquery-ui/1.13.0/jquery-ui.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/datatables/1.11.3/js/jquery.dataTables.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/datatables-buttons/1.7.0/js/dataTables.buttons.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/datatables-searchbuilder/1.3.0/js/dataTables.searchBuilder.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/datatables-searchbuilder/1.3.0/js/dataTables.dateTime.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/datatables-buttons/1.7.0/js/buttons.html5.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/datatables-buttons/1.7.0/js/buttons.print.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/jszip/3.1.0/jszip.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/pdfmake/0.1.72/build/pdfmake.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/pdfmake/0.1.72/build/vfs_fonts.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/bootstrap/4.0.0/js/bootstrap.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/chartjs/3.6.0/dist/chart.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/momentjs/2.29.1/moment.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/momentjs/2.29.1/datetime-moment.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/apexcharts/3.8.0/dist/apexcharts.js"></script>
        
        <link href="${pageContext.request.contextPath}/webjars/datatables/1.11.3/css/jquery.dataTables.css" rel="stylesheet" />
        <link href="${pageContext.request.contextPath}/webjars/bootstrap/4.0.0/css/bootstrap.css" rel="stylesheet" />
        <link href="${pageContext.request.contextPath}/webjars/datatables-buttons/1.7.0/css/buttons.dataTables.css" rel="stylesheet" />
        <link href="${pageContext.request.contextPath}/webjars/datatables-searchbuilder/1.3.0/css/searchBuilder.dataTables.css" rel="stylesheet" />
        <link href="${pageContext.request.contextPath}/webjars/datatables-searchbuilder/1.3.0/css/dataTables.dateTime.min.css" rel="stylesheet" />
        <link href="${pageContext.request.contextPath}/webjars/jquery-ui/1.13.0/jquery-ui.css" rel="stylesheet" />
        <title>Account Activities Monitor</title>
        <script></script>
        <style></style>
    </head>
    <body>
        <div id="wrapper">
            <div class="content-area">
                <div class="container-fluid">
                    <div class="main">
                    
                    </div>
                </div>
            </div>
        </div>
        <script type="text/javascript" src="<c:url value="/resources/js/scripts.js" />"></script>
    </body>
</html>
