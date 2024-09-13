import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RicetteServiceService } from '../../Services/ricette-service.service';
import { Ricetta } from '../../Classi/ricetta';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-lista-ricette',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './lista-ricette.component.html',
  styleUrl: './lista-ricette.component.scss'
})
export class ListaRicetteComponent implements OnInit{

constructor(private ricetteService:RicetteServiceService, private route:ActivatedRoute){}

ricetteCount!:number;
ricette:Ricetta[]=[];

ngOnInit(): void {
  this.route.paramMap.subscribe(params => {
      const prezzo = Number(params.get('prezzo'));
      const tempo = Number(params.get('tempo'));

      this.ricetteService.getRicette(prezzo, tempo).subscribe(data => {
        this.ricette = data;
        this.ricetteCount = this.ricette.length;
      });
    });
}
}