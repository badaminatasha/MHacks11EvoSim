/*
No geminates (dont let two of the same consonants be next to each other)
No /h/ in the syllable coda (make syllables that specifically include h)
No glides in codas (y and w aren't allowed at the end of a word?)
if q then following letter MUST be u and then another vowel
*/





public class NameCreator
{
	//private String[] vowelClusters={"a","e","i","o","u","au","augh","ee","ea","ie","igh","oo","oe","oa","oi","ou","ue"};
	//private String[] consonantClusters={"bl","br","ch","cl","cr","dr","fl","fr","gl","gr","pl","pr","sc","sh","sk","sl","sm","sn","sp","st","sw","th","tr","tw","sch","scr","shr","sph","spl","spr","squ","str","thr","b","c","d","f","g","h","j","k","l","m","n","p","qu","r","s","t","v","w","x","y","z"};

	//attemtping to make only cute names
	private String[] vowelClusters={"a","e","i","o","ee","oo"};
	private String[] consonantClusters={"ch","fl","pl","pr","sh","sp","sw","tw","squ","b","f","l","m","p","s","w","y"};
	
	public NameCreator()
	{
		for(int j=0;j<5;j++)
		{
			int qmax=(int)(Math.random()*2)+3;
			int count=(int)(Math.random()*2);
			String syll;
			for(int q=0;q<qmax;q++)
			{
				if(count%2==0)
					syll=vowelClusters[(int)(Math.random()*vowelClusters.length)];
				else
					syll=consonantClusters[(int)(Math.random()*consonantClusters.length)];
				System.out.print(syll);
				count++;
			}
			System.out.println();
		}
	}
	
	public static void main(String args[])
	{
		NameCreator app = new NameCreator(); //im going to have to remove this eventually bc main driver class thing
	}
}