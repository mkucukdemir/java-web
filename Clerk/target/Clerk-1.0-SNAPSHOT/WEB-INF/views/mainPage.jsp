<%-- 
    Document   : mainPage
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
        <script>
            
            $(document).ready(function(){
            });
            
        </script>
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
        <div class="jumbotron" style="margin-top: 50px">
            <div class="container">
                <h1 class="display-3">Clerk</h1>
                <p>This is a Caesar cipher encoder decoder application for Android platform.</p>
                <p><a class="btn btn-primary btn-lg" href="#tryitnow" role="button">Try It Now »</a></p>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
            </div>
        </div>

        <div class="container" id="tryitnow">
            <!-- Example row of columns -->
            <div class="row">
                <div class="col-md-6">
                    <h2>Encoding</h2>
                    <p>Not implemented yet</p>
                    <p><a class="btn btn-secondary" href="#" role="button">View details »</a></p>
                </div>
                <div class="col-md-6">
                    <h2>Decoding</h2>
                    <p>Not implemented yet</p>
                    <p><a class="btn btn-secondary" href="#" role="button">View details »</a></p>
                </div>
            </div>

            <hr>

            <footer>
                <p>© SmallFe 2016</p>
            </footer>
        </div> <!-- /container -->
    </body>
</html>
