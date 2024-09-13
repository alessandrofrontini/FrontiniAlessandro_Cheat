import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RicetteServiceService } from '../../Services/ricette-service.service';
import { Ricetta } from '../../Classi/ricetta';

@Component({
  selector: 'app-lista-ricette',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './lista-ricette.component.html',
  styleUrl: './lista-ricette.component.scss'
})
export class ListaRicetteComponent implements OnInit{

constructor(private ricetteService:RicetteServiceService){}

ricetteCount!:number;
ricette:Ricetta[]=[];
ngOnInit(): void {
  this.ricetteService.getRicette(19,19).subscribe(data =>{
    this.ricette = data;
    this.ricetteCount = this.ricette.length;
  })
}
}