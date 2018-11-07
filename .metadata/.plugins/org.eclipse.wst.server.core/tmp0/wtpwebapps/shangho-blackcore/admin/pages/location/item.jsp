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
                  指名區域 - 項目管理
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
              <a class="btn btn-success" href="#add"><i class="fa fa-plus"></i></a>
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
            <th>分類</th>
            <th>敘述</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          {{#each arr}}
            <tr>
              <td>
                  <div id="name_{{id}}">{{name}}</div>
                  <div id="name_modify_{{id}}"></div>
              </td>
              <td>
                  <div id="category_name_{{id}}">{{categoryname}}</div>
                  <input type="hidden" id="category_id_{{id}}" value="{{categoryid}}" />
                  <div id="category_name_modify_{{id}}"></div>
              </td>
              <td>
                  <div id="description_{{id}}">{{description}}</div>
                  <div id="description_modify_{{id}}"></div>
              </td>
              <td>
                <a href="javascript:void(0)" class="turnoff" id="turnoff_{{id}}" value="{{id}}" title="停用" {{#ifCond status 0}}style="display:none"{{/ifCond}}>
                  <i class="fa fa-fw fa-pause"></i>
                </a>             
                <a href="javascript:void(0)" class="turnon" id="turnon_{{id}}" value="{{id}}" title="啟用" {{#ifCond status 1}}style="display:none"{{/ifCond}}>
                  <i class="fa fa-fw fa-play"></i>
                </a>                
                <a href="javascript:void(0)" class="edit" id="edit_{{id}}" value="{{id}}" title="編輯">
                  <i class="fa fa-fw fa-edit"></i>
                </a>
                <a href="javascript:void(0)" class="delete" id="delete_{{id}}" value="{{id}}" title="刪除">
                  <i class="fa fa-fw fa-trash-o"></i>
                </a>
                <a href="javascript:void(0)" class="check" id="check_{{id}}" style="display:none" value="{{id}}" title="刪除">
                  <i class="fa fa-fw fa-check"></i>
                </a>
                <input type="hidden" id="status_{{id}}" value="{{status}}"/>
              </td>
            </tr> 
          {{/each}}
        </tbody>
      </table>
   </script>
   <script type="text/template" id="tpl_page_add">
      
      <div class="box box-info">
         <div class="box-header with-border">
            <h3 class="box-title">新增</h3>
         </div>
         <!-- /.box-header -->
         <!-- form start -->
         <form class="form-horizontal" id="myform">
            <div class="box-body">
              <div class="form-group">
                <label for="name" class="col-sm-2 control-label">Name</label>
                <div class="col-sm-5">
                  <input type="text" class="form-control" id="name" placeholder="Name" maxlength="50" required/>
                </div>
              </div>
              <div class="form-group">
                <label for="name" class="col-sm-2 control-label">分類</label>
                <div class="col-sm-5">
                  <select class="form-control" id="category_id">
                    {{#each arr}}
                      <option value="{{id}}">{{name}}</option>
                    {{/each}}
                  </select>
                </div>
              </div>
              <div class="form-group">
                <label for="name" class="col-sm-2 control-label">狀態</label>
                <div class="col-sm-5">
                  <select class="form-control" id="status">
                    <option value="1">啟用</option>
                    <option value="0">停用</option>
                  </select>
                </div>
              </div>
              <div class="form-group">
                <label for="description" class="col-sm-2 control-label">Description</label>
                <div class="col-sm-7">
                  <input type="text" class="form-control" id="description" placeholder="Description" maxlength="500"/>
                </div>
              </div>
            </div>
            <!-- /.box-body -->
            <div class="box-footer">
               <button type="submit" id="add_submit" class="btn btn-info">送出</button>
            </div>
            <!-- /.box-footer -->
         </form>
      </div>
    </script>
   <%@include file="/admin/pages/include/initial_script.jsp" %> 
   <script src="<%=request.getContextPath() %>/admin/plugins/jquery-validate/jquery.validate.min.js"></script>
   <script src="<%=request.getContextPath() %>/admin/js/location/item.js?updated=BA"></script>
</html>