import { Component } from '@angular/core';
import { Ricetta } from '../../Classi/ricetta';
import { FormsModule } from '@angular/forms';
import { RicetteServiceService } from '../../Services/ricette-service.service';
import { ActivatedRoute, Router } from '@angular/router';

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

constructor(private ricetteService:RicetteServiceService, private route:ActivatedRoute, private router:Router){}
//metodo invocato al submit della form
onSubmit(){
  //creazione della ricetta
  this.ricetteService.inserisciRicetta(new Ricetta(this.nome, this.prezzo, this.tempo, this.ingredienti, this.preparazione)).subscribe({
    //intercettazione della risposta del backend
    next: (response) => {
      if (response.status === 201) {
        window.alert('Ricetta creata. Premi OK per continuare.');
        this.router.navigate(['ricette']);
      }
    },
    error: (error) => {
      if (error.status === 401) {
        window.alert('Sessione scaduta. Effettua nuovamente il login.');
        this.router.navigate(['login']);
      } 
    }
  }
      
    );
}
}
