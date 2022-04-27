<%-- 
    Document   : mainPage
    Author     : mkucukdemir
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
        <script>
            $(document).ready(function () {
                // Show Data Table Division
                $("#accountactivitiestablediv").show();
                // Datatable defn
                $('#accountActivitiesTable').DataTable({
                    info:     true,
                    ordering: true,
                    paging:   true,
                    searching: true,
                    stateSave: true,
                    dom: 'QlBfrtip',
                    buttons: ['copy', 'csv', 'excel', 'pdf', 'print'],
                    columns: [
                        {
                            data: 'timestamp',
                            render: function (data, type, row) {
                                return moment(new Date(data)).format('YYYY-MM-DD');
                            }
                        },
                        {
                            data: 'info',
                            type: 'string'
                        },
                        {
                            data: 'payment',
                            type: 'num-fmt'
                        },
                        {
                            data: 'category',
                            type: 'string',
                            mRender : function(data, type, row) {
                                return row.category + '</br><input type="hidden" id="activity_'+row.id+'" value="'+row.id+'"/>'
                            }
                        }
                    ]
                });
                // Push data in table
                var data= ${data};
                $('#accountActivitiesTable').DataTable().clear().draw();
                $('#accountActivitiesTable').DataTable().rows.add(data["activities"]).columns.adjust().draw();
                $("#accountactivitiestablediv").slideDown();
                $(data["categories"]).each(function(index,item){
                    $('#filterlistgroup').append('<button type="button" class="list-group-item list-group-item-action searchbuilderrebuilder" key="'+item.name+'">'+item.name+'<span class="badge badge-primary badge-pill float-right">0</span></button>');
                });
                // Action when a category has been clicked
                $(document).on('click','button.searchbuilderrebuilder',function () {
                    var filtername = $(this).attr('key');
                    var button = $(this);
                    $.ajax({
                        type: "POST",
                        contentType: "application/json;charset=UTF-8",
                        url: "ajax/getDetailsOfCategory",
                        data: filtername,
                        dataType: 'json',
                        timeout: 600000,
                        beforeSend: function (xhr) {
                        },
                        success: function (data){
                            $('#accountActivitiesTable').DataTable().searchBuilder.rebuild(data["details"]);
                            $("#categoryName").val(filtername);
                            button.remove();
                        },
                        error: function (e) {
                        },
                        complete: function (xhr){
                        }
                    });
                });
                // Autocomplete by existing categorynames
                $("#categoryName").autocomplete({
                    source: function (request, response) {
                        $.ajax({
                            url: "ajax/autocompleteCategoryNames",
                            type: "POST",
                            data: request,
                            success: function (data) {
                                response( $.map( JSON.parse(data), function( item ) {
                                    return {
                                        label: item.name,
                                        value: item.name
                                    }
                                }));
                            }
                        });
                    },
                    minLength: 3
                });
                // Save category
                $('#addCategoryButton').click(function(){
                    var formData = {
                        name: $("#categoryName").val(),
                        details: $('#accountActivitiesTable').DataTable().searchBuilder.getDetails(),
                        accountActivityIndexes: $('#accountActivitiesTable').DataTable().$('tr', {"filter":"applied"}).find('input:hidden').map(function(){return $(this).val();}).get()
                    };
                    $.ajax({
                        type: "POST",
                        contentType: "application/json",
                        url: "ajax/addCategory",
                        data: JSON.stringify(formData),
                        dataType: 'json',
                        timeout: 600000,
                        beforeSend: function (xhr) {
                            
                        },
                        success: function (data){
                            $('#categoryName').val('');
                            $('#accountActivitiesTable').DataTable().searchBuilder.rebuild();
                            $('#accountActivitiesTable').DataTable().clear().draw();
                            $('#accountActivitiesTable').DataTable().rows.add(data["activities"]).columns.adjust().draw();
                            $('#filterlistgroup').append('<button type="button" class="list-group-item list-group-item-action searchbuilderrebuilder" key="'+formData["name"]+'">'+formData["name"]+'<span class="badge badge-primary badge-pill float-right">'+formData["accountActivityIndexes"].length+'</span></button>');
                            $('#filterlistdiv').slideDown();
                        },
                        error: function (e) {

                        },
                        complete: function (xhr){
                            
                        }
                    });
                });
                // Export as JSON
                $('#savefiltersbutton').click(function(){
                    $.ajax({
                        type: "GET",
                        url: "ajax/exportCategories"
                    });
                });
            });
            // $('button.searchbuilderrebuilder').each(function(){
            //  $( this ).trigger( "click" );
            //  while ($('#categoryName').val()==="") {
            //      setTimeout(() => {  console.log("Waiting!"); }, 1000);
            //  }
            //  $('#addCategoryButton').trigger( "click" );
            //  while (control-condition) {
            //      setTimeout(() => {  console.log("Waiting!"); }, 1000);
            //  }
            // });
        </script>
        <style>
            .card-custom {
                max-width: 640px;
                min-width: 480px;
            }
            .form-group label{
                font-size: 0.8em;
            }
/*            .footer {
                position: absolute;
                bottom: 0;
                width: 100%;
                height: 60px;
                line-height: 60px;
            }*/
            #chart {
                max-width: 650px;
                margin: 35px auto;
            }
        </style>
    </head>
    <!-- $('#accountActivitiesTable').DataTable().$('tr', {"filter":"applied"}).each(function(index,value) {console.log(value)}); -->
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="#">Account Activities Monitor</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
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
            </div>
        </nav>
        <div class="content">
            <div class="container">
                <div id="accountactivitiestablediv">
                    <div class="row" >
                        <div class="col-md-12">
                            <table class="table table-sm" id="accountActivitiesTable">
                                <thead>
                                    <tr>
                                        <th scope="col">Timestamp</th>
                                        <th scope="col">Info</th>
                                        <th scope="col">Payment</th>
                                        <th scope="col">Category</th>
                                    </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </div>
                        <div class="col-md-12">&nbsp;</div>
                        <div class="col-md-3"></div>
                        <div class="input-group col-md-6">
                            <div class="input-group-prepend">
                                <span class="input-group-text">Save as </span>
                            </div>
                            <input type="text" class="form-control" placeholder="type a filter name..." id="categoryName">
                            <div class="input-group-append">
                                <button class="btn btn-outline-primary" type="button" id="addCategoryButton"> > </button>
                            </div>
                        </div>
                        <div class="col-md-3"></div>
                        <div class="col-md-12">&nbsp;</div>
                        <div class="col-md-3"></div>
                        <div class="col-md-6" id="filterlistdiv">
                            <div class="list-group" id="filterlistgroup" role="tablist">

                            </div>
                            <button type="button" class="btn btn-primary" id="savefiltersbutton">
                                Save Filters <span class="glyphicon glyphicon-save" ></span>
                            </button>
                        </div>
                        <div class="col-md-3"></div>
                    </div>
                    <a href="${pageContext.request.contextPath}/dashboard" class="btn btn-outline-primary btn-sm btn-block" role="button">View Dashboard</a>
                </div>
            </div> <!-- /container -->
        </div>
        <footer class="footer">
            <div class="container">
                <p>© SmallFe 2021</p>
            </div>
        </footer>
    </body>
</html>
