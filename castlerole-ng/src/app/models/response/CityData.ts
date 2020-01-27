export class CityData {


  constructor(owner: string, castle: number, woodwork: number, mine: number,
              forgery: number, barrack: number, oven: number) {
    this.owner = owner;
    this.castle = castle;
    this.woodwork = woodwork;
    this.mine = mine;
    this.forgery = forgery;
    this.barrack = barrack;
    this.oven = oven;
  }

  owner: string;
  castle: number;
  woodwork: number;
  mine: number;
  forgery: number;
  barrack: number;
  oven: number;

}
