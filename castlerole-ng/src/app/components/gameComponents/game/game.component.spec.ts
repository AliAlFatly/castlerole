import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GameComponent } from './game.component';
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {GridComponent} from "../grid/grid.component";
import {UserInformationComponent} from "../user-information/user-information.component";
import {LogoutComponent} from "../../authenticationComponents/logout/logout.component";
import {RouterTestingModule} from "@angular/router/testing";
import {Vector} from "../../../models/generic/Vector";
import {GameDetailsComponent} from "../game-details/game-details.component";
import {NavigatorComponent} from "../navigator/navigator.component";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";


describe('GameComponent', () => {
  let component: GameComponent;
  let fixture: ComponentFixture<GameComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule,
        RouterTestingModule,
        FormsModule,
        ReactiveFormsModule
      ],
      declarations: [
        GameComponent,
        GridComponent,
        GameDetailsComponent,
        UserInformationComponent,
        NavigatorComponent,
        LogoutComponent
        // Add more components other that game.component.ts
      ],
      providers: [  ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GameComponent);
    component = fixture.componentInstance;
    component.coordinates = new Vector(25, 25);
    component.clickTargetCoordinates = new Vector(20, 20);
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
  it('Should have predetermined coordinates', () => {
    expect(component.coordinates).toEqual(new Vector(25, 25));
  });
  it('clickTarget should be predetermined coordinates', () => {
    expect(component.clickTargetCoordinates).toEqual(new Vector(20, 20));
  });
});
