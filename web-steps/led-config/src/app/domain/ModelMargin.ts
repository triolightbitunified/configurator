
import { serialize,deserialize } from 'cerialize';
export class ModelMargin {

  @deserialize
  @serialize
  left: number;

  @deserialize
  @serialize
  right: number;
}
