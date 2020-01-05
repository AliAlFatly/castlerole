import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {Vector} from "../../../models/generic/Vector";
import {HttpClient} from "@angular/common/http";
import {GameServiceService} from "../../../services/game/game-service.service";

@Component({
  selector: 'app-navigator',
  templateUrl: './navigator.component.html',
  styleUrls: ['./navigator.component.css']
})
export class NavigatorComponent implements OnInit {

  @Output() coordinatesEmitter = new EventEmitter<Vector>()

  coordinateForm: FormGroup = this.formBuilder.group({
    x:0,
    y:0
  });
  private coordinates: Vector = new Vector(0,0);

  constructor(private formBuilder: FormBuilder,
              private http: HttpClient,
              private gameService: GameServiceService) { }

  async ngOnInit() {
    let response = await this.gameService.getUserCoordinates().toPromise();
    this.coordinates.x = response.x;
    this.coordinates.y = response.y;

    this.coordinateForm = await this.formBuilder.group({
      x: this.coordinates.x,
      y: this.coordinates.y
    });
    this.coordinatesEmitter.emit(this.coordinates)
  }

  async navigate(){
    this.coordinates.x = await this.coordinateForm.controls.x.value;
    this.coordinates.y = await this.coordinateForm.controls.y.value;
    await this.coordinatesEmitter.emit(JSON.parse(JSON.stringify(this.coordinates)))
  }

}
