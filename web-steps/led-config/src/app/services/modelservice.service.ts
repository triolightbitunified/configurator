import {Injectable} from "@angular/core";
import {Http} from "@angular/http";
import {Observable} from "rxjs";
import {Models} from "../domain/Models";
import {Deserialize} from "cerialize";
import {NotificationService} from "./notificationservice.service";
import {ErrorNotificationState} from "../domain/internal/ErrorNotificationState";
import {NotificationMessage} from "../domain/internal/NotificationMessage";
import { environment } from '../../environments/environment';

@Injectable()
export class ModelserviceService {

  constructor(private http: Http, private notificationService: NotificationService) {
  }

  // private instance variable to hold base url
  private modelsServerUrl = environment.contextroot +'/server/rest/engine/models';

  public getModels(): Observable<Models> {


    return this.http.get(this.modelsServerUrl)
    // ...and calling .json() on the response to return data
      .map(res => {


        let m = Deserialize(res.json(), Models);

        return m;

      })
      .do(data => console.log(data))
      //...errors if any
      .catch((error: any) => {

        this.notificationService.notificationMessageAnnouncement(new NotificationMessage("Error while receiving data from server.", ErrorNotificationState.ERROR));
        return Observable.throw(error.json().error || 'Server error');
      });

  }
}



