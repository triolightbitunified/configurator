import {StepModel} from "./StepModel";
import {Model} from "./Model";
import { serialize,serializeAs,deserialize,deserializeAs } from 'cerialize';
export class ModelChosenStep {

  @serializeAs(StepModel)
  @deserializeAs(StepModel)
  public step:StepModel;

  @serializeAs(Model)
  @deserializeAs(StepModel)
  chosenModel:Model;

  @serialize
  @deserialize
  modelValue:number;

  @serialize
    @deserialize
  skipped:boolean;
}
