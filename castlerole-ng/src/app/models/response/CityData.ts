export class CityData {


  constructor(owner: string, castleLevel: number, woodworksLevel: number, mineLevel: number,
              forgeryLevel: number, barracksLevel: number, ovenLevel: number) {
    this.owner = owner;
    this.castleLevel = castleLevel;
    this.woodworksLevel = woodworksLevel;
    this.mineLevel = mineLevel;
    this.forgeryLevel = forgeryLevel;
    this.barracksLevel = barracksLevel;
    this.ovenLevel = ovenLevel;
  }

  owner: string;
  castleLevel: number;
  woodworksLevel: number;
  mineLevel: number;
  forgeryLevel: number;
  barracksLevel: number;
  ovenLevel: number;

}
