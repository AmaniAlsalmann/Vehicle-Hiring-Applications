public interface LocatorMap<K extends Comparable<K>> {


	public Map<K, Location> getMap();

	public Locator<K> getLocator();

	public Pair<Boolean, Integer> add(K k, Location loc);

	public Pair<Boolean, Integer> move(K k, Location loc);

	public Pair<Location, Integer> getLoc(K k);

	public Pair<Boolean, Integer> remove(K k);

	public List<K> getAll();

	public Pair<List<K>, Integer> getInRange(Location lowerLeft, Location upperRight);

}
