import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {Vector} from '../../../models/generic/Vector';
import {GameServiceService} from '../../../services/game/game-service.service';
import {PointDataResponse} from '../../../models/response/pointDataResponse';
import {AttackResponse} from '../../../models/response/attackResponse';
import {UserDataResponse} from '../../../models/response/userDataResponse';

@Component({
  selector: 'app-game-details',
  templateUrl: './game-details.component.html',
  styleUrls: ['./game-details.component.css']
})
export class GameDetailsComponent implements OnChanges {

  @Input() targetCoordinates: Vector;
  @Input() userCoordinate: Vector;
  private pointDetails: PointDataResponse = new PointDataResponse(-1, -1, 'none', false);
  private attackResponse: AttackResponse = new AttackResponse(-1, -1, false, -1, -1, -1, -1, -1, -1, false);

  constructor(private gameService: GameServiceService) { }

  async ngOnChanges(changes: SimpleChanges) {
    await this.loadDetails();

  }

  loadDetails = async () => {
    if (this.targetCoordinates.x > -1) {
      this.pointDetails = await this.gameService.getPointData(this.targetCoordinates).toPromise();
    }
    if (this.targetCoordinates.x === this.userCoordinate.x && this.targetCoordinates.y === this.userCoordinate.y) {
      this.pointDetails.attackable = false;
    }
    this.attackResponse.x = -1;
  }

  attack = async () => {
    this.attackResponse = await this.gameService.attack(this.targetCoordinates).toPromise();
    // this.pointDetails = new PointDataResponse(-1,-1,"none", false);
  }


}
