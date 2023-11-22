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
                    response.getWriter().write(objectJSON);
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
                        response.getWriter().write(objectJSON);
                        break;
                    }
                    response.getWriter().write(objectJSON);
                    break;
                case "getList":
                    getList(request, response, reDao);
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
                    response.getWriter().write(objectJSON);
                    break;
            }
        } catch (Exception e) {
            objectJSON = objectMapper.writeValueAsString("fail");
            response.getWriter().write(objectJSON);
            e.printStackTrace();
        }

    }

    private void getList(HttpServletRequest request, HttpServletResponse response, ReportDAO reDao) throws IOException, SQLException, ClassNotFoundException {
        PrintWriter out = response.getWriter();
        String type = request.getParameter("type");
        User user = (User) request.getSession().getAttribute("ACCOUNT_USER");
        List<model.Report> reports = reDao.getList();
        if (type.equals("user")) {
            for (model.Report report : reports) {
                if (report.getUserID() == user.getId()) {
                    String re = " <td>NONE</td>\n";
                    String show = report.isIsReaded() ? "none" : "block";
                    
                    out.println("<tr class=\"candidates-list\">\n"
                            + " <td>" + report.getReportID() + "</td>\n"
                            + " <td id='report_"+report.getReportID()+"'>" + report.getTitle() + " </td>\n"
                            + " <td>" + report.getTime() + "</td>\n"
                            + "<td> <a href=\"#\" onclick=\"viewChat("+report.getReportID()+")\" class=\"btn btn-dark my-auto text-light nav-link position-relative\">View\n" +
                                "<span class=\"badge badge-danger badge-pill position-absolute\" style=\"top: 0; right: 0; display: "+show+";\" id=\"notification-dot\">!</span></a> </td>"
                            +"</tr>");
                }
            }
        } else {
            for (model.Report report : reports) {
                String status = " <td class='text-success'> Answered </td>\n";
                if (report.isIsNewComment()) {
                    status = " <td class='text-danger'> Not Yet </td>\n";
                }
                out.println("<tr class=\"candidates-list\" onclick='searchReport(" + report.getReportID() + ")'>\n"
                        + " <td>" + report.getReportID() + "</td>\n"
                        + " <td>" + report.getTitle() + " </td>\n"
                        + " <td>" + report.getTime() + "</td>\n"
                        + status
                        + "</tr>");
            }
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
