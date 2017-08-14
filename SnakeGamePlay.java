import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer; 

public class GamePlay extends JPanel implements KeyListener,ActionListener {
 private int[] xlength=new int[750];
 private int[] ylength=new int[750];
 
 int moves=0;
 private boolean left=false;
 private boolean right=false;
 private boolean up=false;
 private boolean down=false;
  
 private ImageIcon leftmouth;
 private ImageIcon rightmouth;
 private ImageIcon upmouth;
 private ImageIcon downmouth;
 
 private int lengthofsnake=3;
 
private int score=0; 

 private Timer timer;
 private int delay=100;
 
 private int [] enemyxpos={25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,
		 700,725,750,775,800,825,850};
 private int [] enemyypos={75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625};
 
 private ImageIcon snakeimage;
 
 private Random random=new Random();
 private int xpos=random.nextInt(34);
 private int ypos=random.nextInt(23);
 
	private ImageIcon titleImage;
	public  GamePlay(){
	
	addKeyListener(this);
	setFocusable(true);
	setFocusTraversalKeysEnabled(false);
	timer =new Timer(delay,this);
	timer.start();
	
}
    public void paint(Graphics g){
	if(moves==0)
	{
		xlength[2]=50;
		xlength[1]=75;
		xlength[0]=100;
	
		ylength[2]=100;
		ylength[1]=100;
		ylength[0]=100;	
	}
    	
    	//draw title image boarder
	g.setColor(Color.blue);
	g.drawRect(24, 10, 855, 55);
	
	//draw title image
	titleImage =new ImageIcon("snaketitle.jpg");
	titleImage.paintIcon(this,g ,25,11 );
	
	//draw boarder for gp
g.setColor(Color.CYAN);
g.drawRect(24, 74, 851,577);

//draw background for gp
g.setColor(Color.BLACK);
g.fillRect(25,75,850,575);

//draw score
g.setColor(Color.green);
g.setFont(new Font("arial",Font.ITALIC,14));
g.drawString("Score: "+score ,780,30);

rightmouth =new ImageIcon("rightmouth.png");
rightmouth.paintIcon(this,g,xlength[0],ylength[0]);

for(int i=0;i<lengthofsnake;i++)
{
if(i==0 && right)	
{
	rightmouth =new ImageIcon("rightmouth.png");
	rightmouth.paintIcon(this,g,xlength[i],ylength[i]);
	}

if(i==0 && left)	
{
	leftmouth =new ImageIcon("leftmouth.png");
	leftmouth.paintIcon(this,g,xlength[i],ylength[i]);
	}
if(i==0 && up)	
{
	upmouth =new ImageIcon("upmouth.png");
	upmouth.paintIcon(this,g,xlength[i],ylength[i]);
	}
if(i==0 && down)	
{
	downmouth =new ImageIcon("downmouth.png");
	downmouth.paintIcon(this,g,xlength[i],ylength[i]);
	}

if(i!=0)
{
	snakeimage=new ImageIcon("snakeimage.png");
	snakeimage.paintIcon(this,g,xlength[i],ylength[i]);
}
}
 ImageIcon enemyimage = new ImageIcon("enemy.png");
if(enemyxpos[xpos]==xlength[0] && enemyypos[ypos]==ylength[0])
{
lengthofsnake++;
xpos=random.nextInt(34);
ypos=random.nextInt(23);
score++;
}
 enemyimage.paintIcon(this,g, enemyxpos[xpos],enemyypos[ypos]);
 for(int j=1;j<lengthofsnake;++j)
 {
	 if(xlength[j]==xlength[0] && ylength[j]==ylength[0])
		 {
		 right=false;
		 left=false;
		 down=false;
		 up=false;
		 
		 g.setColor(Color.RED);
		 g.setFont(new Font("arial",Font.BOLD,50));
		 g.drawString("Game Over",300,300);
		 

		 g.setColor(Color.RED);
		 g.setFont(new Font("arial",Font.BOLD,20));
		 g.drawString("Space to Restart",350,340);
		 }
 }
 
 g.dispose();
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if(right)
		{
			for(int r=lengthofsnake-1;r>=0;--r)
			{
				ylength[r+1]=ylength[r];
			}
			for(int r=lengthofsnake;r>=0;--r)
			{
				if(r==0)
				{xlength[r]=xlength[r]  +25;}
				else
				{xlength[r]=xlength[r-1];}
				
				if(xlength[r]>850)
				{xlength[r]=25;}
			}
		repaint();
		}
		if(left)
		{
			for(int r=lengthofsnake-1;r>=0;--r)
			{
				ylength[r+1]=ylength[r];
			}
			for(int r=lengthofsnake;r>=0;--r)
			{
				if(r==0)
				{xlength[r]=xlength[r]  -25;}
				else
				{xlength[r]=xlength[r-1];}
				
				if(xlength[r]<25)
				{xlength[r]=850;}
			}
		repaint();	
		}
		if(up)
		{
			for(int r=lengthofsnake-1;r>=0;--r)
			{
				xlength[r+1]=xlength[r];
			}
			for(int r=lengthofsnake;r>=0;--r)
			{
				if(r==0)
				{ylength[r]=ylength[r]  -25;}
				else
				{ylength[r]=ylength[r-1];}
				
				if(ylength[r]<75)
				{ylength[r]=625;}
			}
		repaint();
		}
		if(down)
		{
			for(int r=lengthofsnake-1;r>=0;--r)
			{
				xlength[r+1]=xlength[r];
			}
			for(int r=lengthofsnake;r>=0;--r)
			{
				if(r==0)
				{ylength[r]=ylength[r]  +25;}
				else
				{ylength[r]=ylength[r-1];}
				
				if(ylength[r]>625)
				{ylength[r]=25;}
			}
		repaint();
		}
	}
	@Override
	public void keyPressed(KeyEvent e) {
     if(e.getKeyCode()==KeyEvent.VK_SPACE)
  {
	  score=0;
	  moves=0;
	  lengthofsnake=3;
	  repaint();
  }
		if(e.getKeyCode()==KeyEvent.VK_RIGHT)
    {
    	moves++;
    	right=true;
    	
    	if(!left)
    	{right=true;}
    	else
    	{right=false;
    	left=true;}
    	
    	up=false;    
    	down=false;
    }
    
    if(e.getKeyCode()==KeyEvent.VK_LEFT)
    {
    	moves++;
    	left=true;
    	
    	if(!right)
    	{left=true;}
    	else
    	{left=false;
    	right=true;}
    	
    	up=false;    
    	down=false;
    }
    if(e.getKeyCode()==KeyEvent.VK_UP)
    {
    	moves++;
    	up=true;
    	
    	if(!down)
    	{up=true;}
    	else
    	{up=false;
    	down=true;}
    	
        right=false;    
    	left=false;
    }
    
    if(e.getKeyCode()==KeyEvent.VK_DOWN)
    {
    	moves++;
    	down=true;
    	
    	if(!up)
    	{down=true;}
    	else
    	{down=false;
    	up=true;}
    	
        right=false;    
    	left=false;
    }
    
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	}
