import {Injectable} from '@angular/core';

import {Http} from "@angular/http";
import {Observable} from "rxjs/Rx";
import {StepsModel} from "../domain/StepsModel";
import {Deserialize} from 'cerialize';
import {environment} from '../../environments/environment';
@Injectable()
export class StepsService {
  constructor(private http: Http) {
  }

  // private instance variable to hold base url
  private stepsServerUrl = environment.contextroot + '/server/rest/engine/steps';

  public getSteps(): Observable<StepsModel> {


    return this.http.get(this.stepsServerUrl)
    // ...and calling .json() on the response to return data
      .map(res => {
        console.info(res.json());
        return Deserialize(res.json(), StepsModel);

      })

      //...errors if any
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));

  }

}
