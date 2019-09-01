import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DudeProfileComponent } from './dude-profile.component';

describe('DudeProfileComponent', () => {
  let component: DudeProfileComponent;
  let fixture: ComponentFixture<DudeProfileComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DudeProfileComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DudeProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
