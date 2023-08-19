package pl.krax.web;

import pl.krax.model.ReimbursementLimitsProperties;
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
        request.setAttribute("totalReimbursementLimit", ReimbursementLimitsProperties.getTotalReimbursementLimit());
        request.setAttribute("receiptTypeReimbursementLimit", ReimbursementLimitsProperties.getReceiptTypeReimbursementLimit());
        request.setAttribute("distanceReimbursementLimit", ReimbursementLimitsProperties.getDistanceReimbursementLimit());

        request.getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BigDecimal currentMileageRate = BigDecimal.valueOf(Calculations.MILEAGE_RATE);
        BigDecimal currentDailyAllowance = BigDecimal.valueOf(Calculations.DAILY_ALLOWANCE);

        String newMileageRateParam = request.getParameter("newMileageRate");
        String newDailyAllowanceParam = request.getParameter("newDailyAllowance");
        String totalReimbursementLimitParam = request.getParameter("totalReimbursementLimit");
        String receiptTypeReimbursementLimitParam = request.getParameter("receiptTypeReimbursementLimit");
        String distanceReimbursementLimitParam = request.getParameter("distanceReimbursementLimit");

        BigDecimal newMileageRate = newMileageRateParam != null && !newMileageRateParam.isEmpty()
                ? new BigDecimal(newMileageRateParam)
                : currentMileageRate;

        BigDecimal newDailyAllowance = newDailyAllowanceParam != null && !newDailyAllowanceParam.isEmpty()
                ? new BigDecimal(newDailyAllowanceParam)
                : currentDailyAllowance;

        BigDecimal newTotalReimbursementLimit = totalReimbursementLimitParam != null && !totalReimbursementLimitParam.isEmpty()
                ? new BigDecimal(totalReimbursementLimitParam)
                : ReimbursementLimitsProperties.TOTAL_REIMBURSEMENT_LIMIT;

        BigDecimal newReceiptTypeReimbursementLimit = receiptTypeReimbursementLimitParam != null && !receiptTypeReimbursementLimitParam.isEmpty()
                ? new BigDecimal(receiptTypeReimbursementLimitParam)
                : ReimbursementLimitsProperties.RECEIPT_TYPE_REIMBURSEMENT_LIMIT;

        BigDecimal newDistanceReimbursementLimit = distanceReimbursementLimitParam != null && !distanceReimbursementLimitParam.isEmpty()
                ? new BigDecimal(distanceReimbursementLimitParam)
                : ReimbursementLimitsProperties.DISTANCE_REIMBURSEMENT_LIMIT;

        Calculations.MILEAGE_RATE = newMileageRate.doubleValue();
        Calculations.DAILY_ALLOWANCE = newDailyAllowance.doubleValue();

        ReimbursementLimitsProperties.TOTAL_REIMBURSEMENT_LIMIT = newTotalReimbursementLimit;
        ReimbursementLimitsProperties.RECEIPT_TYPE_REIMBURSEMENT_LIMIT = newReceiptTypeReimbursementLimit;
        ReimbursementLimitsProperties.DISTANCE_REIMBURSEMENT_LIMIT = newDistanceReimbursementLimit;

        response.sendRedirect(request.getContextPath() + "/dashboard");
    }
}

