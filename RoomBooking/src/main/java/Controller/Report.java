package Controller;

import DataAsset.ReportDAO;
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
                    String emailUser = request.getParameter("emailUser");
                    String repContent = request.getParameter("repContent");
                    model.Report newReport = new model.Report(idReport, true, repContent, emailUser);
                    boolean isSuccess = reDao.update(idReport, newReport);
                    if (!isSuccess) {
                        objectJSON = objectMapper.writeValueAsString("fail");
                    }
                    response.getWriter().write(objectJSON);
                    break;
                case "getList":
                    getList(request, response, reDao);
                    break;
                case "sendReport":
                    User user = (User) request.getSession().getAttribute("ACCOUNT_USER");
                    String title = request.getParameter("title");
                    String content = request.getParameter("content");

                    Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    String time = formatter.format(cld.getTime());

                    ReportDAO Rdao = new ReportDAO();
                    model.Report sendReport = new model.Report(user.getId(), time, title, content, false);
                    Rdao.create(sendReport);
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
                    if(report.getReply() != null){
                        re = " <td> <a href='#' onclick='U.messageBox(\"Message\", \""+report.getReply()+"\");' class='btn btn-dark my-auto text-light nav-link'>View</a> </td>";
                    }
                    out.println("<tr class=\"candidates-list\">\n"
                            + " <td>" + report.getReportID() + "</td>\n"
                            + " <td>" + report.getTitle() + " </td>\n"
                            + " <td>" + report.getContent() + "</td>\n"
                            + " <td>" + report.getTime() + "</td>\n"
                            + re
                            +"</tr>");
                }
            }
        } else {
            for (model.Report report : reports) {
                String status = " <td class='text-success'> Answered </td>\n";
                if (!report.getStatus()) {
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
