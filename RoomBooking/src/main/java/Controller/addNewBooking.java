package Controller;

import DataAsset.BookingDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.requestBooking;

public class addNewBooking extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        int idbooking = 0;
        try {
            BookingDAO bdao = new BookingDAO();
            requestBooking booking = (requestBooking) session.getAttribute("RequestBooking");
            if(booking == null){
                response.sendRedirect("home.jsp");
                return;
            }
            if (booking != null) {
                boolean isInserted = bdao.insertRoomBooking(booking);
                if(isInserted){
                    List<model.Equipment> equipments = booking.getEquipments();
                    if(equipments.size() > 0){
                        for (model.Equipment equipment : equipments) {
                            bdao.inssertEquipmentBooking(equipment);
                        }
                    }
                    bdao.inssertPayment(booking);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.removeAttribute("RequestBooking");
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
