import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {GameServiceService} from "../../../services/game/game-service.service";
import { UserDataResponse } from "../../../models/response/userDataResponse";
import {Vector} from "../../../models/generic/Vector";

@Component({
  selector: 'app-user-information',
  templateUrl: './user-information.component.html',
  styleUrls: ['./user-information.component.css']
})
export class UserInformationComponent implements OnChanges {

  private userData = new UserDataResponse("",-1,-1,-1,-1,-1,-1,-1)

  @Output() userCoordinatesEmitter = new EventEmitter<Vector>()

  // private interval = Interval(1000);


  constructor(private gameService: GameServiceService) { }

  async ngOnInit() {
    await this.updateUserData();

  }

  async ngOnChanges(changes: SimpleChanges) {
    await this.updateUserData();
  }

  // async ngDoCheck(){
  //   await this.updateUserData();
  // }

  updateUserData = async () => {
    this.userData = await this.gameService.getUserData().toPromise();
    await this.userCoordinatesEmitter.emit(new Vector(this.userData.coordinateX, this.userData.coordinateY))
  }

}
