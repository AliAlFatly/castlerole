export class cityDataResponse {


    // tslint:disable-next-line:max-line-length
    constructor(owner: string, casteLevel: number, woodworksLevel: number, mineLevel: number, forgeryLevel: number, barracksLevel: number, ovenLevel: number) {
      this.owner = owner;
      this.casteLevel = casteLevel;
      this.woodworksLevel = woodworksLevel;
      this.mineLevel = mineLevel;
      this.forgeryLevel = forgeryLevel;
      this.barracksLevel = barracksLevel;
      this.ovenLevel = ovenLevel;
    }
  
      owner: string;
      casteLevel: number;
      woodworksLevel: number;
      mineLevel: number;
      forgeryLevel: number;
      barracksLevel: number;
      ovenLevel: number;
  }
  