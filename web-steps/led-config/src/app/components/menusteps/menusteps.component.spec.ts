import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MenustepsComponent } from './menusteps.component';

describe('MenustepsComponent', () => {
  let component: MenustepsComponent;
  let fixture: ComponentFixture<MenustepsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MenustepsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MenustepsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
