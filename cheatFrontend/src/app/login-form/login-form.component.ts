import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { LoginServiceService } from '../../Services/login-service.service';


@Component({
  selector: 'app-login-form',
  standalone: true,
  imports: [FormsModule, RouterModule],
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.scss']
})
export class LoginFormComponent {
  user?: string;
  pwd?: string;

  
  constructor(private loginService: LoginServiceService, private router:Router) {
  }

  
  onSubmit() {
    //se entrambe le credenziali sono presenti
    if (this.user && this.pwd) {
      //chiamata al servizio di login
          this.loginService.Login(this.user, this.pwd).subscribe(
            {
              //intercettazione delle risposte e redirecting
                next: (response) => {
                  if (response.status === 200) {
                    this.router.navigate(['select']);
                  }
                },
                error: (error) => {
                  if (error.status === 401) {
                    window.alert('Login errato. Premi OK per riprovare.');
                    
                  } else {
                  }
                }
            }
          );
                  
    }
    else {
      window.alert('Credenziali mancanti. Premi OK per continuare.');
    }
  }
  }
  

