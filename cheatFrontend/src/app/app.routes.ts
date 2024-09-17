import { Routes } from '@angular/router';
import { ListaRicetteComponent } from './lista-ricette/lista-ricette.component';
import { SelettoreComponent } from './selettore/selettore.component';
import { LoginFormComponent } from './login-form/login-form.component';
import { InputRicettaComponent } from './input-ricetta/input-ricetta.component';
import { RegisterFormComponent } from './register-form/register-form.component';

export const routes: Routes = [
    {path:'', component:SelettoreComponent},
    {path:'register', component:RegisterFormComponent},
    {path:'select', component:SelettoreComponent},
    {path:'insert', component:InputRicettaComponent},
    {path: 'ricette/:prezzo/:tempo', component: ListaRicetteComponent},
    {path: 'login', component: LoginFormComponent},
    {path: 'ricette', component: ListaRicetteComponent}
];
