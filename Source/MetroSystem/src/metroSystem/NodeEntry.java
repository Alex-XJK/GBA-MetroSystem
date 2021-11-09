package metroSystem;

/**
 * A self-implemented Pair class.
 * Support the get functions of {@code getKey} and {@code getValue}.
 * Together with a setter(changing) function to change its value field {@code changeValue}.
 * @param <K> The variable type of its Key field
 * @param <V> The variable type of its Value filed
 * @since Oct. 3, 2021
 * @version 1.0
 */
public class NodeEntry<K, V> implements Comparable<NodeEntry>{
    private final K key;
    private V value;

    public NodeEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public void changeValue(V newV) {
        this.value = newV;
    }

    public K getKey(){
        return key;
    }

    public V getValue(){
        return value;
    }

    /**
     * Compare two NodeEntry by their Value.
     * @param o another NodeEntry that waiting compare
     * @return  >0  iff this value < other's value;
     *          0   iff the two value are equal;
     *          <0  iff this value > other's value.
     */
	@Override
	public int compareTo(NodeEntry o) {
        // Code Refactoring to java-liked version
        return Integer.compare(toInt(o.getValue()), toInt(this.value));
	}
	
	private int toInt(Object obj) {
    	return Integer.parseInt(obj.toString());
    }
}
