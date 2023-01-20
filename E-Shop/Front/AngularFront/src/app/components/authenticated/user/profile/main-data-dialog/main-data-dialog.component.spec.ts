import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MainDataDialogComponent } from './main-data-dialog.component';

describe('MainDataDialogComponent', () => {
  let component: MainDataDialogComponent;
  let fixture: ComponentFixture<MainDataDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MainDataDialogComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MainDataDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
