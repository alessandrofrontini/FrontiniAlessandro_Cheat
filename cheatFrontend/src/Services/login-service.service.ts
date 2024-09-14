import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../environments/environment.development';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginServiceService {

  constructor(private httpClient: HttpClient) { }

  Login(user: string, pwd: string): Observable<HttpResponse<any>> {
    const params = new HttpParams()
      .set('username', user.toString())
      .set('password', pwd.toString());
    return this.httpClient.post(environment.baseUrl + "/api/auth/login", {}, { 
      params: params, 
      observe: 'response' 
    });
  }

  Validate():Observable<HttpResponse<any>>{
    return this.httpClient.post(environment.baseUrl + "/api/auth/validate", {}, { 
      params: {},
      observe: 'response' 
    });
  }
}
