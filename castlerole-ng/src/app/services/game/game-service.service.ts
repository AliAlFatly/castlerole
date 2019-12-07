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
  return this.http.get(`${config.apiUrl}/grid/${vector.x}/${vector.y}`)
  };

  getPointData(vector: Vector): Observable<any>{
    return this.http.get(`${config.apiUrl}/pointData/${vector.x}/${vector.y}`)
  };

  getUserCoordinates(): Observable<any>{
      return this.http.get<Vector>(`${config.apiUrl}/userCoordinates`);
  }

  getUserData(): Observable<any>{
      return this.http.get(`${config.apiUrl}/userData`)
  };
}

