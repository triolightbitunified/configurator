import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MenustepitemComponent } from './menustepitem.component';

describe('MenustepitemComponent', () => {
  let component: MenustepitemComponent;
  let fixture: ComponentFixture<MenustepitemComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MenustepitemComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MenustepitemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
