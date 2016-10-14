<%-- 
    Document   : mainPage
    Author     : mkucukdemir
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/jquery/2.1.4/jquery.js"></script>
        <link href="${pageContext.request.contextPath}/webjars/bootstrap/3.2.0/css/bootstrap.css" rel="stylesheet" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/bootstrap/3.2.0/js/bootstrap.js"></script>
        <title>Web Application Template</title>
        <script>
            
            $(document).ready(function(){
            });
            
        </script>
    </head>
    <body>
        <nav class="navbar navbar-static-top">
            <a class="navbar-brand" href="#"> Web Application Template</a>
            <ul class="nav navbar-nav">
                <li class="nav-item active">
                    <a class="nav-link" href="#">Home<span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">About</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Contact</a>
                </li>
            </ul>
        </nav>
        <div class="jumbotron">
            <div class="container">
                <h1 class="display-3">Hi!</h1>
                <p>This is a template for a simple web application. Use it as a starting point to create something more unique.</p>
                <p><a class="btn btn-primary btn-lg" href="#" role="button">Learn more »</a></p>
            </div>
        </div>

        <div class="container">
            <!-- Example row of columns -->
            <div class="row">
                <div class="col-md-4">
                    <h2>Heading 1</h2>
                    <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
                    <p><a class="btn btn-secondary" href="#" role="button">View details »</a></p>
                </div>
                <div class="col-md-4">
                    <h2>Heading 2</h2>
                    <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
                    <p><a class="btn btn-secondary" href="#" role="button">View details »</a></p>
                </div>
                <div class="col-md-4">
                    <h2>Heading 3</h2>
                    <p>Donec sed odio dui. Cras justo odio, dapibus ac facilisis in, egestas eget quam. Vestibulum id ligula porta felis euismod semper. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.</p>
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
