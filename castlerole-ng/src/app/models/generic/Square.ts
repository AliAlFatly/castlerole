export class Square {
  constructor(private ctx: any) {}

  draw(x: number, y: number, z: number) {
    this.ctx.fillRect(z * x, z * y, z, z);
  }
}
