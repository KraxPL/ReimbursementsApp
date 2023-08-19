package pl.krax.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class Receipt {
    private String name;
    private BigDecimal price;
}
