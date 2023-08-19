package pl.krax.service;

import pl.krax.model.Receipt;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Calculations {

    public static double MILEAGE_RATE = 0.3;
    public static double DAILY_ALLOWANCE = 15.0;

    public static BigDecimal calculateTotalCost(List<LocalDate> selectedDays, int carMileage, List<Receipt> receipts) {
        BigDecimal dailyAllowanceRate = BigDecimal.valueOf(DAILY_ALLOWANCE);
        BigDecimal carMileageRate = BigDecimal.valueOf(MILEAGE_RATE);
        BigDecimal totalCost = BigDecimal.ZERO;

        totalCost = totalCost.add(dailyAllowanceRate.multiply(BigDecimal.valueOf(selectedDays.size())));

        totalCost = totalCost.add(BigDecimal.valueOf(carMileage).multiply(carMileageRate));

        for (Receipt receipt : receipts) {
            totalCost = totalCost.add(receipt.getPrice());
        }

        return totalCost;
    }
}
