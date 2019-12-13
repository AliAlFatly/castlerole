import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {Vector} from "../../../models/generic/Vector";
import {GameServiceService} from "../../../services/game/game-service.service";

@Component({
  selector: 'app-game-details',
  templateUrl: './game-details.component.html',
  styleUrls: ['./game-details.component.css']
})
export class GameDetailsComponent implements OnChanges {

  @Input() targetCoordinates: Vector;

  constructor(private gameService: GameServiceService) { }

  async ngOnChanges(changes: SimpleChanges) {
    await this.loadDetails();

  }

  loadDetails = async () => {
    if (this.targetCoordinates.x > -1){

    }
  }

}
