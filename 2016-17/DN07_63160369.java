import java.util.*;

public class DN07_63160369
{
	public static void main(String[] args)
	{
		Scanner cin = new Scanner(System.in);
		
		int N = cin.nextInt(), K = cin.nextInt(), res = 0;
		int[] SP = new int[500];
		int[][] distance = new int[500][500];
		int[][] neighbours = new int[500][500];
		
		for (int i = 0; i < N; i++)
		{
			int counter = 0;
			
			for (int j = 0; j < N; j++)
			{
				distance[i][j] = cin.nextInt();
				
				if (distance[i][j] != 0)
				{
					neighbours[i][counter++] = j;
				}
			}
			
			neighbours[i][counter] = -1;
			
			if (i != 0)
			{
				SP[i] = 999999;
			}
		}
		
		Queue<Integer> BFS = new LinkedList<Integer>();
		BFS.add(0);
		
		while (!BFS.isEmpty())
		{
			int cur = BFS.poll();
			
			for (int i = 0; neighbours[cur][i] != -1; i++)
			{
				if ((SP[cur] + distance[cur][neighbours[cur][i]]) < SP[neighbours[cur][i]])
				{
					SP[neighbours[cur][i]] = SP[cur] + distance[cur][neighbours[cur][i]];
					BFS.add(neighbours[cur][i]);
				}
			}
		}
		
		for (int i = 0; i < N; i++)
		{
			res += ((SP[i] <= K) ? 1 : 0);
			
			/**
			if (SP[i] <= K)
			{
				res++;
			}
			**/
		}
		
		System.out.println(res);
	}
}



/**
public class DN07_63160369
{
	public static void main(String[] args)
	{
		Scanner cin = new Scanner(System.in);
		
		int N = cin.nextInt(), K = cin.nextInt(), res = 0;
		Node[] nodes = new Node[500];
		
		for (int i = 0; i < N; i++)
		{
			for (int j = 0; j < N; j++)
			{
				int a = cin.nextInt();
				
				if (a != 0)
				{
					nodes[i].addNeighbour(j, a);
				}
			}
		}
		
		nodes[0].setSP(0);
		
		Queue<Integer> BFS = new LinkedList<Integer>();
		BFS.add(0);
		
		while (!BFS.isEmpty())
		{
			int cur = BFS.poll();
			Iterator it = nodes[cur].getNeighbours().iterator();
			
			while (it.hasNext())
			{
				Deque<Pair> curNeighbours = it.next();
				
				if ((nodes[cur].getSP() + curNeighbours.getSecond()) < nodes[curNeighbours.getFirst()].getSP())
				{
					nodes[curNeighbours.getFirst()].setSP(nodes[cur].getSP() + curNeighbours.getSecond());
					BFS.add(curNeighbours.getFirst());
				}
				
			}
			
			/**
			for (int i = 0; i < nodes[cur].getNeighbours().size(); i++)
			{
				if ((nodes[cur].getSP() + nodes[cur].getNeighbours()[i].getSecond()) < nodes[nodes[cur].getNeighbours()[i].getFirst()].getSP())
				{
					nodes[nodes[cur].getNeighbours()[i].getFirst()].setSP(nodes[cur].getSP() + nodes[cur].getNeighbours()[i].getSecond());
					BFS.push(nodes[cur].getNeighbours()[i].getFirst());
				}
			}
			
		}
		
		for (int i = 0; i < N; i++)
		{
			if (nodes[i].getSP() <= K)
			{
				res++;
			}
		}
		
		System.out.println(res);
	}
}

class Pair
{
	private int first;
	private int second;
	
	public Pair(int f, int s)
	{
		first = f;
		second = s;
	}
	
	public int getFirst()
	{
		return first;
	}
	
	public int getSecond()
	{
		return second;
	}
}

class Node
{
	private int SP = 999999;
	private Deque<Pair> neighbours;
	
	public Node()
	{
		neighbours = new LinkedList<Pair>();
	}
	
	public int getSP()
	{
		return SP;
	}
	
	public Deque<Pair> getNeighbours()
	{
		return neighbours;
	}
	
	public void setSP(int a)
	{
		SP = a;
	}
	
	public void addNeighbour(int a, int b)
	{
		neighbours.add(new Pair(a, b));
	}
}
**/