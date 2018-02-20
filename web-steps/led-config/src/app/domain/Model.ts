import {ModelDimension} from "./ModelDimension";
import {RelationDefinition} from "./relations/RelationDefinition";
import {ModelTranslation} from "./ModelTranslation";
import {serialize, deserialize, deserializeAs, serializeAs} from "cerialize";
import {ModelMargin} from "./ModelMargin";
import {ModelPropertyValue} from "./ModelPropertyValue";
import {BaseClass} from "./BaseClass";

export class Model extends BaseClass {

  @serialize
  @deserialize
  typeClass: string;


  // --------- Part of Mounting ----------------
  @serializeAs(ModelMargin)
  @deserializeAs(ModelMargin)
  margins: ModelMargin;
  //--------------------------------------------

  @serialize
  @deserialize
  uuid: string;

  @serialize
  @deserialize
  id: string;

  @serialize
  @deserialize
  orientation: string;

  @serialize
  @deserialize
  public name: string;

  @serialize
  @deserialize
  code: string;

  @serialize
  @deserialize
  step: number;

  @serialize
  @deserialize
  imageUrl: string;

  @serialize
  @deserialize
  productPage: string;

  @serializeAs(ModelPropertyValue)
  @deserializeAs(ModelPropertyValue)
  properties: Array<ModelPropertyValue>;

  @serializeAs(ModelTranslation)
  @deserializeAs(ModelTranslation)
  public translations: ModelTranslation;

  // ------------------ Part of RealModel ---------------
  @serializeAs(ModelDimension)
  @deserializeAs(ModelDimension)
  dimension: ModelDimension;

  @serializeAs(ModelDimension)
  @deserializeAs(ModelDimension)
  maxDimension: ModelDimension;

  @serializeAs(ModelMargin)
  @deserializeAs(ModelMargin)
  margin: ModelMargin;
  //---------------------


  @serializeAs(ModelDimension)
  @deserializeAs(ModelDimension)
  lengthForCasting: ModelDimension;
  lengthForCastingStr: string;

  @serialize
  @deserialize
  leftSpace: number;

  @serialize
  @deserialize
  rightSpace: number;

  relations: Array<RelationDefinition> = [];

  static relatedRelations2(m: Model, prevModels: Array<Model>): Array<RelationDefinition> {
    let relatedRelations: Array<RelationDefinition> = [];

    let currentStepModel = m;
    let prevModelInclCurrent: Array<Model> = [];

    for (let prevModel of prevModels) {
      prevModelInclCurrent.push(prevModel);
      if (prevModel && prevModel.relations) {
        for (let relation of prevModel.relations) {
          for (let mrel of relation.models) {
            if (mrel.uuid === currentStepModel.uuid) {
              relatedRelations.push(relation);
            }
          }
        }
      }
    }


    let foundRelations: Array<RelationDefinition> = [];

    for (let rl of relatedRelations) {


         foundRelations.push(rl);



    }
    return foundRelations;
  }


  static relatedRelations(m: Model, prevModels: Array<Model>, currentStep: number): Array<RelationDefinition> {
    let relatedRelations: Array<RelationDefinition> = [];

    let currentStepModel = m;
    let prevModelInclCurrent: Array<Model> = [];

    for (let prevModel of prevModels) {
      prevModelInclCurrent.push(prevModel);
      if (prevModel && prevModel.relations) {
        for (let relation of prevModel.relations) {
          for (let mrel of relation.models) {
            if (mrel.uuid === currentStepModel.uuid) {
              relatedRelations.push(relation);
            }
          }
        }
      }
    }


    let foundRelations: Array<RelationDefinition> = [];

    for (let rl of relatedRelations) {

      let count = Model.countSameModels(prevModelInclCurrent, rl.models);

      if (((count >= 2) || ( count == rl.models.length)) || (currentStep <= 1 && count >= 2)) {
        foundRelations.push(rl);


      }

    }
    return foundRelations;
  }

  static relatedRelationsForWarning(m: Model, prevModels: Array<Model>, currentStep: number): Array<RelationDefinition> {
    let foundRelations: Array<RelationDefinition> = [];
    for (let rl of m.relations) {
      let count = Model.countSameModels(prevModels, rl.models);
      let stepCount=this.countModelsCurrentStep(rl.models);

      if (((count >= currentStep + 1))||(stepCount==count)) {
        foundRelations.push(rl);
      }
    }
    return foundRelations;
  }

  private static containsNumber(steps: Array<number>,step:number) {

    for (let s of steps){
      if (s==step){
        return true;
      }
    }
    return false;
  }
  private static countModelsCurrentStep(models: Array<Model>):number {
    let steps:Array<number>=[];
    if (models.length>0){
      steps.push(models[0].step);
    }

    for (let m of models) {
      if (!this.containsNumber(steps,m.step)){
        steps.push(m.step);
      }
    }
    return steps.length;
  }


  private static countSameModels(models: Array<Model>, prevModels: Array<Model>) {
    let notFound: number = 0;
    for (let m of models) {
      if (!Model.canBeFoundIn(m, prevModels)) {
        notFound++;
      }
    }
    return models.length - notFound;
  }

  private static canBeFoundIn(m: Model, prevModels: Array<Model>) {
    if (m != undefined) {
      for (let pm of prevModels) {
        if (m.uuid === pm.uuid) {
          return true;

        }
      }
    }
    return false;
  }

  public getNameTranslated(defaultLang: string) {

    let name: string = "";
    if (defaultLang == "nl") {
      name = this.translations.nl != undefined ? this.translations.nl : '';
    } else {
      name = this.translations.en != undefined ? this.translations.en : '';
    }
    if (name == "" && this.name != undefined && this.name != null) {
      name = this.name;
    }
    return name;

  }
}

