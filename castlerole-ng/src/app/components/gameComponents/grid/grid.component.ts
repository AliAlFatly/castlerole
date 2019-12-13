import {
  Component,
  ElementRef,
  EventEmitter,
  Input,
  OnChanges,
  OnInit,
  Output,
  SimpleChanges,
  ViewChild
} from '@angular/core';
import {gridResponse} from '../../../models/response/gridResponse';
import {Vector} from '../../../models/generic/Vector';
import {HttpClient} from '@angular/common/http';
import {GameServiceService} from '../../../services/game/game-service.service';
import {canvasWidth, canvasHeight, elementWidth, elementHeight} from "../../../config";

@Component({
  selector: 'app-grid',
  templateUrl: './grid.component.html',
  styleUrls: ['./grid.component.css']
})
export class GridComponent implements OnChanges {

  @ViewChild('canvas', {static: false}) private canvas: ElementRef;
  @Output() clickEmitter = new EventEmitter<Vector>()
  @Input() coordinates: Vector;
  private targetCoordinates: Vector;
  private canvasElement;
  private ctx: any;
  private zeroX: number;
  private zeroY: number;


  private grid: Array<gridResponse> = new Array<gridResponse>();

  constructor(
    private http: HttpClient,
    private gameService: GameServiceService
  ) {}


  async ngOnInit(){
    this.initialDraw();
    await this.setCanvas();
  }

  async ngOnChanges(changes: SimpleChanges) {
    await this.clearCanvas();
    await this.getGridData();
    this.addClickHandle();
    this.drawCanvas();

  }

  drawCanvas = async () =>{
    this.ctx = (<HTMLCanvasElement>this.canvas.nativeElement).getContext("2d");
    let background = new Image();
    background.src = `assets/empty.png`;
    await this.ctx.drawImage(background, 0, 0, canvasWidth, canvasHeight);
    // for (let j = 0; j < 11; j++){
    //   let img = new Image();
    //   img.src = `assets/player.png`;
    //   await this.ctx.drawImage(img, (j*100), 0 , 10, 100 )
    // }
    for (let i = 0; i < this.grid.length; i++) {
      if (this.grid[i].picture !== "empty") {
        let img = new Image();
        img.src = `assets/${this.grid[i].picture}.png`;
        await this.ctx.drawImage(img, (this.grid[i].x - this.grid[0].x) * elementWidth, (this.grid[i].y - this.grid[0].y) * elementHeight, elementWidth, elementHeight);
      }
    }
  };

  initialDraw = async () => {
    if(this.coordinates.x > -1){
      let gridData = await this.gameService.getGrid(this.coordinates).toPromise();
      this.grid = gridData;
      this.drawCanvas();
    }
    else {
      let response = await this.gameService.getUserCoordinates().toPromise();
      this.coordinates.x = response.x;
      this.coordinates.y = response.y;
      let gridData = await this.gameService.getGrid(this.coordinates).toPromise();
      this.grid = gridData;
      this.drawCanvas();
    }
  };

  getGridData = async () => {
    let gridData = await this.gameService.getGrid(this.coordinates).toPromise();
    this.grid = gridData;
  };

  setCanvas = async () => {
    this.canvasElement = await document.querySelector("canvas");
    this.canvasElement.width = canvasWidth;
    this.canvasElement.height = canvasHeight;
    this.canvasElement.style.width = `${canvasWidth}px`;
    this.canvasElement.style.height = `${canvasHeight}px`;
  }

  clearCanvas = async () => {
    this.ctx = await (<HTMLCanvasElement>this.canvas.nativeElement).getContext("2d");
    await this.ctx.clearRect(0,0,canvasWidth,canvasHeight)
  };

  addClickHandle = async () => {
    this.canvasElement = await document.querySelector("canvas");
    this.zeroX = this.grid[0].x;
    this.zeroY = this.grid[0].y;
    this.canvasElement.addEventListener("click", async (event) => {
      // alert(`${this.zeroX} + ${event.pageX} / ${elementWidth}`)
      this.targetCoordinates = new Vector(this.zeroX + Math.floor((event.pageX / elementWidth)),this.zeroY + Math.floor((event.pageY / elementHeight)));
      await this.clickEmitter.emit(this.targetCoordinates)
    })
  };


  // old draw
  // let img = new Image();
  // img.src = `assets/${this.grid[i].picture}.png`;
  //
  // img.onload = async () => {
  //    await this.ctx.drawImage(img, (this.grid[i].x - this.grid[0].x)*100, (this.grid[i].y - this.grid[0].y)*100, 100, 100);
  //    // this.ctx.fillStyle="Black";
  //    // this.ctx.font = "12px Arial";
  //    // await this.ctx.fillText(`${this.grid[i].x}, ${this.grid[i].y}`,(this.grid[i].x - this.grid[0].x) * (100-10),(this.grid[i].y - this.grid[0].y) * (100-10));
  // }

}
