export class AttackResponse {


  // tslint:disable-next-line:max-line-length
  constructor(x: number, y: number, isWon: boolean, enemyTroopCount: number, attackerTroopCount: number, ironWon: number, foodWon: number, stoneWon: number, woodWon: number, attackable: boolean) {
    this.x = x;
    this.y = y;
    this.won = isWon;
    this.enemyTroopCount = enemyTroopCount;
    this.attackerTroopCount = attackerTroopCount;
    this.ironWon = ironWon;
    this.foodWon = foodWon;
    this.stoneWon = stoneWon;
    this.woodWon = woodWon;
    this.attackable = attackable;
  }

  x: number;
  y: number;
  won: boolean;
  enemyTroopCount: number;
  attackerTroopCount: number;
  ironWon: number;
  foodWon: number;
  stoneWon: number;
  woodWon: number;
  attackable: boolean;

}
