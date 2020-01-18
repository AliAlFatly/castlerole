import { Injectable } from '@angular/core';
import { Observable, throwError, BehaviorSubject } from 'rxjs';
import {AuthenticationService} from './authentication.service';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor, HttpErrorResponse } from '@angular/common/http';
import { catchError, filter, take, switchMap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
// implements HttpInterceptor!
// when ever a request is made, this class is called?
// handles token:
// adds => headers: { 'Authorization' : 'bearer ' + sessionStorage.Token } to xhr calls ?????
// process every outgoing call by httpClient
export class TokenInterceptorService implements HttpInterceptor {


  private refreshTokenSubject: BehaviorSubject<any> = new BehaviorSubject<any>(null);

  constructor(public authService: AuthenticationService) { }

  intercept(request: HttpRequest<any>, next: HttpHandler) {
    if (this.authService.getJwtToken() != null) {
      const tokenizedReq = request.clone({
        setHeaders: { Authorization: `Bearer ${this.authService.getJwtToken()}` }
      });
      return next.handle(tokenizedReq);
    } else {
      return next.handle(request.clone());
    }
    // if token is not null, add token to request.


    // if no token, return a error handler for handling unauthorized response
    //   .pipe(catchError(error => {
    //   //pipe error
    //   //if error is a response and is 401
    //   //if (error instanceof HttpErrorResponse && error.status === 401) {
    //     //return handle401Error function and passes the data passed with the error
    //   //   return this.handle401Error(request, next);
    //   // } else {
    //     //else throwError (500??? internal server error????)
    //     return throwError(error);
    //   //}
    // }));
  }

  // add token method
  private addToken(request: HttpRequest<any>, token: string) {
    // clone request (slice?), change cloned request to request + header {'Authorization' : 'bearer ${token}'}
    // return new version of request.
    return request.clone({
      setHeaders: { Authorization: `Bearer ${token}` }
    });
  }

  // private handle401Error(request: HttpRequest<any>, next: HttpHandler) {
  //   if (!this.isRefreshing) {
  //     this.isRefreshing = true;
  //     this.refreshTokenSubject.next(null);
  //
  //     return this.authService.refreshToken().pipe(
  //       switchMap((token: any) => {
  //         this.isRefreshing = false;
  //         this.refreshTokenSubject.next(token.jwt);
  //         return next.handle(this.addToken(request, token.jwt));
  //       }));
  //
  //   } else {
  //     return this.refreshTokenSubject.pipe(
  //       filter(token => token != null),
  //       take(1),
  //       switchMap(jwt => {
  //         return next.handle(this.addToken(request, jwt));
  //       }));
  //   }
  // }
}
