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
		setTitle("�߲��ߵĹ���");
		setSize(810,480);
		setLocation(30,40);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Font f=new Font("�����п�",Font.BOLD,30);
		Font ff=new Font("���ķ���",Font.ITALIC,20);
		a=new JButton("����һ��");
		b=new JButton("����ʮ��");
		c=new JButton("����إ��");
		d=new JButton("ññ����������ʹ�� �� �� �� �� ���з�����ƣ��Ҵ���Ϸ������ͣ");
		a.setFont(f);
		b.setFont(f);
		c.setFont(f);
		d.setFont(ff);
		JLabel tff=new JLabel();
		JLabel tfh=new JLabel();
		JLabel tfff=new JLabel();
		BufferedImage img= null;
		try {
			img=ImageIO.read(new File("D:\\eclipse\\���հ�̰���ߡ�\\Modle\\src\\modle\\m.png"));
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
class cClicked  implements ActionListener{                        //�ٶȳ���
	JLabel mag;
	int row;
	int col;
	String s="�����ʮ�������";
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
class bClicked  implements ActionListener{                        //�ٶ�����
	JLabel mag;
	String s="����Ҳ������";
	private  SnakeFrame sf =new SnakeFrame(s,80);
	bClicked(JLabel mag){
		this.mag=mag;
	}
	public void actionPerformed(ActionEvent e) {
		sf.launch();
	}
}
class aClicked  implements ActionListener{                        //�ٶȽ���
	JLabel mag;
	String s="�Բ������ֱ�ǣ��û��,���ְ�";
	private  SnakeFrame sf =new SnakeFrame(s,150);
	aClicked(JLabel mag){
		this.mag=mag;
	}
	public void actionPerformed(ActionEvent e) {
		sf.launch();
	}
}