package com.bitunified.ledconfig.configuration.parser.steps.types;


import com.bitunified.ledconfig.configuration.parser.steps.ParseStep;
import com.bitunified.ledconfig.domain.Model;
import com.bitunified.ledconfig.domain.product.ModelResult;
import com.bitunified.ledconfig.parts.Part;

import java.util.Set;

public class ParserStepModelRightParse extends ParserStepModel  {



    public ParserStepModelRightParse(Integer step, boolean mandatory, Integer begin, Integer end, Class<? extends Model> model, String regex, String errorMessage) {
        super(step,mandatory,begin,end,model,regex,errorMessage);

    }

    @Override
    public String parse(String productcode) {
        if (productcode.length()-end<0 || productcode.length()-begin<=0){
            return null;
        }
        return productcode.substring(productcode.length()-end,productcode.length()-begin);
    }


}
