import {inject, TestBed} from '@angular/core/testing';

import {Test4GroupService} from './test4-group.service';

describe('Test4GroupService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [Test4GroupService]
    });
  });

  it('should be created', inject([Test4GroupService], (service: Test4GroupService) => {
    expect(service).toBeTruthy();
  }));
});
