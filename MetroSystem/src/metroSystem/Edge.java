package MetroSystem.src.metroSystem;

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

    public void setLine(Line l) {
        this.inLine = l;
    }

    public void setIsOpen(boolean b) {
        this.isOpen = b;
    }

    @Override
    public String toString() {
        return st_station.getName() + "->" + ed_station.getName();
    }

    public String getLine() {
        if(inLine == null)
            return "null";
        return inLine.getName();
    }

    public Station getStartSta(){
        return st_station;
    }

    public  Station getEndSta(){
        return ed_station;
    }
    
    /**
     * A member of {@code getData} member series to retrieve IsConnected data.
     * @return	whether this edge is OK to use (in case some station is offline temporally)
     */
    public boolean dataIsConnect() {
    	return this.isOpen;
    }
}
