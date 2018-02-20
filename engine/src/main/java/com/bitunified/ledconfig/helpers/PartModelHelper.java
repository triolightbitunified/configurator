package com.bitunified.ledconfig.helpers;


import com.bitunified.ledconfig.domain.Model;
import com.bitunified.ledconfig.parts.Part;

public class PartModelHelper {

    public static Part getPart(java.util.Map<String, Part> parts, Class<? extends Model> clazz, String name) {
        for (Part part : parts.values()) {
            Model model = part.getProduct();
            if (model != null && model.getClass().isAssignableFrom(clazz)) {
                if (model.getName().contains(name)) {
                    return part;
                }
            }
        }
        return null;
    }

    public static Part getPart(java.util.Map<String, Part> parts, Class<? extends Model> clazz, Model modelToFind, Model modelMatch) {
        for (Part part : parts.values()) {
            Model model = part.getProduct();
            if (model != null && model.getClass().isAssignableFrom(clazz)) {
                if (modelMatch != null) {
                    if (model.equals(modelToFind) && model.equals(modelMatch)) {
                        return part;
                    }
                } else {
                    if (model.equals(modelToFind)) {
                        return part;
                    }

                }
            }
        }
        return null;
    }
}
