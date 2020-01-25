export class BuildingTooltip {

  constructor(food: number, wood: number, stone: number, iron: number, upgradeable: boolean) {
    this.food = food;
    this.wood = wood;
    this.stone = stone;
    this.iron = iron;
    this.upgradeable = upgradeable;
  }

  food: number;
  wood: number;
  stone: number;
  iron: number;
  upgradeable: boolean;
}
