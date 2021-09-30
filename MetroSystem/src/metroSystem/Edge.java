package metroSystem;

import javax.lang.model.type.PrimitiveType;

public class Edge {
    private final int identifier;
    private final Station st_station, ed_station;
    private Line inLine = null;
    private final Administrator admin;
    private boolean isOpen;

    public Edge(int id, Station st_station, Station ed_station, Administrator admin) {
        this.identifier = id;
        this.st_station = st_station;
        this.ed_station = ed_station;
        this.admin = admin;
        this.isOpen = true;
        st_station.addEdgeTo(this);
    }

    public void setLine(Line l) {
        this.inLine = l;
    }

    public void setIsOpen(boolean b) {
        this.isOpen = b;
    }

    public String toString() {
        return st_station.getName() + "->" + ed_station.getName();
    }

    public String getLine() {
        return inLine.getName();
    }
}
