/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2018-06-17 11:55:56 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class registration_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

 String registerStatus = (String) request.getAttribute("registerStatus"); 
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("\n");
      out.write("<head>\n");
      out.write("    <meta charset=\"utf-8\" />\n");
      out.write("    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n");
      out.write("    <title>JBC Music | Join us!</title>\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale = 1.0, maximum-scale=1.0, user-scalable=no\">\n");
      out.write("    <!-- <meta name=\"apple-mobile-web-app-capable\" content=\"yes\" /> -->\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" media=\"screen\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("./../css/register.css\" />\n");
      out.write("    <!-- <LINK REL=STYLESHEET HREF=\"../css/PDS_style.css\"> -->\n");
      out.write("</head>\n");
      out.write("\n");
      out.write("<body>\n");
      out.write("\n");
      out.write("    <section id=\"form\">\n");
      out.write("        <!-- action=\"/action_page.php\" -->\n");
      out.write("        <form action=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/register\" method=\"POST\" >\n");
      out.write("            <div class=\"container\">\n");
      out.write("                <h1>Sign Up</h1>\n");
      out.write("                <p>Please fill in this form to create an account.</p>\n");
      out.write("                <hr>\n");
      out.write("\n");
      out.write("\n");
      out.write("                <label for=\"username\">\n");
      out.write("                    <b>Username</b>\n");
      out.write("                </label>\n");
      out.write("                <div class=\"tooltip\">\n");
      out.write("                    <input type=\"text\" name=\"username\" id=\"username\" placeholder=\"Username\" required>\n");
      out.write("                    <span class=\"tooltiptext\">Enter your username</span>\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("                <label for=\"email\">\n");
      out.write("                    <b>Email</b>\n");
      out.write("                </label>\n");
      out.write("                <div class=\"tooltip\">\n");
      out.write("                <input type=\"text\" placeholder=\"Enter Email\" name=\"email\" required>\n");
      out.write("                <span class=\"tooltiptext\">Enter your email</span>\n");
      out.write("                </div>\n");
      out.write("                <label for=\"psw\">\n");
      out.write("                    <b>Password</b>\n");
      out.write("                </label>\n");
      out.write("\n");
      out.write("                <input type=\"password\" placeholder=\"Enter Password\" name=\"psw\" required>\n");
      out.write("\n");
      out.write("                <label for=\"psw-repeat\">\n");
      out.write("                    <b>Repeat Password</b>\n");
      out.write("                </label>\n");
      out.write("                <input type=\"password\" placeholder=\"Repeat Password\" name=\"psw-repeat\" required>\n");
      out.write("                <!--\n");
      out.write("          <label>\n");
      out.write("            <input type=\"checkbox\" checked=\"checked\" name=\"remember\" style=\"margin-bottom:15px\"> Remember me\n");
      out.write("          </label> -->\n");
      out.write("                <br>\n");
      out.write("                <label for=\"account-type\">\n");
      out.write("                    <b>Account type</b>\n");
      out.write("                </label>\n");
      out.write("                <div class=\"tooltip\">\n");
      out.write("                <select name=\"account-type\" class=\"dropdown-select\" required>\n");
      out.write("                    <option value=\"\" disabled selected>-----------</option>\n");
      out.write("                    <option value=\"personal\">Person</option>\n");
      out.write("                    <option value=\"company\">Company</option>\n");
      out.write("                </select>\n");
      out.write("                <span class=\"tooltiptext last\">Enter your account type</span>\n");
      out.write("                 </div>\n");
      out.write("                <p>By creating an account you agree to our\n");
      out.write("                    <a href=\"#\" style=\"color:dodgerblue\">Terms & Privacy</a>.</p>\n");
      out.write("\n");
      out.write("                <div class=\"clearfix\">\n");
      out.write("                    <!-- <button type=\"button\" class=\"cancelbtn\">Cancel</button> -->\n");
      out.write("                    <button type=\"submit\" class=\"signupbtn\" > Sign Up </button>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </form>\n");
      out.write("    </section>\n");
      out.write("\n");
      out.write("    <script>\n");
      out.write("        let response =");
      out.print( registerStatus );
      out.write(";\n");
      out.write("\n");
      out.write("        function getRegisterResponse() {\n");
      out.write("\n");
      out.write("            console.log(response);\n");
      out.write("            console.log(\"click\");\n");
      out.write("            alert(response);\n");
      out.write("\n");
      out.write("            return true;\n");
      out.write("\n");
      out.write("        }\n");
      out.write("    </script>\n");
      out.write("    ");
      out.write("\n");
      out.write("</body>\n");
      out.write("\n");
      out.write("</html>\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
