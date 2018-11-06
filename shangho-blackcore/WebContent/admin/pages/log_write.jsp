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
                  Log 設定
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
      <!-- ./wrapper -->
   </body>
   <!-- main page -->
   <script type="text/template" id="tpl_page_main">
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
              <!-- <h3 class="box-title"></h3> -->          
              <div class="box-tools">
                <div class="input-group input-group-sm" style="width: 150px;">              
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
   <!-- 首頁list -->
   <script type="text/template" id="tpl_mode_list">
      <table id="data-table" class="table table-bordered table-hover">
        <thead>
          <tr>
            <th>名稱</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          {{#each arr}}
            <tr>
              <td>
                  {{description}} &nbsp;
                  {{#ifCond isenable true}}
                    <span style="color: green;">(Enable)</span>
                  {{else}}               
                    <span style="color: red;">(Disable)</span>
                  {{/ifCond}}
              </td>
              <td>
                {{#ifCond isenable true}}
                  <a href="javascript:void(0)" class="log-turnoff" value="{{name}}" title="停用">
                    <i class="fa fa-fw fa-pause"></i>
                  </a>
                {{else}}
                  <a href="javascript:void(0)" class="log-turnon" value="{{name}}" title="啟用">
                    <i class="fa fa-fw fa-play"></i>
                  </a>
                {{/ifCond}}
              </td>
            </tr> 
          {{/each}}
        </tbody>
      </table>
   </script>
   <%@include file="/admin/pages/include/initial_script.jsp" %> 
   <script src="<%=request.getContextPath() %>/admin/js/log_write.js?updated=12e4"></script>
</html>