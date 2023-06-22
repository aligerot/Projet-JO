import { TestBed } from '@angular/core/testing';
import {SportService} from './SportService';

describe('SportProvider', () => {
  let service: SportService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SportService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

