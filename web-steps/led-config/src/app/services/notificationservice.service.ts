import { Injectable } from '@angular/core';


import { Subject }    from 'rxjs/Subject';
import {ErrorNotificationState} from "../domain/internal/ErrorNotificationState";
import {NotificationMessage} from "../domain/internal/NotificationMessage";

@Injectable()
export class NotificationService {
  constructor () {}

  private notificationMessageSource = new Subject<NotificationMessage>();

  notificationMessageSource$ = this.notificationMessageSource.asObservable();



  notificationMessageAnnouncement(message: NotificationMessage) {

    this.notificationMessageSource.next(message);
  }


}
