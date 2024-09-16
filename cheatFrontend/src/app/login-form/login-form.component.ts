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

  
  constructor(private loginService: LoginServiceService) {
  }

  
  onSubmit() {
    if (this.user && this.pwd) {
    
          this.loginService.Login(this.user, this.pwd).subscribe(
                  (response) => {
                    if (response.status === 200) {
                      console.log('Login successful');
                    }
                  },
                  (error) => {
                    if (error.status === 401) {
                      console.error('Login failed: Unauthorized');
                      
                    } else {
                      console.error('An error occurred:', error);
                    }
                  }
                );
    }
    else {
      console.error('User or password missing');
    }
  }
  Valida(){
    this.loginService.Validate().subscribe(
      (response) => {
        if (response.status === 200) {
          console.log('validation successful');
          
        }
      },
      (error) => {
        if (error.status === 401) {
          console.error('validation denied');
          
        } else {
          console.error('An error occurred:', error);
        }
      }
    );
  }
  }
  

