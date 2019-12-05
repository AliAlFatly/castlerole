import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of} from "rxjs";
import {catchError, mapTo, tap} from "rxjs/operators";
import {Tokens} from "../../models/authentication/Tokens";
import {config} from "../../config";
import {Vector} from "../../models/generic/Vector";

@Injectable({
  providedIn: 'root'
})
export class GameServiceService {

  constructor(private http: HttpClient) { }

  getGrid(vector: Vector): Observable<any>{
  return this.http.post(`${config.apiUrl}/grid`, vector)};

  getPointData(vector: Vector): Observable<any>{
    return this.http.post(`${config.apiUrl}/pointData`, vector)};

  getUserCoordinates(): Observable<any>{
      return this.http.post<Vector>(`${config.apiUrl}/userCoordinates`, localStorage.getItem('JWT_TOKEN'));
  }

  getUserData(): Observable<any>{
      return this.http.post(`${config.apiUrl}/userData`, localStorage.getItem('JWT_TOKEN'))};
}
