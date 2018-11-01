import java.util.*;

public class DN10_63160369
{
	public static Scanner cin = new Scanner(System.in);
	public static long rooms = 0;
	public static Ulomek perimeter = Ulomek.ustvari(0, 1), smallest = Ulomek.ustvari(999999999, 1);
	
	public static void solve(Ulomek ilen, Ulomek jlen)
	{
		int type = cin.nextInt();
		
		if (type == 0)
		{
			rooms++;
			
			if (ilen.zmnozek(jlen).jeManjsiOd(smallest))
			{
				smallest = ilen.zmnozek(jlen);
			}
		}
		else if (type == -1)
		{
			long iParts = cin.nextLong(), jParts = cin.nextLong();
			rooms += (iParts * jParts);
			perimeter = perimeter.vsota(ilen.zmnozek(jParts - 1).vsota(jlen.zmnozek(iParts - 1)));
			
			if (ilen.kolicnik(iParts).zmnozek(jlen.kolicnik(jParts)).jeManjsiOd(smallest))
			{
				smallest = ilen.kolicnik(iParts).zmnozek(jlen.kolicnik(jParts));
			}
		}
		else if (type == -2)
		{
			long iParts = cin.nextLong(), jParts = cin.nextLong();
			
			rooms += (iParts * jParts);
			perimeter = perimeter.vsota(ilen.zmnozek(jParts - 1).vsota(jlen.zmnozek(iParts - 1)));
			Ulomek mini = Ulomek.ustvari(999999999, 1), minj = Ulomek.ustvari(999999999, 1);
			
			long iTotalParts = 0, jTotalParts = 0;
			
			for (int i = 0; i < iParts; i++)
			{
				long a = cin.nextLong();
				iTotalParts += a;				
				
				if (Ulomek.ustvari(a, 1).jeManjsiOd(mini))
				{
					mini = Ulomek.ustvari(a, 1);
				}
			}
			
			for (int j = 0; j < jParts; j++)
			{
				long a = cin.nextLong();
				jTotalParts += a;
				
				if (Ulomek.ustvari(a, 1).jeManjsiOd(minj))
				{
					minj = Ulomek.ustvari(a, 1);
				}
			}
			
			if (ilen.kolicnik(iTotalParts).zmnozek(mini).zmnozek(jlen.kolicnik(jTotalParts).zmnozek(minj)).jeManjsiOd(smallest))
			{
				smallest = ilen.kolicnik(iTotalParts).zmnozek(mini).zmnozek(jlen.kolicnik(jTotalParts).zmnozek(minj));
			}
		}
		else if (type == -3)
		{
			long iParts = cin.nextLong(), jParts = cin.nextLong();
			long iTotalParts = 0, jTotalParts = 0;
			long[] iRatio = new long[100000];
			long[] jRatio = new long[100000];
			
			for (int i = 0; i < iParts; i++)
			{
				iRatio[i] = cin.nextLong();
				iTotalParts += iRatio[i];
			}
			
			for (int j = 0; j < jParts; j++)
			{
				jRatio[j] = cin.nextLong();
				jTotalParts += jRatio[j];
			}
			
			for (int i = 0; i < iParts; i++)
			{
				for (int j = 0; j < jParts; j++)
				{
					solve(ilen.kolicnik(iTotalParts).zmnozek(iRatio[i]), jlen.kolicnik(jTotalParts).zmnozek(jRatio[j]));
				}
			}
			
			perimeter = perimeter.vsota(ilen.zmnozek(jParts - 1).vsota(jlen.zmnozek(iParts - 1)));
		}
	}
	
	public static void main(String[] args)
	{
		long i = cin.nextLong(), j = cin.nextLong();
		solve(Ulomek.ustvari(i, 1), Ulomek.ustvari(j, 1));
		System.out.println(rooms);
		System.out.println(perimeter.vsota(i).vsota(i).vsota(j).vsota(j).toString());
		System.out.println(smallest.toString());
	}
}