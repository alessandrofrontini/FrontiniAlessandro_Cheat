import { Routes } from '@angular/router';
import { ListaRicetteComponent } from './lista-ricette/lista-ricette.component';
import { SelettoreComponent } from './selettore/selettore.component';
import { LoginFormComponent } from './login-form/login-form.component';
import { InputRicettaComponent } from './input-ricetta/input-ricetta.component';

export const routes: Routes = [
    {path:'', component:LoginFormComponent},
    {path:'select', component:SelettoreComponent},
    {path:'insert', component:InputRicettaComponent},
    {path: 'ricette/:prezzo/:tempo', component: ListaRicetteComponent},
    {path: 'ricette', component: ListaRicetteComponent}
];
