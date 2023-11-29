package Controller;

import DataAsset.BookingDAO;
import DataAsset.RoomDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ViewBooking;
public class ManagementBooking extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        ObjectMapper objectMapper = new JsonMapper();
        BookingDAO bdao = new BookingDAO();
        RoomDAO roomDAO = new RoomDAO();
        String objectJSON = "";
        try {
            switch (action) {
                case "getList":
                    List<ViewBooking> list = bdao.getListBookings();
                    for (ViewBooking viewBooking : list) {
                        int id = roomDAO.getIdByDate(viewBooking.getBookingDate(), 
                                viewBooking.getSlotID(), 
                                viewBooking.getUserId(), 
                                viewBooking.getRoomID());
                        viewBooking.setBookingId(id);
                    }
                    objectJSON = objectMapper.writeValueAsString(list);
                break;
            }
        } catch (Exception e) {
            objectJSON = objectMapper.writeValueAsString("fail");
            e.printStackTrace();
        } finally {
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
