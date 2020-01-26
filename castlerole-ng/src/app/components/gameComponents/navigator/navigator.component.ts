import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {Vector} from '../../../models/generic/Vector';
import {HttpClient} from '@angular/common/http';
import {GameServiceService} from '../../../services/game/game-service.service';
import {halfScreenHeight, halfScreenWidth, totalGridSize} from '../../../config';

@Component({
  selector: 'app-navigator',
  templateUrl: './navigator.component.html'
})
export class NavigatorComponent implements OnInit {

  @Output() coordinatesEmitter = new EventEmitter<Vector>();

  coordinateForm: FormGroup = this.formBuilder.group({
    x: 0,
    y: 0
  });
  private coordinates: Vector = new Vector(0, 0);

  constructor(private formBuilder: FormBuilder,
              private http: HttpClient,
              private gameService: GameServiceService) { }

  async ngOnInit() {
    await this.getUserCoordinates();
    await this.setCoordinatesInsideBoundaries();
    this.coordinatesEmitter.emit(this.coordinates);
  }

  getUserCoordinates = async () => {
    const response = await this.gameService.getUserCoordinates().toPromise();
    this.coordinates.x = response.x;
    this.coordinates.y = response.y;
  }

  setForumCoordinates = () => {
    this.coordinateForm = this.formBuilder.group({
      x: this.coordinates.x,
      y: this.coordinates.y
    });
  }

  setCoordinatesInsideBoundaries = async () => {
    // if x or y are below the middle of screen width set them to screen width.
    if (this.coordinates.x < halfScreenWidth) {
      this.coordinates.x = halfScreenWidth;
    }
    if (this.coordinates.y < halfScreenHeight) {
      this.coordinates.y = halfScreenHeight;
    }

    // if x or y are below the maximum grid node - screen width set them to maximum grid node coordinates - screen width
    if (this.coordinates.x > totalGridSize - halfScreenWidth) {
      this.coordinates.x = totalGridSize - halfScreenWidth + 6;
    }
    if (this.coordinates.y > totalGridSize - halfScreenHeight) {
      this.coordinates.y = totalGridSize - halfScreenHeight - 1;
    }
    this.setForumCoordinates();
  }

  async navigate() {
    this.coordinates.x = await this.coordinateForm.controls.x.value;
    this.coordinates.y = await this.coordinateForm.controls.y.value;
    this.setCoordinatesInsideBoundaries();
    this.coordinatesEmitter.emit(JSON.parse(JSON.stringify(this.coordinates)));
  }

}
