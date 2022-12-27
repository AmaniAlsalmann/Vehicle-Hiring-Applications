public class TreeLocator<T> implements Locator<T> {
	
	public LocNode<T>root,current;

	@Override
	public int add(T e, Location loc) {
		LocNode<T> p = root;
		LocNode<T> q = root;
		int n = 0 ;
		
		if(p == null)
		{
			root = current = new LocNode<T>(e, loc);
			return n;
		}
		
		while(p != null) {
			n++;
			q = p;
			if(loc.x == p.loc.x && loc.y == p.loc.y) {
				p.data.insert(e);
				return n;
			}
			else if(loc.x < p.loc.x && loc.y <= p.loc.y)
				p = p.child1;
			else if(loc.x <= p.loc.x && loc.y > p.loc.y)
				p = p.child2;
			else if(loc.x > p.loc.x && loc.y >= p.loc.y)
				p = p.child3;
			else if(loc.x >= p.loc.x && loc.y < p.loc.y)
				p = p.child4;
			else 
				break;
		}
		
		LocNode<T> newChild = new LocNode<T>(e, loc);
		if(loc.x < q.loc.x && loc.y <= q.loc.y)
			q.child1 = newChild;
		else if(loc.x <= q.loc.x && loc.y > q.loc.y)
			q.child2 = newChild;
		else if(loc.x > q.loc.x && loc.y >= q.loc.y)
			q.child3 = newChild;
		else if(loc.x >= q.loc.x && loc.y < q.loc.y)
			q.child4 = newChild;
		return n;
	}

	@Override
	public Pair<List<T>, Integer> get(Location loc) {
		LocNode<T> p = root;
		List<T> list = new LinkedList<T>();
		int n = 0 ;
		
		if(p == null)
			return new Pair<List<T>, Integer>(list, n) ;
		
		while(p != null) {
			n++;
			if(loc.x == p.loc.x && loc.y == p.loc.y) {
				return new Pair<List<T>, Integer>(p.data, n) ;
			}
			else if(loc.x < p.loc.x && loc.y <= p.loc.y)
				p = p.child1;
			else if(loc.x <= p.loc.x && loc.y > p.loc.y)
				p = p.child2;
			else if(loc.x > p.loc.x && loc.y >= p.loc.y)
				p = p.child3;
			else if(loc.x >= p.loc.x && loc.y < p.loc.y)
				p = p.child4;
		}
		return new Pair<List<T>, Integer>(list, n) ;
	}

	@Override
	public Pair<Boolean, Integer> remove(T e, Location loc) {
		LocNode<T> p = root;
		boolean boo = false;
		int n = 0 ;
		
		if(p == null)
		{
			return new Pair<Boolean, Integer>(boo, n) ;
		}
		while(p != null) {
			n++;
			if(loc.x == p.loc.x && loc.y == p.loc.y) {
				if (!p.data.empty())
				{
					p.data.findFirst();
					while(!p.data.last())
					{
						if(p.data.retrieve().equals(e))
						{
							p.data.remove();
							boo = true;
						}
						else
						p.data.findNext();
					}
					if(p.data.retrieve().equals(e))
					{
						p.data.remove();
						boo = true;
					}
				}
				return new Pair<Boolean, Integer>(boo, n) ;
			}
			else if(loc.x < p.loc.x && loc.y <= p.loc.y)
				p = p.child1;
			else if(loc.x <= p.loc.x && loc.y > p.loc.y)
				p = p.child2;
			else if(loc.x > p.loc.x && loc.y >= p.loc.y)
				p = p.child3;
			else if(loc.x >= p.loc.x && loc.y < p.loc.y)
				p = p.child4;
		}
		return new Pair<Boolean, Integer>(boo, n) ;
	}


	@Override
	public List<Pair<Location, List<T>>> getAll() {
		List<Pair<Location, List<T>>> listAll = new LinkedList<Pair<Location, List<T>>>();
		PreOrder(root, listAll);
		return listAll;
		
	}

	@Override
	public Pair<List<Pair<Location, List<T>>>, Integer> inRange(Location lowerLeft, Location upperRight) {
		int n = 0;
		List<Pair<Location, List<T>>> listInRange = new LinkedList<Pair<Location, List<T>>>();
		Pair<List<Pair<Location, List<T>>>,Integer> InRange = new Pair<List<Pair<Location, List<T>>>,Integer>(listInRange, n);
		LocNode<T> p = root;
		if (root == null)
			return InRange;
		else
			inRange(InRange, p ,lowerLeft, upperRight);
		
		return InRange;
	}
	
	private void PreOrder(LocNode p, List<Pair<Location, List<T>>>listAll)
	{
		if (p == null) 
			return;
		else
		{
			listAll.insert(new Pair<Location, List<T>>(p.loc, p.data));
			PreOrder(p.child1,listAll);
			PreOrder(p.child2,listAll);
			PreOrder(p.child3,listAll);
			PreOrder(p.child4,listAll);
		}
	}

    
    private void inRange(Pair<List<Pair<Location, List<T>>>, Integer>InRange, LocNode<T> p, Location lowerLeft, Location upperRight)
    {	
    	if (p==null)
    		return;
    
    else {
    	InRange.second++;
    	
    	if(lowerLeft.x <= p.loc.x && p.loc.x <= upperRight.x && lowerLeft.y <= p.loc.y && p.loc.y <= upperRight.y)
    	{
    		InRange.first.insert(new Pair<Location, List<T>>(p.loc, p.data));
    		inRange(InRange, p.child1, lowerLeft, upperRight);
    		inRange(InRange, p.child2, lowerLeft, upperRight);
    		inRange(InRange, p.child3, lowerLeft, upperRight);
    		inRange(InRange, p.child4, lowerLeft, upperRight);
    	}
    	
		else if (upperRight.x <= p.loc.x && upperRight.y <= p.loc.y)
		{
			inRange(InRange, p.child1, lowerLeft, upperRight);
			if (upperRight.x == p.loc.x)
			inRange(InRange, p.child4, lowerLeft, upperRight);
		}
		else if (upperRight.x <= p.loc.x && lowerLeft.y >= p.loc.y)
		{
			inRange(InRange, p.child2, lowerLeft, upperRight);
			if (lowerLeft.y == p.loc.y)
				inRange(InRange, p.child1, lowerLeft, upperRight);
		}
	
		else if (lowerLeft.x >= p.loc.x && lowerLeft.y >= p.loc.y)
		{
			inRange(InRange, p.child3, lowerLeft, upperRight);
			if(lowerLeft.x == p.loc.x)
			inRange(InRange, p.child2, lowerLeft, upperRight);
		}
		
		else if (lowerLeft.x >= p.loc.x && upperRight.y <= p.loc.y)
		{
			inRange(InRange, p.child4, lowerLeft, upperRight);
			if (upperRight.y == p.loc.y)
				inRange(InRange, p.child3, lowerLeft, upperRight);
		}

		else if (upperRight.y <= p.loc.y) {
			inRange(InRange, p.child1, lowerLeft, upperRight);
			if (upperRight.y == p.loc.y)
				inRange(InRange, p.child3, lowerLeft, upperRight);
			
			inRange(InRange, p.child4, lowerLeft, upperRight);
		}
		else if (lowerLeft.y >= p.loc.y) {
			inRange(InRange, p.child2, lowerLeft, upperRight);
			inRange(InRange, p.child3, lowerLeft, upperRight);
			if (lowerLeft.y == p.loc.y)
				inRange(InRange, p.child1, lowerLeft, upperRight);
		}
		
		else if (upperRight.x <= p.loc.x)
		{
			inRange(InRange, p.child1, lowerLeft, upperRight);
			inRange(InRange, p.child2, lowerLeft, upperRight);
			if (upperRight.x == p.loc.x)
			inRange(InRange, p.child4, lowerLeft, upperRight);
		}
    	
		else if (lowerLeft.x >= p.loc.x)
		{
			inRange(InRange, p.child3, lowerLeft, upperRight);
			inRange(InRange, p.child4, lowerLeft, upperRight);
			if (lowerLeft.x == p.loc.x)
			inRange(InRange, p.child2, lowerLeft, upperRight);
		}


    	
    	}
    }
    
}