import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SelettoreComponent } from './selettore.component';

describe('SelettoreComponent', () => {
  let component: SelettoreComponent;
  let fixture: ComponentFixture<SelettoreComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SelettoreComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SelettoreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
