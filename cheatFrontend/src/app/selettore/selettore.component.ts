import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-selettore',
  standalone:true,
  templateUrl: './selettore.component.html',
  styleUrls: ['./selettore.component.scss'],
  imports:[FormsModule, RouterModule]
})
export class SelettoreComponent {
  prezzo: number = 5; 
  tempo: number = 10; 

  constructor() {}

}
