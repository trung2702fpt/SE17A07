package Controller;

import DataAsset.BookingDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Booking;
import model.EnumSlot;

public class DetailBooked extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        String IdBooking = request.getParameter("idBooking");
        String objectJSON = "";
        try {
            BookingDAO bookingDAO = new BookingDAO();
            ObjectMapper objectMapper = new JsonMapper();
            switch (action) {
                case "getDetail":
                    int id = Integer.parseInt(IdBooking);
                    Booking booking = bookingDAO.getDetailBooking(id);
                    objectJSON = objectMapper.writeValueAsString(booking);
                    break;
                case "check":
                    String slotRequest = request.getParameter("slot");
                    int slotSelected = Integer.parseInt(slotRequest);
                    String timeSlot = EnumSlot.getTimeSlotInt(slotSelected);
                    String dateRequest = request.getParameter("date") + " " + timeSlot;
                    boolean check = bookingDAO.checkForUpdate(slotSelected, dateRequest);
                    if(check){
                        objectJSON = objectMapper.writeValueAsString("true");
                    }else{
                       objectJSON = objectMapper.writeValueAsString("false");
                    }
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("fail");
        }finally{
            response.getWriter().write(objectJSON);
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
