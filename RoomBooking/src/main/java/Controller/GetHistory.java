package Controller;

import DataAsset.BookingDAO;
import DataAsset.HistoryDAO;
import Utils.StringExtention;
import Utils.Validate;
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
        try {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            HistoryDAO historyDAO = new HistoryDAO();
            HttpSession session = request.getSession();

            User user = (User) session.getAttribute("ACCOUNT_USER");
            BookingDAO bdao = new BookingDAO();

            List<History> histories = historyDAO.GetBookings(user.getId());

            if (histories.isEmpty()) {
                out.println("<tr>"
                        + "<td colspan=\"4\"><h2 class='text-center'>EMPTY HISTORY!!</h2></td>"
                        + "</tr>");
                return;
            }

            for (History history : histories) {
                String cancel;
                String date = StringExtention.ConverDateToString(history.getBookingDate());
                int idBooking = bdao.getId(date, history.getSlotID(), user.getId(), history.getRoomID());
                if (history.getCancelDate() != null) {
                    cancel = "<td>" + history.getCancelDate().toString() + "</td>";
                } else {
                    if (history.isIsCancel()) {
                        cancel = " <td class='text-danger'> Canceled </td>";
                    } else {
                        if (history.isIsUsed() || Validate.isOverTimeBooking(date)) {
                            cancel = " <td> Time was over for cancel </td>";
                        } else {
                            cancel = " <td> <a href='#' onclick='callCencalBooking(\"" + history.getBookingDate() + "\",\"" + history.getSlotID() + "\", \"" + history.getRoomID() + "\")' class='btn btn-dark my-auto text-light nav-link'>Cancel</a> </td>";
                        }
                    }
                }
                out.println("<tr>"
                        + "<td>" + history.getRoomID() + "</td>"
                        + "<td>" + history.getBookingDate().toString() + "</td>"
                        + cancel
                        + "<td> <a href='viewDetailBooking.jsp?IdBooking=" + idBooking + "' class='btn btn-dark my-auto text-light nav-link'>Detail</a> </td>"
                        + "</tr>");
            }
        } catch (Exception e) {
            e.printStackTrace();
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
