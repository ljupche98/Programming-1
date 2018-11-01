// 63160369

class Block
{
	private int ID;
	private int day;
	private int start;
	private int duration;
	private boolean inUse;
	
	public Block(int a, int b, int c, int d, boolean e)
	{
		ID = a;
		day = b;
		start = c;
		duration = d;
		inUse = e;
	}
	
	public int getID()
	{
		return ID;
	}
	
	public int getDay()
	{
		return day;
	}
	
	public int getStart()
	{
		return start;
	}
	
	public int getDuration()
	{
		return duration;
	}
	
	public boolean isInUse()
	{
		return inUse;
	}
	
	public void setDay(int a)
	{
		day = a;
	}
	
	public void setStart(int a)
	{
		start = a;
	}
	
	public void changeStatus(boolean a)
	{
		inUse = a;
	}
}

public class Urnik
{
	private int counter;
	private int[] hours;
	private Block[] blocks;
	private int[][] timetable;
	
	public Urnik()
	{
		counter = 0;
		hours = new int[100];
		blocks = new Block[100000];
		timetable = new int[6][24];
	}
	
	public int max(int a, int b)
	{
		return ((a >= b) ? a : b);
	}
	
	public boolean dodajBlok(int ID, int day, int start, int duration)
	{
		for (int i = start; i < start + duration; i++)
		{
			if (timetable[day][i] != 0)
			{
				blocks[counter++] = new Block(ID, day, start, duration, false);
				return false;
			}
		}
		
		for (int i = start; i < start + duration; i++)
		{
			timetable[day][i] = ID;
		}
		
		blocks[counter++] = new Block(ID, day, start, duration, true);
		
		hours[ID] += duration;
		
		return true;
	}
	
	public int kaj(int day, int hour)
	{
		return timetable[day][hour];
	}
	
	public void odstraniPredmet(int ID)
	{
		hours[ID] = 0;
		
		for (int i = 1; i <= 5; i++)
		{
			for (int j = 7; j < 24; j++)
			{
				timetable[i][j] = ((timetable[i][j] == ID) ? 0 : timetable[i][j]);
			}
		}
		
		for (int i = 0; i < counter; i++)
		{
			if (blocks[i].getID() == ID)
			{
				blocks[i].changeStatus(false);
			}
		}
	}
	
	public int steviloUr(int ID)
	{
		return hours[ID];
	}
	
	public int najPredmet()
	{
		int maxHours = 0;
		
		for (int i = 1; i < 100; i++)
		{
			maxHours = max(hours[i], maxHours);
		}
		
		if (maxHours != 0)
		{
			for (int i = 1; i < 100; i++)
			{
				if (hours[i] == maxHours)
				{
					return i;
				}
			}
		}
		
		return 0;
	}
	
	public int vrzeli(int day)
	{
		int begin = -1, end = -1, res = 0;
		
		for (int i = 7; i < 24; i++)
		{
			if (timetable[day][i] != 0)
			{
				begin = i;
				break;
			}
		}
		
		for (int i = 23; i >= 7; i--)
		{
			if (timetable[day][i] != 0)
			{
				end = i;
				break;
			}
		}
		
		for (int i = begin; i <= end; i++)
		{
			if (i == -1)
			{
				break;
			}
			
			if (timetable[day][i] == 0)
			{
				res++;
			}
		}
		
		return res;
	}
	
	public void cleanTimetable()
	{
		for (int i = 1; i <= 5; i++)
		{
			for (int j = 0; j < 24; j++)
			{
				timetable[i][j] = 0;
			}
		}
	}
	
	public void strni()
	{
		int cnt = 0, curDay = 1, curHour = 7;
		int[] pos = new int[100000];

		for (int i = 0; i < counter; i++)
		{
			if (blocks[i].isInUse())
			{
				pos[cnt++] = i;
			}
		}

		/// SORT
		for (int i = 0; i < cnt - 1; i++)
		{
			for (int j = i + 1; j < cnt; j++)
			{
				if ((24 * blocks[pos[i]].getDay()) + blocks[pos[i]].getStart() > (24 * blocks[pos[j]].getDay()) + blocks[pos[j]].getStart())
				{
					int temp = pos[i];
					pos[i] = pos[j];
					pos[j] = temp;
				}
			}
		}

		cleanTimetable();
		
		for (int i = 0; i < cnt; i++)
		{
			if (curHour > 18)
			{
				curHour = 7;
				curDay++;
			}
			
			blocks[pos[i]].setDay(curDay);
			blocks[pos[i]].setStart(curHour);
			
			for (int j = curHour; j < curHour + blocks[pos[i]].getDuration(); j++)
			{
				timetable[curDay][j] = blocks[pos[i]].getID();
			}
			
			curHour += blocks[pos[i]].getDuration();
		}
	}
}