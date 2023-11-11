package Controller;

import java.io.IOException;
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
        try {
            int slotRequest = Integer.parseInt(request.getParameter("slotSelected"));
            String idRoom = request.getParameter("idRoom");
            double price = Double.parseDouble(request.getParameter("price"));
            String date = request.getParameter("date");
            String slot = EnumSlot.getTimeSlotInt(slotRequest);
            User user = (User) session.getAttribute("ACCOUNT_USER");
            requestBooking reBooking = new requestBooking(Integer.parseInt(idRoom), date+" "+slot, user.getId());
            reBooking.setPriceOfRoom(price);
            session.setAttribute("RequestBooking", reBooking);
            request.setAttribute("date", date);
            request.setAttribute("slot", slot);
            request.setAttribute("slotRequest", slotRequest);
            request.setAttribute("idRoom", idRoom);
            request.setAttribute("isSelectDate", true);
            request.getRequestDispatcher("SelectEquipment.jsp").forward(request, response);
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
