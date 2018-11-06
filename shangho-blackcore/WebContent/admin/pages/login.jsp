<!DOCTYPE html>
<html>
   <head>
      <meta charset="utf-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <title>SIDC-HMI</title>
      <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
      <link rel="stylesheet" href="<%=request.getContextPath() %>/admin/plugins/bootstrap/css/bootstrap.min.css">
      <link rel="stylesheet" href="<%=request.getContextPath() %>/admin/plugins/font-awesome/font-awesome.min.css">
      <link rel="stylesheet" href="<%=request.getContextPath() %>/admin/plugins/ionicons/ionicons.min.css">
      <link rel="stylesheet" href="<%=request.getContextPath() %>/admin/plugins/dist/css/AdminLTE.min.css">
      <link rel="stylesheet" href="<%=request.getContextPath() %>/admin/plugins/iCheck/square/blue.css">
    
   </head>
   <body class="hold-transition login-page" id="login-body">
      <div class="login-box">
         <div class="login-logo">
            <a href="<%=request.getContextPath() %>/index.jsp"><b>SIDC-HMI</b></a>
         </div>
         <!-- /.login-logo -->
         <div class="login-box-body">
            <p class="login-box-msg">Login</p>
            <form id="myForm">
               <div class="form-group has-feedback">
                  <input type="text" class="form-control" placeholder="ID" size="100" id="login-id"/>
                  <span class="glyphicon glyphicon-user form-control-feedback"></span>
               </div>
               <div class="form-group has-feedback">
                  <input type="password" class="form-control" placeholder="Password" size="100" id="login-pwd" />
                  <span class="glyphicon glyphicon-lock form-control-feedback"></span>
               </div>
               <div class="row">
                  <div class="col-xs-8 text-red" >
                     <div id="error-mesage" style="display: none;">
                        Incorrect password or confirmation code entered.
                     </div>
                  </div>
                  <div class="col-xs-4">
                     <button type="button" id="submit" class="btn btn-primary btn-block btn-flat">Sign In</button>
                  </div>
               </div>
            </form>
         </div>
         
      </div>
      <%@include file="/admin/pages/include/initial_script.jsp" %> 
      <script src="<%=request.getContextPath() %>/admin/plugins/iCheck/icheck.min.js"></script>
      <script src="<%=request.getContextPath() %>/admin/js/login.js"></script>
      <script src="<%=request.getContextPath() %>/admin/plugins/jquery-validate/jquery.validate.min.js"></script>
      
   </body>
</html>