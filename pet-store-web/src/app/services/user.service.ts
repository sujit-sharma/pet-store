import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {UserEntity} from "./entities";


@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  createUser(entity: UserEntity) {
    return this.http.post<UserEntity>(`${environment.baseURL}/api/user`, entity)
  }

}
