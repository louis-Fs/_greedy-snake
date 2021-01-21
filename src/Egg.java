package modle;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Egg {
	//所在的位置
		private int row;
		private int col;
		//大小
		private static final int BLOCK_WIDTH = SnakeFrame.BLOCK_WIDTH;
		private static final int BLOCK_HEIGHT = SnakeFrame.BLOCK_HEIGHT;	
		private static final Random r = new Random();
		//设置闪闪发光的egg
		private Color color = new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255));
		public Egg(int row, int col) {
			this.row = row;
			this.col = col;
		}
		public Egg() {
			this((r.nextInt(SnakeFrame.ROW-4))+2,(r.nextInt(SnakeFrame.COL-4))+2);
		}
		//在次出现egg
		public void reAppear(){
			this.row = (r.nextInt(SnakeFrame.ROW-8))+2;
			this.col = (r.nextInt(SnakeFrame.COL-8))+2;
		} 
		//画egg
		public void draw(Graphics g){
			Random r = new Random();
			Color c= g.getColor();
			g.setColor(color);
			g.fillOval(col*BLOCK_WIDTH, row*BLOCK_HEIGHT, 4*BLOCK_WIDTH,4* BLOCK_HEIGHT);
			g.setColor(c);
			//改变下一次的颜色
				color = new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255));
			
		}
		//用于碰撞检测
		public Rectangle getRect(){
			return new Rectangle(col*BLOCK_WIDTH, row*BLOCK_HEIGHT, 4*BLOCK_WIDTH,4* BLOCK_HEIGHT);
		}
		
}
