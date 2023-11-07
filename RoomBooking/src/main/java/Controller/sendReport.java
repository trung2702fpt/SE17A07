package Controller;

import DataAsset.ReportDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Report;
import model.User;

public class sendReport extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        int idUser = ((User) session.getAttribute("ACCOUNT_USER")).getId();
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String time = request.getParameter("time");

        ReportDAO Rdao = new ReportDAO();
        Report newReport = new Report(idUser, time, title, content, false);
        ObjectMapper objectMapper = new JsonMapper();
        String objectJSON = "";
        try {
            Rdao.create(newReport);
        } catch (Exception e) {
            objectJSON = objectMapper.writeValueAsString("fail");
            e.printStackTrace();
        }
        response.getWriter().write(objectJSON);
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
    }
}
