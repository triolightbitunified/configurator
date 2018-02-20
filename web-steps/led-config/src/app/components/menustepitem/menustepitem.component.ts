import {Component, OnInit, Input, ViewEncapsulation} from "@angular/core";
import {ModelserviceService} from "../../services/modelservice.service";
import {ProductcodeService} from "../../services/productcode.service";
import {MatCheckboxChange} from "@angular/material";
import {Subscription} from "rxjs/Subscription";
import {StepModel} from "../../domain/StepModel";
import {Model} from "../../domain/Model";
import {ProductconfigurationService} from "../../services/productconfiguration.service";
import Utils from "../../utils/Utils";
import {RelationDefinition} from "../../domain/relations/RelationDefinition";
import {RelationState} from "../../domain/relations/RelationState";
import {DisplayRelation} from "../../domain/internal/DisplayRelation";
import {StepType} from "../../domain/StepType";
import {ModelChosenStep} from "../../domain/ModelChosenStep";
import {PartService} from "../../services/partservice.service";
import {Part} from "../../domain/server/Part";
import {ProductConfiguration} from "../../domain/ProductConfiguration";
import {TranslateService} from "@ngx-translate/core";
import {Observable} from "rxjs/Rx";
import {ProductWarningService} from "../../services/productwarning.service";
import {environment} from "../../../environments/environment";
@Component({
  selector: 'menustepitem',
  templateUrl: './menustepitem.component.html',
  styleUrls: ['./menustepitem.component.css'],
  providers: [ModelserviceService, StepModel],
  encapsulation: ViewEncapsulation.None
})
export class MenustepitemComponent implements OnInit {


  @Input()
  set step(step: StepModel) {
    this._step = step;
  }

  get step() {
    return this._step;
  }


  _step: StepModel;

  @Input()
  currentStep: number;

  @Input()
  selectedModel: Model;

  filteredModels: Array<Model> = [];

  displayRelation: DisplayRelation;

  skip: boolean = false;

  checked: boolean = false;


  currentPart: Observable<Part>;


  currentImageUrl: string;


  @Input()
  m: Model;


  private productcodeServiceSubscription: Subscription;

  private productConfigSubscription: Subscription;


  constructor(private productWarningService: ProductWarningService, public translate: TranslateService, private productcodeService: ProductcodeService, private productconfigService: ProductconfigurationService, private partService: PartService) {

     this.productConfigSubscription = productconfigService.productconfigSource$.subscribe(
      res => {
        let selectedModels: Array<Model> = [];
        res.modelsForSteps.forEach((f) => {
          if (f.chosenModel) {
            selectedModels.push(f.chosenModel);
          }
        });

        let filteredModels = this.filter2(this._step.models, this.selectedModel, selectedModels);

        if (filteredModels.length > 0) {
          this.filteredModels = filteredModels;
        } else {
          this.filteredModels = this._step.models;
        }
        if ((selectedModels.length == 1 && selectedModels[0].step == this._step.stepindex)) {
          this.filteredModels = this._step.models;
        }


      });


  }

  ngOnDestroy() {
    this.productcodeServiceSubscription.unsubscribe();
    this.productConfigSubscription.unsubscribe();
  }

  ngOnInit() {
    this.filteredModels = this._step.models;
  }

  subscribePart(model: Model, productConfig: ProductConfiguration, currentStep: number) {
    this.currentPart = this.partService.getPart(productConfig, currentStep);
  }

  checkResetButton() {
    if (this.m) {
      return true;
    }
    return false;
  }


  skipThisStep(value: MatCheckboxChange) {

    this.onInputChange(null);
    this.skip = value.checked;
    if (this._step.skippable) {
      let modelC: ModelChosenStep = this.productconfigService.productConfiguration.getModelChosenFromStep(this._step.stepindex);

      if (modelC) {
        modelC.skipped = value.checked;
      }
    }


    if (value.checked) {
      this.selectedModel = null;

    }

  }

