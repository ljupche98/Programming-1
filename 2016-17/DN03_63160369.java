import java.util.*;

public class DN03_63160369
{
	public static int page = 1, row = 1, chars = 0;
	
	public static int abs(int a)
	{
		return ((a >= 0) ? a : -a);
	}
	
	public static void main(String[] args)
	{
		Scanner cin = new Scanner(System.in);
		
		int jlen = cin.nextInt(), ilen = cin.nextInt(), N = cin.nextInt();
		boolean shouldBeFree = false;
		
		for (int i = 0; i < N; i++)
		{
			int cur = cin.nextInt();
			
			if (cur > 0)
			{
				if (shouldBeFree == true)
				{
					shouldBeFree = false;
					row++;
					
					if (row > ilen)
					{
						row = 1;
						page++;
					}
				}
				
				if (chars != 0)
				{
					chars++;
				}
				
				if ((chars + cur) <= jlen)
				{
					chars += cur;
				}
				else
				{
					if (row < ilen)
					{
						row++;
					}
					else
					{
						row = 1;
						page++;
					}
					
					chars = cur;
				}
			}
			else if (cur == 0)
			{
				shouldBeFree = true;
				
				if (chars != 0)
				{
					row++;
				}
				
				chars = 0;
			}
			else
			{
				shouldBeFree = true;
				
				if ((row == 1) && (chars == 0))
				{
					row = abs(cur) + 1;
				}
				else ///if (chars == 0)
				{
					if ((row + abs(cur) + ((chars != 0) ? 1 : 0)) > ilen)
					{
						page++;
						row = abs(cur) + 1; /// + 2;
					}
					else
					{
						row += (abs(cur) + 1 + ((chars != 0) ? 1 : 0)); /// + 1); /// 1 ili 2
					}
				}
				
				/**
				else
				{
					if ((row + abs(cur) + 1) > ilen) /// > (ilen - 1)
					{
						page++;
						row = abs(cur) + 1;
					}
					else
					{
						row += (abs(cur) + 2);
					}
				}
				**/
				
				chars = 0;
			}
			
			///System.out.println(cur + " " + page + " " + row + " " + chars);
		}
		
		System.out.println(page);
		System.out.println(row);
		System.out.println(chars);
	}
}

				/**
				else if (((row + abs(cur) - 1) >= (ilen - 1)) && (chars != 0))
				{
					page++;
					row = 1;
				}
				else if (((row + abs(cur) - 1) < (ilen - 1)) && (chars != 0))
				{
					row += (abs(cur) + 3);
				}
				else if ((row + abs(cur) - 1) > (ilen - 1))
				{
					page++;
					row = 1;
				}
				else
				{
					row += (abs(cur) + 2);
				}

				/**
				if ((row + 1 + abs(cur)) < (ilen - 1))
				{
					row += (abs(cur) + 3 - ((chars == 0) ? 1 : 0) - ((row == 1) ? 1 : 0));
				}
				else if ((row + 1 + abs(cur)) <= ilen)
				{
					row = 1;
					page++;
				}
				else
				{
					if (abs(cur) >= (ilen - 1))
					{
						row = 1;
						page += 2;
					}
					else
					{
						row = abs(cur) + 2;
						page++;
					}
				}
				**/