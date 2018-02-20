import {ErrorNotificationState} from "./ErrorNotificationState";
export class NotificationMessage {
  constructor (msg:string,st:ErrorNotificationState){
    this.message=msg;
    this.state=st;
  }
  state:ErrorNotificationState;
  message:string;
}
