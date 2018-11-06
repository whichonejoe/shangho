<!DOCTYPE html>
<html>
<head>
  <%@include file="/admin/pages/include/head.jsp" %> 
  <link href="<%=request.getContextPath() %>/admin/css/common.css" rel="stylesheet" type="text/css"/>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
  <header class="main-header">
    <%@include file="/admin/pages/include/header.jsp" %> 
  </header>
  <!-- Left side column. contains the logo and sidebar -->  
  <aside class="main-sidebar">
    <%@include file="/admin/pages/include/left_menu.jsp" %> 
  </aside>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <section class="content-header">
      <h1>
        客房狀況
      </h1>
      
    </section>
    <!-- Main content -->
    <section class="content" id="main-content">
       <div class="row">
          <div class="col-md-12">
             <!-- Custom Tabs -->
             <div class="nav-tabs-custom">
                <ul class="nav nav-tabs" id="floorDiv">                 
                </ul>
                <div class="tab-content" id="roomStatusDiv">                  
                </div>                
             </div>             
          </div>
       </div>
    </section>


    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
  <footer class="main-footer">
    <%@include file="/admin/pages/include/footer.jsp" %> 
  </footer>
  
</div>
<!-- ./wrapper -->

</body>

<!-- single room html -->
<script type="text/template" id="tpl_single_room">
  <div class="col-md-3">
    <div class="box box-widget widget-user-2">
        <div class="widget-user-header bg-{{backgroundColor}}">
          <div class="widget-user-image">
             <img class="img-circle" src="{{image}}" id="{{roomNo}}_icon" onerror='$(this).attr("src","../../images/status_code/default.jpg")'/>
          </div>
          <h4 class="widget-user-username">{{roomNo}}</h4>
          <h4 class="widget-user-desc" id="{{roomNo}}_statusName">{{statusName}}</h4>
        </div>
    </div>
  </div>
  
</script>
<!-- room -->
<script type="text/template" id="tpl_room_status">
  {{#each arr}}
    <div class="tab-pane{{#ifCond @index 0}} active{{/ifCond}}" id="tab_{{floor}}">
      <div class="row">
        {{#each data}}
          <div class="col-md-3">
            <div class="box box-widget widget-user-2" id="{{roomNo}}_element">
                <div class="widget-user-header bg-{{backgroundColor}}">
                  <div class="widget-user-image">
                     <img class="img-circle" src="{{image}}" id="{{roomNo}}_icon" onerror='$(this).attr("src","../../images/status_code/default.jpg")'/>
                  </div>
                  <h4 class="widget-user-username">{{roomNo}}</h4>
                  <h4 class="widget-user-desc" id="{{roomNo}}_statusName">{{statusName}}</h4>
                </div>
            </div>
          </div>
        {{/each}}
      </div>
    </div>
  {{/each}}
</script>
<!-- room -->
<script type="text/template" id="tpl_room_status_change">
  <div class="widget-user-header bg-{{backgroundColor}}">
    <div class="widget-user-image">
       <img class="img-circle" src="{{image}}" id="{{roomNo}}_icon" onerror='$(this).attr("src","../../images/status_code/default.jpg")'/>
    </div>
    <h4 class="widget-user-username">{{roomNo}}</h4>
    <h4 class="widget-user-desc" id="{{roomNo}}_statusName">{{statusName}}</h4>
  </div>
    
</script>
<script type="text/template" id="tpl_no_data">
  <div class="callout">
    <h4>Empty!</h4>
  </div>
</script>
<!-- floor -->
<script type="text/template" id="tpl_floor">
  {{#each arr}}
    {{#ifCond @index 0}}
      <li class="floorClick active" id="floor_{{floor}}">
        <a href="#tab_{{floor}}" data-toggle="tab" aria-expanded="true">{{floor}}</a>
      </li>
    {{else}}
      <li class="floorClick" id="floor_{{floor}}">
        <a href="#tab_{{floor}}" data-toggle="tab" aria-expanded="false">{{floor}}</a>
      </li>
    {{/ifCond}}
  {{/each}}
</script>

<%@include file="/admin/pages/include/initial_script.jsp" %> 
<script src="<%=request.getContextPath() %>/admin/js/room_status.js"></script>
</html>
  