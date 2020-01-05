import {Component, Input, OnChanges, OnInit, SimpleChanges, Output, EventEmitter} from '@angular/core';
import {GameServiceService} from "../../../services/game/game-service.service";
import { UserDataResponse } from "../../../models/response/userDataResponse";

@Component({
  selector: 'app-user-information',
  templateUrl: './user-information.component.html',
  styleUrls: ['./user-information.component.css']
})
export class UserInformationComponent implements OnChanges {

  private userData = new UserDataResponse("",-1,-1,-1,-1,-1,-1,-1)
  @Output() userCoordsMessage = new EventEmitter<UserDataResponse>();
  // private interval = Interval(1000);


  constructor(private gameService: GameServiceService) { }

  async ngOnInit() {
    await this.updateUserData();
    //this.userCoordsMessage.emit(this.userData);
    this.userCoordsMessage.emit(this.userData)
    await this.sendUserCoords();

  }

  async ngOnChanges(changes: SimpleChanges) {
    await this.updateUserData();
  }

  // async ngDoCheck(){
  //   await this.updateUserData();
  // }

  updateUserData = async () => {
    this.userData = await this.gameService.getUserData().toPromise();
  }

  async sendUserCoords(){
    await this.userCoordsMessage.emit(JSON.parse(JSON.stringify(this.userData)))
  }

}
