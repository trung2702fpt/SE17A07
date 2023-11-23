package Controller;

import DataAsset.ReportDAO;
import Utils.StringExtention;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Comment;
import model.User;

public class Report extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        ObjectMapper objectMapper = new JsonMapper();
        String objectJSON = "";
        ReportDAO reDao = new ReportDAO();
        int idReport = 0;
        try {
            switch (action) {
                case "search":
                    idReport = Integer.parseInt(request.getParameter("idReport"));
                    objectJSON = objectMapper.writeValueAsString(reDao.read(idReport));
                    break;
                case "edit":
                    idReport = Integer.parseInt(request.getParameter("idReport"));
                    String repContent = request.getParameter("repContent");
                    Comment commentReply = null;
                    if(request.getParameter("user")== null){
                        commentReply = new Comment(true, repContent, StringExtention.GetCurrentDate());
                        reDao.reply(idReport, false);
                    }else{
                        commentReply = new Comment(false, repContent, StringExtention.GetCurrentDate());
                        reDao.reply(idReport, true);
                    }
                    
                    boolean isSuccess = reDao.createComment(commentReply, idReport);
                    if (!isSuccess) {
                        objectJSON = objectMapper.writeValueAsString("fail");
                        break;
                    }
                    
                    break;
                case "getList":
                    List<model.Report> reports = reDao.getList();
                    objectJSON = objectMapper.writeValueAsString(reports);
                    
                    break;
                case "createReport":
                    User user = (User) request.getSession().getAttribute("ACCOUNT_USER");
                    String title = request.getParameter("title");
                    String content = request.getParameter("content");

                    String time = StringExtention.GetCurrentDate();
                    Comment newComment = new Comment(false, content, time);
                    ReportDAO Rdao = new ReportDAO();
                    model.Report sendReport = new model.Report(user.getId(), time, title, true);
                    Rdao.create(sendReport);
                    Rdao.createComment(newComment, 0);
                    break;
                case "getComment":
                    idReport = Integer.parseInt(request.getParameter("idReport"));
                    List<Comment> comments = reDao.getComments(idReport);
                    objectJSON = objectMapper.writeValueAsString(comments);
                    
                    break;
            }
        } catch (Exception e) {
            objectJSON = objectMapper.writeValueAsString("fail");
            e.printStackTrace();
        }finally{
            response.getWriter().write(objectJSON);
        }

    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
