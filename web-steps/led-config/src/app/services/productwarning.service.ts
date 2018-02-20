import {Injectable} from "@angular/core";
import {Subject} from "rxjs/Subject";
import {DisplayRelation} from "../domain/internal/DisplayRelation";
@Injectable()
export class ProductWarningService {

  private productWarningSource = new Subject<DisplayRelation>();

  productWarningSource$ = this.productWarningSource.asObservable();


  constructor() {
  }

  productWarningAnnouncement(productWarning: DisplayRelation) {

    this.productWarningSource.next(productWarning);

  }


}
