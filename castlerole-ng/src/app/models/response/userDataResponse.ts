export class UserDataResponse {


  // tslint:disable-next-line:max-line-length
  constructor(username: string, x: number, y: number, wood: number, iron: number, stone: number, food: number, troops: number) {
    this.username = username;
    this.x = x;
    this.y = y;
    this.wood = wood;
    this.iron = iron;
    this.stone = stone;
    this.food = food;
    this.troops = troops;
  }

    username: string;
    x: number;
    y: number;
    wood: number;
    iron: number;
    stone: number;
    food: number;
    troops: number;
}
