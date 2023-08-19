package pl.krax.model;

import java.math.BigDecimal;

public class ReimbursementLimitsProperties {

    public static BigDecimal TOTAL_REIMBURSEMENT_LIMIT = new BigDecimal("1000.00");
    public static BigDecimal RECEIPT_TYPE_REIMBURSEMENT_LIMIT = new BigDecimal("500.00");
    public static BigDecimal DISTANCE_REIMBURSEMENT_LIMIT = new BigDecimal("200.00");

    public static BigDecimal getTotalReimbursementLimit() {
        return TOTAL_REIMBURSEMENT_LIMIT;
    }

    public static BigDecimal getReceiptTypeReimbursementLimit() {
        return RECEIPT_TYPE_REIMBURSEMENT_LIMIT;
    }

    public static BigDecimal getDistanceReimbursementLimit() {
        return DISTANCE_REIMBURSEMENT_LIMIT;
    }
}