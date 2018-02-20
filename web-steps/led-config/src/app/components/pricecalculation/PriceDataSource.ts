import {DataSource} from "@angular/cdk/collections";
import {Observable} from "rxjs/Observable";
import {PriceAmountItem} from "./PriceAmountItem";
import {PriceDatabase} from "./PriceDatabase";
export class PriceDataSource extends DataSource<any> {
  constructor(private priceDatabase: PriceDatabase) {
    super();
  }

  /** Connect function called by the table to retrieve one stream containing the data to render. */
  connect(): Observable<PriceAmountItem[]> {
    return this.priceDatabase.dataChange;
  }

  disconnect() {
  }
}
