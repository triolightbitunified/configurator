import {Component, OnInit, Input, Output, EventEmitter,ViewEncapsulation} from "@angular/core";
import {StepsModel} from "../../domain/StepsModel";
import {ProductconfigurationService} from "../../services/productconfiguration.service";
import {Subscription} from "rxjs/Subscription";
import {ProductConfiguration} from "../../domain/ProductConfiguration";
import {Relations} from "../../domain/relations/Relations";
import {Observable} from "rxjs/Rx";
import {NotificationService} from "../../services/notificationservice.service";
import {ProductcodeService} from "../../services/productcode.service";
import {ErrorNotificationState} from "../../domain/internal/ErrorNotificationState";
import {NotificationMessage} from "../../domain/internal/NotificationMessage";
import {PriceCalculation} from "../../domain/server/PriceCalculation";
import {ClipboardModule} from "ngx-clipboard";
import {environment} from "../../../environments/environment";

@Component({
  selector: 'menusteps',
  templateUrl: './menusteps.component.html',
  styleUrls: ['./menusteps.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class MenustepsComponent implements OnInit {


  @Input()
  steps: Observable<StepsModel>;

  @Input()
  relations: Observable<Relations>;

  relationsAll: Relations;

  @Input()
  maxSteps: number;

  @Output()
  isProcessing: boolean = false;

  @Output('finished')
  finishEvent = new EventEmitter<PriceCalculation>();

  stepsall: StepsModel;

  currentStep: number;

  private productConfigSubscription: Subscription;


  private productCondeSubscription: Subscription;

  private productconfig: ProductConfiguration;

  enableFinishButton: boolean;

  productcode: String;

  constructor(private productcodeService: ProductcodeService, private productConfigService: ProductconfigurationService, private notificationService: NotificationService) {
    this.productConfigSubscription = productConfigService.productconfigSource$.subscribe(
      res => {
        this.productconfig = res;
      });
    this.productconfig = productConfigService.productConfiguration;

    this.productCondeSubscription = productcodeService.productcodeSource$.subscribe(res=> {
      this.currentStep = res.currentStep;
      this.productcode = res.getCode();
    });


  }

  ngOnInit() {
    this.currentStep = 0;

    const combineRelationSteps: Observable<{rels: Relations,steps: StepsModel}> = Observable.combineLatest(this.relations, this.steps, (rels: Relations, steps: StepsModel)=> {
      return {rels: rels, steps: steps};
    });
    combineRelationSteps.subscribe((combinedRelStep)=> {
      this.evaluateRelations(combinedRelStep.steps, combinedRelStep.rels);
      this.stepsall = combinedRelStep.steps;
      this.relationsAll = combinedRelStep.rels;

    });

  }


  checkFinish(event) {


    for (let stepI of this.stepsall.steps) {
      if (!(stepI.skippable || this.productconfig.containsStep(stepI.stepindex))) {
        this.enableFinishButton = false;
        return false;
      }
    }
    this.enableFinishButton = true;

    return true;
  }
  showPrice(): boolean {
    if (environment.application.show_price) {
      return true;
    }
    return false;
  }
  finish() {

    this.isProcessing = true;
    let subscriptionProductPrice = this.productConfigService.sendProductConfigToServer();
    subscriptionProductPrice.subscribe((productPrice)=> {
      let productPriceCalculated: PriceCalculation = productPrice;
      this.notificationService.notificationMessageAnnouncement(new NotificationMessage("Price calculation received", ErrorNotificationState.INFO));
      this.isProcessing = false;
      this.finishEvent.emit(productPrice);
    });
  }

  evaluateRelations(steps: StepsModel, relations: Relations) {
    for (let step of steps.steps) {
      for (let stepModel of step.models) {
        for (let relation of relations.relations) {
          for (let model of relation.models) {
            if (model.uuid == stepModel.uuid) {
              stepModel.relations.push(relation);
            }
          }
        }
      }
    }
  }


  ngOnDestroy() {
    this.productCondeSubscription.unsubscribe();
  }


}
