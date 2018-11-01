import java.util.*;

public class DN03_63160369
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		long p = sc.nextLong(), k = sc.nextLong();
		
		long cnt = 1, r = 0;
		
		while (k > 0)
		{
			long cr = 1, ts = p;
			
			while (cr * p + 1 <= k)
			{
				cr = cr * p + 1;
				ts *= p;
			}
			
			r += ts;
			k -= cr;
		}
		
		System.out.println(r);
	}
}
