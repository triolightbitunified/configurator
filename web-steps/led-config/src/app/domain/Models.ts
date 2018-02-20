import {Model} from "./Model";
import { deserializeAs } from 'cerialize';
export class Models {

  @deserializeAs(Model)
  models: Array<Model>;
}
