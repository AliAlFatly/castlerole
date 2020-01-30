import { TestBed } from '@angular/core/testing';

import { TokenInterceptorService } from './token-interceptor.service';
import {HttpClientTestingModule} from '@angular/common/http/testing';

describe('TokenInterceptorService', () => {
  beforeEach(() => TestBed.configureTestingModule({
    imports: [HttpClientTestingModule],
  }));

  it('should create', () => {
    const service: TokenInterceptorService = TestBed.get(TokenInterceptorService);
    expect(service).toBeTruthy();
  });
});
