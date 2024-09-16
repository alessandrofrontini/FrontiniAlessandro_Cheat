import { Component } from '@angular/core';
import { Ricetta } from '../../Classi/ricetta';
import { FormsModule } from '@angular/forms';
import { RicetteServiceService } from '../../Services/ricette-service.service';

@Component({
  selector: 'app-input-ricetta',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './input-ricetta.component.html',
  styleUrl: './input-ricetta.component.scss'
})
export class InputRicettaComponent {
nome:string = "";
ingredienti:string = "";
preparazione:string ="";
prezzo:number = 0;
tempo:number = 0;

constructor(private ricetteService:RicetteServiceService){}

onSubmit(){
  this.ricetteService.inserisciRicetta(new Ricetta(this.nome, this.prezzo, this.tempo, this.ingredienti, this.preparazione)).subscribe(
      (response) => {
        if (response.status === 201) {
          console.log('Ricetta creata');
        }
      },
      (error) => {
        if (error.status === 401) {
          console.error('Errore');
          
        } else {
          console.error('An error occurred:', error);
        }
      }
    );
}
}
