package exercise;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Comparator;

// BEGIN
public class App {
	public static List<String> buildAppartmentsList(List<Home> appartments, int n) {
		return appartments
			.stream()
			.sorted(Comparator.comparingDouble(Home::getArea))
			.limit(3)
			.map(home -> home.toString())
			.toList();
	}
}
// END
