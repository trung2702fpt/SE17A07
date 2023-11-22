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
                    response.getWriter().write(objectJSON);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("fail");
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
