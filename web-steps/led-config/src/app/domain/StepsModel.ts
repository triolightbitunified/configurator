import {StepModel} from "./StepModel";

import { deserializeAs } from 'cerialize';
export class StepsModel {

  @deserializeAs(StepModel)
  steps:Array<StepModel>=[];

}
