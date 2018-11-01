import java.util.*;

public class DN06_63160369
{
	public static int min(int a, int b)
	{
		return ((a < b) ? a : b);
	}
	
	public static int max(int a, int b)
	{
		return ((a > b) ? a : b);
	}
	
	public static void main(String[] args)
	{
		Scanner cin = new Scanner(System.in);
		
		int res = 0;
		int ilen = cin.nextInt();
		int jlen = cin.nextInt();
		int[] comi = {1, -1, 0, 0};
		int[] comj = {0, 0, 1, -1};
		boolean[][] isVisited = new boolean[200][200];
		
		for (int i = 0; i < ilen; i++)
		{
			for (int j = 0; j < jlen; j++)
			{
				int a = cin.nextInt();
				isVisited[i][j] = ((a == 0) ? true : false);
			}
		}
		
		for (int i = 0; i < ilen; i++)
		{
			for (int j = 0; j < jlen; j++)
			{				
				if (!isVisited[i][j]) /// (isVisited[i][j] == false)
				{
					int imin = i, imax = i, jmin = j, jmax = j;
					
					Queue<Integer> BFS = new LinkedList<Integer>();
					
					BFS.add(i);	BFS.add(j);
					
					while (!BFS.isEmpty())
					{
						int curi = BFS.poll(), curj = BFS.poll();
						imin = min(curi, imin);	imax = max(curi, imax);
						jmin = min(curj, jmin); jmax = max(curj, jmax);
						
						for (int k = 0; k < 4; k++)
						{
							if (((curi + comi[k]) >= 0) && ((curi + comi[k]) < ilen) && ((curj + comj[k]) >= 0) && ((curj + comj[k]) < jlen))
							{
								if (!isVisited[curi+comi[k]][curj+comj[k]]) /// (isVisited[curi+comi[k]][curj+comj[k]] == false)
								{
									isVisited[curi+comi[k]][curj+comj[k]] = true;
									BFS.add(curi+comi[k]);	BFS.add(curj+comj[k]);
								}
							}
						}
					}
					
					res += 2 * ((imax - imin + 1) + (jmax - jmin + 1));
				}
			}
		}
		
		System.out.println(res);
	}
}

						/**
						if (!isVisited[curi][curj]) /// (isVisited[curi][curj] == false)
						{
							isVisited[curi][curj] = true;
							imin = min(curi, imin);	imax = max(curi, imax);
							jmin = min(curj, jmin); jmax = max(curj, jmax);
							
							for (int k = 0; k < 4; k++)
							{
								if (((curi + comi[k]) >= 0) && ((curi + comi[k]) < ilen) && ((curj + comj[k]) >= 0) && ((curj + comj[k]) < jlen))
								{
									BFS.add(curi+comi[k]);	BFS.add(curj+comj[k]);
								}
							}
						}
						**/