import { Component, OnInit } from '@angular/core';
import {GameServiceService} from '../../../services/game/game-service.service';
import {FormBuilder, FormGroup} from '@angular/forms';
import {Vector} from '../../../models/generic/Vector';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-city',
  templateUrl: './city.component.html',
  styleUrls: ['./city.component.css']
})
export class CityComponent implements OnInit {

  private cityData: CityData;

  coordinateForm: FormGroup = this.formBuilder.group({amount: 0});

  constructor(private formBuilder: FormBuilder,
              private http: HttpClient,
              private gameService: GameServiceService) { }

  async ngOnInit() {}

  async recruit(): Promise<any> {
    let response: any;
    let amount: number;
    if (this.coordinateForm.controls.amount.value < 0) {
      amount = 0;
    } else {
      amount = this.coordinateForm.controls.amount.value;
    }
    response = await this.gameService.recruit(amount).toPromise();
    alert(JSON.stringify(response));
  }

}
