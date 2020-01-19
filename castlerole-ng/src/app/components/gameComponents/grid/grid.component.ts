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
import {GridResponse} from '../../../models/response/gridResponse';
import {Vector} from '../../../models/generic/Vector';
import {HttpClient} from '@angular/common/http';
import {GameServiceService} from '../../../services/game/game-service.service';
import {canvasWidth, canvasHeight, elementWidth, elementHeight, halfScreenHeight, halfScreenWidth} from '../../../config';

@Component({
  selector: 'app-grid',
  templateUrl: './grid.component.html',
  styleUrls: ['./grid.component.css']
})
export class GridComponent implements OnChanges {

  @ViewChild('canvas', {static: false}) private canvas: ElementRef;
  @Output() clickEmitter = new EventEmitter<Vector>();
  @Input() coordinates: Vector;
  private targetCoordinates: Vector;
  private canvasElement;
  private ctx: any;  // = (<HTMLCanvasElement>this.canvas.nativeElement).getContext("2d");
  private zeroX: number;
  private zeroY: number;


  private grid: Array<GridResponse> = new Array<GridResponse>();

  constructor(
    private http: HttpClient,
    private gameService: GameServiceService
  ) {}


  // tslint:disable-next-line:use-lifecycle-interface
  async ngOnInit() {
    this.initialDraw();
    await this.setCanvas();
  }

  async ngOnChanges(changes: SimpleChanges) {
    // await this.clearCanvas();
    await this.getGridData();
    this.addClickHandle();
    await this.drawCanvas();

  }

  drawCanvas = async () => {
    this.ctx = (this.canvas.nativeElement as HTMLCanvasElement).getContext('2d');
    await this.ctx.clearRect(0, 0, canvasWidth, canvasHeight);
    const background = new Image();
    background.src = `assets/empty.png`;
    await this.ctx.drawImage(background, 0, 0, canvasWidth, canvasHeight);
    // for (let j = 0; j < 11; j++){
    //   let img = new Image();
    //   img.src = `assets/player.png`;
    //   await this.ctx.drawImage(img, (j*100), 0 , 10, 100 )
    // }
    // tslint:disable-next-line:prefer-for-of
    for (let i = 0; i < this.grid.length; i++) {
      if (this.grid[i].picture !== 'empty') {
        const img = new Image();
        img.src = `assets/${this.grid[i].picture}.png`;
        await this.ctx.drawImage(img, (this.grid[i].x - (this.coordinates.x - halfScreenWidth)) * elementWidth,
          (this.grid[i].y - (this.coordinates.y - halfScreenHeight)) * elementHeight, elementWidth, elementHeight);
      }
    }
  }

  // todo: set private functions for unit tests scope
  // todo: tests van canvas ->
  initialDraw = async () => {
    if (this.coordinates.x > -1) {
      const gridData = await this.gameService.getGrid(this.coordinates).toPromise();
      this.grid = gridData;
      this.drawCanvas();
    } else {
      const response = await this.gameService.getUserCoordinates().toPromise();
      this.coordinates.x = await response.x;
      this.coordinates.y = await response.y;
      // this.getGridData();
      const gridData = await this.gameService.getGrid(this.coordinates).toPromise();
      this.grid = await gridData;
      await this.drawCanvas();
    }
  }

  getGridData = async () => {
    const gridData = await this.gameService.getGrid(this.coordinates).toPromise();
    this.grid = gridData;
  }

  setCanvas = async () => {
    this.canvasElement = document.querySelector('canvas');
    this.canvasElement.width = canvasWidth;
    this.canvasElement.height = canvasHeight;
    this.canvasElement.style.width = `${canvasWidth}px`;
    this.canvasElement.style.height = `${canvasHeight}px`;
  }

  // clearCanvas = async () => {
  //   this.ctx = await (<HTMLCanvasElement>this.canvas.nativeElement).getContext("2d");
  //   await this.ctx.clearRect(0,0,canvasWidth,canvasHeight)
  // };

  addClickHandle = async () => {
    this.canvasElement = document.querySelector('canvas');
    this.zeroX = this.coordinates.x - halfScreenWidth; // this.grid[0].x;
    this.zeroY = this.coordinates.y - halfScreenHeight; // this.grid[0].y;
    this.canvasElement.addEventListener('click', async (event) => {
      // alert(`${this.zeroX} + ${event.pageX} / ${elementWidth}`)
      this.targetCoordinates = new Vector(this.zeroX + Math.floor((event.pageX / elementWidth)),
        this.zeroY + Math.floor((event.pageY / elementHeight)));
      this.clickEmitter.emit(this.targetCoordinates);
    });
  }


  // old draw
  // let img = new Image();
  // img.src = `assets/${this.grid[i].picture}.png`;
  //
  // img.onload = async () => {
  //    await this.ctx.drawImage(img, (this.grid[i].x - this.grid[0].x)*100, (this.grid[i].y - this.grid[0].y)*100, 100, 100);
  //    // this.ctx.fillStyle="Black";
  //    // this.ctx.font = "12px Arial";
  //    // await this.ctx.fillText(`${this.grid[i].x}, ${this.grid[i].y}`,(this.grid[i].x - this.grid[0].x)
  //    * (100-10),(this.grid[i].y - this.grid[0].y) * (100-10));
  // }

}
