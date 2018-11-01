import java.util.*;

public class DN05_63160369
{
	public static void main(String[] args)
	{
		Scanner cin = new Scanner(System.in);
		
		int L = cin.nextInt(), K = cin.nextInt(), N = cin.nextInt(), sum = 0;
		String num = cin.next();
		int[] res = new int[1<<21];
		
		for (int i = 0; i < K; i++)
		{
			if (num.charAt(i) == '1')
			{
				sum += (1 << (K - i - 1)); /// Convert the first K digits to binary
			}
		}
		
		res[sum]++;

		for (int i = K; i < L; i++)
		{
			if (sum >= (1 << (K - 1)))
			{
				sum -= (1 << (K - 1)); /// Remove the 1st digit in binary if it's 1
			}
			
			sum *= 2; /// Shift all 1s by 1 left
			
			if (num.charAt(i) == '1')
			{
				sum++; /// sum += (1 << 0); Add 1 to the sum if the last digit is 1
			}
			
			res[sum]++;
		}
		
		for (int i = 0; i < N; i++)
		{
			String cur = cin.next();
			int curSum = 0;
			
			for (int j = 0; j < K; j++)
			{
				if (cur.charAt(j) == '1')
				{
					curSum += (1 << (K - j - 1)); /// Convert the number to binary
				}
			}
			
			System.out.println(res[curSum]);
		}
	}
}