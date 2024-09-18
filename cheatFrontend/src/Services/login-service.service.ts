import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../environments/environment.development';
import { Observable } from 'rxjs';
import { Utente } from '../Classi/utente';

@Injectable({
  providedIn: 'root'
})
export class LoginServiceService {

  constructor(private httpClient: HttpClient) { }

  Login(user: string, pwd: string): Observable<HttpResponse<any>> {
    //creazione dei parametri della richiesta
    const params = new HttpParams()
      .set('username', user.toString())
      .set('password', pwd.toString());
      //esecuzione della chiamata
    return this.httpClient.post(environment.baseUrl + "/api/auth/login", {}, { 
      params: params, 
      observe: 'response' 
    });
  }

  Register(utente:Utente):Observable<HttpResponse<any>>{
    //chiamata per la registrazione dell'utente, che viene passato nel body
    return this.httpClient.post(environment.baseUrl + "/api/auth/register", utente, {
      observe: "response"
    });
  }
}
