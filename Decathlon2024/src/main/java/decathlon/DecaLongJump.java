package decathlon;

import common.*;

public class DecaLongJump {

	private int score;
	private double A = 0.14354; // ändrade  0.13454
	private double B = 220;
	private double C = 1.4;
	boolean active = true;
	CalcTrackAndField calc = new CalcTrackAndField();
	public InputResult inputResult = new InputResult();

	// Calculate the score based on distance and height. Measured in centimetres.
	public int calculateResult(double distance) {

		while (active) {

			try {
				// Acceptable values.
				if (distance < 0) {
					System.out.println("Deca Value too low");
					distance = inputResult.enterResult();
				} else if (distance > 1000) {

					System.out.println("Deca Value too high");
					distance = inputResult.enterResult();

				} else {

					score = calc.calculateField(A, B, C, distance);
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
