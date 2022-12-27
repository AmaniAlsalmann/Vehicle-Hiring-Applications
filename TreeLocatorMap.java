public class TreeLocatorMap<K extends Comparable<K>> implements LocatorMap<K> {

	Map<K,Location>bst;
	Locator<K>locations;
	
	public TreeLocatorMap()
	{
		bst = new BST<K,Location>();
		locations = new TreeLocator<K>();
	}
	
	public TreeLocatorMap(Map<K,Location> bst, Locator<K>locations)
	{
		this.bst = bst;
		this.locations = locations;
	}
	
	@Override
	public Map<K, Location> getMap() {
		return bst;
	}

	@Override
	public Locator<K> getLocator() {
		return locations;
	}

	@Override
	public Pair<Boolean, Integer> add(K k, Location loc) {
		Pair<Boolean, Integer>add = bst.insert(k, loc);
		if(add.first)
			locations.add(k, loc);
		
		return add;
	}

	@Override
	public Pair<Boolean, Integer> move(K k, Location loc) {
		Pair<Boolean, Integer>move = bst.find(k);
		if(move.first)
		{
			locations.remove(k, bst.retrieve());
			locations.add(k, loc);
			bst.update(loc);
			return move;
		}
		return move;
	}

	@Override
	public Pair<Location, Integer> getLoc(K k) {
		Pair<Location, Integer>get;
		Pair<Boolean, Integer>get1 = bst.find(k);
		if(get1.first)
		{
			get = new Pair<Location, Integer>(bst.retrieve(), get1.second);
			return get;
		}
		get = new Pair<Location, Integer>(null, get1.second);
		return get;
	}

	@Override
	public Pair<Boolean, Integer> remove(K k) {
		Pair<Boolean, Integer>re = bst.find(k);
		if(re.first)
		{
			locations.remove(k, bst.retrieve());
			bst.remove(k);
		}
		return re;
	}

	@Override
	public List<K> getAll() {
		return bst.getAll();
	}

	@Override
	public Pair<List<K>, Integer> getInRange(Location lowerLeft, Location upperRight) {
		Pair<List<Pair<Location, List<K>>>,Integer> list = locations.inRange(lowerLeft, upperRight);
		List<Pair<Location,List<K>>> allList = list.first;
        List<K>allKey = new LinkedList<K>();
        if(!allList.empty())
        {
        	allList.findFirst();
        	while(!allList.last())
        	{
        		List<K> k = allList.retrieve().second;
        		AddToList(allKey, k);
        		allList.findNext();
        	}
        	List<K> k = allList.retrieve().second;
    		AddToList(allKey, k);
        }
        int sec = list.second;
        Pair<List<K>, Integer> inRange = new Pair<List<K>, Integer>(allKey, sec);
        
		return inRange;
	}
	
	private void AddToList(List<K>list1,List<K>list2)
	{
		if(list2.empty())
			return;
		list2.findFirst();
		while(!list2.last())
		{
			K k = list2.retrieve();
			if(!InList(list1,k))
				list1.insert(k);
			list2.findNext();
		}
		K k = list2.retrieve();
		if(!InList(list1,k))
			list1.insert(k);			
	}
	
	private boolean InList(List<K>list, K k)
	{
		boolean boo = false;
		if(list.empty())
			return boo;
		list.findFirst();
		while(!list.last())
		{
			if(list.retrieve().compareTo(k)==0) 
				boo= true;
			list.findNext();
		}
		if(list.retrieve().compareTo(k)==0) 
			boo= true;
		
		return boo;
	}

}
