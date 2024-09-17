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

ricetteCount!:number;
ricette:Ricetta[]=[];
deletable:boolean = false;

ngOnInit(): void {
  this.route.paramMap.subscribe(params => {
      const prezzo = Number(params.get('prezzo'));
      const tempo = Number(params.get('tempo'));

      if(prezzo&tempo){
      this.ricetteService.getRicette(prezzo, tempo).subscribe(data => {
        this.ricette = data;
        this.ricetteCount = this.ricette.length;
      });
    }
    else{
      this.ricetteService.getMieRicette().subscribe({
        next: (data: Ricetta[]) => {
          this.ricette = data;
          this.ricetteCount = this.ricette.length;
          this.deletable = true;
        },
        error: (error) => {
          window.alert("Errore. Effettua nuovamente il login.");
          this.router.navigate(['login']);
        }
      });      
    }
    });
}

cancella(id?:number){
  if(id)
    this.ricetteService.eliminaRicetta(id).subscribe({
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