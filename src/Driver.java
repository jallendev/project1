/**
 * @author Mohammad Mukhtaruzzaman
 * @version 2019-08-29
*/
import java.io.IOException;

public class Driver 
{
	public static void main(String[] args) throws IOException 
	{
		HammingDist hammDist1 = new HammingDist("PORT", "SALL");
		HammingDist hammDist2 = new HammingDist("BOIS", "WEBR");
		System.out.println(hammDist1);
		System.out.print("\n");
		System.out.println(hammDist2);
	}
}