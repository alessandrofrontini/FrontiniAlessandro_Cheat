import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ListaRicetteComponent } from './lista-ricette/lista-ricette.component';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, ListaRicetteComponent, HttpClientModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'cheatFrontend';
}
