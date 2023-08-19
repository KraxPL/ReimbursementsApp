package pl.krax.web;

import org.json.JSONArray;
import pl.krax.model.Receipt;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.json.JSONObject;


import static pl.krax.service.Calculations.calculateTotalCost;

@WebServlet("")
public class HomeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/index.jsp");
        requestDispatcher.forward(req, resp);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws  IOException {

        String requestBody = request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);
        JSONObject jsonData = new JSONObject(requestBody);

        List<LocalDate> selectedDays = extractSelectedDays(jsonData.getJSONArray("selectedDays"));
        int carMileage = jsonData.getInt("distance");
        List<Receipt> receipts = extractReceipts(jsonData.getJSONArray("receipts"));

        BigDecimal totalCost = calculateTotalCost(selectedDays, carMileage, receipts);

        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("totalCost", totalCost);

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(jsonResponse);
        out.flush();
    }

    private List<LocalDate> extractSelectedDays(JSONArray jsonArray) {
        return IntStream.range(0, jsonArray.length())
                .mapToObj(i -> LocalDate.parse(jsonArray.getString(i)))
                .collect(Collectors.toList());
    }

    private List<Receipt> extractReceipts(JSONArray jsonArray) {
        return IntStream.range(0, jsonArray.length())
                .mapToObj(jsonArray::getJSONObject)
                .map(receiptObject -> new Receipt(
                        receiptObject.getString("name"),
                        receiptObject.getBigDecimal("price")
                ))
                .collect(Collectors.toList());
    }
}