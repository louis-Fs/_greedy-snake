package modle;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/*
 * 完成的功能：开始游戏
 * */
import java.util.Random;

public class SnakeFrame extends Frame{
	//方格的宽度和长度
		public static final int BLOCK_WIDTH = 10 ;
		public static final int BLOCK_HEIGHT = 10 ;
		//界面的方格的行数和列数
		public static final int ROW = 80;
		public static final int COL = 80;
		
		//得分
		private int score = 0;
		int spe;
		String st;
		public int getScore() {
			return score;
		}

		public void setScore(int score) {
			this.score = score;
		}
		//画图的线程对象
		private MyPaintThread paintThread = new MyPaintThread();

		private Image offScreenImage = null;
		
		private Snake snake = new Snake(this);
		
		private Egg egg = new Egg();
		
		private static SnakeFrame sf =null;
		
		private Pattern2 pa=new Pattern2();
		
//		private Comment c=new Comment();
		
		public SnakeFrame(String b, int c) {
			st=b;
			spe=c;
			
		}
		public void launch(){
			this.setTitle("见过七彩蛇吗？？？"+"("+st+")");
			this.setSize(ROW*BLOCK_HEIGHT, COL*BLOCK_WIDTH);
			this.setLocation(830, 41);
			this.setBackground(Color.WHITE);
			this.addWindowListener(new WindowAdapter() {

				@Override
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}				
			});
			this.setResizable(false);
			this.setVisible(true);
	//		c.Windcommment();
			//为界面添加监听事件
			this.addKeyListener(new KeyMonitor());
			
			new Thread(paintThread).start();
		}	
		private boolean b_gameOver = false;

		public void gameOver(){
			b_gameOver = true;
		}		
		 // 重写update方法
		@Override
		public void update(Graphics g) {
			if(offScreenImage==null){
				offScreenImage = this.createImage(ROW*BLOCK_HEIGHT, COL*BLOCK_WIDTH);
			}
			Graphics offg = offScreenImage.getGraphics();
			//先将内容画在虚拟画布上
			paint(offg);
			//然后将虚拟画布上的内容一起画在画布上
			g.drawImage(offScreenImage, 0, 0, null);	
			if(b_gameOver){
				g.setFont(new Font("楷体",Font.CENTER_BASELINE,100));
				g.drawString("游戏结束！！", ROW/6*BLOCK_HEIGHT, COL/2*BLOCK_WIDTH);
				paintThread.dead();
			}
			snake.draw(g);
			boolean b_Success=snake.eatEgg(egg);
			//吃一个加5分
			if(b_Success){
				score+=5;
			}
			egg.draw(g);
			displaySomeInfor(g);			
		}
		// 函数功能：在界面上显示一些提示信息
		public void displaySomeInfor(Graphics g){
			Color c = g.getColor();
			Random r = new Random();
			g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
			g.drawString("", 8*BLOCK_HEIGHT, 8*BLOCK_WIDTH);
			g.setFont(new Font("楷体",Font.CENTER_BASELINE,25));
			g.drawString("得分:"+score, 2*BLOCK_HEIGHT, 7*BLOCK_WIDTH);		
			g.setColor(c);		
		}
		@Override
		public void paint(Graphics g) {
			Color c = g.getColor();
			g.setColor(Color.gray);			
			 // 将界面画成由ROW*COL的方格构成,两个for循环即可解决		 
			for(int i = 0;i<ROW;i++){
				g.drawLine(0, i*BLOCK_HEIGHT, COL*BLOCK_WIDTH,i*BLOCK_HEIGHT );
			}
			for(int i=0;i<COL;i++){
				g.drawLine(i*BLOCK_WIDTH, 0 , i*BLOCK_WIDTH ,ROW*BLOCK_HEIGHT);
			}
			
			g.setColor(c);
		}				
	//	 重画线程类		 
		private class MyPaintThread implements Runnable{
			//running不能改变，改变后此线程就结束了
			private static final boolean  running = true;
			private boolean  pause = false;
			@Override
			public void run() {
				while(running){
					//如果pause 为true ，则暂停
					if(pause){
						continue;
					}
					repaint();
					try {
						Thread.sleep(spe);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}			
			}		
			public void dead(){
				pause = true;
			}			
		}		
		private class KeyMonitor extends KeyAdapter{
			
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();		
				snake.keyPressed(e);						
			}			
		}
}
