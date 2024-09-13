import { Routes } from '@angular/router';
import { ListaRicetteComponent } from './lista-ricette/lista-ricette.component';
import { SelettoreComponent } from './selettore/selettore.component';

export const routes: Routes = [
    {path:'', component:SelettoreComponent},
    {path: 'ricette/:prezzo/:tempo', component: ListaRicetteComponent}
];
