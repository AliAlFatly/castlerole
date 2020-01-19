import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of} from 'rxjs';
import {catchError, mapTo, tap} from 'rxjs/operators';
import {Tokens} from '../../models/authentication/Tokens';
import {config} from '../../config';


@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  // defining a const of JWT_TOKEN (KEY for JSON!),
  // change value from ='JWT_TOKEN' to a config.ts value for more globalization(for easier change later if needed)
  private readonly JWT_TOKEN = 'JWT_TOKEN';
  // NOTE: refresh_token might be useful in later stages, it is used to call the last state from the server??
  // private readonly REFRESH_TOKEN = 'REFRESH_TOKEN';
  private loggedUser: string;

  // injecting HttpClient class to make api calls.
  constructor(private http: HttpClient) {}

  login(user: { username: string, password: string }): Observable<boolean> {
    // user : {username, password} defined as parameter,
    // return:
    // post call to config.apiUrl(base url => for spring boot example http://localhost:8080)/login
    // pass user as argument for api call.
    return this.http.post<any>(`${config.apiUrl}/login`, user)
    // pipe asynchronous response (since the return is a observable it cant be assigned only (awaited?) subscribed/piped):
      .pipe(
        // tap keyword??? i assume it a lambda syntax? search later.
        // pass token to login function, setting localstorage key values:
        // username = user.username, token = response (jwt)token.
        tap(tokens => this.doLoginUser(user.username, tokens)),
        // mapTo change value of current state to true, so reponse = observable<true>,
        // so: try{return observable<true>} catch { alert(error) return false}
        mapTo(true),
        catchError(error => {
          alert(error.message);
          return of(false);
        }));
  }

  register(user: { username: string, password: string }): Observable<boolean> {
    // user : {username, password} defined as parameter,
    // return:
    // post call to config.apiUrl(base url => for spring boot example http://localhost:8080)/login
    // pass user as argument for api call.
    return this.http.post<any>(`${config.apiUrl}/register`, user)
    // pipe asynchronous response (since the return is a observable it cant be assigned only (awaited?) subscribed/piped):
      .pipe(
        // tap keyword??? i assume it a lambda syntax? search later.
        // pass token to login function, setting localstorage key values:
        // username = user.username, token = response (jwt)token.
        tap(tokens => this.doLoginUser(user.username, tokens)),
        // mapTo change value of current state to true, so reponse = observable<true>,
        // so: try{return observable<true>} catch { alert(error) return false}
        mapTo(true),
        catchError(error => {
          alert(error.message);
          return of(false);
        }));
  }

  logout() {
    this.doLogoutUser();
  }

  // refresh token logout, might be useful later???
  // logout() {
  //   return this.http.post<any>(`${config.apiUrl}/logout`, {
  //     'refreshToken': this.getRefreshToken()
  //   }).pipe(
  //     tap(() => this.doLogoutUser()),
  //     mapTo(true),
  //     catchError(error => {
  //       alert(error.error);
  //       return of(false);
  //     }));
  // }

  isLoggedIn() {
    return !!this.getJwtToken();
  }

  // refreshToken() {
  //   return this.http.post<any>(`${config.apiUrl}/refresh`, {
  //     'refreshToken': this.getRefreshToken()
  //   }).pipe(tap((tokens: Tokens) => {
  //     this.storeJwtToken(tokens.jwt);
  //   }));
  // }

  getJwtToken() {
    // return localStorage.getItem(this.JWT_TOKEN);
    return localStorage.getItem('JWT_TOKEN');
  }

  private doLoginUser(username: string, tokens: Tokens) {
    this.loggedUser = username;
    this.storeTokens(tokens);
  }

  private doLogoutUser() {
    this.loggedUser = null;
    this.removeTokens();
  }

  // private getRefreshToken() {
  //   return localStorage.getItem(this.REFRESH_TOKEN);
  // }

  private storeJwtToken(jwt: string) {
    localStorage.setItem(this.JWT_TOKEN, jwt);
  }

  private storeTokens(tokens: Tokens) {
    localStorage.setItem(this.JWT_TOKEN, tokens.token);
    // localStorage.setItem(this.REFRESH_TOKEN, tokens.refreshToken);
  }

  private removeTokens() {
    localStorage.removeItem(this.JWT_TOKEN);
    // localStorage.removeItem(this.REFRESH_TOKEN);
  }
}
