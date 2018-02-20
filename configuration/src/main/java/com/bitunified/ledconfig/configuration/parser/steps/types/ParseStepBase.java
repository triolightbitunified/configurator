package com.bitunified.ledconfig.configuration.parser.steps.types;


import com.bitunified.ledconfig.domain.Model;
import com.bitunified.ledconfig.domain.modeltypes.RealModel;
import com.bitunified.ledconfig.domain.product.ModelResult;
import com.bitunified.ledconfig.parts.NotExistingPart;
import com.bitunified.ledconfig.parts.Part;

public abstract class ParseStepBase {



    private final String regex;
    private final Class<? extends Model> modelClass;
    private final boolean mandatory;
    private final Integer step;
    private String errorMessage;
    private ModelResult modelResult;
    private boolean isError;

    public ParseStepBase(Integer step,boolean mandatory, Class<? extends Model> model, String regex, String errorMessage) {
        this.mandatory=mandatory;
        this.modelClass = model;
        this.regex = regex;
        this.errorMessage = errorMessage;
        this.step=step;
    }
    protected Integer parseInteger(String length) {
        try{
            return Integer.parseInt(length);
        }catch(Exception e){
            return null;
        }
    }
    protected boolean checkModel(Model part) {
        if (part!=null && isInstance(part)){
            return true;
        }

        return false;
    }
    protected boolean checkModel(Part part) {

        if (part.getProduct()!=null && isInstance(part.getProduct())){
            return true;
        }
        if (part.getConfigModel()!=null && isInstance(part.getConfigModel())){
            return true;
        }
        return false;
    }

    private boolean isInstance(Model model) {
        if (model.getClass().getName().equals(modelClass.getName()) || modelClass.isAssignableFrom(model.getClass())){
            return true;
        }
        return false;
    }

    public String getRegex() {
        return regex;
    }

    public Class<? extends Model> getModel() {
        return modelClass;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public boolean isError(){
        return isError;
    }
    public Model createPartModel(Part part){

        Model model= part.getConfigModel();
        if (model!=null) {
            model.setStep(step);
        }
        return model;
    }
    public RealModel createPart(Part part){

        RealModel model= part.getProduct();
        if (model!=null) {
            model.setStep(step);
        }
        return model;
    }

    public boolean isMantatory() {
        return mandatory;
    }

    public Integer getStep() {
        return step;
    }



    public ModelResult getModelResult() {
        return modelResult;
    }

    public void addModelResult(ModelResult modelResult) {
        this.modelResult = modelResult;
    }

    public void setError(boolean error) {
        isError = error;
    }
}
