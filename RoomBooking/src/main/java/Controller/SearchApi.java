package Controller;

import DataAsset.RoomDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Room;

public class SearchApi extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        String dateRequest = request.getParameter("dateSelect");
        String slotRequest = request.getParameter("slotSelect");
        int slotSelected = Integer.parseInt(slotRequest);
        RoomDAO db = new RoomDAO();
        try {
            List<Room> rooms = db.searchByDateAndSlOT(slotSelected, dateRequest);
            PrintWriter out = response.getWriter();
            int index = 1;
            for (Room o : rooms) {
                out.println(getHTMLText(o, index++));
            };
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getHTMLText(Room o, int index) {
        return "<tr class=\"candidates-list\">\n"
                + "                                    <td>" + index + "</td>\n"
                + "                                    <td class=\"title\">\n"
                + "                                        <div class=\"thumb\">\n"
                + "                                            <img class=\"img-fluid w-25\" src=\"inlcude/asset/images/gallery1.jpg\" alt=\"\">\n"
                + "                                        </div>\n"
                + "                                    </td>\n"
                + "                                    <td>" + o.getRoomNumber() + "</td>\n"
                + "                                    <td>" + o.getPrice() + "</td>\n"
                + "                                    <td>\n"
                + "                                        <a type=\"button\" href='#' onclick='openDialog(" + o.getId() + ")' class=\"btn btn-primary\" data-toggle=\"modal\"\n"
                + "            data-target=\".bd-example-modal-lg\">Booking</a>\n"
                + "                                    </td>\n"
                + "                                </tr>";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(SearchApi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(SearchApi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
