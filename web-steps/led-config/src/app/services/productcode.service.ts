import { Injectable } from '@angular/core';
import { Subject }    from 'rxjs/Subject';
import {ProductCodeComposition} from "../domain/ProductCodeComposition";

@Injectable()
export class ProductcodeService {

  private productcodeSource = new Subject<ProductCodeComposition>();

  productcodeSource$ = this.productcodeSource.asObservable();

  productCodeComposition:ProductCodeComposition=new ProductCodeComposition();

  productcodeAnnouncement(code: string,step:number) {
    if (this.productCodeComposition.codes!==undefined) {
      this.productCodeComposition.codes[step] = code;
    }else{
      this.productCodeComposition.codes=["-"];
    }
    if (this.productCodeComposition.currentStep-step==-1) {
      this.productCodeComposition.currentStep = this.productCodeComposition.currentStep +1;


    }
    this.productcodeSource.next(this.productCodeComposition);

  }
  productcodeRecall(step:number) {

    if (this.productCodeComposition.currentStep==step) {
      if (this.productCodeComposition.codes !== undefined) {
        this.productCodeComposition.codes[this.productCodeComposition.currentStep] = null;
        this.productCodeComposition.currentStep = this.productCodeComposition.currentStep - 1;
      } else {
        this.productCodeComposition.codes = [""];
        this.productCodeComposition.currentStep = 0;
      }

      this.productcodeSource.next(this.productCodeComposition);
    }
  }
}