  isStepTypeValues(step: StepModel) {
    if (step && step.type == StepType.VALUES) {
      return true;
    }
    return false;
  }

  isStepTypeNumber(step: StepModel) {
    if (step.type == StepType.NUMBER) {
      return true;
    }
    return false;
  }

  getDescriptionFromPart(part: Part) {
    if (part != null) {

      return part.getNameTranslated(this.translate.defaultLang);
    }
    return "";
  }

  evaluatePrice(part: Part) {
    if (part != null) {
      return part.price;
    }
    return 0;
  }

  showPrice(): boolean {
    if (environment.application.show_price) {
      return true;
    }
    return false;
  }

  getModelTitle(): string {
    let modelC: ModelChosenStep = this.productconfigService.productConfiguration.getModelChosenFromStep(this.step.stepindex);
    if (modelC) {


      let modl: Model = modelC.chosenModel;
      if (modelC && modl) {


        if (this.step.type == StepType.VALUES) {
          return modl.getNameTranslated(this.translate.defaultLang);
        }
        return '' + (this.step.modelValue ? this.step.modelValue : '') + ' mm';
      }
    }
    return '';
  }

  filter2(models: Model[], currentModel: Model, selectedModels: Model[]) {
    let mls: Model[] = [];
    let gatheredModels: Model[] = [];

    let mcount: Map<Model, number> = new Map<Model,number>();
    if (models && selectedModels) {
      for (let model of selectedModels) {
        if ((currentModel != null || currentModel != undefined) && model.uuid == currentModel.uuid) {
          continue;
        }
        for (let modelRel of model.relations) {
          if (modelRel.relationState == RelationState.ALLOWED) {
            for (let modelOfRel of modelRel.models) {
              //optimize by currentStepIndex
              gatheredModels.push(modelOfRel);
            }
          }
        }

      }

      for (let m of gatheredModels) {
        for (let ml of models) {
          if (ml.uuid == m.uuid) {
            mls.push(ml);
            if (mcount.has(ml)) {
              mcount.set(ml, mcount.get(ml) + 1);
            } else {
              mcount.set(ml, 1)
            }
            break;
          }
        }
      }
    }

    let max: number = 0;
    for (let p of Array.from(mcount.values())) {
      if (p > max) {
        max = p;
      }
    }
    let fmls: Array<Model> = [];
    mcount.forEach((v, k)=> {
      if (v == max) {
        fmls.push(k);
      }
    });
    return fmls;
  }


  filter(models: Model[]) {
    let mls: Array<Model> = [];
    let dp: DisplayRelation = new DisplayRelation();
    for (let model of models) {
      let rl = this.filterRelationStateForNextModel(dp, model).relationState;
      if (rl == RelationState.ALLOWED || rl == RelationState.ALLOWEDWITHWARNING || rl == RelationState.ALLOWEDWITHINFO) {
        mls.push(model);
      }
    }
    this.filteredModels = mls;
    return mls;
  }

  getTabColor(m: Model): DisplayRelation {
    this.displayRelation = this.determineRelationState(new DisplayRelation(), m);
    this.productWarningService.productWarningAnnouncement(this.displayRelation);
    return this.displayRelation;
  }

  filterRelationStateForNextModel(displayRelation: DisplayRelation, m: Model) {

    let prevModels: Array<Model> = this.productconfigService.productConfiguration.prevModels();

    prevModels.push(m);
    let allowed: boolean = false;
    if (prevModels.length > 0 && m) {
      let relations: Array<RelationDefinition> = [];
      if (this.currentStep == 0) {
        allowed = true;
      } else {
        if (this.currentStep == 1 && m.step == 1) {
          allowed = true;
        } else {
          relations = Model.relatedRelations(m, prevModels, this.currentStep + 1);

        }
      }
      for (let rel of relations) {
        if (RelationState.ALLOWED == rel.relationState) {
          allowed = true;
          break;
        }
      }
      if (allowed) {

        displayRelation.relationState = RelationState.ALLOWED;
        displayRelation.color = "black";
        return displayRelation;
      }
      displayRelation.relationState = null;
      displayRelation.color = "red";
      return displayRelation;
    }
  }

