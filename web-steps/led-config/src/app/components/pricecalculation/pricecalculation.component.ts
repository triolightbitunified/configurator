import {Component, OnInit, Inject, Optional} from "@angular/core";
import {PriceCalculation} from "../../domain/server/PriceCalculation";
import {MatDialogRef, MAT_DIALOG_DATA} from "@angular/material";
import {Part} from "../../domain/server/Part";
import { TranslateService } from '@ngx-translate/core';
import {PriceDataSource} from "./PriceDataSource";
import {PriceDatabase} from "./PriceDatabase";
@Component({
  selector: 'pricecalculation',
  templateUrl: './pricecalculation.component.html',
  styleUrls: ['./pricecalculation.component.css']
})
export class PricecalculationComponent implements OnInit {

  priceCalculation: PriceCalculation;
  dataSource: PriceDataSource | null;

  displayedColumns:Array<string>=["id","description","price","amount"];
  constructor(@Optional() @Inject(MAT_DIALOG_DATA) public data: any, public dialogRef: MatDialogRef<PricecalculationComponent>,public translate:TranslateService) {

  }



  ngOnInit() {
    this.priceCalculation = this.data;
    this.dataSource = new PriceDataSource(new PriceDatabase(this.priceCalculation));
  }

  getTranslatedDescription(part:Part){
    return part.getNameTranslated(this.translate.defaultLang);
  }
}
