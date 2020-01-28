import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CityComponent } from './city.component';
import {MAT_TOOLTIP_SCROLL_STRATEGY, MatTooltip} from "@angular/material/tooltip";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {LogoutComponent} from "../../authenticationComponents/logout/logout.component";
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {Overlay, OverlayModule} from "@angular/cdk/overlay";
import {RouterTestingModule} from "@angular/router/testing";

describe('CityComponent', () => {
  let component: CityComponent;
  let fixture: ComponentFixture<CityComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        FormsModule,
        ReactiveFormsModule,
        HttpClientTestingModule,
        OverlayModule,
        RouterTestingModule
      ],
      declarations: [
        CityComponent,
        MatTooltip,
        LogoutComponent
      ],
      providers: [
        {
          provide: MAT_TOOLTIP_SCROLL_STRATEGY,
          deps: [ Overlay ],
          useFactory(overlay) { return () => overlay.scrollStrategies.close(); }
        }
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
