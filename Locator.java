public interface Locator<T> {

	public int add(T e, Location loc);

	Pair<List<T>, Integer> get(Location loc);

	Pair<Boolean, Integer> remove(T e, Location loc);

	List<Pair<Location, List<T>>> getAll();

	Pair<List<Pair<Location, List<T>>>, Integer> inRange(Location lowerLeft, Location upperRight);



}
