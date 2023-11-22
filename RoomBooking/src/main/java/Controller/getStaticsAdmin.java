/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DataAsset.Admin;
import DataAsset.UserDAO;
import Utils.StringExtention;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class getStaticsAdmin extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Admin ad = new Admin();
        try {
            int getStatic = 0;
            UserDAO dAO = new UserDAO();
            HttpSession session = request.getSession();
            Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            String date = formatter.format(cld.getTime());
            date += " 00:00:00";
            String currentTime = StringExtention.GetCurrentDate();
            getStatic = ad.getBookedDailyCount(date);
            session.setAttribute("bookedDaily", getStatic);
            session.setAttribute("countUser", dAO.getCountUser());
            session.setAttribute("countbookedYear", ad.getBookedYearCount());
            session.setAttribute("persentCancel", ad.getpPersenCancel());
            session.setAttribute("feadBackInDay", ad.getFeedbackInDay(date));
            session.setAttribute("feadBackInYear", ad.getFeedbackInYear());
            session.setAttribute("roomUsed", ad.getBookedUsed(date, currentTime));
            
            request.setAttribute("getStatics", true);
        } catch (Exception e) {
            System.out.println(e);
        }
        request.getRequestDispatcher("managing.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
