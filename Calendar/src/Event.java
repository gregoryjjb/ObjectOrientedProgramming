import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Homework #1: Calendar
 * Represents a single event
 * @author Greg Brisebois
 * @version 1.0
 */
public class Event implements Comparable<Event>
{
	public Date date;
	public String title;
	
	public Date startTime;
	public Date endTime;
	
	public Event(Date d, String t)
	{
		date = d;
		title = t.replace(EventCalendar.DIV, ""); // Remove div to avoid breaking file saving
	}
	
	public Event(Date d, String t, Date start, Date end)
	{
		date = d;
		title = t.replace(EventCalendar.DIV, ""); // Remove div to avoid breaking file saving;
		startTime = start;
		endTime = end;
	}

	/**
	 * Draws this event to the console
	 */
	public void draw()
	{
		String prettyDate = EventCalendarUI.inputDateFormat.format(date);
		String prettyStartTime = EventCalendarUI.inputTimeFormat.format(startTime);
		String prettyEndTime = EventCalendarUI.inputTimeFormat.format(endTime);

		UI.output("\t");
		UI.output(prettyDate);
		
		if(startTime != null)
			UI.output(" " + prettyStartTime);
		
		if(!prettyStartTime.equals(prettyEndTime))
			UI.output("-" + prettyEndTime);
		else
			UI.output("\t");
		
		UI.output("\t\t");
		UI.outputln(title);
	}
	
	/**
	 * Check if this event overlaps with a particular event
	 * @param e other event to check with
	 * @return true if overlaps
	 */
	public boolean conflicts(Event e)
	{
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");

		if(df.format(e.date).equals(df.format(date)))
		{
			if(e.startTime.after(startTime) && e.startTime.before(endTime))
				return true;
			if(e.endTime.after(startTime) && e.endTime.before(endTime))
				return true;
		}

		return false;
	}
	
	/**
	 * Convert event to simple string
	 * @return string of event title and date
	 */
	public String toString()
	{
		String prettyDate = UI.eventDateFormat.format(date);
		
		return prettyDate + "\t\t" + title;
	}
	
	/**
	 * Used for sorting
	 * @param o
	 * @return
	 */
	@Override
	public int compareTo(Event o)
	{
		int dateCompare = this.date.compareTo(o.date);

		if(dateCompare == 0)
			return this.startTime.compareTo(o.startTime);

		return dateCompare;
	}
}
