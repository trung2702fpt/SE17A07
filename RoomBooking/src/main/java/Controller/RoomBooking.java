package Controller;

import DataAsset.BookingDAO;
import DataAsset.RoomDAO;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.EnumSlot;
import model.User;
import model.requestBooking;

public class RoomBooking extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        try {
            User user = (User) session.getAttribute("ACCOUNT_USER");
            if(user == null){
                response.sendRedirect("searching.jsp");
                return;
            }
            switch (action) {
                case "Booking":
                    int slotRequest = Integer.parseInt(request.getParameter("slotSelected"));
                    String idRoom = request.getParameter("idRoom");
                    double price = Double.parseDouble(request.getParameter("price"));
                    String date = request.getParameter("date");
                    String slot = EnumSlot.getTimeSlotInt(slotRequest);
                    requestBooking reBooking = new requestBooking(Integer.parseInt(idRoom), date + " " + slot, user.getId(), slotRequest);
                    reBooking.setPriceOfRoom(price);
                    session.setAttribute("RequestBooking", reBooking);
                    request.setAttribute("date", date);
                    request.setAttribute("slot", slot);
                    request.setAttribute("slotRequest", slotRequest);
                    request.setAttribute("idRoom", idRoom);
                    request.setAttribute("isSelectDate", true);
                    request.getRequestDispatcher("SelectEquipment.jsp").forward(request, response);
                    break;
                case "cancel":
                    RoomDAO dAO = new RoomDAO();
                    BookingDAO bdao = new BookingDAO();
                    String dateSearch = request.getParameter("date");
                    int slotCa = Integer.parseInt(request.getParameter("slot"));
                    int roomId = Integer.parseInt(request.getParameter("roomId"));
                    int idUser = user.getId();
                    
                    Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    String dateCancel = formatter.format(cld.getTime());
                    
                    int idbooking = dAO.getIdByDate(dateSearch, slotCa, idUser, roomId);
                    if (bdao.cancelBooking(idbooking)) {
                        bdao.updateAction(dateCancel, dateSearch, idUser ,idbooking);
                    }
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
