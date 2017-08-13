<%-- 
    Document   : about
    Created on : 13.Ağu.2017, 19:48:24
    Author     : mkucukdemir
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/jquery/2.1.4/jquery.js"></script>
        <link href="${pageContext.request.contextPath}/webjars/bootstrap/3.2.0/css/bootstrap.css" rel="stylesheet" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/bootstrap/3.2.0/js/bootstrap.js"></script>
        <title><spring:message code="app.title"/></title>
    </head>
    <body>
        <div class="navbar navbar-default navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand" href="${pageContext.request.contextPath}/"> Clerk</a>
                    <button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#navbar-main">
                        <span class="icon-bar"></span>
                    </button>
                </div>
                <div class="navbar-collapse collapse" id="navbar-main">
                    <ul class="nav navbar-nav navbar-right">
                        <li><a class="nav-link" href="${pageContext.request.contextPath}/about">About</a></li>
                    </ul>

                </div>
            </div>
        </div>
        <div class="container" style="margin-top: 50px">
            <div class="row">
                <div class="col-md-12">
                    <h3>SmallFe Web Apps</h3>
                    <p>Mehm@ SmallFe</p>
                </div>
            </div>
        </div>
    </body>
</html>
