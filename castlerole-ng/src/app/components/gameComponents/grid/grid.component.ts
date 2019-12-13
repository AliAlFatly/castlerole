import {Component, ElementRef, Input, OnChanges, OnInit, SimpleChanges, ViewChild} from '@angular/core';
import {gridResponse} from '../../../models/response/gridResponse';
import {Vector} from '../../../models/generic/Vector';
import {HttpClient} from '@angular/common/http';
import {GameServiceService} from '../../../services/game/game-service.service';

@Component({
  selector: 'app-grid',
  templateUrl: './grid.component.html',
  styleUrls: ['./grid.component.css']
})
export class GridComponent implements OnChanges {

  @ViewChild('canvas', {static: false})
  private canvas: ElementRef;
  @Input() coordinates: Vector;
  private ctx: any;

  private grid: Array<gridResponse> = new Array<gridResponse>();

  constructor(
    private http: HttpClient,
    private gameService: GameServiceService
  ) {}


  async ngOnInit(){
    if(this.coordinates.x > -1){
      let gridData = await this.gameService.getGrid(this.coordinates).toPromise();
      //alert(this.coordinates.x)
      this.grid = gridData;
      //alert(this.coordinates.x)
      //alert(this.coordinates.y)
      this.drawCanvas();
    }
    else {
      let response = await this.gameService.getUserCoordinates().toPromise();
      this.coordinates.x = response.x;
      this.coordinates.y = response.y;
      let gridData = await this.gameService.getGrid(this.coordinates).toPromise();
      //alert(this.coordinates.x)
      this.grid = gridData;
      //alert(this.coordinates.x)
      //alert(this.coordinates.y)
      this.drawCanvas();
    }
  }

  async ngOnChanges(changes: SimpleChanges) {
      this.ctx = (<HTMLCanvasElement>this.canvas.nativeElement).getContext("2d");
      await this.ctx.clearRect(0,0,1000,1000)
      let gridData = await this.gameService.getGrid(this.coordinates).toPromise();
      //alert(this.coordinates.x)
      this.grid = gridData;
      //alert(this.coordinates.x)
      //alert(this.coordinates.y)
      this.drawCanvas();
  }

  // async ngDoCheck(){
  //   alert(this.coordinates.x)
  //   alert(this.coordinates.y)
  //   this.drawCanvas();
  // }

  drawCanvas = async () =>{
    this.ctx = (<HTMLCanvasElement>this.canvas.nativeElement).getContext("2d");
    //await this.ctx.clearRect(0,0,1000,1000)
    let background = new Image();
    background.src = `assets/empty.png`;
    await this.ctx.drawImage(background, 0, 0, 1000, 1000);
    for (let i = 0; i < this.grid.length; i++) {
      if (this.grid[i].picture !== "empty") {
        let img = new Image();
        img.src = `assets/${this.grid[i].picture}.png`;
        await this.ctx.drawImage(img, (this.grid[i].x - this.grid[0].x) * 100, (this.grid[i].y - this.grid[0].y) * 100, 100, 100);
      }
    }
      // let img = new Image();
      // img.src = `assets/${this.grid[i].picture}.png`;
      //
      // img.onload = async () => {
      //    await this.ctx.drawImage(img, (this.grid[i].x - this.grid[0].x)*100, (this.grid[i].y - this.grid[0].y)*100, 100, 100);
      //    // this.ctx.fillStyle="Black";
      //    // this.ctx.font = "12px Arial";
      //    // await this.ctx.fillText(`${this.grid[i].x}, ${this.grid[i].y}`,(this.grid[i].x - this.grid[0].x) * (100-10),(this.grid[i].y - this.grid[0].y) * (100-10));
      // }

  };

  // ngAfterViewInit() allows us to bring dynamic changes into the canvas. As such, we can render updates to the
  // canvas here, like when a new player joins, etc.

  ngAfterViewInit() {

      // this.ctx = this.canvas.nativeElement.getContext("2d");
      //this.ctx = (<HTMLCanvasElement>this.canvas.nativeElement).getContext("2d");

    // this.gameService.getUserCoordinates().subscribe(vector => {
    //   // TODO: Dirty fix for now, can be better.
    //   // Look into improving and optimalizing this code.
    //   this.ctx = (<HTMLCanvasElement>this.canvas.nativeElement).getContext("2d");
    //   //this.ctx.fillRect(vector.x, vector.y, 20, 20);
    //
    //
    // });

  }

}
