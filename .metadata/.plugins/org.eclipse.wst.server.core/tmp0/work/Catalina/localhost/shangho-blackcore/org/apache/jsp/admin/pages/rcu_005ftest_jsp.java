/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.0.48
 * Generated at: 2018-11-06 02:05:44 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.admin.pages;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class rcu_005ftest_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(5);
    _jspx_dependants.put("/admin/pages/include/header.jsp", Long.valueOf(1540812838173L));
    _jspx_dependants.put("/admin/pages/include/footer.jsp", Long.valueOf(1540813469618L));
    _jspx_dependants.put("/admin/pages/include/left_menu.jsp", Long.valueOf(1540814026942L));
    _jspx_dependants.put("/admin/pages/include/head.jsp", Long.valueOf(1540812838169L));
    _jspx_dependants.put("/admin/pages/include/initial_script.jsp", Long.valueOf(1540812838197L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

final java.lang.String _jspx_method = request.getMethod();
if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
return;
}

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("  ");
      out.write("<meta charset=\"utf-8\">\r\n");
      out.write("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n");
      out.write("<title>SIDC rcu-hmi</title>\r\n");
      out.write("<!-- Tell the browser to be responsive to screen width -->\r\n");
      out.write("<meta content=\"width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no\" name=\"viewport\">\r\n");
      out.write("<!-- Bootstrap 3.3.6 -->\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.print(request.getContextPath() );
      out.write("/admin/plugins/bootstrap/css/bootstrap.min.css\">\r\n");
      out.write("<!-- Font Awesome -->\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.print(request.getContextPath() );
      out.write("/admin/plugins/font-awesome/font-awesome.min.css\">\r\n");
      out.write("<!-- Ionicons -->\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.print(request.getContextPath() );
      out.write("/admin/plugins/ionicons/ionicons.min.css\">\r\n");
      out.write("<!-- Theme style -->\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.print(request.getContextPath() );
      out.write("/admin/plugins/dist/css/AdminLTE.min.css\">\r\n");
      out.write("<!-- AdminLTE Skins. Choose a skin from the css/skins\r\n");
      out.write("     folder instead of downloading all of them to reduce the load. -->\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.print(request.getContextPath() );
      out.write("/admin/plugins/dist/css/skins/_all-skins.min.css\">\r\n");
      out.write("<!-- iCheck -->\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.print(request.getContextPath() );
      out.write("/admin/plugins/iCheck/flat/blue.css\">\r\n");
      out.write("<!-- Morris chart -->\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.print(request.getContextPath() );
      out.write("/admin/plugins/morris/morris.css\">\r\n");
      out.write("<!-- jvectormap -->\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.print(request.getContextPath() );
      out.write("/admin/plugins/jvectormap/jquery-jvectormap-1.2.2.css\">\r\n");
      out.write("<!-- Date Picker -->\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.print(request.getContextPath() );
      out.write("/admin/plugins/datepicker/datepicker3.css\">\r\n");
      out.write("<!-- Daterange picker -->\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.print(request.getContextPath() );
      out.write("/admin/plugins/daterangepicker/daterangepicker.css\">\r\n");
      out.write("<!-- bootstrap wysihtml5 - text editor -->\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.print(request.getContextPath() );
      out.write("/admin/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css\">\r\n");
      out.write("<!-- DataTables -->\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.print(request.getContextPath() );
      out.write("/admin/plugins/datatables/dataTables.bootstrap.css\">\r\n");
      out.write("\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.print(request.getContextPath() );
      out.write("/admin/plugins/bootstrap-switch-master/docs/css/highlight.css\">\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.print(request.getContextPath() );
      out.write("/admin/plugins/bootstrap-switch-master/dist/css/bootstrap3/bootstrap-switch.css\">\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.print(request.getContextPath() );
      out.write("/admin/plugins/bootstrap-switch-master/docs/css/main.css\">\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.print(request.getContextPath() );
      out.write("/admin/plugins/clockpicker-gh-pages/dist/bootstrap-clockpicker.min.css\">\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.print(request.getContextPath() );
      out.write("/admin/plugins/duallistbox/bootstrap-duallistbox.css\">\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.print(request.getContextPath() );
      out.write("/admin/plugins/bootstrap-slider/slider.css\">\r\n");
      out.write("<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->\r\n");
      out.write("<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->\r\n");
      out.write("<!--[if lt IE 9]>\r\n");
      out.write("<script src=\"https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js\"></script>\r\n");
      out.write("<script src=\"https://oss.maxcdn.com/respond/1.4.2/respond.min.js\"></script>\r\n");
      out.write("<![endif]-->\r\n");
      out.write(" \r\n");
      out.write("  <link href=\"");
      out.print(request.getContextPath() );
      out.write("/admin/css/common.css\" rel=\"stylesheet\" type=\"text/css\"/>\r\n");
      out.write("</head>\r\n");
      out.write("<body class=\"hold-transition skin-blue sidebar-mini\">\r\n");
      out.write("<div class=\"wrapper\">\r\n");
      out.write("  <header class=\"main-header\">\r\n");
      out.write("    ");
      out.write("<a href=\"");
      out.print(request.getContextPath() );
      out.write("/index.jsp\" class=\"logo\">\r\n");
      out.write("   <!-- mini logo for sidebar mini 50x50 pixels -->\r\n");
      out.write("   <span class=\"logo-mini\"><b>H</b>MI</span>\r\n");
      out.write("   <!-- logo for regular state and mobile devices -->\r\n");
      out.write("   <span class=\"logo-lg\"><b>SIDC-HMI</b></span>\r\n");
      out.write("</a>\r\n");
      out.write("<!-- Header Navbar: style can be found in header.less -->\r\n");
      out.write("<nav class=\"navbar navbar-static-top\">\r\n");
      out.write("   <!-- Sidebar toggle button-->\r\n");
      out.write("   <a href=\"javascript:void(0);\" class=\"sidebar-toggle\" data-toggle=\"offcanvas\" role=\"button\">\r\n");
      out.write("   <span class=\"sr-only\">Toggle navigation</span>\r\n");
      out.write("   </a>\r\n");
      out.write("   <div class=\"navbar-custom-menu\">\r\n");
      out.write("      <ul class=\"nav navbar-nav\">\r\n");
      out.write("         <!-- Messages: style can be found in dropdown.less-->\r\n");
      out.write("         <!-- Notifications: style can be found in dropdown.less -->\r\n");
      out.write("      </ul>\r\n");
      out.write("   </div>\r\n");
      out.write("</nav>");
      out.write(" \r\n");
      out.write("  </header>\r\n");
      out.write("  <!-- Left side column. contains the logo and sidebar -->  \r\n");
      out.write("  <aside class=\"main-sidebar\">\r\n");
      out.write("    ");
      out.write("<section class=\"sidebar\">\r\n");
      out.write("   <div class=\"user-panel\">\r\n");
      out.write("   </div>\r\n");
      out.write("   <ul class=\"sidebar-menu\">\r\n");
      out.write("      <li>\r\n");
      out.write("         <a href=\"");
      out.print(request.getContextPath() );
      out.write("/index.jsp\">\r\n");
      out.write("         <i class=\"fa fa-dashboard\"></i> <span>監控</span>\r\n");
      out.write("         </a>\r\n");
      out.write("      </li>\r\n");
      out.write("      <li>\r\n");
      out.write("         <a href=\"");
      out.print(request.getContextPath() );
      out.write("/admin/pages/room.jsp\">\r\n");
      out.write("         <i class=\"fa fa-home\"></i> <span>房間資訊</span>\r\n");
      out.write("         </a>\r\n");
      out.write("      </li>\r\n");
      out.write("      <li>\r\n");
      out.write("         <a href=\"");
      out.print(request.getContextPath() );
      out.write("/admin/pages/mode_setting.jsp\">\r\n");
      out.write("         <i class=\"fa fa-cogs\"></i> <span>模式設定</span>\r\n");
      out.write("         </a>\r\n");
      out.write("      </li>\r\n");
      out.write("      <li>\r\n");
      out.write("         <a href=\"");
      out.print(request.getContextPath() );
      out.write("/admin/pages/roommodule_device_setting.jsp\">\r\n");
      out.write("         <i class=\"fa fa-gear\"></i> <span>房型設定</span>\r\n");
      out.write("         </a>\r\n");
      out.write("      </li>\r\n");
      out.write("      <li>\r\n");
      out.write("         <a href=\"");
      out.print(request.getContextPath() );
      out.write("/admin/pages/hvac_control.jsp\">\r\n");
      out.write("         <i class=\"fa fa-asterisk\"></i> <span>冷氣控制</span>\r\n");
      out.write("         </a>\r\n");
      out.write("      </li>\r\n");
      out.write("      <li>\r\n");
      out.write("         <a href=\"");
      out.print(request.getContextPath() );
      out.write("/admin/pages/schedule_control.jsp\">\r\n");
      out.write("         <i class=\"fa fa-gear\"></i> <span>Check Out 排程設定</span>\r\n");
      out.write("         </a>\r\n");
      out.write("      </li>\r\n");
      out.write("      <li>\r\n");
      out.write("         <a href=\"");
      out.print(request.getContextPath() );
      out.write("/admin/pages/room_status.jsp\">\r\n");
      out.write("         <i class=\"fa fa-bed\"></i> <span>客房狀況</span>\r\n");
      out.write("         </a>\r\n");
      out.write("      </li>\r\n");
      out.write("      <li>\r\n");
      out.write("         <a href=\"");
      out.print(request.getContextPath() );
      out.write("/admin/pages/rcu_test.jsp\">\r\n");
      out.write("         <i class=\"fa fa-wrench\"></i> <span>指令測試</span>\r\n");
      out.write("         </a>\r\n");
      out.write("      </li>\r\n");
      out.write("      <li>\r\n");
      out.write("         <a href=\"");
      out.print(request.getContextPath() );
      out.write("/admin/pages/log_write.jsp\">\r\n");
      out.write("         <i class=\"fa fa-file-code-o\"></i> <span>Log設定</span>\r\n");
      out.write("         </a>\r\n");
      out.write("      </li>\r\n");
      out.write("      <li>\r\n");
      out.write("         <a href=\"");
      out.print(request.getContextPath() );
      out.write("/admin/pages/location/category.jsp\">\r\n");
      out.write("         <i class=\"fa fa-file-code-o\"></i> <span>指名區域</span>\r\n");
      out.write("         </a>\r\n");
      out.write("      </li>\r\n");
      out.write("   </ul>\r\n");
      out.write("</section>");
      out.write(" \r\n");
      out.write("  </aside>\r\n");
      out.write("\r\n");
      out.write("  <!-- Content Wrapper. Contains page content -->\r\n");
      out.write("  <div class=\"content-wrapper\">\r\n");
      out.write("    <section class=\"content-header\">\r\n");
      out.write("      <h1>\r\n");
      out.write("        指令測試\r\n");
      out.write("      </h1>\r\n");
      out.write("      \r\n");
      out.write("    </section>\r\n");
      out.write("    <!-- Main content -->\r\n");
      out.write("    <section class=\"content\" id=\"main-content\">\r\n");
      out.write("      \r\n");
      out.write("    </section>\r\n");
      out.write("    <!-- /.content -->\r\n");
      out.write("  </div>\r\n");
      out.write("  <!-- /.content-wrapper -->\r\n");
      out.write("  <footer class=\"main-footer\">\r\n");
      out.write("    ");
      out.write("<div class=\"pull-right hidden-xs\">\r\n");
      out.write("  <b>Version</b> 0.0.1\r\n");
      out.write("</div>\r\n");
      out.write("<strong>\r\n");
      out.write("\tCopyright &copy; 2018 <a href=\"http://www.google.com.tw\">-</a>.\r\n");
      out.write("</strong> All rights\r\n");
      out.write("reserved.");
      out.write(" \r\n");
      out.write("  </footer>\r\n");
      out.write("</div>\r\n");
      out.write("<!-- ./wrapper -->\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("\r\n");
      out.write("<!-- 新增頁面 -->\r\n");
      out.write("<script type=\"text/template\" id=\"tpl_page_add\">\r\n");
      out.write("  <div class=\"row\">\r\n");
      out.write("    <div class=\"col-md-12\">\r\n");
      out.write("      \r\n");
      out.write("        <div id=\"body-model\">\r\n");
      out.write("\r\n");
      out.write("        </div>\r\n");
      out.write("        <!-- submit -->\r\n");
      out.write("        <div class=\"box-footer\">\r\n");
      out.write("          <button type=\"button\" class=\"btn btn-info pull-left\" id=\"stop\"><i class=\"fa a-stop\"></i>Stop</button>\r\n");
      out.write("          <button type=\"button\" class=\"btn btn-info pull-left\" id=\"submit\" style=\"margin-left: 5px;\">Submit</button>\r\n");
      out.write("        </div> \r\n");
      out.write("      \r\n");
      out.write("    </div>\r\n");
      out.write("  </div>\r\n");
      out.write("</script>\r\n");
      out.write("<script type=\"text/template\" id=\"tpl_index\">\r\n");
      out.write("  <div class=\"box box-info\">   \r\n");
      out.write("    <!-- form start -->        \r\n");
      out.write("    <form class=\"form-horizontal\" id=\"myForm\" novalidate=\"novalidate\">    \r\n");
      out.write("      <div class=\"box-body\">\r\n");
      out.write("        <div class=\"form-group\">\r\n");
      out.write("          <label class=\"col-sm-2 text-center\">房號</label>\r\n");
      out.write("          <div class=\"col-sm-10\" id=\"tabs\">\r\n");
      out.write("            \r\n");
      out.write("          </div>\r\n");
      out.write("        </div>        \r\n");
      out.write("        <div class=\"form-group input-option\" style=\"display:none;\">\r\n");
      out.write("          <label class=\"col-sm-2 text-center\">類別</label>\r\n");
      out.write("          <div class=\"col-sm-10\" id=\"group\">\r\n");
      out.write("            \r\n");
      out.write("          </div>\r\n");
      out.write("        </div> \r\n");
      out.write("        <div class=\"form-group input-option\" style=\"display:none;\" id=\"device-div\">\r\n");
      out.write("          <label class=\"col-sm-2 text-center\">設備</label>\r\n");
      out.write("          <div class=\"col-sm-10\" id=\"device\">\r\n");
      out.write("            \r\n");
      out.write("          </div>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"form-group input-option\" style=\"display:none;\">\r\n");
      out.write("          <label class=\"col-sm-2 text-center\">選項</label>\r\n");
      out.write("          <div class=\"col-sm-10\">\r\n");
      out.write("            <div data-toggle=\"buttons\" class=\"function\">\r\n");
      out.write("              <label class=\"btn btn-default active\">\r\n");
      out.write("                <input name=\"function\" value=\"1\" type=\"radio\" checked=\"true\">開\r\n");
      out.write("              </label>\r\n");
      out.write("              <label class=\"btn btn-default\" >\r\n");
      out.write("                <input name=\"function\" value=\"0\" type=\"radio\" >關\r\n");
      out.write("              </label>\r\n");
      out.write("            </div>\r\n");
      out.write("          </div>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"form-group input-option\" style=\"display:none;\">\r\n");
      out.write("          <label class=\"col-sm-2 text-center\">檢查次數</label>\r\n");
      out.write("          <div class=\"col-sm-10\" id=\"returnOption\">\r\n");
      out.write("            <div class=\"btn-group\">\r\n");
      out.write("               <button type=\"button\" class=\"btn btn-success\" id=\"returnCount\">5</button>\r\n");
      out.write("               <button type=\"button\" class=\"btn btn-success dropdown-toggle\" data-toggle=\"dropdown\" aria-expanded=\"false\">\r\n");
      out.write("               <span class=\"caret\"></span>\r\n");
      out.write("               <span class=\"sr-only\">Toggle Dropdown</span>\r\n");
      out.write("               </button>\r\n");
      out.write("               <ul class=\"dropdown-menu\" role=\"menu\">\r\n");
      out.write("                  <li><a href=\"javascript:void(0);\" class=\"returnLink\">5</a></li>\r\n");
      out.write("                  <li><a href=\"javascript:void(0);\" class=\"returnLink\">10</a></li>\r\n");
      out.write("                  <li><a href=\"javascript:void(0);\" class=\"returnLink\">50</a></li>\r\n");
      out.write("                  <li><a href=\"javascript:void(0);\" class=\"returnLink\">100</a></li>\r\n");
      out.write("                  <li><a href=\"javascript:void(0);\" class=\"returnLink\">500</a></li>\r\n");
      out.write("                  <li><a href=\"javascript:void(0);\" class=\"returnLink\">1000</a></li>\r\n");
      out.write("               </ul>\r\n");
      out.write("            </div>\r\n");
      out.write("          </div>\r\n");
      out.write("        </div>  \r\n");
      out.write("        <div class=\"form-group\" id=\"enter-group\" style=\"display:none;\" isDisplay=\"false\">\r\n");
      out.write("          \r\n");
      out.write("        </div>\r\n");
      out.write("      </div>\r\n");
      out.write("      <input type=\"hidden\" id=\"hiddenRoomNo\"/>\r\n");
      out.write("    </form>\r\n");
      out.write("  </div>\r\n");
      out.write("</script>\r\n");
      out.write("<!-- 單一對話框 -->\r\n");
      out.write("<script type=\"text/template\" id=\"tpl_dialogue_box\">  \r\n");
      out.write("  <div class=\"col-sm-6\">\r\n");
      out.write("    <div class=\"box box-success box-solid\">\r\n");
      out.write("       <div class=\"box-header with-border\">\r\n");
      out.write("          <h3 class=\"box-title\" id=\"box-title\"></h3>\r\n");
      out.write("          <div class=\"box-tools pull-right\">\r\n");
      out.write("            <!-- <button type=\"button\" class=\"btn btn-box-tool stop\"><i class=\"fa fa-stop\"></i></button> -->\r\n");
      out.write("            <button type=\"button\" class=\"btn btn-box-tool remove\" data-widget=\"remove\"><i class=\"fa fa-trash-o\"></i></button>\r\n");
      out.write("          </div>\r\n");
      out.write("       </div>\r\n");
      out.write("       <div class=\"box-body\" id=\"box-body\">\r\n");
      out.write("          \r\n");
      out.write("       </div>\r\n");
      out.write("    </div>\r\n");
      out.write("  </div>\r\n");
      out.write("</script>\r\n");
      out.write("<!-- 多對話框 -->\r\n");
      out.write("<script type=\"text/template\" id=\"tpl_dialogue_boxes\">  \r\n");
      out.write("  <div class=\"col-sm-6\">\r\n");
      out.write("    <div class=\"box box-success box-solid\">\r\n");
      out.write("       <div class=\"box-header with-border\">\r\n");
      out.write("          <h3 class=\"box-title\" id=\"box-title-{{title}}\">{{title}}</h3>\r\n");
      out.write("          <div class=\"box-tools pull-right\">            \r\n");
      out.write("            <button type=\"button\" class=\"btn btn-box-tool remove\" data-widget=\"remove\"><i class=\"fa fa-trash-o\"></i></button>\r\n");
      out.write("          </div>\r\n");
      out.write("       </div>\r\n");
      out.write("       <div class=\"box-body\" id=\"box-body-{{title}}\">\r\n");
      out.write("          \r\n");
      out.write("       </div>\r\n");
      out.write("    </div>\r\n");
      out.write("  </div>\r\n");
      out.write("</script>\r\n");
      out.write("<!-- meesage -->\r\n");
      out.write("<script type=\"text/template\" id=\"tpl_dialogue_message\">\r\n");
      out.write("  <p>\r\n");
      out.write("    <span class=\"direct-chat-timestamp pull-left\">{{now}} &nbsp;</span>\r\n");
      out.write("    {{{message}}}\r\n");
      out.write("  </p>\r\n");
      out.write("</script>\r\n");
      out.write("<!-- rcu類別 -->\r\n");
      out.write("<script type=\"text/template\" id=\"tpl_group\">\r\n");
      out.write("  <div class=\"btn-group\">\r\n");
      out.write("     <button type=\"button\" class=\"btn btn-success\" id=\"displayGroup\" data-index=\"{{groupArr.[0].groupid}}\">{{groupArr.[0].groupname}}</button>\r\n");
      out.write("     <button type=\"button\" class=\"btn btn-success dropdown-toggle\" data-toggle=\"dropdown\" aria-expanded=\"false\">\r\n");
      out.write("     <span class=\"caret\"></span>\r\n");
      out.write("     <span class=\"sr-only\">Toggle Dropdown</span>\r\n");
      out.write("     </button>\r\n");
      out.write("     <ul class=\"dropdown-menu\" role=\"menu\">\r\n");
      out.write("        {{#each groupArr}}\r\n");
      out.write("          <li><a href=\"javascript:void(0);\" class=\"groupLink\" data=\"{{groupname}}\" data-index=\"{{groupid}}\">{{groupname}}</a></li>\r\n");
      out.write("        {{/each}}\r\n");
      out.write("     </ul>\r\n");
      out.write("  </div>\r\n");
      out.write("</script>\r\n");
      out.write("<!-- Rcu device -->\r\n");
      out.write("<script type=\"text/template\" id=\"tpl_device\">\r\n");
      out.write("  <div class=\"btn-group\">\r\n");
      out.write("     <button type=\"button\" class=\"btn btn-success\" id=\"displayDevice\">{{deviceArr.[0].device}}</button>\r\n");
      out.write("     <button type=\"button\" class=\"btn btn-success dropdown-toggle\" data-toggle=\"dropdown\" aria-expanded=\"false\">\r\n");
      out.write("     <span class=\"caret\"></span>\r\n");
      out.write("     <span class=\"sr-only\">Toggle Dropdown</span>\r\n");
      out.write("     </button>\r\n");
      out.write("     <ul class=\"dropdown-menu\" role=\"menu\">\r\n");
      out.write("        {{#each deviceArr}}\r\n");
      out.write("          <li><a href=\"javascript:void(0);\" class=\"deviceLink\" data=\"{{device}}\">{{device}}</a></li>\r\n");
      out.write("        {{/each}}\r\n");
      out.write("     </ul>\r\n");
      out.write("  </div>\r\n");
      out.write("</script>\r\n");
      out.write("<!-- 房號 tabs -->\r\n");
      out.write("<script type=\"text/template\" id=\"tpl_tabs\">\r\n");
      out.write("  <div class=\"nav-tabs-custom\">\r\n");
      out.write("    <ul class=\"nav nav-tabs\">\r\n");
      out.write("      {{#each floorArr}}\r\n");
      out.write("          <li {{#ifCond @index 0}} class=\"active\" {{/ifCond}} >\r\n");
      out.write("            <a href=\"#tab_{{@index}}\" data-toggle=\"tab\">{{floorNo}}</a>\r\n");
      out.write("          </li>\r\n");
      out.write("      {{/each}}\r\n");
      out.write("    </ul>\r\n");
      out.write("    <div class=\"tab-content\">\r\n");
      out.write("      {{#each floorArr}}\r\n");
      out.write("        <div class=\"tab-pane {{#ifCond @index 0}}active{{/ifCond}}\" id=\"tab_{{@index}}\">\r\n");
      out.write("          {{#each roomArr}}\r\n");
      out.write("            <button type=\"button\" value=\"{{roomNo}}\" class=\"btn btn-info selectButton\">{{roomNo}}</button>\r\n");
      out.write("          {{/each}}\r\n");
      out.write("        </div>\r\n");
      out.write("      {{/each}}\r\n");
      out.write("  </div>\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath() );
      out.write("/admin/plugins/jQuery/jquery-2.2.3.min.js\"></script>\r\n");
      out.write("\r\n");
      out.write("<!-- Bootstrap 3.3.6 -->\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath() );
      out.write("/admin/plugins/bootstrap/js/bootstrap.min.js\"></script>\r\n");
      out.write("\r\n");
      out.write("<!-- daterangepicker -->\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath() );
      out.write("/admin/plugins/daterangepicker/moment.min.js\"></script>\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath() );
      out.write("/admin/plugins/daterangepicker/daterangepicker.js\"></script>\r\n");
      out.write("<!-- datepicker -->\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath() );
      out.write("/admin/plugins/datepicker/bootstrap-datepicker.js\"></script>\r\n");
      out.write("<!-- Bootstrap WYSIHTML5 -->\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath() );
      out.write("/admin/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js\"></script>\r\n");
      out.write("\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath() );
      out.write("/admin/plugins/datatables/jquery.dataTables.min.js\"></script>\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath() );
      out.write("/admin/plugins/datatables/dataTables.bootstrap.min.js\"></script>\r\n");
      out.write("\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath() );
      out.write("/admin/plugins/handlebars/handlebars-v4.0.5.js\"></script>\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath() );
      out.write("/admin/plugins/bootstrap-switch-master/dist/js/bootstrap-switch.js\"></script>\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath() );
      out.write("/admin/plugins/bootstrap-switch-master/dist/js/bootstrap-switch.js\"></script>\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath() );
      out.write("/admin/plugins/clockpicker-gh-pages/dist/bootstrap-clockpicker.min.js\" type=\"text/javascript\" ></script>\r\n");
      out.write("\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath() );
      out.write("/admin/plugins/duallistbox/jquery.bootstrap-duallistbox.js\" type=\"text/javascript\" ></script>\r\n");
      out.write("\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath() );
      out.write("/admin/plugins/dist/js/app.min.js\"></script>\r\n");
      out.write("\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath() );
      out.write("/admin/plugins/director/director.min.js\"></script>\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath() );
      out.write("/admin/plugins/bootstrap-slider/bootstrap-slider.js\"></script>\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath() );
      out.write("/admin/plugins/underscore/underscore-min.js\"></script>\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath() );
      out.write("/admin/js/common/server_config.js\"></script>\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath() );
      out.write("/admin/js/common/handlebars_helper.js\"></script>");
      out.write(" \r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath() );
      out.write("/admin/js/rcu_test.js\"></script>\r\n");
      out.write("</html>\r\n");
      out.write("  ");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
