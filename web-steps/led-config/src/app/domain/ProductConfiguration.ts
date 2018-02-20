import {ModelChosenStep} from "./ModelChosenStep";
import {Model} from "./Model";
import {StepModel} from "./StepModel";
import { serializeAs } from 'cerialize';
export class ProductConfiguration {

  @serializeAs(ModelChosenStep)
  public modelsForSteps: Array<ModelChosenStep> = [];

  containsStep(step: number) {
    let found: boolean = false;
    if (step) {
      for (let i of this.modelsForSteps) {
        if ((i.step && i.step.stepindex == step) || (i.skipped && i.step.skippable)) {
          found = true;
          break;
        }
      }

    }

    return found;
  }

  getModelChosenFromStep(step: number):ModelChosenStep {
    if (step) {
      for(let ms of this.modelsForSteps) {

        if (ms.step && ms.step.stepindex == step) {
          return ms;
        }
      }

    }

    return null;
  }

  getModelFromStep(step: number):Model {
    if (step) {
      for(let ms of this.modelsForSteps) {

        if (ms.step && ms.step.stepindex == step) {
          return ms.chosenModel;
        }
      }

    }

    return null;
  }

  prevModels() {
    let models: Array<Model> = [];
    for (let modelStep of this.modelsForSteps) {
      if ((!modelStep.skipped) && modelStep.chosenModel!=null) {
        models.push(modelStep.chosenModel);
      }
    }
    return models;
  }

  deleteModel(step:StepModel){
    if (this.modelsForSteps != undefined) {
      let mix=null;
      for (let i of this.modelsForSteps) {
        if (i.step && i.step.stepindex == step.stepindex) {

          mix=i;
          break;
        }
      }
      if (mix){
        this.modelsForSteps=this.modelsForSteps.filter(obj => obj !== mix);
      }
    }
  }

  assignModel(step: StepModel, model: Model, value: number) {
    let found: ModelChosenStep = null;
    if (this.modelsForSteps != undefined) {
      for (let i of this.modelsForSteps) {
        if (i.step && i.step.stepindex == step.stepindex) {
          if (model != null) {
            i.chosenModel = model;
            i.modelValue=value;
          } else {
            i.skipped=false;
            if (model==null && value == null){
              i.skipped=true;
            }
            i.modelValue = value;
          }
          found = i;
        }
      }
      if (!found) {
        let mc = new ModelChosenStep();
        if (model != null) {
          mc.chosenModel = model;
          mc.modelValue=value;
        } else {
          mc.skipped=false;
          if (model==null && value == null){
            mc.skipped=true;
          }
          mc.modelValue = value;
        }
        mc.step = step;
        this.modelsForSteps.push(mc);
      }
    }
  }

}
