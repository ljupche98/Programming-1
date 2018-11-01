import java.util.*;
import java.math.*;

public class DN02_63160369
{
	public static int[] pos = new int[1001];
	public static int[] cachei = new int[1001];
	public static int[] cachej = new int[1001];
	public static int[] cachePos = new int[1001];
	public static int[] cacheRow = new int[1001];
	public static int[] cacheSRow = new int[1001];
	
	public static int abs(int a)
	{
		return ((a >= 0) ? a : -a);
	}
	
	public static int findRow(int a, int b, int c)
	{
		if (cacheRow[c] != 0)
		{
			return cacheRow[c];
		}
		
		if (a < (b * b))
		{
			return 1;
		}
		
		return cacheRow[c] = findRow(a, (b + 1), c) + 1;
	}
	
	public static int findPos(int a, int b, int c)
	{
		if (cachePos[c] != 0)
		{
			return cachePos[c];
		}
		
		return cachePos[c] = (a + b + 1 - findRow(a, 1, c) - (findRow(a, 1, c) - 1) * (findRow(a, 1, c) - 1));
	}
	
	public static int findSRow(int a, int b, int c)
	{
		if (cacheSRow[c] != 0)
		{
			return cacheSRow[c];
		}
		
		if (a < (b * b))
		{
			return 1;
		}
		
		return cacheSRow[c] = findSRow(a, (b + 2), c) + 1;
	}
	
	public static int findPhase(int a, int K, char c, int b)
	{
		if ((cachei[b] != 0) && (cachej[b] != 0))
		{
			return ((c == 'i') ? cachei[b] : cachej[b]);
		}
		
		if (a == 0)
		{
			return (K / 2) + 1;
		}
		
		int resi = 0, resj = 0, dif = findSRow(a, 1, b) - 1;
		dif *= 2;
		dif--;
		int length = (2 * findSRow(a, 1, b)) - 1, curLen = a - (dif * dif), phase = curLen / (length - 1), rem = curLen % (length - 1);

		if (phase == 0)
		{
			resi = (K / 2) - (findSRow(a, 1, b) - 1);
			resj = (K / 2) - (findSRow(a, 1, b) - 1) + rem;
		}
		else if (phase == 1)
		{
			resi = (K / 2) - (findSRow(a, 1, b) - 1) + rem;
			resj = (K / 2) + (findSRow(a, 1, b) - 1);
		}
		else if (phase == 2)
		{
			resi = (K / 2) + (findSRow(a, 1, b) - 1);
			resj = (K / 2) + (findSRow(a, 1, b) - 1) - rem;
		}
		else if (phase == 3)
		{
			resi = (K / 2) + (findSRow(a, 1, b) - 1) - rem;
			resj = (K / 2) - (findSRow(a, 1, b) - 1);
		}
		
		cachei[b] = (resi + 1);
		cachej[b] = (resj + 1);
		
		return ((c == 'i') ? cachei[b] : cachej[b]);
	}
	
	public static void main(String[] args)
	{
		Scanner cin = new Scanner(System.in);
		
		int res = 0, type = cin.nextInt(), K = cin.nextInt(), N = cin.nextInt();
		
		for (int i = 0; i < N; i++)
		{
			pos[i] = cin.nextInt();
		}
		
		if (type == 1)
		{
			for (int i = 0; i < (N - 1); i++)
			{
				res += abs(pos[i] - pos[i+1]);
			}
		}
		else if (type == 2)
		{
			for (int i = 0; i < (N - 1); i++)
			{
				res += (abs((pos[i] / K) - (pos[i+1] / K)) + abs((pos[i] % K) - (pos[i+1] % K)));
			}
		}
		else if (type == 3)
		{
			for (int i = 0; i < (N - 1); i++)
			{
				res += (abs(findRow(pos[i], 1, i) - findRow(pos[i+1], 1, (i + 1))) + (abs(findPos(pos[i], K, i) - findPos(pos[i+1], K, (i + 1)))));
			}
		}
		else if (type == 4)
		{			
			for (int i = 0; i < (N - 1); i++)
			{
				res += (abs(findPhase(pos[i], K, 'i', i) - findPhase(pos[i+1], K, 'i', (i + 1))) + abs(findPhase(pos[i], K, 'j', i) - findPhase(pos[i+1], K, 'j', (i + 1))));
			}
		}
		
		System.out.println(res);
	}
}

/**
///CASE 4:
public static int[] cachei = new int[100000000]; /// cachei[A] = i coordinate for A			10^4 * 10^4
public static int[] cachej = new int[100000000]; /// cachej[A] = j coordinate for A			10^4 * 10^4

///CASE 3:
public static int[] cacheRow = new int[10000000]; /// cacheRow[A] = i coordinate for A
public static int[] cachePos = new int[10000000]; /// cachePos[A] = j coordinate for A

///Finds i coordinate of a
public static int findRow(int a, int dif, int lb, int ub)
{
	if (cacheRow[a] != 0)
	{
		return cacheRow[a];
	}
	
	if ((a >= lb) && (a <= ub))
	{
		return 1;
	}
	
	return cacheRow[a] = findRow(a, (dif + 2), (ub + 1), (ub + dif + 2)) + 1;
}

///Finds j coordinate of a
public static int findPos(int a, int b)
{
	if (cachePos[a] != 0)
	{
		return cachePos[a];
	}
	
	int res = 0;
	
	for (int i = ((findRow(a, 1, 0, 0) - 1) * (findRow(a, 1, 0, 0) - 1)); i < (findRow(a, 1, 0, 0) * findRow(a, 1, 0, 0)); i++)
	{
		if (i == a)
		{
			res = (b - findRow(a, 1, 0, 0) + i - ((findRow(a, 1, 0, 0) - 1) * (findRow(a, 1, 0, 0) - 1)) + 1);
			break;
		}
	}
	
	return cachePos[a] = res;
}

/// PHASE 0: + j
/// PHASE 1: + j + i
/// PHASE 2: + j + i - j
/// PHASE 3: + j + i - j - i
	

/// Lenght of i-th row: 2 * findSRow(i, 1) - 1
/// Start of i-th row: findSRow(i, 1) * findSRow(i, 1)

cachei[0] = cachej[0] = (K / 2);

int cur = 1;
int[] comi = {0, 1, 0, -1};
int[] comj = {1, 0, -1, 0};

for (int i = ((K / 2) - 1); i >= 0; i--)
{
	int phase = 0, curPaseed = 1, curi = i, curj = i, start = (cur * cur);
	{
		cur += 2;
		
		while (start != ((cur * cur)))
		{
			while ((curPaseed < cur) && (start != ((cur * cur))))
			{
				cachei[start] = curi;
				cachej[start] = curj;
				curi += comi[phase];
				curj += comj[phase];
				start++;
				curPaseed++;
			}
			
			phase++;
			curPaseed = 1;
		}
	}
	
	for (int i = 0; i < (N - 1); i++)
	{
		res += (abs(cachei[pos[i]] - cachei[pos[i+1]]) + abs(cachej[pos[i]] - cachej[pos[i+1]]));
	}
}
**/