package com.bitunified.ledconfig.helpers;


import com.bitunified.ledconfig.domain.Model;
import com.bitunified.ledconfig.parts.Part;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.stream.Stream;

public class PartListHelper {

    public static boolean isModelInPartList(Map<Part,Double> partDoubleMap,Part part){
        Stream<Part> ff = partDoubleMap.keySet().stream().filter(f -> {
            if (f.getProduct() != null && part.getProduct()!=null) {
                return f.getProduct().equals(part.getProduct());
            } else {
                if (f.getConfigModel() != null && part.getConfigModel()!=null) {
                    return f.getConfigModel().equals(part.getConfigModel());
                }
            }
            return false;
        });
        return ff.count() > 0;
    }

    public static void addToPartCountList(Map<Part,Double> partDoubleMap,Part part, Double add){
        if (part!=null) {
            Double count = partDoubleMap.get(part);
            if (count == null) {
                count = 0d;
            }

            partDoubleMap.put(part, round(count + add,2));
        }
    }
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
