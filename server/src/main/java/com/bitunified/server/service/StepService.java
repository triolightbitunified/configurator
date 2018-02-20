package com.bitunified.server.service;

import com.bitunified.ledconfig.composedproduct.ComposedProduct;
import com.bitunified.ledconfig.configuration.parser.steps.Data;
import com.bitunified.ledconfig.configuration.parser.steps.Parser;
import com.bitunified.ledconfig.domain.Model;
import com.bitunified.ledconfig.domain.modeltypes.RealModel;
import com.bitunified.ledconfig.productconfiguration.steps.Step;
import com.bitunified.ledconfig.productconfiguration.steps.StepType;
import com.bitunified.ledconfig.productconfiguration.steps.Steps;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class StepService {


    public StepService(){

    }

    public Steps getSteps() {

        Steps steps = new Steps();

        Map<Integer, List<Model>> groupedModels = Data.parser.getModels().stream().filter(f -> f.getStep() != null).collect(Collectors.groupingBy(Model::getStep));

        List<String> configDescriptions = Stream.of("Profile","Cover or PU Resin","PCB or LED strip","Cable","Cable Entry","End Cap(s)"," PCB or LED Strip Length").collect(Collectors.toList());

        List<Step> stepListValues = groupedModels.entrySet().stream().filter(f -> f.getKey() <= 6 ).map(t -> new Step(t.getKey(), t.getKey() == null ? "" : "Step " + t.getKey().toString(), t.getValue(),configDescriptions.get(t.getKey()-1))).collect(Collectors.toList());
        List<Step> stepListNumbers = new ArrayList<>();

        stepListNumbers.add(new Step(7, "Step 7", new ArrayList<>(), StepType.NUMBER,false,"PCB or LED Strip Length"));
        List<Model> composedProduct=new ArrayList<>();
        composedProduct.add(new ComposedProduct());
        stepListNumbers.add(new Step(8, "Step 8", composedProduct, StepType.NUMBER,true,"Total Product length"));

        List<Step> stepAccessoires = groupedModels.entrySet().stream().filter(f -> f.getKey() ==9 ).map(t -> new Step(t.getKey(), t.getKey() == null ? "" : "Step " + t.getKey().toString(), t.getValue(),StepType.VALUES,true,"Accessoires")).collect(Collectors.toList());

        List<Step> allSteps = new ArrayList<>();
        allSteps.addAll(stepListValues);
        allSteps.addAll(stepListNumbers);
        allSteps.addAll(stepAccessoires);
        steps.setSteps(allSteps);
        return steps;
    }

    private static RealModel getStep(RealModel t) {
        return null;
    }


}
