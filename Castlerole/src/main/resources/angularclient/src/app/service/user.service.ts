import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "../model/user";


@Injectable({
  providedIn: 'root'
})
export class UserService {

  private registerUrl: string;
  private loginUrl: string;

  constructor(private http: HttpClient) {
    this.registerUrl = 'http://localhost:8080/register';
    this.loginUrl = 'http://localhost:8080/authenticate';
  }

  public save(user: User) {
    return this.http.post<User>(this.registerUrl, user);
  }

  public authenticate(user: User) {
    return this.http.post<User>(this.loginUrl, user);
  }
}
