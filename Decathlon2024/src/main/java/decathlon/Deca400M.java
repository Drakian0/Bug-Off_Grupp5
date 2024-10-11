package decathlon;

import common.*;

public class Deca400M {

	private int score;
	private double A = 1.53775;
	private double B = 82;
	private double C = 1.81;
	boolean active = true;

	CalcTrackAndField calc = new CalcTrackAndField();
	public InputResult inputResult = new InputResult();

	// Calculate the score based on time. All running events.
	public int calculateResult(double runningTime) {

		while (active) {

			try {
				// Acceptable values.
				if (runningTime < 20) {
					System.out.println("Value too low");
					runningTime = inputResult.enterResult();
				} else if (runningTime > 100) {
					System.out.println("Value too high");
					runningTime = inputResult.enterResult();
				} else {

					score = calc.calculateTrack(A, B, C, runningTime);
					active = false;
				}
			} catch (Exception e) {

				System.out.println("Please enter numbers");
			}
		}
		System.out.println("The result is: " + score);
		return score;
	}

	// Method to set the active flag in order to get around the while loop during testing
	public void setActive(boolean active) {
		this.active = active;
	}

	// Getter methods for A, B, and C to send to JUnit for testing
	public double getA() {
		return A;
	}

	public double getB() {
		return B;
	}

	public double getC() {
		return C;
	}
}
