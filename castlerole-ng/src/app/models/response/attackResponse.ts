export class AttackResponse {


  constructor(x: number, y: number, isWon: boolean, enemyTroopCount: number, attackerTroopCount: number, ironWon: number, foodWon: number, stoneWon: number, woodWon: number, attackable: boolean) {
    this.x = x;
    this.y = y;
    this.isWon = isWon;
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
  isWon: boolean;
  enemyTroopCount: number;
  attackerTroopCount: number;
  ironWon: number;
  foodWon: number;
  stoneWon: number;
  woodWon: number;
  attackable: boolean;

}
