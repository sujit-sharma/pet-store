import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";

import {environment} from "../../environments/environment";
import {PetEntity} from "./entities";
import {Observable, of} from "rxjs";
import {catchError, tap} from "rxjs/operators";


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

  // listPets() {
  //   this.http.get<PetEntity>(`${environment.baseURL}/api/pet`,this.httpOptions).subscribe(
  //     response => {
  //       this.pets = response;
  //       console.log(response);}
  //   );
  //   return this.pets;
  // }

  private handleError<T>(operation: string, result?: any[]) {
    return (error: any): Observable<T> => {
      console.error(error);
      console.log(`${operation} failed: ${error.message}`);
      return of(result as unknown as T );
    }
  }
}
