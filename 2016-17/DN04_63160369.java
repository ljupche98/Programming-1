// 63160369

public class Zp
{
	private int mod;
	
	public Zp(int a)
	{
		mod = a;
	}
	
	public int vrniModul()
	{
		return mod;
	}
	
	public String toString()
	{
		return ("Z_" + mod);
	}
	
	public int vsota(int a, int b)
	{
		return ((a + b) % mod);
	}
	
	public int zmnozek(int a, int b)
	{
		return ((a * b) % mod);
	}
	
	public int nasprotno(int a)
	{
		return ((a == 0) ? 0 : (mod - a));
	}
	
	public int razlika(int a, int b)
	{
		return (vsota(a, nasprotno(b)));
	}
	
	public int obratno(int a)
	{
		for (int i = 1; ; i++)
		{
			if (zmnozek(a, i) == 1)
			{
				return i;
			}
		}
	}
	
	public int kolicnik(int a, int b)
	{
		return zmnozek(a, obratno(b));
	}
	
	public int steviloKvadratnihKorenov(int a)
	{
		int res = 0;
		
		for (int i = 0; i < mod; i++)
		{
			if (zmnozek(i, i) == a)
			{
				res++;
			}
		}
		
		return res;
	}
	
	public int potenca(int a, long b)
	{		
		if (b > 2)
		{
			int num = potenca(a, (b / 2));
			
			if ((b % 2) == 0)
			{
				return zmnozek(num, num); ///(potenca(a, (b / 2)), potenca(a, (b / 2)));
			}
			
			return zmnozek(num, zmnozek(a, num)); ///(potenca(a, (b / 2)), potenca(a, ((b + 1) / 2)));
		}
		
		if (b == 0)
		{
			return 1;
		}
		
		if (b > 0)
		{
			return zmnozek(a, potenca(a, (b - 1)));
		}
		
		return obratno(potenca(a, -b));
	}
	
	public boolean jeMultiplikativniGenerator(int a)
	{
		boolean[] isVisited = new boolean[101];
		
		for (int i = 0; i < mod; i++)
		{
			isVisited[potenca(a, i)] = true;
		}
		
		for (int i = 1; i < mod; i++)
		{
			if (isVisited[i] == false)
			{
				return false;
			}
		}
		
		return true;
	}
}