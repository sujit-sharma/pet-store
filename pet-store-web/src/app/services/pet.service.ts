import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";

import {environment} from "../../environments/environment";
import {PetEntity} from "./entities";
import {Observable, of} from "rxjs";
import {catchError, tap} from "rxjs/operators";
import {buildMonths} from "@ng-bootstrap/ng-bootstrap/datepicker/datepicker-tools";


@Injectable({
  providedIn: 'root'
})
export class PetService {
  token = localStorage.getItem("authToken");

  constructor(private http: HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders( {
      Authorization: this.token
    })
  };

  listPet(): Observable<PetEntity[]> {
    console.log('Pet Service: retrieving from backend');
    return this.http.get<PetEntity[]>(`${environment.baseURL}/api/pet`, this.httpOptions)
      .pipe(
        tap(_ => console.log('Pet Service: fetches' +' pets')),
        catchError(this.handleError<PetEntity[]>('listPet',[]))
      );
  }
  createPet(petEntity: PetEntity) {
    console.log('PetService: creating pet');
    return this.http.post<PetEntity>(`${environment.baseURL}/api/pet`, petEntity, this.httpOptions);
  }
  private handleError<T>(operation: string, result?: any[]) {
    return (error: any): Observable<T> => {
      console.error(error);
      console.log(`${operation} failed: ${error.message}`);
      return of(result as unknown as T );
    }
  }

  findPetById(petId: number): Observable<PetEntity> {
    return this.http.get<PetEntity>(`${environment.baseURL}/api/pet/`+petId , this.httpOptions)
      .pipe(
        tap( _ => console.log('PetService: fetches petDetails')),
        catchError(this.handleError<PetEntity>('pet details', ))
      );
  }
}
