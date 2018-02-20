
import { serialize,deserialize } from 'cerialize';
export class ModelDimension {

  @deserialize
  @serialize
  width: number;

  @deserialize
  @serialize
  height: number;

  @deserialize
  @serialize
  depth: number;

  @deserialize
  @serialize
  unit: string

}
