package com.bitunified.ledconfig.configuration.parser.steps;


import com.bitunified.ledconfig.domain.product.ModelResult;
import com.bitunified.ledconfig.parts.Part;

import java.util.List;
import java.util.Set;

public interface ParseStep {

    ModelResult create(String productcode, Set<Part> parts);


    boolean isMantatory();

    Integer getStep();

    void addModelResult(ModelResult createdModel);

    ModelResult getModelResult();

    String getErrorMessage();

    boolean isError();
}
