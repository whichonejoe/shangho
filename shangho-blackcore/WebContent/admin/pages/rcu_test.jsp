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
        指令測試
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

<!-- 新增頁面 -->
<script type="text/template" id="tpl_page_add">
  <div class="row">
    <div class="col-md-12">
      
        <div id="body-model">

        </div>
        <!-- submit -->
        <div class="box-footer">
          <button type="button" class="btn btn-info pull-left" id="stop"><i class="fa a-stop"></i>Stop</button>
          <button type="button" class="btn btn-info pull-left" id="submit" style="margin-left: 5px;">Submit</button>
        </div> 
      
    </div>
  </div>
</script>
<script type="text/template" id="tpl_index">
  <div class="box box-info">   
    <!-- form start -->        
    <form class="form-horizontal" id="myForm" novalidate="novalidate">    
      <div class="box-body">
        <div class="form-group">
          <label class="col-sm-2 text-center">房號</label>
          <div class="col-sm-10" id="tabs">
            
          </div>
        </div>        
        <div class="form-group input-option" style="display:none;">
          <label class="col-sm-2 text-center">類別</label>
          <div class="col-sm-10" id="group">
            
          </div>
        </div> 
        <div class="form-group input-option" style="display:none;" id="device-div">
          <label class="col-sm-2 text-center">設備</label>
          <div class="col-sm-10" id="device">
            
          </div>
        </div>
        <div class="form-group input-option" style="display:none;">
          <label class="col-sm-2 text-center">選項</label>
          <div class="col-sm-10">
            <div data-toggle="buttons" class="function">
              <label class="btn btn-default active">
                <input name="function" value="1" type="radio" checked="true">開
              </label>
              <label class="btn btn-default" >
                <input name="function" value="0" type="radio" >關
              </label>
            </div>
          </div>
        </div>
        <div class="form-group input-option" style="display:none;">
          <label class="col-sm-2 text-center">檢查次數</label>
          <div class="col-sm-10" id="returnOption">
            <div class="btn-group">
               <button type="button" class="btn btn-success" id="returnCount">5</button>
               <button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
               <span class="caret"></span>
               <span class="sr-only">Toggle Dropdown</span>
               </button>
               <ul class="dropdown-menu" role="menu">
                  <li><a href="javascript:void(0);" class="returnLink">5</a></li>
                  <li><a href="javascript:void(0);" class="returnLink">10</a></li>
                  <li><a href="javascript:void(0);" class="returnLink">50</a></li>
                  <li><a href="javascript:void(0);" class="returnLink">100</a></li>
                  <li><a href="javascript:void(0);" class="returnLink">500</a></li>
                  <li><a href="javascript:void(0);" class="returnLink">1000</a></li>
               </ul>
            </div>
          </div>
        </div>  
        <div class="form-group" id="enter-group" style="display:none;" isDisplay="false">
          
        </div>
      </div>
      <input type="hidden" id="hiddenRoomNo"/>
    </form>
  </div>
</script>
<!-- 單一對話框 -->
<script type="text/template" id="tpl_dialogue_box">  
  <div class="col-sm-6">
    <div class="box box-success box-solid">
       <div class="box-header with-border">
          <h3 class="box-title" id="box-title"></h3>
          <div class="box-tools pull-right">
            <!-- <button type="button" class="btn btn-box-tool stop"><i class="fa fa-stop"></i></button> -->
            <button type="button" class="btn btn-box-tool remove" data-widget="remove"><i class="fa fa-trash-o"></i></button>
          </div>
       </div>
       <div class="box-body" id="box-body">
          
       </div>
    </div>
  </div>
</script>
<!-- 多對話框 -->
<script type="text/template" id="tpl_dialogue_boxes">  
  <div class="col-sm-6">
    <div class="box box-success box-solid">
       <div class="box-header with-border">
          <h3 class="box-title" id="box-title-{{title}}">{{title}}</h3>
          <div class="box-tools pull-right">            
            <button type="button" class="btn btn-box-tool remove" data-widget="remove"><i class="fa fa-trash-o"></i></button>
          </div>
       </div>
       <div class="box-body" id="box-body-{{title}}">
          
       </div>
    </div>
  </div>
</script>
<!-- meesage -->
<script type="text/template" id="tpl_dialogue_message">
  <p>
    <span class="direct-chat-timestamp pull-left">{{now}} &nbsp;</span>
    {{{message}}}
  </p>
</script>
<!-- rcu類別 -->
<script type="text/template" id="tpl_group">
  <div class="btn-group">
     <button type="button" class="btn btn-success" id="displayGroup" data-index="{{groupArr.[0].groupid}}">{{groupArr.[0].groupname}}</button>
     <button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
     <span class="caret"></span>
     <span class="sr-only">Toggle Dropdown</span>
     </button>
     <ul class="dropdown-menu" role="menu">
        {{#each groupArr}}
          <li><a href="javascript:void(0);" class="groupLink" data="{{groupname}}" data-index="{{groupid}}">{{groupname}}</a></li>
        {{/each}}
     </ul>
  </div>
</script>
<!-- Rcu device -->
<script type="text/template" id="tpl_device">
  <div class="btn-group">
     <button type="button" class="btn btn-success" id="displayDevice">{{deviceArr.[0].device}}</button>
     <button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
     <span class="caret"></span>
     <span class="sr-only">Toggle Dropdown</span>
     </button>
     <ul class="dropdown-menu" role="menu">
        {{#each deviceArr}}
          <li><a href="javascript:void(0);" class="deviceLink" data="{{device}}">{{device}}</a></li>
        {{/each}}
     </ul>
  </div>
</script>
<!-- 房號 tabs -->
<script type="text/template" id="tpl_tabs">
  <div class="nav-tabs-custom">
    <ul class="nav nav-tabs">
      {{#each floorArr}}
          <li {{#ifCond @index 0}} class="active" {{/ifCond}} >
            <a href="#tab_{{@index}}" data-toggle="tab">{{floorNo}}</a>
          </li>
      {{/each}}
    </ul>
    <div class="tab-content">
      {{#each floorArr}}
        <div class="tab-pane {{#ifCond @index 0}}active{{/ifCond}}" id="tab_{{@index}}">
          {{#each roomArr}}
            <button type="button" value="{{roomNo}}" class="btn btn-info selectButton">{{roomNo}}</button>
          {{/each}}
        </div>
      {{/each}}
  </div>
</script>

<%@include file="/admin/pages/include/initial_script.jsp" %> 
<script src="<%=request.getContextPath() %>/admin/js/rcu_test.js"></script>
</html>
  