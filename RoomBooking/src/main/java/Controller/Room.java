package Controller;

import DataAsset.RoomDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.EnumSlot;

public class Room extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        ObjectMapper objectMapper = new JsonMapper();
        String objectJSON = "";
        int idRoom = 0;
        RoomDAO roomdao = new RoomDAO();
        try {
            switch (action) {
                case "search":
                    idRoom = Integer.parseInt(request.getParameter("idRoom"));
                    objectJSON = objectMapper.writeValueAsString(roomdao.read(idRoom));
                    
                    break;
                case "edit":
                    idRoom = Integer.parseInt(request.getParameter("idRoom"));
                    int price = Integer.parseInt(request.getParameter("price"));
                    model.Room newRoom = new model.Room(idRoom, price);
                    boolean isSuccess = roomdao.update(idRoom, newRoom);
                    if (!isSuccess) {
                        objectJSON = objectMapper.writeValueAsString("fail");
                    }
                    
                    break;
                case "getList":
                    List<model.Room> Listrooms = roomdao.getList();
                    objectJSON = objectMapper.writeValueAsString(Listrooms);
                    
                    break;
                case "filterRoom":
                    String slotRequest = request.getParameter("slotSelect");
                    int slotSelected = Integer.parseInt(slotRequest);
                    String timeSlot = EnumSlot.getTimeSlotInt(slotSelected);
                    String dateRequest = request.getParameter("dateSelect") + " " + timeSlot;
                    List<model.Room> rooms = roomdao.searchByDateAndSlOT(slotSelected, dateRequest);
                    objectJSON = objectMapper.writeValueAsString(rooms);
                    
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
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
