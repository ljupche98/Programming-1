import java.util.*;

class Wish
{
	private int i;
	private int j;
	private int ilen;
	private int jlen;
	
	public Wish(int a, int b, int c, int d)
	{
		i = a;
		j = b;
		ilen = c;
		jlen = d;
	}
	
	public int getI()
	{
		return i;
	}
	
	public int getJ()
	{
		return j;
	}
	
	public int getIlen()
	{
		return ilen;
	}
	
	public int getJlen()
	{
		return jlen;
	}
}

class Citizen
{
	private int idx;
	private long age;
	private long priority;
	private boolean isHonorary;
	private Wish[] wishes;
	
	
	public Citizen(int a, long b, long c, int d, boolean e)
	{
		idx = a;
		age = b;
		priority = c;
		wishes = new Wish[d];
		isHonorary = e;
	}
	
	public void setWish(Wish a, int b)
	{
		wishes[b] = a;
	}
	
	public int getIdx()
	{
		return idx;
	}
	
	public long getPriority()
	{
		return priority;
	}
	
	public boolean isHonorary()
	{
		return isHonorary;
	}
	
	public Wish[] getWishes()
	{
		return wishes;
	}
}

public class DN09_63160369
{
	public static long abs(long a)
	{
		return ((a >= 0) ? a : -a);
	}
	
	public static long max(long a, long b)
	{
		return ((a >= b) ? a : b);
	}
	
	public static long calculatePriority(int type, long rel, long age, long minAge, long maxAge, long dif)
	{
		/// Group:        (age - minAge + 1) / dif + ((age - minAge + 1) % dif != 0)
		/// Total groups: (maxAge - minAge + 1) / dif + ((maxAge - minAge + 1) % dif != 0)
		/// return (type == 2 || type == 3 ? max(group - total, -rel) : group - total);
		return ((type == 2 || type == 3) ? max(((age - minAge + 1) / dif) + ((age - minAge + 1) % dif != 0 ? 1 : 0) - (((maxAge - minAge + 1) / dif) + ((((maxAge - minAge + 1) % dif) != 0) ? 1 : 0)), -rel) : ((age - minAge + 1) / dif) + ((age - minAge + 1) % dif != 0 ? 1 : 0) - (((maxAge - minAge + 1) / dif) + ((((maxAge - minAge + 1) % dif) != 0) ? 1 : 0)));
	}
	
	public static void main(String[] args)
	{
		Scanner cin = new Scanner(System.in);
		
		int N = cin.nextInt();
		long K = cin.nextLong(), D = cin.nextLong();
		long minAge = cin.nextLong(), maxAge = cin.nextLong(), dif = cin.nextLong();
		int C = cin.nextInt();
		Citizen[] citizens = new Citizen[100];
		
		for (int i = 0; i < C; i++)
		{
			int type = cin.nextInt();
			long age = cin.nextInt(), rel = 0;
			
			if (type == 2)
			{
				rel = cin.nextInt();
			}
			
			int wish = cin.nextInt();
			
			citizens[i] = new Citizen(i, age, calculatePriority(type, rel, age, minAge, maxAge, dif), wish, (type == 4));
			
			for (int j = 0; j < wish; j++)
			{
				int ii = cin.nextInt(), jj = cin.nextInt(), ilen = cin.nextInt(), jlen = cin.nextInt();
				Wish cur = new Wish(ii, jj, ilen, jlen);
				citizens[i].setWish(cur, j);
			}
		}
		
		for (int i = 0; i < C - 1; i++)
		{
			for (int j = i + 1; j < C; j++)
			{
				if ((citizens[i].getPriority() < citizens[j].getPriority()) || (citizens[i].getPriority() == citizens[j].getPriority() && citizens[i].getIdx() > citizens[j].getIdx()))
				{
					Citizen temp = citizens[i];
					citizens[i] = citizens[j];
					citizens[j] = temp;
				}
			}
		}
		
		boolean[][] isVisited = new boolean[100][100];
		
		for (int i = 0; i < C; i++)
		{
			int wish = -1;
			long totalPrice = 0;
			
			for (int j = 0; j < citizens[i].getWishes().length; j++)
			{
				boolean checkField = true;
				
				for (int m = citizens[i].getWishes()[j].getI(); m < citizens[i].getWishes()[j].getI() + citizens[i].getWishes()[j].getIlen() && checkField; m++)
				{
					for (int n = citizens[i].getWishes()[j].getJ(); n < citizens[i].getWishes()[j].getJ() + citizens[i].getWishes()[j].getJlen(); n++)
					{
						if (isVisited[m][n])
						{
							checkField = false;
							break;
						}
					}
				}
				
				if (!checkField)
				{
					continue;
				}
				
				wish = j;
				
				for (int m = citizens[i].getWishes()[j].getI(); m < citizens[i].getWishes()[j].getI() + citizens[i].getWishes()[j].getIlen() && checkField && m < N; m++)
				{
					for (int n = citizens[i].getWishes()[j].getJ(); n < citizens[i].getWishes()[j].getJ() + citizens[i].getWishes()[j].getJlen() && n < N; n++)
					{
						isVisited[m][n] = true;
						totalPrice += max(K - D * (abs(m - (N / 2)) + abs(n - (N / 2))), 0);
					}
				}
				
				break;
			}
			
			System.out.println(citizens[i].getIdx() + " " + citizens[i].getPriority() + " " + wish + " " + (citizens[i].isHonorary() ? (totalPrice + 1) / 2 : totalPrice));
		}
	}
}

		/**
		/// RANGE SUM QUERY
		int[][] price = new int[100][100];
		price[0][0] = max(K - D * 2 * (N / 2), 0);
		/// price[0][1] = price[1][0] = max(K - D * (2 * (N / 2) - 1), 0);
		
		for (int i = 1; i < N; i++)
		{
			price[i][0] = price[i-1][0] + max(K - D * (abs((N / 2) - i) + (N / 2)), 0);
		}
		
		for (int i = 0; i < N; i++)
		{
			for (int j = 1; j < N; j++)
			{
				price[i][j] = price[i][j-1] + max(K - D * (abs((N / 2) - i) + abs((N / 2) - j)), 0);
			}
		}
		
		
		/// totalPrice += (price[m][citizens[i].getWishes()[j].getJ()+citizens[i].getWishes()[j].getJlen()] - ((citizens[i].getWishes()[j].getJ() > 0) ? price[m][citizens[i].getWishes()[j].getJ()-1] : 0));
		**/