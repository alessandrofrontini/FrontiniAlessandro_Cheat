import { Component, numberAttribute, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RicetteServiceService } from '../../Services/ricette-service.service';
import { Ricetta } from '../../Classi/ricetta';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-lista-ricette',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './lista-ricette.component.html',
  styleUrl: './lista-ricette.component.scss'
})
export class ListaRicetteComponent implements OnInit{

constructor(private ricetteService:RicetteServiceService, private route:ActivatedRoute, private router:Router){}

ricetteCount:number = 0;
ricette:Ricetta[]=[];
deletable:boolean = false;

ngOnInit(): void {
  //il componente potrebbe essere attivato dalla rotta /:prezzo/:tempo
  this.route.paramMap.subscribe(params => {
      const prezzo = Number(params.get('prezzo'));
      const tempo = Number(params.get('tempo'));

      //in caso le due variabili sono settate
      if(prezzo&&tempo){
        //ottenimento delle ricette
      this.ricetteService.getRicette(prezzo, tempo).subscribe({
        //intercettazione della risposta e parsing nella classe Ricetta
        next: (data: Ricetta[]) => {
          this.ricette = data;
          this.ricetteCount = this.ricette.length;
        },
        //intercettazione del possibile errore 404
        error: (error) =>{
          window.alert("Non ci sono ricette disponibili. Riprova più tardi.");
          this.router.navigate(['select']);
        }
      });
    }
    else{
      //ottenimento delle ricette dell'utente loggato
      this.ricetteService.getMieRicette().subscribe({
        //intercettazione della risposta e parsing nella classe Ricetta
        next: (data: Ricetta[]) => {
          this.ricette = data;
          this.ricetteCount = this.ricette.length;
          //flag che abilita la presenza del bottone "elimina" e della scritta "Le mie Ricette" nell'HTML
          this.deletable = true;
        },
        error: (error) => {
          //intercettazione del possibile errore 404
          if(error.status === 404){
            window.alert("Non hai ancora inserito delle ricette");
            this.router.navigate(['select']);
          }
          //intercettazione del possibile errore 401
          else{
            window.alert("Sessione scaduta. Premi OK per effettuare il login");
            this.router.navigate(['login']);
          }
        }
      });      
    }
    });
}

cancella(id?:number){
  if(id)
    //cancellazione della ricetta selezionata
    this.ricetteService.eliminaRicetta(id).subscribe({
      //intercettazione della risposta
      next: (response) =>{
        if (response.status === 200) {
          window.alert('Ricetta eliminata. Premi OK per continuare.');
          this.router.navigate(['ricette']);
        }
      },
      error: (error) => {
        if (error.status === 401) {
          window.alert('Errore nella cancellazione. Effettua nuovamente il login.');
          this.router.navigate(['login']);
        }
      }
  }
  );
}
}