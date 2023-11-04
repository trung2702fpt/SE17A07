package Controller.Admin;

import DataAsset.RoomDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Room;

public class EditRoom extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                response.setContentType("text/html;charset=UTF-8");
        int idRoom = Integer.parseInt(request.getParameter("idRoom"));
        int price = Integer.parseInt(request.getParameter("price"));
        
        RoomDAO roomdao = new RoomDAO();
        Room newRoom = new Room(idRoom, price);
        ObjectMapper objectMapper = new JsonMapper();
        String objectJSON = "";
        try {
            boolean isSuccess = roomdao.update(idRoom, newRoom);
            if(!isSuccess){
               objectJSON = objectMapper.writeValueAsString("fail");
            }
            response.getWriter().write(objectJSON);
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
    }// </editor-fold>

}
