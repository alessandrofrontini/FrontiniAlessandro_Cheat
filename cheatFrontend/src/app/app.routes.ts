import { Routes } from '@angular/router';
import { ListaRicetteComponent } from './lista-ricette/lista-ricette.component';
import { SelettoreComponent } from './selettore/selettore.component';
import { LoginFormComponent } from './login-form/login-form.component';

export const routes: Routes = [
    {path:'', component:LoginFormComponent},
    {path:'select', component:SelettoreComponent},
    {path: 'ricette/:prezzo/:tempo', component: ListaRicetteComponent}
];
