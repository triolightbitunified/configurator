package com.bitunified.ledconfig;


import com.bitunified.ledconfig.productconfiguration.price.PriceCount;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

public class PriceCalculator {


    private static final BigDecimal FACTOR = BigDecimal.valueOf(1);

    public BigDecimal calculate(ProductConfigResult productConfigResult) {

        List<BigDecimal> counts = productConfigResult.getPartList().entrySet().stream().map(item -> {
            if (item.getKey() != null && item.getValue() != null) {
                PriceCount pc = new PriceCount();
                pc.setCount(item.getValue());
                pc.setPart(item.getKey());
                return pc;
            }
            return null;
        }).map(pc -> {
            BigDecimal p = pc.getPart() == null ? BigDecimal.ZERO : pc.getPart().getPrice();
            return BigDecimal.valueOf(pc.getCount()).multiply(p);
        })
                .collect(Collectors.toList());

        BigDecimal totalPrice = counts.stream().reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal total = FACTOR.multiply(totalPrice);
        total = total.setScale(2, RoundingMode.HALF_UP);
        System.out.println("TotalPrice: " + total);
        return total;
    }
}
