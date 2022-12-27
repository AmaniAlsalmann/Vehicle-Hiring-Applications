public class LocNode<T>{
	
	public List<T> data;
    public Location loc;
    LocNode<T> child1;
    LocNode<T> child2;
    LocNode<T> child3;
    LocNode<T> child4;

    public LocNode (T data, Location loc) {
	this.data = new LinkedList<T>();
	this.data.insert(data);
	this.loc = loc;
	child1 = null;
	child2 = null;
	child3 = null;
	child4 = null;
    }

    public List<T> getData() {
        return data;
    }

    public Location getLoc() {
        return loc;
    }

    public LocNode<T> getchild1() {
        return child1;
    }

    public LocNode<T> getchild2() {
        return child2;
    }

    public LocNode<T> getchild3() {
        return child3;
    }

    public LocNode<T> getchild4() {
        return child4;
    }

    public void setData(T data) {
    	this.data.insert(data);
    }

    public void setLoc(Location loc) {
        this.loc = loc;
    }

    public void setchild1(LocNode<T> child1) {
        this.child1 = child1;
    }

    public void setchild2(LocNode<T> child2) {
        this.child2 = child2;
    }

    public void setchild3(LocNode<T> child3) {
        this.child3 = child3;
    }

    public void setchild4(LocNode<T> child4) {
        this.child4 = child4;
    }

}