export class PointDataResponse {
  constructor(x: number, y: number, type: string, attackable: boolean) {
    this.coordinateX = x;
    this.coordinateY = y;
    this.type = type;
    this.attackable = attackable;
  }

  coordinateX: number;
  coordinateY: number;
  type: string;
  attackable: boolean;
}
