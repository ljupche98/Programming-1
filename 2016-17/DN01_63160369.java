import java.util.*;

public class DN01_63160369
{
	public static void main(String[] args)
	{
		Scanner cin = new Scanner(System.in);
		
		int res = 0, type = cin.nextInt();
		
		if (type == 1)
		{
			int a = cin.nextInt();
			res = a * a * a;
		}
		else if (type == 2)
		{
			int a = cin.nextInt(), b = cin.nextInt(), c = cin.nextInt();
			res = a * b * c;
		}
		else if (type == 3)
		{
			int a = cin.nextInt();
			res = ((a * (a + 1) * ((2 * a) + 1)) / 6);
			
			/**
			for (int i = 1; i <= a; i++)
			{
				res += (i * i);
			}
			**/
		}
		else if (type == 4)
		{
			int a = cin.nextInt(), b = cin.nextInt();
			res = ((b * a * (a + 1)) / 2);
			
			/**
			for (int i = 1; i <= a; i++)
			{
				res += (i * b);
			}
			**/
		}
		else if (type == 5)
		{
			int a = cin.nextInt();
			res = ((a > 2) ? (((a * (a + 1) * ((2 * a) + 1)) / 6) - (((a - 2) * (a - 1) * ((2 * a) - 3)) / 6)) : ((a * (a + 1) * ((2 * a) + 1)) / 6));
			
			/**
			res = 1;
			
			for (int i = 2; i <= a; i++)
			{
				res += ((i * i) - ((i - 2) * (i - 2)));
			}
			**/
		}
		else if (type == 6)
		{
			int a = cin.nextInt(), b = cin.nextInt();
			res = (((a > 2) && (b > 2)) ? (((b * a * (a + 1)) / 2) - (((b - 2) * (a - 2) * (a - 1)) / 2)) : ((b * a * (a + 1)) / 2));
			
			/**
			if (b <= 2)
			{
				for (int i = 1; i <= a; i++)
				{
					res += (i * b);
				}
			}
			else
			{
				res = b;
			
				for (int i = 2; i <= a; i++)
				{
					res += ((i * b) - ((i - 2) * (b - 2)));
				}
			}
			**/
		}
		
		System.out.println(res);
	}
}