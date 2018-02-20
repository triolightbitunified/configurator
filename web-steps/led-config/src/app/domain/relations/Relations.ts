import {RelationDefinition} from "./RelationDefinition";

import { deserializeAs,serializeAs } from 'cerialize';
export class Relations {

  @serializeAs(RelationDefinition)
  @deserializeAs(RelationDefinition)
  relations:Array<RelationDefinition>;

}
