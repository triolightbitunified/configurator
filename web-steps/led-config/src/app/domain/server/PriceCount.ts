import {Part} from "./Part";

import { deserialize,deserializeAs } from 'cerialize';
export class PriceCount {

  @deserialize
  count:number;

  @deserializeAs(Part)
  part:Part;
}
