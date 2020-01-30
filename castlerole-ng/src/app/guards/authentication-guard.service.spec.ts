import { TestBed } from '@angular/core/testing';

import { AuthenticationGuardService } from './authentication-guard.service';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {RouterTestingModule} from '@angular/router/testing';

describe('AuthenticationGuardService', () => {
  beforeEach(() => TestBed.configureTestingModule({
    imports: [HttpClientTestingModule,
          RouterTestingModule,
    ],
  }));

  it('should create', () => {
    const service: AuthenticationGuardService = TestBed.get(AuthenticationGuardService);
    expect(service).toBeTruthy();
  });
});
