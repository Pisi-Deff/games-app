import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MainUiComponent } from './main-ui.component';

describe('MainUiComponent', () => {
  let component: MainUiComponent;
  let fixture: ComponentFixture<MainUiComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MainUiComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MainUiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
