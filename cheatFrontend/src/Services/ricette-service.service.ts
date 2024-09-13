import { HttpClient, HttpParams } from '@angular/common/http';
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
}