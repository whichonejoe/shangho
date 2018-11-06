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
      <!-- Content Header (Page header) -->
      <section class="content-header">
        <h1>
          房間資訊 
        </h1>
      </section>

      <!-- Main content -->
      <section class="content" id="main-content">
        
      </section>
      <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
    <footer class="main-footer">
      <%@include file="/admin/pages/include/footer.jsp" %> 
    </footer>
  </div>
</body>
<!-- main page -->
<script type="text/template" id="tpl_page_main">
  <div class="row">
    <div class="col-xs-12">
      <div class="box">
        <div class="box-header">
          <h3 class="box-title">
            <a class="btn btn-success" href="#add"><i class="fa fa-plus"></i></a>
          </h3>

          <div class="box-tools">
            <div class="input-group input-group-sm" style="width: 150px;">
              <!-- <input type="text" name="table_search" class="form-control pull-right" placeholder="Search">

              <div class="input-group-btn">
                <button type="submit" class="btn btn-default"><i class="fa fa-search"></i></button>
              </div> -->
            </div>
          </div>
        </div>
        <!-- /.box-header -->
        <div class="box-body" id="table_list">
          
        </div>
        <!-- /.box-body -->
      </div>
      <!-- /.box -->
    </div>
  </div>
</script>
<!-- data table -->
<script type="text/template" id="tpl_room_list">
  <table id="data-table" class="table table-bordered table-hover">
    <thead>
      <tr>
        <th class="text-center">房號</th>
        <th class="text-center">房型</th>
        <th class="text-center">Check In 狀態</th>
        <!-- <th class="text-center">Room Module</th> -->
        <th class="text-center">其他狀態</th>
        <th class="text-center"><!-- Last Connection -->上次連線時間</th>
        
        <!-- <th></th> -->
      </tr>
    </thead>
    <tbody>
      {{#each arr}}
        <tr>
          <td class="text-center">{{roomNo}}</td>
          <td class="text-center modify-module">
            <div class="btn-group">
              <button type="button" class="btn btn-success btn-xs" id="module" data-code="">
                {{roomModule}}
              </button>
              <button type="button" class="btn btn-success btn-xs dropdown-toggle" data-toggle="dropdown">
                <span class="caret"></span>
                <span class="sr-only">Toggle Dropdown</span>
              </button>
              <ul class="dropdown-menu" role="menu">
                {{#each ../roomModuleArr}}
                  <li>
                    <a href="javascript:void(0);" class="room-module" data-code="{{id}}" data-room-no="{{../roomNo}}">
                      {{name}}
                    </a>
                  </li>
                {{/each}}
              </ul>
            </div>
          </td>
          {{#ifCond isCheckin 'Y'}}
            <td class="text-center bg-green">
                <i class="glyphicon glyphicon-ok"></i>
            </td>
          {{/ifCond}}
          {{#ifCond isCheckin 'N'}}
            <td class="text-center bg-blue">
                <i class="glyphicon glyphicon-remove"></i>
            </td>
          {{/ifCond}}
         
          <td>
            {{#ifCond sosStatus 'Y'}}
               <i class="fa fa-exclamation-circle" data-toggle="tooltip" title="SOS"></i>
            {{/ifCond}}
            {{#ifCond dndStatus 'Y'}}
               <i class="fa fa-bell-slash" data-toggle="tooltip" title="DND"></i>
            {{/ifCond}}
            {{#ifCond murStatus 'Y'}}
               <i class="fa fa-lightbulb-o" data-toggle="tooltip" title="MUR"></i>
            {{/ifCond}}            
            {{#ifCond cardStatus 'Y'}}
               <i class="fa fa-fw fa-key" data-toggle="tooltip" title="CARD"></i>
            {{/ifCond}}
            {{#ifCond hvacStatus 'Y'}}
                <i class="glyphicon glyphicon-asterisk" data-toggle="tooltip" title="HVAC"></i>
            {{/ifCond}}
            {{#ifCond rcuStatus 'Y'}}
               <i class="ion-cube" data-toggle="tooltip" title="RCU"></i>
            {{/ifCond}}

            {{#ifCond pirStatus 'Y'}}
               <i class="fa-user-plus" data-toggle="tooltip" title="PIR"></i>
            {{/ifCond}}

           
            <!-- <span class="label label-danger"><i class="fa fa-fw fa-key"></i></span> -->
          </td>
          <td>
            {{#ifCond rcuTime 'heartbeat'}}
              <i class="fa fa-heartbeat" data-toggle="tooltip" title="HEARTBEAT"></i>
            {{else}}
              {{rcuTime}}
            {{/ifCond}}
          </td>
         <!--  <td>
            <a href="javascript:void(0);">
              <i class="fa fa-fw fa-gear modify"></i>
            </a>
          </td> -->
        </tr> 
      {{/each}}
    </tbody>
  </table>
</script>
<!-- add page -->
<script type="text/template" id="tpl_room_add">
  <div class="row">
    <div class="col-md-12">
      <form class="form-horizontal" id="myForm">
        <div class="box box-info">
          <div class="box-header with-border">
                       
          </div>
          <div class="box-body">
            <div class="form-group" id="div-mode-name">
              <label class="col-sm-2 control-label" style="text-align:center">房型</label>
              <div class="col-sm-3">
                <div class="btn-group">
                  <button type="button" class="btn btn-success" id="module" data-code="{{roomModuleArr.[0].id}}">
                    {{roomModuleArr.[0].name}}
                  </button>
                  <button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown">
                    <span class="caret"></span>
                    <span class="sr-only">Toggle Dropdown</span>
                  </button>
                  <ul class="dropdown-menu" role="menu">
                    {{#each roomModuleArr}}
                      <li>
                        <a href="javascript:void(0);" class="room-module" data-code="{{id}}">
                          {{name}}
                        </a>
                      </li>
                    {{/each}}
                  </ul>
                </div>
              </div>
            </div>
            <!--  -->
            <div class="form-group">
              <div class="col-sm-5" id="multiple-select">
                
              </div>
            </div> 
            <div class="box-footer">
              <button type="button" class="btn btn-info pull-left" id="submit-add">Submit</button>
            </div> 
          </div>
          <div class="box-footer">

          </div>
        </div>
        <div id="body-model">

        </div>
      </form>
    </div>
  </div>
</script>
<!-- 左右拉表單 -->
<script type="text/template" id="tpl_multiple_select">
  <select multiple="multiple" size="5" id="multiple_select">
    {{#each selectArr}}
      {{#if rcugroup}}
        {{#ifCond rcugroup ../module}}
          <option value="{{roomno}}" selected="selected">
            ({{floor}})&nbsp;{{roomno}}
          </option>
        {{/ifCond}}
      {{else}}
        <option value="{{roomno}}" {{#ifCond rcugroup 'Y'}}selected="selected"{{/ifCond}}>
          ({{floor}})&nbsp;{{roomno}}
        </option>
      {{/if}}
    {{/each}}
  </select>
</script>
<%@include file="/admin/pages/include/initial_script.jsp" %> 
<script src="<%=request.getContextPath() %>/admin/js/room.js?updated=201806221513"></script>
</html>
