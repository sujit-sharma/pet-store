import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";

import {environment} from "../../environments/environment";
import {PetEntity} from "./entities";


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

  listPet() {
    return this.http.get<PetEntity>(`${environment.baseURL}/api/pet`,this.httpOptions);
  }
}
