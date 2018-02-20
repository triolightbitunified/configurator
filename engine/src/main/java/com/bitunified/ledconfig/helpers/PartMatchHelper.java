package com.bitunified.ledconfig.helpers;


import com.bitunified.ledconfig.domain.Model;
import com.bitunified.ledconfig.domain.Orientation;
import com.bitunified.ledconfig.parts.Part;
import com.bitunified.ledconfig.parts.Relatable;

import java.util.*;

public class PartMatchHelper {

    private static <T,F> void findPartsFromModel(List<Part> endCaps, Model model, Orientation orientation, T modelClass,F inAssinableFrom) {
        System.out.println("Match model:" + model);
        if (model.getRelation() != null) {
            Set<Relatable> relations = null;
            if (orientation == Orientation.Left) {
                relations = model.getRelation().getRelateToLeft();
            } else {
                relations = model.getRelation().getRelateToRight();
            }
            for (Relatable relation : relations) {
                if (relation instanceof Part) {
                    if (((Part) relation).getProduct() != null) {
                        if (modelClass != null) {
                            if ((((Part) relation).getProduct().getClass().equals(modelClass))){
                                endCaps.add((Part) relation);
                            }
                        }
                        if (inAssinableFrom != null) {
                            if (((Class<?>) inAssinableFrom).isAssignableFrom(((Part) relation).getProduct().getClass()) ){
                                endCaps.add((Part) relation);
                            }
                        }
                    }
                }
            }
        }
    }

    private static PartMostWanted findMatches(List<Part> endCaps, Orientation orientation) {
        Map<Part, Integer> map = new HashMap<Part, Integer>();

        for (Part temp : endCaps) {
            Integer count = map.get(temp);
            map.put(temp, (count == null) ? 1 : count + 1);
        }
        Integer ci = 0;
        for (Integer i : map.values()) {
            if (i > ci) {
                ci = i;
            }
        }

        PartMostWanted endCapMostWanted
                = new PartMostWanted();
        for (Map.Entry<Part, Integer> entry : map.entrySet()) {
            if (entry.getValue().equals(ci)) {
                endCapMostWanted.part = entry.getKey();
                endCapMostWanted.count = ci;
                System.out.println("H" + ci + " part for " + orientation + ": " + entry.getKey());
            }
            System.out.println("Key : " + entry.getKey() + " Value : "
                    + entry.getValue());
        }
        return endCapMostWanted;
    }

    public static class PartMostWanted {
        int count;
        Part part;

        public int getCount() {
            return count;
        }

        public Part getPart() {
            return part;
        }

        @Override
        public String toString() {
            return "Count: "+count+" Part: "+(part!=null?part.toString():"");
        }
    }

    public static <T,F> Map<Orientation, PartMostWanted> match(T modelClass, F inAssinableFrom,Model... models) {
        List<Part> endCapsLeft = new ArrayList<>();
        List<Part> endCapsRight = new ArrayList<>();


        for (Model model : models) {
            findPartsFromModel(endCapsLeft, model, Orientation.Left, modelClass,inAssinableFrom);
            findPartsFromModel(endCapsRight, model, Orientation.Right, modelClass,inAssinableFrom);
        }


        PartMostWanted mostWantedLeft = findMatches(endCapsLeft, Orientation.Left);
        PartMostWanted mostWantedRight = findMatches(endCapsRight, Orientation.Right);

        Map<Orientation, PartMostWanted> result = new HashMap<>();
        result.put(Orientation.Left, mostWantedLeft);
        result.put(Orientation.Right, mostWantedRight);
        return result;
    }
}
