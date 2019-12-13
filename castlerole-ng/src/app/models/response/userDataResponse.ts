export class UserDataResponse{


  constructor(username: string, coordinateX: number, coordinateY: number, wood: number, iron: number, stone: number, food: number, troops: number) {
    this.username = username;
    this.coordinateX = coordinateX;
    this.coordinateY = coordinateY;
    this.wood = wood;
    this.iron = iron;
    this.stone = stone;
    this.food = food;
    this.troops = troops;
  }

  username: string;
    coordinateX: number;
    coordinateY: number;
    wood: number;
    iron: number;
    stone: number;
    food: number;
    troops: number;
}
