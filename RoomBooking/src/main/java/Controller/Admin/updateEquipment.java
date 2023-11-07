package Controller.Admin;

import DataAsset.EquipmentDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Equipment;
import model.typeEquipment;


public class updateEquipment extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        int price = Integer.parseInt(request.getParameter("price"));
        int type = Integer.parseInt(request.getParameter("type"));
        String des = request.getParameter("des");
        String name = request.getParameter("name");
        
        EquipmentDAO Edao = new EquipmentDAO();
        Equipment newEquipment = new Equipment(id, name, des, price, new typeEquipment(type));
        ObjectMapper objectMapper = new JsonMapper();
        String objectJSON = "";
        try {
            boolean isSuccess = Edao.update(id, newEquipment);
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
    }
}
