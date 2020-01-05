import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {GameServiceService} from "../../../services/game/game-service.service";
import {Router} from "@angular/router";
import {Vector} from "../../../models/generic/Vector";
import { UserDataResponse } from "../../../models/response/userDataResponse";

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.css']
})
export class GameComponent implements OnInit {

  coordinates: Vector = new Vector(-1,-1);
  clickTargetCoordinates: Vector = new Vector(-1,-1);
  receivedUserData: UserDataResponse = new UserDataResponse("",-1,-1,-1,-1,-1,-1,-1);

  constructor(
    private http: HttpClient,
    private gameService: GameServiceService
  ) { }

  async ngOnInit() {}

  async getCoordinatesFromNavigator($event){
    this.coordinates = await $event;
  }

  async getTargetFromGridClick($event){
    this.clickTargetCoordinates = await $event;
  }

  async receiveUserCoords($event_two){
  this.receivedUserData = await $event_two;
  }
}
