import {serialize, deserialize} from "cerialize";
export class ModelTranslation {

  @deserialize
  @serialize
  en: string;
  @deserialize
  @serialize
  nl: string;
}
