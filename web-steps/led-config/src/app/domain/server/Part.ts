import {deserialize,serializeAs,deserializeAs} from "cerialize";
import {ModelTranslation} from "../ModelTranslation";
export class Part {

  @deserialize
  price: number;

  @deserialize
  description: string;

  @deserialize
  id: string;

  @deserialize
  type: string;

  @deserialize
  imageUrl:string;

  @deserialize
  productPage:string;

  @serializeAs(ModelTranslation)
  @deserializeAs(ModelTranslation)
  translations: ModelTranslation;

  public getNameTranslated(defaultLang:string){

    let name: string = "";
    if (defaultLang == "nl") {
      name = this.translations.nl != undefined ? this.translations.nl : '';
    } else {
      name = this.translations.en != undefined ? this.translations.en : '';
    }
    if (name == "" && this.description != undefined && this.description != null) {
      name = this.description;
    }
    return name;

  }
}
