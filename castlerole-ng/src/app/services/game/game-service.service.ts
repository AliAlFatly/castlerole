import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of, interval } from 'rxjs';
import {flatMap} from 'rxjs/operators';
import {config} from '../../config';
import {Vector} from '../../models/generic/Vector';
import {UserDataResponse} from '../../models/response/userDataResponse';
import {CityData} from '../../models/response/CityData';

@Injectable({
  providedIn: 'root'
})
export class GameServiceService {

  constructor(private http: HttpClient) { }

  getGrid(vector: Vector): Observable<any> {
  return this.http.get(`${config.apiUrl}/grid/${vector.x}/${vector.y}`);
  }

  getPointData(vector: Vector): Observable<any> {
    return this.http.get(`${config.apiUrl}/pointData/${vector.x}/${vector.y}`);
  }

  getUserCoordinates(): Observable<any> {
      return this.http.get<Vector>(`${config.apiUrl}/userCoordinates`);
  }

  getInitialUserData(): Observable<any>  {
    return this.http.get<Vector>(`${config.apiUrl}/userData`);

  }

  getUserData() {
    return interval(1000).pipe(flatMap(() => {
      return this.http.get<UserDataResponse>(`${config.apiUrl}/userData`);
    }));
  }

  recruit(amount: number): Observable<any> {
    return this.http.get(`${config.apiUrl}/recruit/${amount}`);
  }

  updateBuilding(action: string): Observable<any> {
    return this.http.get(`${config.apiUrl}/update/${action}`);
  }

  getTooltip(action: string): Observable<any> {
    return this.http.get(`${config.apiUrl}/tooltip/${action}`);
  }

  getInitialCityData(): Observable<any> {
    return this.http.get(`${config.apiUrl}/cityData`);
  }

  getCityData(): Observable<any> {
    return interval(1000).pipe(flatMap(() => {
      return this.http.get<CityData>(`${config.apiUrl}/cityData`);
    }));
  }

  getRecruitmentTooltip(): Observable<any> {
    return this.http.get<number>(`${config.apiUrl}/recruitmentTooltip`);
  }

  updateRecruitmentTooltip(): Observable<any> {
    return interval(1000).pipe(flatMap(() => {
      return this.http.get<number>(`${config.apiUrl}/recruitmentTooltip`);
    }));
  }

  attack(vector: Vector): Observable<any> {
    return this.http.get(`${config.apiUrl}/attack/${vector.x}/${vector.y}`);
  }
}

