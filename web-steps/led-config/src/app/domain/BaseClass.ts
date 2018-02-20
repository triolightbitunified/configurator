import {ModelDimension} from "./ModelDimension";
import {RelationDefinition} from "./relations/RelationDefinition";
import {ModelTranslation} from "./ModelTranslation";
import {serialize, deserialize, deserializeAs, serializeAs} from 'cerialize';
import {ModelMargin} from "./ModelMargin";
import {ModelPropertyValue} from "./ModelPropertyValue";

export class BaseClass {

  @serialize
  @deserialize
  typeClass: string;

}
