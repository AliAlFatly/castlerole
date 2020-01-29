import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { GameComponent } from './game.component';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {GridComponent} from '../grid/grid.component';
import {UserInformationComponent} from '../user-information/user-information.component';
import {LogoutComponent} from '../../authenticationComponents/logout/logout.component';
import {RouterTestingModule} from '@angular/router/testing';
import {Vector} from '../../../models/generic/Vector';
import {GameDetailsComponent} from '../game-details/game-details.component';
import {NavigatorComponent} from '../navigator/navigator.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {CityComponent} from "../city/city.component";
import {MAT_TOOLTIP_SCROLL_STRATEGY, MatTooltip} from "@angular/material/tooltip";
import { Overlay, OverlayModule} from "@angular/cdk/overlay";
import {MatDialogModule} from "@angular/material/dialog";

xdescribe('GameComponent', () => {
  let component: GameComponent;
  let fixture: ComponentFixture<GameComponent>;
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule,
        RouterTestingModule,
        FormsModule,
        ReactiveFormsModule,
        MatDialogModule,
        OverlayModule,
      ],
      declarations: [
        GameComponent,
        GridComponent,
        GameDetailsComponent,
        UserInformationComponent,
        NavigatorComponent,
        LogoutComponent,
        MatTooltip,
        CityComponent,
      ],
      providers: [
        {
          provide: MAT_TOOLTIP_SCROLL_STRATEGY,
          deps: [ Overlay ],
          useFactory(overlay) { return () => overlay.scrollStrategies.close(); }
        }
      ],
      schemas: [

      ]

    })
    .compileComponents();
  }));

  beforeEach(async () => {
    fixture = TestBed.createComponent(GameComponent);
    component = fixture.componentInstance;
    component.coordinates = new Vector(25, 25);
    fixture.autoDetectChanges();
  });

  it('should be create', () => {
    expect(component).toBeTruthy();
  });
  it('Should get target from grid click coordinates', async () => {
    await component.getTargetFromGridClick(new Vector(25, 25));
    expect(component.clickTargetCoordinates).toEqual(new Vector(25, 25));
  });
  it('should get usercoordinates', async () => {
    await component.getUserCoordinates(new Vector(20, 20));
    expect(component.userCoordinates).toEqual(new Vector(20, 20));
  });
  it('should get coordinates from navigator', async () => {
    await component.getCoordinatesFromNavigator(new Vector(15, 15));
    expect(component.coordinates).toEqual(new Vector(15, 15));
  });
});
