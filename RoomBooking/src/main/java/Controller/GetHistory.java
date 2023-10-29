package Controller;

import DataAsset.HistoryDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.History;
import model.User;

public class GetHistory extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HistoryDAO historyDAO = new HistoryDAO();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("ACCOUNT_USER");
        List<History> histories = historyDAO.GetBookings(user.getId());
        if (user == null) {
            out.println("<tr>"
                    + "<td colspan=\"5\"><h2 class='text-center'><a class='btn btn-dark my-auto text-light nav-link' href=\"login.jsp\">Login</a></h2></td>"
                    + "</tr>");
        } else if (!histories.isEmpty()) {
            for (History history : histories) {
                out.println("<tr>"
                        + "<td>" + history.getRoomID() + "</td>"
                        + "<td>" + history.getBookingDate().toString() + "</td>"
                        + "<td>" + history.getSlotID() + "</td>"
                        + "<td>" + history.getCancelDate() + "</td>"
                        + "</tr>");
            }
        } else {
            out.println("<tr>"
                    + "<td colspan=\"5\"><h2 class='text-center'>EMPTY HISTORY!!</h2></td>"
                    + "</tr>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GetHistory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GetHistory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GetHistory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GetHistory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
