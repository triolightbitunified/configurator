package com.bitunified.ledconfig.configuration.parser.steps.types;


import com.bitunified.ledconfig.configuration.parser.steps.ParseStep;
import com.bitunified.ledconfig.domain.Model;
import com.bitunified.ledconfig.domain.product.ModelResult;
import com.bitunified.ledconfig.parts.Part;

import java.util.List;
import java.util.Set;

public class ParserStepModel extends ParseStepBase implements ParseStep {


    protected final Integer begin;
    protected final Integer end;

    public ParserStepModel(Integer step, boolean mandatory, Integer begin, Integer end, Class<? extends Model> model, String regex, String errorMessage) {
        super(step,mandatory,model,regex,errorMessage);
        this.begin = begin;
        this.end = end;
    }

    public ModelResult create(String productcode, Set<Part> parts) {
        String code=parse(productcode);
        for (Part part : parts) {
            if (checkModel(part)) {
                Model model = createPartModel(part);
                if (code != null && model!=null) {
                    if (code.equalsIgnoreCase(model.getCode())) {

                        return new ModelResult(model);
                    }
                }
            }
        }
        setError(true);
        return new ModelResult(getErrorMessage());
    }

    public String parse(String productcode) {
        if ((begin==null || end==null)||(begin.equals(end)) || (begin>productcode.length())||(end<begin)){
            return null;
        }
        return productcode.substring(begin,end);
    }


}
