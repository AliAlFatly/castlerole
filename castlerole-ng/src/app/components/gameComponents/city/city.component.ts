import { Component, OnInit } from '@angular/core';
import {GameServiceService} from '../../../services/game/game-service.service';
import {FormBuilder, FormGroup} from '@angular/forms';
import {Vector} from '../../../models/generic/Vector';
import {HttpClient} from '@angular/common/http';
import {CityData} from '../../../models/response/CityData';
import {BuildingTooltip} from '../../../models/response/BuildingTooltip';

@Component({
  selector: 'app-city',
  templateUrl: './city.component.html',
  styleUrls: ['./city.component.css']
})
export class CityComponent implements OnInit {

  private cityData = new CityData('', 0, 0, 0, 0, 0, 0);
  private tooltipData = new BuildingTooltip(0, 0, 0, 0, false);
  private tooltipText = `empty`;
  private maxBuildingLevel = 35;
  coordinateForm: FormGroup = this.formBuilder.group({amount: 0});

  constructor(private formBuilder: FormBuilder,
              private http: HttpClient,
              private gameService: GameServiceService) { }

  async ngOnInit() {
    await this.getCityData();
    this.updateCityData();
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
    const levelingRequirements = `, Requirements for leveling: food(${this.tooltipData.food}), wood(${this.tooltipData.wood}), stone(${this.tooltipData.stone}), iron(${this.tooltipData.iron})`;
    switch (action) {
      case 'Castle':
        // tslint:disable-next-line:max-line-length
        this.tooltipText = `Each level of the ${action} increases troop dameg by 1% up to a maximum of 35 levels (35% more dameg!)` + levelingRequirements;
        break;
      case 'Barack':
        // tslint:disable-next-line:max-line-length
        this.tooltipText = `Each level of the ${action} increases troop dameg by 1% up to a maximum of 35 levels (35% more dameg!)` + levelingRequirements;
        break;
      case 'Forgery':
        this.tooltipText = `Each level of the ${action} increases the iron production by 5%` + levelingRequirements;
        break;
      case 'Mine':
        this.tooltipText = `Each level of the ${action} increases the stone production by 5%` + levelingRequirements;
        break;
      case 'Oven':
        this.tooltipText = `Each level of the ${action} increases the food production by 5%` + levelingRequirements;
        break;
      case 'Woodworks':
        this.tooltipText = `Each level of the ${action} increases the wood production by 5%` + levelingRequirements;
        break;
    }
  }

  getCityData = async () => {
    this.cityData = await this.gameService.getInitialCityData().toPromise();
  }

  updateBuilding = async (action: string) => {
    await this.gameService.updateBuilding(action).toPromise();
    // this.cityData = await this.gameService.getCityData().toPromise();
  }

  updateCityData = async () => {
    this.gameService.getCityData().subscribe(data => this.cityData = data, err => console.log(err));
    // this.cityData = await this.gameService.getCityData().toPromise();
  }

}
