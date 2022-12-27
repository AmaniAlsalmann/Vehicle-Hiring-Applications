public class VehicleHiringManager {
	
	LocatorMap<String>Loc;

	public VehicleHiringManager() {
		Loc = new TreeLocatorMap<String>();
	}
	
	public VehicleHiringManager(TreeLocatorMap<String> Loc) {
		this.Loc = Loc;
	}
	
	public LocatorMap<String> getLocatorMap() {
		return Loc;
	}


	public void setLocatorMap(LocatorMap<String> locatorMap) {
		Loc = locatorMap;
	}

	public boolean addVehicle(String id, Location loc) {
		Pair<Boolean, Integer>add = Loc.add(id, loc);
		boolean boo = add.first;
		return boo;
	}

	public boolean moveVehicle(String id, Location loc) {
		Pair<Boolean, Integer>re = Loc.move(id, loc);
		boolean boo = re.first;
		return boo;
	}

	public boolean removeVehicle(String id) {
		Pair<Boolean, Integer>re = Loc.remove(id);
		boolean boo = re.first;
		return boo;
	}

	public Location getVehicleLoc(String id) {
		return Loc.getLoc(id).first;
	}

	public List<String> getVehiclesInRange(Location loc, int r) {
		Location n1 = new Location(loc.x-r, loc.y-r);
		Location n2 = new Location(loc.x+r, loc.y+r);
		Pair<List<String>, Integer> get = Loc.getInRange(n1, n2);
		List<String> getV = get.first;
		return getV;
	}
}
