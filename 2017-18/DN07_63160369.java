import java.util.*;

public class DN07_63160369
{
	public static int N, res;
	public static int[][] DP;
	public static Scanner cin = new Scanner(System.in);
	
	public static void main(String[] args)
	{
		N = cin.nextInt(); DP = new int[N+1][N+1];
		
		for (int i = 1; i <= N; i++)
			for (int j = 1; j <= N; DP[i][j] = (cin.nextInt() == 1 ? Math.min(DP[i-1][j-1], Math.min(DP[i-1][j], DP[i][j-1])) + 1 : 0), res += DP[i][j++]);
	
		System.out.println(res);
	}
}