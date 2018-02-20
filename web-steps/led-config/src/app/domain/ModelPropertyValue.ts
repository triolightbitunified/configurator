
import { deserialize,serialize } from 'cerialize';
export class ModelPropertyValue {


  @serialize
  @deserialize
  name: string;

  @serialize
  @deserialize
  value: string;


}
