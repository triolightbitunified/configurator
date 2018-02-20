import {ModelPropertyValue} from "./ModelPropertyValue";

import { deserialize,deserializeAs,serialize,serializeAs } from 'cerialize';
export class ModelProperty {

  @serialize
  @deserialize
  key: string;

  @serializeAs(ModelPropertyValue)
  @deserializeAs(ModelPropertyValue)
  value: ModelPropertyValue;


}
