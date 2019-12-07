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

  async ngOnInit() {
    let response = await this.gameService.getUserCoordinates().toPromise();

    this.initialCoordinate.x = response.x;
    this.initialCoordinate.y = response.y;

    let gridData = await this.gameService.getGrid(this.initialCoordinate).toPromise();

    this.grid = gridData;

    alert(this.grid[0].picture)
    alert(this.initialCoordinate.x)
  }


  // ngAfterViewInit() allows us to bring dynamic changes into the canvas. As such, we can render updates to the
  // canvas here, like when a new player joins, etc.
  ngAfterViewInit() {
    // alert(this.grid[0])
    // alert(this.initialCoordinate.x)
    // alert(this.initialCoordinate.y)
    // alert(this.grid[0].picture)
    this.gameService.getUserCoordinates().subscribe(vector => {
      // TODO: Dirty fix for now, can be better.
      // Look into improving and optimalizing this code.
      this.ctx = (<HTMLCanvasElement>this.canvas.nativeElement).getContext("2d");
      this.ctx.fillRect(vector.x, vector.y, 20, 20);});

    // this.ctx = this.canvas.nativeElement.getContext("2d");
  }
}
