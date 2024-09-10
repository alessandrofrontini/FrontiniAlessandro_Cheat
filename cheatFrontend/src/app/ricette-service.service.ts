import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Ricetta } from './ricetta';

@Injectable({
  providedIn: 'root'
})
export class RicetteServiceService {
  private url = "http://localhost:8080/api/ricette";
  constructor(private httpClient: HttpClient) {}
  
  getRicette(prezzo: number, tempo: number): Observable<Ricetta[]> {
    const params = new HttpParams().set('prezzo', prezzo.toString()).set('tempo', tempo.toString());
    return this.httpClient.post<Ricetta[]>(this.url, {}, { params });
  }
}
