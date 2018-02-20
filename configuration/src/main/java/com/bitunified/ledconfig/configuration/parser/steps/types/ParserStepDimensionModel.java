package com.bitunified.ledconfig.configuration.parser.steps.types;

import com.bitunified.ledconfig.configuration.parser.steps.ParseStep;
import com.bitunified.ledconfig.domain.Dimension;
import com.bitunified.ledconfig.domain.ErrorModel;
import com.bitunified.ledconfig.domain.Model;
import com.bitunified.ledconfig.domain.modeltypes.RealModel;
import com.bitunified.ledconfig.domain.product.ModelResult;
import com.bitunified.ledconfig.parts.Part;

import java.util.List;
import java.util.Set;

public class ParserStepDimensionModel extends ParseStepBase implements ParseStep {


    private final Integer begin;
    private final Integer end;

    private Set<Model> models;
    public ParserStepDimensionModel(Integer i, boolean b, Integer begin,Integer end, Class<? extends Model> model, String regex, String s1,Set<Model> models) {
        super(i,b,model,regex,s1);
        this.begin=begin;
        this.end=end;
        this.models=models;
    }

    private String parse(String productcode) {
        if ((begin==null || end==null)||(begin.equals(end))){
            return null;
        }
        return
                productcode.substring(begin,end);
    }
    @Override
    public ModelResult create(String lengthCode, Set<Part> parts) {
        String lengthCodeParsed=parse(lengthCode);
        for (Model model : models) {
            if (checkModel(model)) {
                RealModel product = (RealModel) model;
                if (lengthCodeParsed != null) {

                            Integer lengthInt=parseInteger(lengthCodeParsed);
                            if (lengthInt!=null) {
                                product.setDimension(new Dimension(lengthInt));
                            }else{
                                setError(true);
                                return new ModelResult(createErrorModel(),getErrorMessage());
                            }


                    return new ModelResult(product);



                }
            }
        }
        setError(true);
        return new ModelResult(createErrorModel(),getErrorMessage());
    }

    private Model createErrorModel(){

        return new ErrorModel();
    }
}
