import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {GameServiceService} from '../../../services/game/game-service.service';
import { UserDataResponse } from '../../../models/response/userDataResponse';
import {Vector} from '../../../models/generic/Vector';

@Component({
  selector: 'app-user-information',
  templateUrl: './user-information.component.html'
})
export class UserInformationComponent implements OnChanges {

  private userData = new UserDataResponse('', -1, -1, -1, -1, -1, -1, -1);

  @Output() userCoordinatesEmitter = new EventEmitter<Vector>();

  // private interval = Interval(1000);


  constructor(private gameService: GameServiceService) { }

  // tslint:disable-next-line:use-lifecycle-interface
  async ngOnInit() {
    await this.getInitialData();
    this.updateUserData();

  }

  async ngOnChanges(changes: SimpleChanges) {
    await this.updateUserData();
  }

  // async ngDoCheck(){
  //   await this.updateUserData();
  // }

  getInitialData = async () => {
    this.userData = await this.gameService.getInitialUserData().toPromise();
    this.userCoordinatesEmitter.emit(new Vector(this.userData.coordinateX, this.userData.coordinateY));
  }

  updateUserData = async () => {
    this.gameService.getUserData().subscribe(data => this.userData = data, err => console.log(err));
    // this.userCoordinatesEmitter.emit(new Vector(this.userData.coordinateX, this.userData.coordinateY));
  }

}
