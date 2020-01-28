import { TestBed } from '@angular/core/testing';

import { GameServiceService } from './game-service.service';
import {HttpClientTestingModule} from '@angular/common/http/testing';

describe('GameServiceService', () => {
  beforeEach(async () => TestBed.configureTestingModule({
    imports: [HttpClientTestingModule],
  }));

  it('should be4 created', () => {
    const service: GameServiceService = TestBed.get(GameServiceService);
    expect(service).toBeTruthy();
  });
});
