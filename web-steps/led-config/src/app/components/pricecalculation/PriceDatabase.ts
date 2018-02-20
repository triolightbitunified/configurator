import {PriceAmountItem} from "./PriceAmountItem";
import {BehaviorSubject} from "rxjs/BehaviorSubject";
import {PriceCalculation} from "../../domain/server/PriceCalculation";
export class PriceDatabase {
  dataChange: BehaviorSubject<PriceAmountItem[]> = new BehaviorSubject<PriceAmountItem[]>([]);

  get data(): PriceAmountItem[] {
    return this.dataChange.value;
  }

  constructor(public priceCalculation: PriceCalculation) {

    let prices: Array<PriceAmountItem> = [];
    for (let p of priceCalculation.prices) {
      let priceItem:PriceAmountItem = {id: p.part.id, description: p.part.getNameTranslated('en'),price: String(p.part.price),amount: String(p.count)};
      prices.push(priceItem);

    }
    this.dataChange.next(prices);

  }
}
