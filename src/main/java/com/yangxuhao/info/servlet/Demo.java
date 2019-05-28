//package com.yangxuhao.info.servlet;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//
///**
// * Created by yangxvhao on 17-4-23.
// */
//public class Demo extends HttpServlet {
//    Logger logger= LoggerFactory.getLogger(Demo.class);
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
////        logger.info(req.getRequestURI());
////        resp.sendRedirect("https://www.baidu.com");
////        resp.setHeader("12","12");
//        String ip= String.valueOf(req.getAttribute("ip"));
//        resp.setContentType("text/html");
//            PrintWriter out = resp.getWriter();
//                 out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
//                out.println("<HTML>");
//                 out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
//                out.println("  <BODY>");
//                out.print("    This is ");
//                out.print(ip);
//               out.println(", using the GET method");
//                out.println("  </BODY>");
//                out.println("</HTML>");
//              out.flush();
//                 out.close();
//        return;
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//    }
//}
