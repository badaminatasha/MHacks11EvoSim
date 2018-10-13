//For MapCreator4

import java.awt.Color;

public class MapTile4
{
	private final int SEA_LEVEL_CUTOFF = 40;
	private Color color;
	private int r1,r2,r3,b1,b2,b3,g1,g2,g3,r,g,b;

	public MapTile4(int t1, int t2, int t3)
	{

		// ELEVATION
		r1=b1=g1=(int)(2.55*t1);




		// MOISTURE
		if(t2<=40){
			r2=0;
			g2=0;
			b2=255;
		}
		else{
			r2=(int)(255-1.4*t2);
			g2=(int)(240-1.55*t2);
			b2=(int)(130+1.25*t2);
		}




		// TEMPERATURE
		r3=(int)(Math.abs(255-5.1*t3)); //makes sure red is max for coldest tiles (white), minimum for green tiles, and max for hottest tiles (orange-brown)
		if(t3<=50)
		{
			g3=255; //green is max for coldest tiles (white) and green tiles
			b3=(int)(255-5.1*t3); //makes sure blue is max for coldest tiles (white) and sharply drops for green tiles to zero for hottest tiles (orange-brown)
		}
		else
		{
			g3=(int)(340-1.7*t3); //green steadily drops to 170 @ 50 degrees bc that's what gets u the orange-brown color
			b3=0;
		}

		r = (r1+r3)/2;
		g = (g1+g3)/2;
		b = (b1+b3)/2;

		color = new Color(r,g,b);
	}

	public Color get_elev_color(){
		return new Color(r1,g1,b1);
	}

	public Color get_moist_color(){
		return new Color(r2,g2,b2);
	}

	public Color get_temp_color(){
		return new Color(r3,g3,b3);
	}

	public Color getColor()
	{
		return color;
	}
}