import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {Vector} from '../../../models/generic/Vector';
import {GameServiceService} from '../../../services/game/game-service.service';
import {PointDataResponse} from '../../../models/response/pointDataResponse';
import {AttackResponse} from '../../../models/response/attackResponse';
import {UserDataResponse} from '../../../models/response/userDataResponse';
import {cityDataResponse} from '../../../models/response/cityDataResponse';

@Component({
  selector: 'app-game-details',
  templateUrl: './game-details.component.html',
  styleUrls: ['./game-details.component.css']
})
export class GameDetailsComponent implements OnChanges {

  @Input() targetCoordinates: Vector;
  @Input() userCoordinate: Vector;
  private displayUserCity: boolean;
  private pointDetails: PointDataResponse = new PointDataResponse(-1, -1, 'none', false);
  private attackResponse: AttackResponse = new AttackResponse(-1, -1, false, -1, -1, -1, -1, -1, -1, false);
  private cityDataResponse: cityDataResponse = new cityDataResponse('', 1, 1, 1, 1, 1, 1);

  constructor(private gameService: GameServiceService) { }

  async ngOnChanges(changes: SimpleChanges) {
    await this.loadDetails();

  }

  loadDetails = async () => {
    if (this.targetCoordinates.x > -1) {
      this.pointDetails = await this.gameService.getPointData(this.targetCoordinates).toPromise();
      this.displayUserCity = false;
    }
    if (this.targetCoordinates.x === this.userCoordinate.x && this.targetCoordinates.y === this.userCoordinate.y) {
      this.pointDetails.attackable = false;
      this.displayUserCity = true;
    }
    this.attackResponse.x = -1;
    
  }

  attack = async () => {
    this.attackResponse = await this.gameService.attack(this.targetCoordinates).toPromise();
    // this.pointDetails = new PointDataResponse(-1,-1,"none", false);
  }



}
