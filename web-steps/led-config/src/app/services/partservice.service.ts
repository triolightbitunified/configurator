import {Injectable} from "@angular/core";
import {Http, URLSearchParams, Response} from "@angular/http";
import {Observable} from "rxjs/Rx";
import {environment} from "../../environments/environment";
import {Part} from "../domain/server/Part";
import {Serialize, Deserialize} from "cerialize";
import {ProductConfiguration} from "../domain/ProductConfiguration";
import {ModelTranslation} from "../domain/ModelTranslation";
import {PartResult} from "../domain/server/PartResult";
@Injectable()
export class PartService {

  constructor(private http: Http) {
  }

  private partServerUrl = environment.contextroot + '/server/rest/engine/part';

  public getPart(productConfig: ProductConfiguration, currentStep: number): Observable<Part> {
    let params: URLSearchParams = new URLSearchParams();
    params.append("currentStep", String(currentStep));
    return this.http.post(this.partServerUrl, Serialize(productConfig, ProductConfiguration), {search: params})
      .debounceTime(400)
      .map((res: Response) => {
        if (res.status === 200) {
          let m = Deserialize(res.json(), PartResult);
          let p: Part = null;
          switch (m.result) {
            case 'found':
              p = m.part;
              break;
            case "not_found":
              p = this.createMockPart();
              break;
            default:
              break;
          }
          return p;
        }
        if (res.status === 204) {
          return this.createMockPart();
        }
      })

      .catch((error: any) => Observable.throw(error.error || 'Server error'));

  }

  private createMockPart() {
    let part: Part = new Part();
    part.imageUrl = 'assets/images/favicon.png';
    part.translations = new ModelTranslation();
    part.translations.en = "";
    part.description = "Could not be resolved in this stage.";
    return part;
  }
}



