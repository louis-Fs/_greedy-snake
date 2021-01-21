package modle;
import java.util.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
public class Pattern2 {

	public static void main(String[] args) {
	MinWnd kw=new MinWnd();
	
	}
}
class MinWnd extends JFrame{
	private JButton a,b,c,d;
	int  nu1,nu2,nu3;
//	private Comment c=new Comment();
	public MinWnd() {
		setTitle("七彩蛇的故事");
		setSize(810,480);
		setLocation(30,40);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Font f=new Font("华文行楷",Font.BOLD,30);
		Font ff=new Font("华文仿宋",Font.ITALIC,20);
		a=new JButton("单身一年");
		b=new JButton("单身十年");
		c=new JButton("单身廿年");
		d=new JButton("帽帽提醒您：请使用 → ← ↑ ↓ 进行方向控制，且此游戏不可暂停");
		a.setFont(f);
		b.setFont(f);
		c.setFont(f);
		d.setFont(ff);
		JLabel tff=new JLabel();
		JLabel tfh=new JLabel();
		JLabel tfff=new JLabel();
		BufferedImage img= null;
		try {
			img=ImageIO.read(new File("D:\\eclipse\\【终版贪吃蛇】\\Modle\\src\\modle\\m.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		JLabel jpg=new JLabel(new ImageIcon(img));
		Container cp=getContentPane();
		cp.setLayout(null);
		cp.add(jpg);
		cp.add(c);
		cp.add(a);
		cp.add(b);
		cp.add(d);
		jpg.setBounds(0, 0, img.getWidth(), img.getHeight());
		jpg.setOpaque(false);
		a.setBounds(10, 10, 180, 50);
		b.setBounds(10, 70, 180, 50);
		c.setBounds(10, 130,180, 50);
		d.setBounds(125, 330, 650, 50);
		cp.invalidate();
		a.addActionListener(new aClicked(tff));
		b.addActionListener(new bClicked(tfh));
		c.addActionListener(new cClicked(tfff));
	}
}
class cClicked  implements ActionListener{                        //速度超快
	JLabel mag;
	int row;
	int col;
	String s="单身二十年的手速";
	int spe;
	private  SnakeFrame sf =new SnakeFrame(s,20);
	cClicked(JLabel mag){
		Random r1=new Random();
		int numn=13;
		int num=27;
		this.row=r1.nextInt(1000) % numn +3;
		this.col=r1.nextInt(1000) % num +3;
		this.mag=mag;
	}
	public void actionPerformed(ActionEvent e) {
		sf.launch();
	}
}
class bClicked  implements ActionListener{                        //速度适中
	JLabel mag;
	String s="曾经也浪漫过";
	private  SnakeFrame sf =new SnakeFrame(s,80);
	bClicked(JLabel mag){
		this.mag=mag;
	}
	public void actionPerformed(ActionEvent e) {
		sf.launch();
	}
}
class aClicked  implements ActionListener{                        //速度较慢
	JLabel mag;
	String s="对不起右手被牵着没空,左手吧";
	private  SnakeFrame sf =new SnakeFrame(s,150);
	aClicked(JLabel mag){
		this.mag=mag;
	}
	public void actionPerformed(ActionEvent e) {
		sf.launch();
	}
}