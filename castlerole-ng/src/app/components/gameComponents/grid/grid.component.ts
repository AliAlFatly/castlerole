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
  templateUrl: './grid.component.html'
})
export class GridComponent implements OnChanges {

  @ViewChild('canvas', {static: false}) private canvas: ElementRef;
  @Output() clickEmitter = new EventEmitter<Vector>();
  // Middle of the screen
  @Input() coordinates: Vector;
  private targetCoordinates: Vector;
  private backgroundImage = new Image();
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
    // set user coordinates
    await this.getUserCoordinates();
    // set background image
    await this.setBackgroundImage();
    // set the canvas height/width variables
    await this.setCanvas();
    // set the html canvas element
    await this.getCanvas();
    // draw
    await this.initialDraw();
  }

  async ngOnChanges(changes: SimpleChanges) {
    // await this.clearCanvas();
    await this.getGridData();
    this.addClickHandle();
    await this.drawCanvas();

  }

  getCanvas = async () => {
    this.ctx = await (this.canvas.nativeElement as HTMLCanvasElement).getContext('2d');
  }

  setCanvas = async () => {
    this.canvasElement = await document.querySelector('canvas');
    this.canvasElement.width = canvasWidth;
    this.canvasElement.height = canvasHeight;
    this.canvasElement.style.width = `${canvasWidth}px`;
    this.canvasElement.style.height = `${canvasHeight}px`;
  }

  setBackgroundImage = async () => {
    this.backgroundImage.src = `assets/empty.png`;
  }

  drawBackground = async () => {
    await this.ctx.clearRect(0, 0, canvasWidth, canvasHeight);
    await this.ctx.drawImage(this.backgroundImage, 0, 0, canvasWidth, canvasHeight);
  }

  drawContent = async () => {
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

  drawCanvas = async () => {
    await this.drawBackground();
    await this.drawContent();
  }

  getUserCoordinates = async () => {
    const response = await this.gameService.getUserCoordinates().toPromise();
    this.coordinates.x = response.x;
    this.coordinates.y = response.y;
  }

  initialDraw = async () => {
    this.grid = await this.gameService.getGrid(this.coordinates).toPromise();
    await this.drawCanvas();
  }

  getGridData = async () => {
    this.grid = await this.gameService.getGrid(this.coordinates).toPromise();
  }

  addClickHandle = async () => {
    this.zeroX = this.coordinates.x - halfScreenWidth; // this.grid[0].x;
    this.zeroY = this.coordinates.y - halfScreenHeight; // this.grid[0].y;
    this.canvasElement.addEventListener('click', async (event) => {
      this.targetCoordinates = new Vector(this.zeroX + Math.floor((event.pageX / elementWidth)),
        this.zeroY + Math.floor((event.pageY / elementHeight)));
      this.clickEmitter.emit(this.targetCoordinates);
    });
  }
}
