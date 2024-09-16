import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { LoginServiceService } from '../../Services/login-service.service';
import { Utente } from '../../Classi/utente';

@Component({
  selector: 'app-register-form',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './register-form.component.html',
  styleUrl: './register-form.component.scss'
})
export class RegisterFormComponent {
  nome:string = "";
  cognome:string = "";
  email:string = "";
  user:string = "";
  pwd:string = "";
  constructor(private loginService:LoginServiceService){}

  registraUtente(){

    this.loginService.Register(new Utente(this.nome, this.cognome, this.email, this.user, this.pwd)).subscribe(
      (response) =>{
        if (response.status === 200) {
          console.log('Registrazione avvenuta');
        }
      },
      (error) => {
        if (error.status === 401) {
          console.error('Registrazione non avvenuta');
          
        } else {
          console.error('An error occurred:', error);
        }
      });

    
  }
}
