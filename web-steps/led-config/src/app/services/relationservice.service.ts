import { Injectable } from '@angular/core';

import {Http} from "@angular/http";
import {Observable} from "rxjs/Rx";
import {Relations} from "../domain/relations/Relations";
import { environment } from '../../environments/environment';

@Injectable()
export class RelationService {

  constructor (private http: Http) {}

  private relationsServerUrl = environment.contextroot+'/server/rest/engine/relations';

  public getRelations() : Observable<Relations>{


    return this.http.get(this.relationsServerUrl)
    // ...and calling .json() on the response to return data
      .map(res => <Relations>res.json())
      //...errors if any
      .catch((error:any) => Observable.throw( 'Server error'));

  }
}



