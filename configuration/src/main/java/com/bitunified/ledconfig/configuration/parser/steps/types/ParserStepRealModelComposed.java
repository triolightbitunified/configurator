package com.bitunified.ledconfig.configuration.parser.steps.types;


import com.bitunified.ledconfig.configuration.parser.steps.ParseStep;
import com.bitunified.ledconfig.domain.Dimension;
import com.bitunified.ledconfig.domain.Model;
import com.bitunified.ledconfig.domain.modeltypes.RealModel;
import com.bitunified.ledconfig.domain.product.ModelResult;
import com.bitunified.ledconfig.parts.Part;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ParserStepRealModelComposed extends ParseStepBase implements ParseStep{


    private final Integer dataBegin;
    private final Integer dataEnd;
    private  Set<Model> models=new HashSet<Model>();


    public ParserStepRealModelComposed(Integer step, Class<? extends Model> model, String regex, String errorMessage, Integer dataBegin, Integer dataEnd,Set<Model> models) {
    super(step,false,model,regex,errorMessage);

        this.models=models;
        this.dataBegin = dataBegin;
        this.dataEnd = dataEnd;
    }

    public ModelResult create(String productcode, Set<Part> parts) {

        for (Part part : parts) {
            if (checkModel(part)) {
                RealModel product = createPart(part);

                Integer lengthNumber=null;
                        if (dataEnd != null && dataBegin<productcode.length() && dataEnd<=productcode.length()) {
                            String length = productcode.substring(dataBegin, dataEnd);
                            lengthNumber=parseInteger(length);
                        }

                if (lengthNumber!=null) {
                    product.setDimension(new Dimension(lengthNumber));

                }
                        return new ModelResult(product);
                    }





        }
        setError(true);
        return new ModelResult(getErrorMessage());
    }




}
