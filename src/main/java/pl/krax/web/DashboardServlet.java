package pl.krax.web;

import pl.krax.service.Calculations;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("currentMileageRate", Calculations.MILEAGE_RATE);
        request.setAttribute("currentDailyAllowance", Calculations.DAILY_ALLOWANCE);
        request.setAttribute("receiptsList", ReceiptsListServlet.receiptsList);

        request.getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        BigDecimal newMileageRate = new BigDecimal(request.getParameter("newMileageRate"));
        BigDecimal newDailyAllowance = new BigDecimal(request.getParameter("newDailyAllowance"));

        Calculations.MILEAGE_RATE = newMileageRate.doubleValue();
        Calculations.DAILY_ALLOWANCE = newDailyAllowance.doubleValue();

        response.sendRedirect(request.getContextPath() + "/dashboard");
    }
}

