import { Component, OnInit } from '@angular/core';
import {GameServiceService} from '../../../services/game/game-service.service';
import {FormBuilder, FormGroup} from '@angular/forms';
import {Vector} from '../../../models/generic/Vector';
import {HttpClient} from '@angular/common/http';
import {CityData} from '../../../models/response/CityData';
import {BuildingTooltip} from "../../../models/response/BuildingTooltip";

@Component({
  selector: 'app-city',
  templateUrl: './city.component.html',
  styleUrls: ['./city.component.css']
})
export class CityComponent implements OnInit {

  private cityData = new CityData('', 0, 0, 0, 0, 0, 0);
  private tooltipData = new BuildingTooltip(0, 0, 0, 0, false);
  private tooltipText = `empty`;
  coordinateForm: FormGroup = this.formBuilder.group({amount: 0});

  constructor(private formBuilder: FormBuilder,
              private http: HttpClient,
              private gameService: GameServiceService) { }

  async ngOnInit() {
    await this.updateCityData();
  }

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

  updateTooltip = async (action: string) => {
    this.tooltipData = await this.gameService.getTooltip(action).toPromise();
    this.tooltipText = `${action} + ${this.tooltipData.food}`;
  }

  updateCityData = async () => {
    this.cityData = await this.gameService.getCityData().toPromise();
  }

  updateBuilding = async (action: string) => {
    await this.gameService.updateBuilding(action).toPromise();
    // this.cityData = await this.gameService.getCityData().toPromise();
  }

}
