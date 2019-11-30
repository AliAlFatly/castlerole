export class square{
  constructor(private ctx: CanvasRenderingContext2D) {}

  draw(x: number, y: number, z: number) {
      this.ctx.fillRect(z * x, z * y, z, z);
    }
}
