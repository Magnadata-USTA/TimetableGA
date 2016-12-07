package TimetableGA;

public class Preference {

	private int day;
	private int startTime;
	/* value [0,1,2]
	 * 0: available 100%
	 * 1: available with constraints
	 * 2: not available */
	private int value;
	
	public Preference(int day, int startTime, int value) {
		this.setDay(day);
		this.setStartTime(startTime);
		this.setValue(0);
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
