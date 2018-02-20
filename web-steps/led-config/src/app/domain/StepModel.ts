
import {Model} from "./Model";

import { serialize,deserialize,deserializeAs,serializeAs } from 'cerialize';
import {StepType} from "./StepType";

export class StepModel {

  @serializeAs(Model)
  @deserializeAs(Model)
  public description:string;

  @serialize
  @deserialize
  stepindex:number;

  @serializeAs(Model)
  @deserializeAs(Model)
  models:Array<Model>=[];

  @serializeAs(StepType)
  @deserializeAs(StepType)
  type:StepType;

  @serialize
  modelValue:any;

  @serialize
  @deserialize
  skippable:boolean;

  @serialize
  @deserialize
  configDescription:string;
}
