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
 * ��ɵĹ��ܣ���ʼ��Ϸ
 * */
import java.util.Random;

public class SnakeFrame extends Frame{
	//����Ŀ�Ⱥͳ���
		public static final int BLOCK_WIDTH = 10 ;
		public static final int BLOCK_HEIGHT = 10 ;
		//����ķ��������������
		public static final int ROW = 80;
		public static final int COL = 80;
		
		//�÷�
		private int score = 0;
		int spe;
		String st;
		public int getScore() {
			return score;
		}

		public void setScore(int score) {
			this.score = score;
		}
		//��ͼ���̶߳���
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
			this.setTitle("�����߲����𣿣���"+"("+st+")");
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
			//Ϊ������Ӽ����¼�
			this.addKeyListener(new KeyMonitor());
			
			new Thread(paintThread).start();
		}	
		private boolean b_gameOver = false;

		public void gameOver(){
			b_gameOver = true;
		}		
		 // ��дupdate����
		@Override
		public void update(Graphics g) {
			if(offScreenImage==null){
				offScreenImage = this.createImage(ROW*BLOCK_HEIGHT, COL*BLOCK_WIDTH);
			}
			Graphics offg = offScreenImage.getGraphics();
			//�Ƚ����ݻ������⻭����
			paint(offg);
			//Ȼ�����⻭���ϵ�����һ���ڻ�����
			g.drawImage(offScreenImage, 0, 0, null);	
			if(b_gameOver){
				g.setFont(new Font("����",Font.CENTER_BASELINE,100));
				g.drawString("��Ϸ��������", ROW/6*BLOCK_HEIGHT, COL/2*BLOCK_WIDTH);
				paintThread.dead();
			}
			snake.draw(g);
			boolean b_Success=snake.eatEgg(egg);
			//��һ����5��
			if(b_Success){
				score+=5;
			}
			egg.draw(g);
			displaySomeInfor(g);			
		}
		// �������ܣ��ڽ�������ʾһЩ��ʾ��Ϣ
		public void displaySomeInfor(Graphics g){
			Color c = g.getColor();
			Random r = new Random();
			g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
			g.drawString("", 8*BLOCK_HEIGHT, 8*BLOCK_WIDTH);
			g.setFont(new Font("����",Font.CENTER_BASELINE,25));
			g.drawString("�÷�:"+score, 2*BLOCK_HEIGHT, 7*BLOCK_WIDTH);		
			g.setColor(c);		
		}
		@Override
		public void paint(Graphics g) {
			Color c = g.getColor();
			g.setColor(Color.gray);			
			 // �����滭����ROW*COL�ķ��񹹳�,����forѭ�����ɽ��		 
			for(int i = 0;i<ROW;i++){
				g.drawLine(0, i*BLOCK_HEIGHT, COL*BLOCK_WIDTH,i*BLOCK_HEIGHT );
			}
			for(int i=0;i<COL;i++){
				g.drawLine(i*BLOCK_WIDTH, 0 , i*BLOCK_WIDTH ,ROW*BLOCK_HEIGHT);
			}
			
			g.setColor(c);
		}				
	//	 �ػ��߳���		 
		private class MyPaintThread implements Runnable{
			//running���ܸı䣬�ı����߳̾ͽ�����
			private static final boolean  running = true;
			private boolean  pause = false;
			@Override
			public void run() {
				while(running){
					//���pause Ϊtrue ������ͣ
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
