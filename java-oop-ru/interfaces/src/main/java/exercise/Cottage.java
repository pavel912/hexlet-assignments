package exercise;

// BEGIN
public class Cottage implements Home {
	private double area;
	private int floorCount;

	public Cottage(double area, int floorCount) {
		this.area = area;
		this.floorCount = floorCount;
	}

	@Override
	public double getArea() {
		return area;
	}

	public String toString() {
		return String.format("%d этажный коттедж площадью %f метров", floorCount, area);
	}

	@Override
	public int compareTo(Home another) {
		if (this.getArea() > another.getArea()) {
			return 1;
		}

		if (this.getArea() < another.getArea()) {
			return -1;
		}

		return 0;
	}
}
// END
