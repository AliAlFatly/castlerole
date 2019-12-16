import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GameComponent } from './game.component';
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {GridComponent} from "../grid/grid.component";
import {UserInformationComponent} from "../user-information/user-information.component";
import {LogoutComponent} from "../../authenticationComponents/logout/logout.component";
import {RouterTestingModule} from "@angular/router/testing";

describe('GameComponent', () => {
  let component: GameComponent;
  let fixture: ComponentFixture<GameComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule,
                RouterTestingModule,
      ],
      declarations: [ GameComponent,
        // Add more components other that game.component.ts
        GridComponent,
        UserInformationComponent,
        LogoutComponent,
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GameComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
