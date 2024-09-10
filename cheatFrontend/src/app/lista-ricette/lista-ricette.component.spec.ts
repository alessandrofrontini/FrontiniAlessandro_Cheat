import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListaRicetteComponent } from './lista-ricette.component';

describe('ListaRicetteComponent', () => {
  let component: ListaRicetteComponent;
  let fixture: ComponentFixture<ListaRicetteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListaRicetteComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListaRicetteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
