import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InputRicettaComponent } from './input-ricetta.component';

describe('InputRicettaComponent', () => {
  let component: InputRicettaComponent;
  let fixture: ComponentFixture<InputRicettaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [InputRicettaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(InputRicettaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
