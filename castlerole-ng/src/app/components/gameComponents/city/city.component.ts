import { Component, OnInit } from '@angular/core';
import {GameServiceService} from '../../../services/game/game-service.service';
import {FormBuilder, FormGroup} from '@angular/forms';
import {Vector} from '../../../models/generic/Vector';
import {HttpClient} from '@angular/common/http';
import {CityData} from '../../../models/response/CityData';
import {Tooltip} from '../../../models/response/tooltip';

@Component({
  selector: 'app-city',
  templateUrl: './city.component.html'
})
export class CityComponent implements OnInit {

  private cityData = new CityData('', 0, 0, 0, 0, 0, 0);
  private tooltipData = new Tooltip(0, 0, 0, 0);
  private tooltipText = `empty`;
  private maximumRecruitableTroops = 0;
  private maxBuildingLevel = 35;
  private recruitmentTooltip = `You can recruit upto ${this.maximumRecruitableTroops} troops`;
  private recruitmentAmount = 0;
  coordinateForm: FormGroup = this.formBuilder.group({amount: 0});

  constructor(private formBuilder: FormBuilder,
              private http: HttpClient,
              private gameService: GameServiceService) { }

  async ngOnInit() {
    await this.getCityData();
    await this.getRecruitmentTooltip();
    this.updateCityData();
    this.updateRecruitmentTooltip();
  }

  async recruit(): Promise<any> {
    let amount: number;
    if (this.coordinateForm.controls.amount.value < 0) {
      amount = 0;
    } else {
      amount = this.coordinateForm.controls.amount.value;
    }
    await this.gameService.recruit(amount).toPromise();
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

  updateRecruitmentTooptip = async () => {
    this.recruitmentTooltip = `You can recruit upto ${this.maximumRecruitableTroops} troops`;
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

  getRecruitmentTooltip = async () => {
    this.maximumRecruitableTroops = await this.gameService.getRecruitmentTooltip().toPromise();
  }

  updateRecruitmentTooltip = async () => {
    this.gameService.updateRecruitmentTooltip().subscribe( amount => this.maximumRecruitableTroops = amount, err => console.log(err));
  }

  setRecruitmentAmount = async () => {
    this.recruitmentAmount = this.coordinateForm.controls.amount.value;
  }

}
