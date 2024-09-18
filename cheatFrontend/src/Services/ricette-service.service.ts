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
    //costruzione dei parametri della richiesta
    const params = new HttpParams().set('prezzo', prezzo.toString()).set('tempo', tempo.toString());
    //esecuzione della chiamata
    return this.httpClient.post<Ricetta[]>(environment.baseUrl + "/api/ricette", {}, { params });
  }

  getMieRicette():Observable<Ricetta[]> {
    //chiamata senza parametri perchè il JWT è immagazzinato in un cookie
    return this.httpClient.post<Ricetta[]>(environment.baseUrl + "/api/ricette/my", {}, {});
  }

  inserisciRicetta(r:Ricetta):Observable<HttpResponse<any>>{
    //chiamata per la creazione di una nuova ricetta, passata nel body
    return this.httpClient.post(environment.baseUrl + "/api/ricette/create", r, {
      observe: "response"
    });
  }

  eliminaRicetta(id:number):Observable<HttpResponse<any>>{
    //costruzione del parametro della richiesta
    const params = new HttpParams().set('idr', id.toString());
    //chiamata per l'eliminazione di una ricetta; il JWT è memorizzato in un cookie
    return this.httpClient.delete(environment.baseUrl + "/api/ricette/delete", {
        params: params, 
        observe: 'response'  
    });
  }
}