<!DOCTYPE html>
<html>
   <head>
      <%@include file="/admin/pages/include/head.jsp" %>
      <link href="<%=request.getContextPath() %>/admin/css/index.css" rel="stylesheet" type="text/css"/>
      <link href="<%=request.getContextPath() %>/admin/css/common.css" rel="stylesheet" type="text/css"/>
   </head>
   <body class="hold-transition skin-blue sidebar-mini">
      <audio loop="true" id="alarm" controls="controls" style="display: none">
         <source src="<%=request.getContextPath() %>/music/alarm.mp3" type="audio/mpeg" />
      </audio>
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
            <div class="button-style">
               <button class="button" name="alarm_puse" id="alarm_puse" type="button">pause</button>
            </div>
            <section class="content-header">
               <h1>
                  <!-- Service Requests    <button type="button" class="btn btn-primary" data-toggle="modal" data-target=".bs-example-modal-lg">Large modal</button> -->
                  <!-- SOS Info -->
               </h1>
               <!-- <ol class="breadcrumb">
                  <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                  <li class="active">Dashboard</li>
                  </ol> -->
            </section>
            <!-- Main content -->
            <section class="content">
               <div class="row" >
               </div>
               <!-- /.row -->
            </section>
            <!-- /.content -->
         </div>
         <!-- /.content-wrapper -->
         <footer class="main-footer">
            <%@include file="/admin/pages/include/footer.jsp" %> 
         </footer>
      </div>
      <!-- ./wrapper -->
      <!-- Modal Start -->
      <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
         <div class="modal-dialog" role="document">
            <div class="modal-content" id="index_modal-content">
            </div>
         </div>
      </div>
      <!-- Modal End -->
   </body>
   <script type="text/template" id="tpl_model">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
        <h4 class="modal-title" id="myModalLabel">Modal title</h4>
      </div>
      <div class="modal-body">
        <div class="box-body">
          <table class="table table-bordered">
            <tbody>
              <tr>
                <th style="width: 10px">#</th>
                <th>Task</th>
                <th>Progress</th>
                <th style="width: 40px">Label</th>
              </tr>
              <tr>
                <td>1.</td>
                <td>Update software</td>
                <td>
                  <div class="progress progress-xs">
                    <div class="progress-bar progress-bar-danger" style="width: 55%"></div>
                  </div>
                </td>
                <td><span class="badge bg-red">55%</span></td>
              </tr>
              <tr>
                <td>2.</td>
                <td>Clean database</td>
                <td>
                  <div class="progress progress-xs">
                    <div class="progress-bar progress-bar-yellow" style="width: 70%"></div>
                  </div>
                </td>
                <td><span class="badge bg-yellow">70%</span></td>
              </tr>
            </tbody>
          </table>
        </div>
        <!-- /.box-body -->
        <div class="box-footer clearfix">
          <ul class="pagination pagination-sm no-margin pull-right">
            <li><a href="#">«</a></li>
            <li><a href="#">1</a></li>
            <li><a href="#">2</a></li>
            <li><a href="#">3</a></li>
            <li><a href="#">»</a></li>
          </ul>
        </div>
      </div>
      <div class="modal-footer">        
      </div>
   </script>
   <script type="text/template" id="tpl_model_service">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">{{title}}</h4>
      </div>
      <div class="modal-body" >
        <div class="box-body" id="modal-body">
          <table class="table table-bordered">
            <tbody>
              <tr>
                <th>Room No</th>
                <th>Time</th>
              </tr>
              {{#each arr}}
                <tr>
                  <td>{{roomNo}}</td>
                  <td>{{triggerTime}}</td>
                </tr>
              {{/each}}
            </tbody>
          </table>
        </div>
        <!-- /.box-body -->
        <div class="box-footer clearfix">
          <ul class="pagination pagination-sm no-margin pull-right">
            <!-- <li><a href="#">«</a></li>
            <li><a href="#">1</a></li>
            <li><a href="#">2</a></li>
            <li><a href="#">3</a></li>
            <li><a href="#">»</a></li> -->
          </ul>
        </div>
      </div>
      <div class="modal-footer" id="modal-footer">        
      </div>
   </script>
   <!-- template start -->
   <script type="text/template" id="tpl_data_table">
      <table id="data-table" class="table table-bordered table-hover">
        <thead>
          <tr>
            <th>Room No</th>
          </tr>
        </thead>
        <tbody>
          {{#each arr}}
            <tr>
              <td>{{roomNo}}</td>
            </tr> 
          {{/each}}
        </tbody>
      </table>
   </script>
   <!-- SOS 求救信號-->
   <script type="text/template" id="tpl_sos_list">
      {{#if arr}}
        <h3>
          緊急按鈕
        </h3>
      {{/if}}
      {{#each arr}}
        <div class="col-lg-3 col-xs-6 sos-item area-sos-{{roomNo}}">
          <input type="hidden" name="sos-roomno" value="{{roomNo}}"/>
          <!-- small box -->
          <div class="small-box bg-red">
            <div class="inner">
              <h3>{{roomNo}}</h3>
              <p>&nbsp;</p>
            </div>
            <div class="icon">
              <i class="fa fa-exclamation-circle"></i>
            </div>  
            <a href="javascript:void(0);" class="small-box-footer sos-stop" data-index="{{roomNo}}">          
              {{#ifCond ../isEnable true}}
                {{#ifCond alertFlag 'Y'}}
                  Lost {{intervalTime}} minutes ago <i class="fa fa-check-square-o"></i>
                {{else}}
                  {{sosMessage}} <i class="fa fa-hand-pointer-o"></i>
                {{/ifCond}}
                {{else}}
                  &nbsp;
              {{/ifCond}}
            </a>
          </div>
        </div>
      {{/each}} 
   </script>
   <!-- SOS 老闆要求 要放......-->
   <script type="text/template" id="tpl_sos">
      <h3>{{sosCount}}</h3>
      <p>緊急按鈕</p>
   </script>
   <!-- dnd-->
   <script type="text/template" id="tpl_dnd">
      <h3>{{dndCount}}</h3>
      <p>請勿打擾</p>
   </script>
   <!-- 房間清潔-->
   <script type="text/template" id="tpl_make_up">
      <h3>{{makeUpRoomCount}}</h3>
      <p>請打掃</p>
   </script>
   <!-- 管家服務-->
   <script type="text/template" id="tpl_bulters">
      <h3>{{bulterCount}}</h3>
      <p>管家服務</p>
   </script>
   <!-- occupied-->
   <script type="text/template" id="tpl_occupied">
      <div class="col-lg-3 col-xs-6">
        <div class="small-box bg-green">
          <div class="inner">
            <h3>{{occupiedCount}}</h3>
            <p>已入住房數</p>
          </div>
          <div class="icon">
            <i class="fa fa-user"></i>
          </div>
          <a href="#" class="small-box-footer">
            &nbsp;
            <!-- More info <i class="fa fa-arrow-circle-right"></i> -->
          </a>
        </div>
      </div>
   </script>
   <!-- 尚有空房數-->
   <script type="text/template" id="tpl_vacant">
      <div class="col-lg-3 col-xs-6">
        <div class="small-box bg-blue">
          <div class="inner">
            <h3>{{vacantCount}}</h3>
            <p>尚有空房數</p>
          </div>
          <div class="icon">
            <i class="fa fa-home"></i>
          </div>
          <a href="#" class="small-box-footer">
            &nbsp;
            <!-- More info <i class="fa fa-arrow-circle-right"></i> -->
          </a>
        </div>
      </div>
   </script>
   <!-- 不能賣的-->
   <script type="text/template" id="tpl_out_of_order">
      <div class="col-lg-3 col-xs-6">
        <div class="small-box bg-yellow">
          <div class="inner">
            <h3>{{outCount}}</h3>
            <p>Out of order</p>
          </div>
          <div class="icon">
            <i class="fa fa-close"></i>
          </div>
          <a href="#" class="small-box-footer">
            More info <i class="fa fa-arrow-circle-right"></i>
          </a>
        </div>
      </div>
   </script>
   <!-- 還沒初始化的rcu資料-->
   <script type="text/template" id="tpl_lost_rcu">
      <h3>{{count}}</h3>
      <p>Lost RCU</p>
   </script>
   <!-- 應該傳心跳過來卻沒傳的-->
   <script type="text/template" id="tpl_no_return">
      <h3>{{count}}</h3>
      <p>RCU未回傳心跳</p>
   </script>
   <!-- template end -->
   <%@include file="/admin/pages/include/initial_script.jsp" %> 
   <script src="<%=request.getContextPath() %>/admin/js/index.js?updated=201a3s16506"></script>
</html>