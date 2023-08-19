package pl.krax.web;

import org.json.JSONArray;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@WebServlet("/receipts")
public class ReceiptsListServlet extends HttpServlet {
    protected static List<String> receiptsList = new ArrayList<>();

    static {
        receiptsList.add("Taxi");
        receiptsList.add("Plane Ticket");
        receiptsList.add("Hotel");
        receiptsList.add("Train");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        JSONArray jsonArray = new JSONArray(receiptsList);
        String json = jsonArray.toString();

        resp.getWriter().write(json);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String action = req.getParameter("action");

        if ("add".equals(action)) {
            String newReceipt = req.getParameter("newReceipt");
            if (newReceipt != null && !newReceipt.isEmpty()) {
                receiptsList.add(newReceipt);
            }
        } else if ("remove".equals(action)) {
            String receiptToRemove = req.getParameter("receiptToRemove");
            if (receiptToRemove != null && !receiptToRemove.isEmpty()) {
                receiptsList.remove(receiptToRemove);
            }
        }

        resp.sendRedirect(req.getContextPath() + "/dashboard");
    }
}