  determineRelationState(displayRelation: DisplayRelation, m: Model) {

    let prevModels: Array<Model> = this.productconfigService.productConfiguration.prevModels();

    if (prevModels.length > 0 && m) {

      let relations: Array<RelationDefinition> = Model.relatedRelationsForWarning(m, prevModels, this.currentStep - 1);

      let allowed: boolean = false;
      if (this.currentStep <= 2) {
        relations = [];
        allowed = true;
      }
      let allowedWithWarning: boolean = false;
      for (let rel of relations) {
        if (rel.relationState == RelationState.ALLOWEDWITHWARNING) {
          allowedWithWarning = true;
          displayRelation.message = rel.allowedWithMessage;
          break;
        }
      }
      if (allowedWithWarning) {
        displayRelation.relationState = RelationState.ALLOWEDWITHWARNING;
        displayRelation.color = "orange";
        return displayRelation;
      }
      let allowedWithInfo: boolean = false;
      for (let rel of relations) {
        if (rel.relationState == RelationState.ALLOWEDWITHINFO) {
          allowedWithInfo = true;
          displayRelation.message = rel.allowedWithMessage;
          break;
        }
      }
      if (allowedWithInfo) {
        displayRelation.relationState = RelationState.ALLOWEDWITHINFO;
        displayRelation.color = "black";
        return displayRelation;
      }
      for (let rel of relations) {
        if (RelationState.ALLOWED == rel.relationState) {
          allowed = true;
          break;
        }
      }
      if (allowed) {

        displayRelation.relationState = RelationState.ALLOWED;
        displayRelation.color = "black";
        return displayRelation;
      }
      displayRelation.relationState = null;
      displayRelation.color = "red";
      return displayRelation;
    } else {
      displayRelation.relationState = RelationState.ALLOWED;
      displayRelation.color = "black";
      return displayRelation;
    }
  }

  reset() {
    this.m = null;
    this.skip = false;
    this.selectedModel = null;
    //this.filteredModels=null;
    //this.currentPart = null;
    this.deannounceModel();

  }

  deannounceModel() {
    this.productconfigService.deleteModelStep(this._step);
    let code: string = null;
    if (this.selectedModel != null) {
      code = this.selectedModel.code;
    }
    this.productcodeService.productcodeAnnouncement(code, this.step.stepindex);

  }

  onInputChange(value: string) {
    this.step.modelValue = value;
    let valueN = null;
    let valueS = null;
    let codeString = '';
    if (value) {
      valueN = Number(value);
      valueS = value;
      codeString = Utils.padString(valueS, 4);
    }

    let modelToSet = null;
    if (this.step.models.length > 0) {
      modelToSet = this.step.models[0];
    }
    this.productconfigService.productconfigAnnouncement(this.step, modelToSet, valueN);
    this.productcodeService.productcodeAnnouncement(codeString, this.step.stepindex);
  }

  calculateStepValue(step: StepModel) {
    if (step.stepindex == 7) {

      return this.getKeyValueModelFromStep(3, 'section');
    }
    if (step.stepindex == 8) {
      return 1;
    }
  }

  changeSelect(event: Event) {
    if (this.m != undefined) {
      this.selectedModel = this.m;
      this.changeSelection();
      this.subscribePart(this.m, this.productconfigService.productConfiguration, this.selectedModel.step);
    }
  }

