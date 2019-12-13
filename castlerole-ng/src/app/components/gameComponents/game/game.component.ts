import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {GameServiceService} from "../../../services/game/game-service.service";
import {Router} from "@angular/router";
import {Vector} from "../../../models/generic/Vector";

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.css']
})
export class GameComponent implements OnInit {

  coordinates: Vector = new Vector(-1,-1);
  message = this.coordinates.x.toString();

  //private initialCoordinate: Vector = new Vector(0,0);
  constructor(
    private http: HttpClient,
    private gameService: GameServiceService
  ) { }

  async ngOnInit() {
    // let response = await this.gameService.getUserCoordinates().toPromise();
    //
    // this.coordinates.x = response.x;
    // this.coordinates.y = response.y;

  }

  getCoordinatesFromNavigator($event){
    this.coordinates = $event;
    //this.message = this.coordinates.x.toString()
  }
}
