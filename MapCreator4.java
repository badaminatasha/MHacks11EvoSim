//NEWEST ONE WITH FANCY STUFF IDK

/*This is actually working pretty well so far; I changed +1 and -1 in the switch up and down to 2
because that allows for greater flexibility especially when the randomness is very low
what I still have to work on is trying to make the newTile method recursive if I can
aaand possibly a less sketchy way of determining biome continuity (+2 is annoying me rn)
also have to find a way to eliminate repeats of biomes because thats resulting in rainforest and tundra never getting picked
try changing method to "cellular automata" like saket said?
that means seeding (producing 10 random tiles or so in random locations to start)
and then letting them grow out as I have them here
a good idea that sakko had was to look at all the null spots after seeding and say
give them a 1/some # chance of the surrounded tile to spread into the null space
that would mean I dont hae to deal with any of this +1 +2 stuff I just need to randomly generate those 10 seeds
and then BAM the whole map is determined based off of that
saket told me to make sure that the spreading into the null spot is based on the previous
state of the thing that's spreading, because say desert leaks into a null spot and the null spot
right next to the now desert null spot sees the desert spot and says hey im gonna  become a desert spot
you dont want that to happen because then everything will die, it has to be based off the previous state
*/

import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.lang.Math;

public class MapCreator4 extends JPanel
{
	private final int RENDER_SIZE=10;
	private final int MAXX=40;
	private final int MAXY=20;
	private final int RANDOMNESS_FACTOR=15;
	private int tilesFilled=0;
	private JFrame frame;
	private MapTile4 mapTiles[][] = new MapTile4[MAXY][MAXX];

	private TemperatureTile[] temperatureTiles=new TemperatureTile[101];

	public MapCreator4()
	{
		frame=new JFrame();
		frame.add(this);
		frame.setSize((MAXX+1)*RENDER_SIZE,(MAXY+2)*RENDER_SIZE);//+1 & +2 are me trying to account for the fact that it's usually a little too small
		frame.setVisible(true);
		run();
	}

	public void run()
	{
			for(int i=0;i<101;i++)
			{
				temperatureTiles[i]=new TemperatureTile(i);
			}

			newTile((int)(Math.random()*101),(int)(Math.random()*101),(int)(Math.random()*101),0,0);
			System.out.println("Done");
			/*for(int j=0;j<MAXY;j++)//y
				for(int b=0;b<MAXX;b++)//x
				{
					if(j+1<MAXY&&j-1>-1&&b+1<MAXX&&b-1>-1&&mapTiles[j+1][b].getType()==mapTiles[j][b+1].getType()&&mapTiles[j][b+1].getType()==mapTiles[j][b-1].getType()&&mapTiles[j][b-1].getType()==mapTiles[j-1][b].getType())
						mapTiles[j][b]=new MapTile4(mapTiles[j][b+1].getType());

				}
			System.out.println(tilesFilled);*/
			//repaint();
	}

	public void newTile(int t1,int t2,int t3,int y, int x)
	{
		t1 += (int)(RANDOMNESS_FACTOR*(Math.random()-0.5));
		t2 += (int)(RANDOMNESS_FACTOR*(Math.random()-0.5));
		t3 += (int)(RANDOMNESS_FACTOR*(Math.random()-0.5));
		if(t1 < 0) t1=0;
		if(t2 < 0) t2=0;
		if(t3 < 0) t3=0;
		if(t1>100) t1=100;
		if(t2>100) t2=100;
		if(t3>100) t3=100;

		mapTiles[y][x]=new MapTile4(t1,t2,t3);
		tilesFilled++;
		int tempy=y;
		int tempx=x;

		ArrayList<Integer> tempVals=new ArrayList<Integer>();
		for(int f=0;f<4;f++)
			tempVals.add(f);
		int[] vals=new int[tempVals.size()];
		for(int f=0;f<vals.length;f++)
			vals[f]=tempVals.remove((int)(Math.random()*tempVals.size())); //creates an array with a random order of tiles; the number 8 is used because there are always 8 surrounding tiles around a tile unless it's an edge tile, although I can change it to 4 to avoid random unflowy tiles

		for(int v=0;v<4;v++)
		{
			switch(vals[v])
			{
				case 0: tempy=y-1; break;
				case 1: tempx=x+1; break;
				case 2: tempy=y+1; break;
				case 3: tempx=x-1; break;
			}

			if(tempy<MAXY&&tempy>-1&&tempx<MAXX&&tempx>-1&&mapTiles[tempy][tempx]==null)
				newTile(t1,t2,t3,tempy,tempx);

			tempy = y;
			tempx = x;
		}
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d=(Graphics2D)g;
		for(int s=mapTiles.length-1;s>-1;s--)
			for(int r=mapTiles[0].length-1;r>-1;r--)
				if(mapTiles[s][r]!=null)
				{
					MapTile4 mt=mapTiles[s][r];
					g2d.setColor(mt.getColor());
					g2d.fillRect(r*RENDER_SIZE,s*RENDER_SIZE,RENDER_SIZE,RENDER_SIZE);

					g2d.setColor(mt.get_temp_color());
					g2d.fillRect(RENDER_SIZE*MAXX+r*RENDER_SIZE, s*RENDER_SIZE, RENDER_SIZE,RENDER_SIZE);

					g2d.setColor(mt.get_elev_color());
					g2d.fillRect(2*RENDER_SIZE*MAXX+r*RENDER_SIZE, s*RENDER_SIZE, RENDER_SIZE,RENDER_SIZE);

					g2d.setColor(mt.get_moist_color());
					g2d.fillRect(3*RENDER_SIZE*MAXX+r*RENDER_SIZE, s*RENDER_SIZE, RENDER_SIZE,RENDER_SIZE);
				}
/*		for(int s=0;s<temperatureTiles.length;s++)
		{
			TemperatureTile t=temperatureTiles[s];
			g2d.setColor(t.getColor());
			g2d.fillRect(s*RENDER_SIZE,100,RENDER_SIZE,RENDER_SIZE);
		}*/
	}

	public static void main(String args[])
	{
		MapCreator4 app = new MapCreator4();
	}
}