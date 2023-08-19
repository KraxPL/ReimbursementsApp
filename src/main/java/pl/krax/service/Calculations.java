package pl.krax.service;

import pl.krax.model.Receipt;
import pl.krax.model.ReimbursementLimitsProperties;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Calculations {

    public static double MILEAGE_RATE = 0.3;
    public static double DAILY_ALLOWANCE = 15.0;
    public static BigDecimal TOTAL_REIMBURSEMENT_LIMIT = ReimbursementLimitsProperties.getTotalReimbursementLimit();
    public static BigDecimal RECEIPT_TYPE_REIMBURSEMENT_LIMIT = ReimbursementLimitsProperties.getReceiptTypeReimbursementLimit();
    public static BigDecimal DISTANCE_REIMBURSEMENT_LIMIT = ReimbursementLimitsProperties.getDistanceReimbursementLimit();

    public static BigDecimal calculateTotalCost(List<LocalDate> selectedDays, int carMileage, List<Receipt> receipts) {
        BigDecimal dailyAllowanceRate = BigDecimal.valueOf(DAILY_ALLOWANCE);
        BigDecimal carMileageRate = BigDecimal.valueOf(MILEAGE_RATE);
        BigDecimal totalCost = BigDecimal.ZERO;

        totalCost = totalCost.add(dailyAllowanceRate.multiply(BigDecimal.valueOf(selectedDays.size())));

        BigDecimal mileageCost = BigDecimal.valueOf(carMileage).multiply(carMileageRate);
        if (mileageCost.compareTo(DISTANCE_REIMBURSEMENT_LIMIT) > 0) {
            mileageCost = DISTANCE_REIMBURSEMENT_LIMIT;
        }
        totalCost = totalCost.add(mileageCost);

        for (Receipt receipt : receipts) {
            BigDecimal receiptCost = receipt.getPrice();
            if (receiptCost.compareTo(RECEIPT_TYPE_REIMBURSEMENT_LIMIT) > 0) {
                receiptCost = RECEIPT_TYPE_REIMBURSEMENT_LIMIT;
            }
            totalCost = totalCost.add(receiptCost);
        }

        if (totalCost.compareTo(TOTAL_REIMBURSEMENT_LIMIT) > 0) {
            totalCost = TOTAL_REIMBURSEMENT_LIMIT;
        }

        return totalCost;
    }
}
