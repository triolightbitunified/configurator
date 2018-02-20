import {Model} from "../Model";
import {RelationState} from "./RelationState";

import { serialize,deserialize,deserializeAs,serializeAs } from 'cerialize';
export class RelationDefinition {

  relationState:RelationState=RelationState.ALLOWED;

  @serialize
  @deserialize
  allowedWithMessage:string;

  @serializeAs(Model)
  @deserializeAs(Model)
  models:Array<Model>;

}
