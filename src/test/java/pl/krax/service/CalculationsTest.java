package pl.krax.service;

import org.junit.jupiter.api.Test;
import pl.krax.model.Receipt;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CalculationsTest {

    @Test
    public void testCalculateTotalCostWithoutReceipts() {
        List<LocalDate> selectedDays = Arrays.asList(
                LocalDate.of(2023, 8, 1),
                LocalDate.of(2023, 8, 2),
                LocalDate.of(2023, 8, 3)
        );
        int carMileage = 100;
        List<Receipt> receipts = Arrays.asList();

        BigDecimal totalCost = Calculations.calculateTotalCost(selectedDays, carMileage, receipts);
        BigDecimal expectedCost = BigDecimal.valueOf(3 * Calculations.DAILY_ALLOWANCE)
                .add(BigDecimal.valueOf(carMileage).multiply(BigDecimal.valueOf(Calculations.MILEAGE_RATE)));

        assertEquals(expectedCost, totalCost);
    }

    @Test
    public void testCalculateTotalCostWithReceipts() {
        List<LocalDate> selectedDays = Arrays.asList(
                LocalDate.of(2023, 8, 4),
                LocalDate.of(2023, 8, 5)
        );
        int carMileage = 150;
        List<Receipt> receipts = Arrays.asList(
                new Receipt("Taxi", BigDecimal.valueOf(20.0)),
                new Receipt("Hotel", BigDecimal.valueOf(100.0))
        );

        BigDecimal totalCost = Calculations.calculateTotalCost(selectedDays, carMileage, receipts);
        BigDecimal expectedCost = BigDecimal.valueOf(2 * Calculations.DAILY_ALLOWANCE)
                .add(BigDecimal.valueOf(carMileage).multiply(BigDecimal.valueOf(Calculations.MILEAGE_RATE)))
                .add(BigDecimal.valueOf(20.0).add(BigDecimal.valueOf(100.0)));

        assertEquals(expectedCost, totalCost);
    }
    @Test
    public void testCalculateTotalCostWithNoSelectedDaysAndNoReceipts() {
        List<LocalDate> selectedDays = new ArrayList<>();
        int carMileage = 0;
        List<Receipt> receipts = new ArrayList<>();

        BigDecimal expectedCost = BigDecimal.valueOf(0.0);
        BigDecimal actualCost = Calculations.calculateTotalCost(selectedDays, carMileage, receipts);

        assertEquals(expectedCost, actualCost);
    }

    @Test
    public void testCalculateTotalCostWithSelectedDaysAndNoCarMileage() {
        List<LocalDate> selectedDays = new ArrayList<>();
        selectedDays.add(LocalDate.of(2023, 8, 1));
        selectedDays.add(LocalDate.of(2023, 8, 2));
        int carMileage = 0;
        List<Receipt> receipts = new ArrayList<>();

        BigDecimal expectedCost = BigDecimal.valueOf(30.0);
        BigDecimal actualCost = Calculations.calculateTotalCost(selectedDays, carMileage, receipts);

        assertEquals(expectedCost, actualCost);
    }
}