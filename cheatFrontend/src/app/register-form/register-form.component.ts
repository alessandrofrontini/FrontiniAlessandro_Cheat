import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { LoginServiceService } from '../../Services/login-service.service';
import { Utente } from '../../Classi/utente';
import { Router } from '@angular/router';

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
  constructor(private loginService:LoginServiceService, private router:Router){}

  registraUtente() {
    //creazione di un nuovo Utente e chiamata al backend
    this.loginService.Register(new Utente(this.nome, this.cognome, this.email, this.user, this.pwd)).subscribe({
      next: (response) => {
        //intercettazione delle risposte
        if (response.status === 200) {
          window.alert('Registrazione avvenuta. Premi OK per continuare.');
          this.router.navigate(['login']);
        }
      },
      error: (error) => {
        if (error.status === 401) {
          window.alert('Registrazione non avvenuta. Premi OK per riprovare.');
        } else {
          console.error('An error occurred:', error);
        }
      }
    });
  }
  
}
