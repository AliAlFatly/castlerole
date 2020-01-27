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
  }

  // add token method
  private addToken(request: HttpRequest<any>, token: string) {
    // clone request, change cloned request to request + header {'Authorization' : 'bearer ${token}'}
    // return new version of request.
    return request.clone({
      setHeaders: { Authorization: `Bearer ${token}` }
    });
  }

}
