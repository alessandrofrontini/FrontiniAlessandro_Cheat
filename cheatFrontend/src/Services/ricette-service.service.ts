import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Ricetta } from '../Classi/ricetta';
import { environment } from '../environments/environment.development';


@Injectable({
  providedIn: 'root'
})
export class RicetteServiceService {
  constructor(private httpClient: HttpClient) {}
  
  getRicette(prezzo: number, tempo: number): Observable<Ricetta[]> {
    const params = new HttpParams().set('prezzo', prezzo.toString()).set('tempo', tempo.toString());
    return this.httpClient.post<Ricetta[]>(environment.baseUrl + "/api/ricette", {}, { params });
  }

  getMieRicette():Observable<Ricetta[]> {
    return this.httpClient.post<Ricetta[]>(environment.baseUrl + "/api/ricette/my", {}, {});
  }

  inserisciRicetta(r:Ricetta):Observable<HttpResponse<any>>{
    return this.httpClient.post(environment.baseUrl + "/api/ricette/create", r, {
      observe: "response"
    });
  }

  eliminaRicetta(id:number):Observable<HttpResponse<any>>{
    const params = new HttpParams().set('idr', id.toString());
    return this.httpClient.delete(environment.baseUrl + "/api/ricette/delete", {
        params: params, 
        observe: 'response'  
    });
  }
}