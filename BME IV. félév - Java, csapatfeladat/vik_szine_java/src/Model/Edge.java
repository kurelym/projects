package Model;

import java.awt.Point;

public class Edge {
    private Point start;
    private Point end;

    public Edge(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }
}
