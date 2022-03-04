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
                $("#accountActivityTitleMappingForm").hide();
                $("#accountactivitiestablediv").hide();
                $("#filterlistdiv").hide();
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
                $( "form" ).submit(function( event ) {
                    event.preventDefault();
                    var formData;
                    var formURL;
                    var formId=$(this).attr("id");
                    switch(formId){
                        case "accountActivityFileForm":
                            formData = {path: $('#cardfilepath').val(),titleRow: $('#cardtitlerow').val(),numOfColumns: $('#cardnumofcolumns').val()};
                            formURL = "ajax/uploadAccountActivityFile";
                            break;
                        case "accountActivityTitleMappingForm":
                            formData = {filePath: $('#cardfilepath').val(),timestampIndex: $("#inputTimestamp").val(),infoIndex: $("#inputInfo").val(),paymentIndex: $("#inputPayment").val()};
                            formURL = "ajax/setAccountActivityTitleMapping";
                            break;
                        default:
                    }
                    $.ajax({
                        type: "POST",
                        contentType: "application/json",
                        url: formURL,
                        data: JSON.stringify(formData),
                        dataType: 'json',
                        timeout: 600000,
                        beforeSend: function (xhr) {
                            switch(formId){
                                case "accountActivityFileForm":
                                    $("#accountActivityFileForm").slideUp();
                                    break;
                                case "accountActivityTitleMappingForm":
                                    $("#accountActivityTitleMappingForm").slideUp();
                                    break;
                                default:
                            }
                        },
                        success: function (data){
                            switch(formId){
                                case "accountActivityFileForm":
                                    $('#cardtitlerow').val('');
                                    $('#cardnumofcolumns').val('');
                                    $(".custom-file-input").siblings(".custom-file-label").removeClass("selected").html('Choose file');
                                    for (var key in data) {
                                        $('#inputTimestamp').append($('<option>', {
                                            value: key,
                                            text: data[key]
                                        }));
                                        $('#inputInfo').append($('<option>', {
                                            value: key,
                                            text: data[key]
                                        }));
                                        $('#inputPayment').append($('<option>', {
                                            value: key,
                                            text: data[key]
                                        }));
                                    }
                                    $("#accountActivityTitleMappingForm").slideDown();
                                    break;
                                case "accountActivityTitleMappingForm":
                                    $('#cardfilepath').val('');
                                    $('#inputTimestamp option:not(:first)').remove();
                                    $('#inputInfo option:not(:first)').remove();
                                    $('#inputPayment option:not(:first)').remove();
                                    $("#accountActivityFileForm").slideDown();
                                    break;
                                default:
                            }
                        },
                        error: function (e) {
                            console.log("Error: " + e);
                            switch(formId){
                                case "accountActivityFileForm":
                                    $("#accountActivityFileForm").slideDown();
                                    break;
                                case "accountActivityTitleMappingForm":
                                    $("#accountActivityTitleMappingForm").slideDown();
                                    break;
                                default:
                            }
                        },
                        complete: function (xhr){
                            switch(formId){
                                case "accountActivityFileForm":
                                    break;
                                case "accountActivityTitleMappingForm":
                                    break;
                                default:
                            }
                        }
                    });
                });
                $(".custom-file-input").on("change", function() {
                    var fileName = $(this).val().split("\\").pop();
                    $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
                });
                $('#listCategoriesAndActivitiesButton').click(function () {
                    $.ajax({
                        type: "POST",
                        contentType: "application/json",
                        url: "ajax/listCategoriesAndActivities",
                        data: JSON.stringify(''),
                        dataType: 'json',
                        timeout: 600000,
                        beforeSend: function (xhr) {
                            $("#carddiv").slideUp();
                        },
                        success: function (data){
                            $('#accountActivitiesTable').DataTable().clear().draw();
                            $('#accountActivitiesTable').DataTable().rows.add(data["activities"]).draw();
                            $("#accountactivitiestablediv").slideDown();
                            $(data["categories"]).each(function(index,item){
                                $('#filterlistgroup').append('<button type="button" class="list-group-item list-group-item-action searchbuilderrebuilder" key="'+item.name+'">'+item.name+'<span class="badge badge-primary badge-pill float-right">0</span></button>');
                            });
                            $('#filterlistdiv').slideDown();
                        },
                        error: function (e) {
                            $("#carddiv").slideDown();
                            $("#accountactivitiestablediv").slideUp();
                        },
                        complete: function (xhr){

                        }
                    });
                });
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
                            $('#accountActivitiesTable').DataTable().rows.add(data["activities"]).draw();
                            $('#filterlistgroup').append('<button type="button" class="list-group-item list-group-item-action searchbuilderrebuilder" key="'+formData["name"]+'">'+formData["name"]+'<span class="badge badge-primary badge-pill float-right">'+formData["accountActivityIndexes"].length+'</span></button>');
                            $('#filterlistdiv').slideDown();
                        },
                        error: function (e) {

                        },
                        complete: function (xhr){
                            
                        }
                    });
                });
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
                <div id="carddiv">
                    <h4>Write down some properties and browse the account activities
                        excel file. Properties depend on file export format of the banks.</h4>
                    <div class="card-columns d-flex justify-content-center">
                        <div class="card card-custom box-shadow">
                            <div class="card-header">
                                <h5 class="my-0 font-weight-normal">File Properties</h5>
                            </div>
                            <div class="card-body d-flex flex-column">
                                <form id="accountActivityFileForm">
                                    <div class="form-group row">
                                        <label class="col-sm-8 col-form-label" for="cardtitlerow">Row Number of Titles</label>
                                        <div class="col-sm-4"><input class="form-control" type="text" id="cardtitlerow" name="titlerow" required></div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-sm-8 col-form-label" for="cardnumofcolumns">Number of Columns</label>
                                        <div class="col-sm-4"><input class="form-control" type="text" placeholder="" id="cardnumofcolumns" name="numofcolumns" required></div>
                                    </div>
                                    <div class="form-group row">
                                        <div class="custom-file">
                                            <input type="file" class="custom-file-input" id="cardcustomFile" onchange="$(this).parent().parent().find('.form-control').val($(this).val());" style="display: none;">
                                            <label class="custom-file-label" for="cardcustomFile">Choose file</label>
                                            <input type="hidden" class="form-control" name="filepath" id="cardfilepath" />
                                        </div>
                                    </div>
                                    <button class="btn btn-primary btn-sm btn-block" type="submit">+ Add</button>
                                </form>
                                <form id="accountActivityTitleMappingForm">
                                    <div class="form-group row">
                                        <label class="col-sm-2 col-form-label" for="inputTimestamp">Timestamp</label>
                                        <div class="col-sm-10">
                                            <select id="inputTimestamp" name="timestamp" class="form-control" required>
                                                <option selected>Choose...</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-sm-2 col-form-label" for="inputInfo">Info</label>
                                        <div class="col-sm-10">
                                            <select id="inputInfo" name="info" class="form-control" required>
                                                <option selected>Choose...</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-sm-2 col-form-label" for="inputPayment">Payment</label>
                                        <div class="col-sm-10">
                                            <select id="inputPayment" name="payment" class="form-control" required>
                                                <option selected>Choose...</option>
                                            </select>
                                        </div>
                                    </div>
                                    <button class="btn btn-primary btn-sm btn-block" type="submit">Match Titles</button>
                                </form> 
                            </div>
                        </div>
                    </div>
                    <button class="btn btn-outline-primary btn-sm btn-block" type="button" id="listCategoriesAndActivitiesButton">Done >></button>
                </div>

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
