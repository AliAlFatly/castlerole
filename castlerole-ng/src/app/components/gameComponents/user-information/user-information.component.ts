import {Component, Input, OnInit} from '@angular/core';
import {GameServiceService} from "../../../services/game/game-service.service";
import { userDataResponse } from "../../../models/response/userDataResponse";

@Component({
  selector: 'app-user-information',
  templateUrl: './user-information.component.html',
  styleUrls: ['./user-information.component.css']
})
export class UserInformationComponent implements OnInit {

  //Gathers user data
  private userData: Array<userDataResponse>
  private userName: string;
  private userWood: number;
  private userStone: number;
  private userIron: number;
  private userFood: number;
  private userTroops: number;


  constructor(
  private gameService: GameServiceService) { }

  ngOnInit() {

      this.gameService.getUserData().subscribe(userD => {
              this.userData = userD;
              this.userName = userD.username;
              this.userWood = userD.wood;
              this.userStone = userD.stone;
              this.userIron = userD.iron;
              this.userFood = userD.food;
              this.userTroops = userD.troops;

      });

  }

}
