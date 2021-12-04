package metroSystem;

/**
 * A class that encapsulates information about an edge.
 * <p>
 *     Encapsulate all the information required by the "edge",
 *     accept the management of the {@link metroSystem.Database},
 *     used for more detailed function implementation.
 *     <br>
 *     The "edge" here refers to the line between two subway stations.<br>
 * </p>
 * @since Sept. 30, 2021
 * @version 2
 */
public class Edge {
    private final int identifier;
    private final Station st_station, ed_station;
    private Line inLine = null;
    private final Administrator admin;
    private boolean isOpen;
    private final int time;

    public Edge(int id, Station st_station, Station ed_station, int time, Administrator admin) {
        this.identifier = id;
        this.st_station = st_station;
        this.ed_station = ed_station;
        this.admin = admin;
        this.isOpen = true;
        this.time = time;
        st_station.addEdgeTo(this);
    }

    /**
     * Setter function of {@code inLine}.
     * @param l The line that this edge belongs to
     */
    public void setLine(Line l) {
        this.inLine = l;
    }

    /**
     * Setter function of {@code isOpen}.
     * @param b The boolean switcher that shows the current
     */
    public void setIsOpen(boolean b) {
        this.isOpen = b;
    }

    @Override
    public String toString() {
        return st_station.getName() + "->" + ed_station.getName();
    }

    /**
     * Getter function of the line it belongs to.
     * @return  Line name;
     *          "null" if no line information stored
     */
    public String getLine() {
        if(inLine == null)
            return "null";
        return inLine.getName();
    }

    /**
     * Getter function of starting station.
     * @return Station object that refers to its starting point
     */
    public Station getStartSta(){
        return st_station;
    }

    /**
     * Getter function of ending station.
     * @return Station object that refers to its ending point
     */
    public Station getEndSta(){
        return ed_station;
    }
    
    /**
     * A member of {@code getData} member series to retrieve IsConnected data.
     * @return	whether this edge is OK to use (in case some station is offline temporally)
     */
    public boolean getIsConnect() {
    	return this.isOpen;
    }

    /**
     * A member of {@code getData} member series to retrieve time data.
     * @return	the time that need to spend on this edge
     */
    public int getTimeSpend() {
        return this.time;
    }
}
