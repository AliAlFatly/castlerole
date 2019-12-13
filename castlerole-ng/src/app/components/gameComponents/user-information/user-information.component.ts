import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {GameServiceService} from "../../../services/game/game-service.service";
import { UserDataResponse } from "../../../models/response/userDataResponse";

@Component({
  selector: 'app-user-information',
  templateUrl: './user-information.component.html',
  styleUrls: ['./user-information.component.css']
})
export class UserInformationComponent implements OnChanges {

  private userData = new UserDataResponse("",-1,-1,-1,-1,-1,-1,-1)

  constructor(private gameService: GameServiceService) { }

  async ngOnInit() {
    await this.updateUserData();

  }

  async ngOnChanges(changes: SimpleChanges) {
    await this.updateUserData();
  }

  updateUserData = async () => {
    this.userData = await this.gameService.getUserData().toPromise();
  }

}
