
import { deserialize,deserializeAs } from 'cerialize';
import {PriceCount} from "./PriceCount";

export class PriceCalculation{

  @deserialize
  totalPrice:number;

  @deserializeAs(PriceCount)
  prices:Array<PriceCount>;


}