  calculateMaxValue(step: StepModel): number {
    let totalMaxLength: number = 2000;

    if (step.stepindex == 8) {
      let modelResinCover: ModelChosenStep = this.productconfigService.productConfiguration.getModelChosenFromStep(2);

      if (modelResinCover && modelResinCover.chosenModel && modelResinCover.chosenModel.maxDimension && modelResinCover.chosenModel.maxDimension.width) {
        totalMaxLength = modelResinCover.chosenModel.maxDimension.width;
        if (step.modelValue > totalMaxLength) {
          step.modelValue = totalMaxLength;

        }
      }
    }
    let totalMaxLengthPCB: number = 0;
    let totalMaxLengthR: number = 0;
    if (step.stepindex == 7) {
      let modelPCB: ModelChosenStep = this.productconfigService.productConfiguration.getModelChosenFromStep(3);
      let modelResinCover: ModelChosenStep = this.productconfigService.productConfiguration.getModelChosenFromStep(2);

      if (modelPCB && modelPCB.chosenModel && modelPCB.chosenModel.maxDimension && modelPCB.chosenModel.maxDimension.width) {
        totalMaxLengthPCB = modelPCB.chosenModel.maxDimension.width;
        totalMaxLength=totalMaxLengthPCB;
        if (step.modelValue > totalMaxLength) {
          step.modelValue = totalMaxLength;
        }
      }
      if (modelResinCover && modelResinCover.chosenModel && modelResinCover.chosenModel.maxDimension && modelResinCover.chosenModel.maxDimension.width) {
        totalMaxLengthR = modelResinCover.chosenModel.maxDimension.width;
        totalMaxLength=totalMaxLengthR;
        if (step.modelValue > totalMaxLength) {
          step.modelValue = totalMaxLength;
        }
      }
      if (totalMaxLengthPCB>0 && totalMaxLengthPCB<totalMaxLengthR){
        totalMaxLength=totalMaxLengthPCB;
        if (step.modelValue > totalMaxLength) {
          step.modelValue = totalMaxLength;
        }
      }

    }
    return totalMaxLength;
  }

  calculateMinValue(step: StepModel) {
    if (step.stepindex == 7) {
      return this.calculateStepValue(step);
    }
    let totalMinLength = 100;
    if (step.stepindex == 8) {
      let model: ModelChosenStep = this.productconfigService.productConfiguration.getModelChosenFromStep(7);
      if (model) {
        let modelMountingEndCaps: ModelChosenStep = this.productconfigService.productConfiguration.getModelChosenFromStep(6);
        let ledstripLength: number = Number(model.modelValue);
        let totalMargins: number = 0;
        if (modelMountingEndCaps && modelMountingEndCaps.chosenModel.margins) {
          totalMargins = modelMountingEndCaps.chosenModel.margins.left + modelMountingEndCaps.chosenModel.margins.right;
        }
        totalMinLength = ledstripLength + Number(totalMargins);
      }
    }
    return totalMinLength;
  }

  private changeSelection() {
    this.selectedModel = this.m;
    let productConfig = this.productconfigService.productconfigAnnouncement(this.step, this.selectedModel, null);
    let curStep = this.step.stepindex;
    if (curStep == 0) {
      curStep = 1;
    }
    this.productcodeService.productcodeAnnouncement(this.selectedModel.code, this.step.stepindex);
    this.getTabColor(this.m);

  }


  getModelName(m: Model) {
    if (m) {
      return m.getNameTranslated(this.translate.defaultLang);
      //return m.name;
    }
    return "";
  }

  getImageUrl(m: Model, currentPart: Part) {
    if (m != null && m.imageUrl != null) {
      return m.imageUrl;
    }
    if (currentPart != null && currentPart.imageUrl != null) {

      return currentPart.imageUrl;
    }
    return null;
  }

  getKeyValueModelFromStep(stepIndex: number, propkey: string): string {
    let model: Model = this.productconfigService.productConfiguration.getModelFromStep(stepIndex);
    if (model) {
      if (model.properties) {
        for (let prop of model.properties) {
          if (prop.name == propkey) {
            return prop.value;
          }
        }
      }
    }
  }

}
