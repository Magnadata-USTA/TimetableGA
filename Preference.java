
public class Preference {

	private int day;
	private int startTime;
	/* value [0,1,2]
	 * 0: available 100%
	 * 1: available with constraints
	 * 2: not available */
	private int value;
	
	public Preference(int day, int startTime, int value) {
		this.day = day;
		this.startTime = startTime;
		this.value = value;
	}

}
