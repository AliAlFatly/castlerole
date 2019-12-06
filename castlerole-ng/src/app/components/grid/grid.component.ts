import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {gridResponse} from '../../models/response/gridResponse';
import {Vector} from '../../models/generic/Vector';
import {HttpClient} from '@angular/common/http';
import {GameServiceService} from '../../services/game/game-service.service';

@Component({
  selector: 'app-grid',
  templateUrl: './grid.component.html',
  styleUrls: ['./grid.component.css']
})
export class GridComponent implements OnInit {

  @ViewChild('canvas', {static: false})
  private canvas: ElementRef;

  private ctx: any;

  private grid: Array<gridResponse> = new Array<gridResponse>();
  private initialCoordinate: Vector = new Vector(0,0);



  private x = 0;
  private y = 0;
  constructor(
    private http: HttpClient,
    private gameService: GameServiceService
  ) {}

  ngOnInit() {
    this.gameService.getUserCoordinates().subscribe(vector => {
      // alert(vector.x);
      // alert(vector.y);
      this.initialCoordinate.x = vector.x;
      this.initialCoordinate.y = vector.y;
    });//


    // alert(this.initialCoordinate.y);
    this.gameService.getGrid(this.initialCoordinate).subscribe(gridData => {
      this.grid = gridData;
    });
  }

  // ngAfterViewInit() allows us to bring dynamic changes into the canvas. As such, we can render updates to the
  // canvas here, like when a new player joins, etc.
  ngAfterViewInit() {
    this.gameService.getUserCoordinates().subscribe(vector => {
      // TODO: Dirty fix for now, can be better.
      // Look into improving and optimalizing this code.
      this.ctx = (<HTMLCanvasElement>this.canvas.nativeElement).getContext("2d");
      this.ctx.fillRect(vector.x, vector.y, 20, 20);});

    // this.ctx = this.canvas.nativeElement.getContext("2d");
  }
}
