import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NavigatorComponent } from './navigator.component';
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {FormBuilder, ReactiveFormsModule} from "@angular/forms";

describe('NavigatorComponent', () => {
  let component: NavigatorComponent;
  let fixture: ComponentFixture<NavigatorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NavigatorComponent,

      ],
      imports: [
        HttpClientTestingModule,
        ReactiveFormsModule
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NavigatorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
