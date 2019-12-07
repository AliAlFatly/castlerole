import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {GameServiceService} from "../../services/game/game-service.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.css']
})
export class GameComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
