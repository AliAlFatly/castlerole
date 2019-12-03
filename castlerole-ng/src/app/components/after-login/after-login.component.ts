import { Component, ViewChild, ElementRef, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {AuthenticationService} from "../../services/authentication/authentication.service";
import {GameServiceService} from "../../services/game/game-service.service";
import { HttpClient } from "@angular/common/http";
import { gridResponse } from "../../models/response/gridResponse";
import { Vector } from "../../models/generic/Vector";

@Component({
  selector: 'app-after-login',
  templateUrl: './after-login.component.html',
  styleUrls: ['./after-login.component.css']
})
export class AfterLoginComponent implements OnInit {

  private canvas: ElementRef;
  private ctx: any;
  private grid: Array<gridResponse>;
  private initialCoordinate: Vector;

  constructor(
  private http: HttpClient,
  private gameService: GameServiceService
    ) { }

  ngOnInit() {
    this.gameService.getUserCoordinates().subscribe(vector => {
    alert(vector.x);
    this.initialCoordinate.x = vector.x;
    alert(this.initialCoordinate);
    alert(this.initialCoordinate.x);
    });


    //alert(this.initialCoordinate.y);
    this.gameService.getGrid(this.initialCoordinate).subscribe(gridData =>{
    this.grid = gridData;});
    alert(this.grid);
  }

  //ngAfterViewInit() allows us to bring dynamic changes into the canvas. As such, we can render updates to the
  //canvas here, like when a new player joins, etc.
  ngAfterViewInit() {
    //this.ctx = this.canvas.nativeElement.getContext("2d");
  }
}
