import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PricecalculationComponent } from './pricecalculation.component';

describe('PricecalculationComponent', () => {
  let component: PricecalculationComponent;
  let fixture: ComponentFixture<PricecalculationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PricecalculationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PricecalculationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
