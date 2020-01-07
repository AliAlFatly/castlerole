import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GameDetailsComponent } from './game-details.component';
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {Vector} from "../../../models/generic/Vector";
import {PointDataResponse} from "../../../models/response/pointDataResponse";
import {AttackResponse} from "../../../models/response/attackResponse";
import {GridComponent} from "../grid/grid.component";

describe('GameDetailsComponent', () => {
  let component: GameDetailsComponent;
  let fixture: ComponentFixture<GameDetailsComponent>;
  let pointDetails: any;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        GameDetailsComponent,
        GridComponent
      ],
      imports: [
        HttpClientTestingModule,
      ],
      providers: [
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GameDetailsComponent);
    component = fixture.componentInstance;
    component.targetCoordinates = new Vector(20, 20);
    pointDetails = new PointDataResponse(25, 25, 'Player', true);
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
  it('Should have predetermined coordinates', () => {
    expect(component.targetCoordinates).toEqual(new Vector(20, 20));
  });

  // it('Setting value to input properties on button click', () => {
  //   component.targetCoordinates = new Vector(25, 25);
  //   fixture.detectChanges();
  //   expect(component).toBeTruthy();
  // });
});
