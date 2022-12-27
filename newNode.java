public class newNode<K extends Comparable<K>,T> {
    
    public K key;
    public T data;
    public newNode<K,T> left, right;
    
    public newNode(K k, T e) {
        this.key = k;
	    data = e;
	    left = null;
	    right = null;
    }
    
    public newNode(K k, T e,newNode l, newNode r) {
        this.key = key;
	    data = e;
	    left = l;
	    right = r;
    }
    

    public K getKey() {
        return key;
    }

    public T getData() {
        return data;
    }

    public newNode<K, T> getLeft() {
        return left;
    }

    public newNode<K, T> getRight() {
        return right;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setLeft(newNode<K, T> left) {
        this.left = left;
    }

    public void setRight(newNode<K, T> right) {
        this.right = right;
    }
    
}
