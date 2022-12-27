public class BST<K extends Comparable<K>, T> implements Map<K, T> {

    public newNode<K,T> root;
    public newNode <K,T> current;
        
    public BST() {
    	root = current = null;
    }
    
	@Override
	public boolean empty() {
		return root == null;
	}

	@Override
	public boolean full() {
		return false;
	}

	@Override
	public T retrieve() {
		return current.data;
	}

	@Override
	public void update(T e) {
            current.data = e;

	}

	@Override
	public Pair<Boolean, Integer> find(K key) {
		newNode<K,T>p = root;
		newNode<K,T>q;
		boolean boo = false;
		if (root == null)
			boo = false;
                int n = 0;
                if(!empty())
                {
                while(p!=null){
                    n++;
                    q = p;
                    if (key.compareTo(p.key)== 0)
                    {
                        current = p;
                        boo = true;
                        return new Pair<Boolean, Integer>(boo, n);
                    }
                    else if(key.compareTo(p.key)< 0)
                        p = p.left;
                    else
                        p = p.right;
                }
                }
                return new Pair<Boolean, Integer>(boo, n);
	}

@Override
    public Pair<Boolean, Integer> insert(K key, T data)
    {
	int n = 0;
	boolean boo;
    newNode<K, T> p = root;
    newNode<K, T> q = null;
   
    for (p = root; p != null;) {
				q = p;
				n++;
				{
				if (key.compareTo(p.key) == 0)
					break;
				if (key.compareTo(p.key) < 0)
					p = p.left;
				else
					p = p.right;
				}
			}
    boo = false;
    if (p != null)
				boo = false;
    else if (q == null)
    {
        root = current = new newNode<K,T>(key, data);
        return new Pair<Boolean, Integer>(true, n);
    }
    else if (key.compareTo(q.key) < 0)
   {
        q.left = new newNode<K,T>(key, data);
        return new Pair<Boolean, Integer>(true, n);
   }
    else
    {
        q.right = new newNode<K,T>(key, data);
        return new Pair<Boolean, Integer>(true, n);
    }
    return new Pair<Boolean, Integer>(boo, n);
    }

   
	@Override
	public Pair<Boolean, Integer> remove(K key) {
		newNode<K,T>p = root;
		newNode<K,T>q;
		boolean boo = false;
                int n = 0;
                if(!empty())
                {
                while(p!=null){
                	n++;
                    q = p;
                    if (key.compareTo(p.key) == 0)
                    {
                    	if ((p.left != null) && (p.right != null)) 
                    	{ 
                    		newNode<K,T> min = p.right;
                    		q = p;
                    		while (min.left != null) {
                    			n++;
                    			q = min;
                    			min = min.left;
                    			}              
                    		p.key = min.key;
                    		p.data = min.data;
                    		key = min.key;
                    		p = min; 
                    		}
                    	else if (p.left != null)  
                    		p = p.left;	
                    	else 
                    		p = p.right;
                    	boo = true;
                    }
                    else if(key.compareTo(p.key)< 0)
                        p = p.left;
                    else
                        p = p.right;
                }
                }
		
            return new Pair<Boolean, Integer>(boo, n);
	}
	  

	
	@Override
	public List<K> getAll() {
		List<K> list = new LinkedList<K>();
		InOrder(root, list);
		return list;
	}
	
	// Traverse method.
	
	private void InOrder(newNode<K,T>p, List<K>list)
	{
		if (p == null) 
			return;
		else
		{
			InOrder(p.left,list);
			list.insert(p.key);
			InOrder(p.right,list);
		}
	}

}


