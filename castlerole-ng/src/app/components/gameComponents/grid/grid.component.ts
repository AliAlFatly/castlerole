import {
  AfterViewInit,
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
export class GridComponent implements AfterViewInit, OnChanges {

  @ViewChild('canvas', {static: false}) private canvas: ElementRef;
  @Output() clickEmitter = new EventEmitter<Vector>();
  // Middle of the screen
  @Input() coordinates: Vector;
  private targetCoordinates: Vector;
  private canvasElement;
  // tslint:disable-next-line:max-line-length
  private ctx: any;
  private zeroX: number;
  private zeroY: number;

  private grid: Array<GridResponse> = new Array<GridResponse>();

  // Assets initialization:
  private backgroundImage = new Image();
  private player = new Image();
  private mountain = new Image();
  private mine = new Image();
  private lake = new Image();
  private forest = new Image();

  constructor(
    private http: HttpClient,
    private gameService: GameServiceService
  ) {
    this.canvasElement = document.querySelector('canvas');
  }

  async ngAfterViewInit() {
    await this.getUserCoordinates();
    await this.getFields();
    await this.drawCanvas();
  }

  async ngOnChanges(changes: SimpleChanges) {
    if (this.coordinates.x !== -1) {
      await this.getFields();
      this.addClickHandle();
      await this.drawCanvas();
    }
  }

  setAssets = () => {
    this.player.src = 'assets/player.png';
    this.mountain.src = 'assets/mountain.png';
    this.mine.src = 'assets/mine.png';
    this.lake.src = 'assets/lake.png';
    this.forest.src = 'assets/forest.png';
    this.backgroundImage.src = 'assets/empty.png';
  }

  selectCanvas = async () => {
    this.canvasElement = document.querySelector('canvas');
  }

  setCanvas = async () => {
    await this.selectCanvas();
    this.canvasElement.width = canvasWidth;
    this.canvasElement.height = canvasHeight;
    this.canvasElement.style.width = `${canvasWidth}px`;
    this.canvasElement.style.height = `${canvasHeight}px`;
  }

  getGridData = async () => {
    this.grid = await this.gameService.getGrid(this.coordinates).toPromise();
  }

  getFields = async () => {
    await this.setCanvas();
    this.ctx = (this.canvas.nativeElement as HTMLCanvasElement).getContext('2d');
    this.setAssets();
    await this.getGridData();
  }

  drawBackground = async () => {
    await this.ctx.clearRect(0, 0, canvasWidth, canvasHeight);
    await this.ctx.drawImage(this.backgroundImage, 0, 0, canvasWidth, canvasHeight);
  }

  drawGridContent = async () => {
    // tslint:disable-next-line:prefer-for-of
    for (let i = 0; i < this.grid.length; i++) {
      const currentGridIndex = this.grid[i];
      switch (currentGridIndex.picture) {
        case 'player':
          await this.ctx.drawImage(this.player, (this.grid[i].x - (this.coordinates.x - halfScreenWidth)) * elementWidth,
            (this.grid[i].y - (this.coordinates.y - halfScreenHeight)) * elementHeight, elementWidth, elementHeight);
          break;
        case 'mountain':
          await this.ctx.drawImage(this.mountain, (this.grid[i].x - (this.coordinates.x - halfScreenWidth)) * elementWidth,
            (this.grid[i].y - (this.coordinates.y - halfScreenHeight)) * elementHeight, elementWidth, elementHeight);
          break;
        case 'mine':
          await this.ctx.drawImage(this.mine, (this.grid[i].x - (this.coordinates.x - halfScreenWidth)) * elementWidth,
            (this.grid[i].y - (this.coordinates.y - halfScreenHeight)) * elementHeight, elementWidth, elementHeight);
          break;
        case 'lake':
          await this.ctx.drawImage(this.lake, (this.grid[i].x - (this.coordinates.x - halfScreenWidth)) * elementWidth,
            (this.grid[i].y - (this.coordinates.y - halfScreenHeight)) * elementHeight, elementWidth, elementHeight);
          break;
        case 'forest':
          await this.ctx.drawImage(this.forest, (this.grid[i].x - (this.coordinates.x - halfScreenWidth)) * elementWidth,
            (this.grid[i].y - (this.coordinates.y - halfScreenHeight)) * elementHeight, elementWidth, elementHeight);
          break;
        default:
          break;
      }
    }
  }


  drawCanvas = async () => {
    await this.drawBackground();
    await this.drawGridContent();
    // await this.drawContent();
  }

  getUserCoordinates = async () => {
    const response = await this.gameService.getUserCoordinates().toPromise();
    this.coordinates.x = response.x;
    this.coordinates.y = response.y;
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
