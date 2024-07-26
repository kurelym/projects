package Model;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.geom.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import javax.swing.JPanel;

public class Graph extends JPanel {

    private HashMap<String,Point> nodes;
    private HashMap<Edge,Boolean> edges;
    private Point currentNode;
    private Point selectedNode;

    public Graph(ArrayList<String> _nodes, String _currentNode, HashMap<String,ArrayList<String>> connections, int width, int height) {
        nodes = new HashMap<>();
        edges = new HashMap<>();
        generateData(_nodes,_currentNode,connections,width, height);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1) {
                    for (String s : nodes.keySet()) {
                        if (distance(nodes.get(s), evt.getPoint()) <= 30 && !nodes.get(s).equals(currentNode)) {
                            selectedNode = nodes.get(s);
                            if(!isNeighboursWithCurrentPoint()) {
                            	selectedNode = null;
                            }
                            break;
                        }
                    }
                } 
                else if (evt.getButton() == MouseEvent.BUTTON3 && selectedNode != null) {
                    selectedNode = null;
                }
                repaint();
            }
        });
        repaint();
        setBackground(Color.GRAY); 
    }
    private void generateData(ArrayList<String> _nodes, String _currentNode, HashMap<String,ArrayList<String>> connections, int width, int height) {
    	if(!nodes.isEmpty()) {
    		ArrayList<String> keysToRemove = new ArrayList<>();
    		for(String s: nodes.keySet()) {
    			if(!_nodes.contains(s)) {
    				keysToRemove.add(s);
    			}
    		}
    		for(String s: keysToRemove) {
    			nodes.remove(s, nodes.get(s));
    		}
    	}
    	for(String s: _nodes) {
    		if(!nodes.containsKey(s)) {
    			Random r = new Random();
            	Point newPoint = new Point(r.nextInt(0, width-150),r.nextInt(0, height-150));
            	while(!acceptNewPoint(newPoint)) {
            		newPoint.x = r.nextInt(0, width-150);
            		newPoint.y = r.nextInt(0, height-150);
            	}
            	if(s.equals(_currentNode)) {
            		currentNode = newPoint;
            	}
            	nodes.put(s, newPoint);
    			
    		}
    		else if(nodes.containsKey(s) && s.equals(_currentNode)) {
    			currentNode = nodes.get(_currentNode);
    		}
        	
        }

    	if(!edges.isEmpty()) {
    		edges.clear();
    	}
        for(String s: connections.keySet()) {
        	for(String n: connections.get(s)) {
        		//Duplex kacsolatnál az adott edge-hez true értéket rendelünk hozzá, amúgy meg false
				ArrayList<String> tempNeighbour = connections.get(n);
				if(tempNeighbour!=null){
					if(connections.get(n).contains(s)) {
						edges.put(new Edge(nodes.get(s),nodes.get(n)),true);
					}
					else {
						edges.put(new Edge(nodes.get(s),nodes.get(n)),false);
					}
				}
        	}
        }
    }
    private boolean acceptNewPoint(Point p1) {
    	for(String name : nodes.keySet()) {
        	Point p2 = nodes.get(name);
        	if(Math.sqrt(Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2))<=70) {
    			return false;
    		}	
        }
        return true;
    }
    private double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2));
    }
    private boolean isNeighboursWithCurrentPoint() {
    	for(Edge e : edges.keySet()) {
    		if(e.getStart().equals(currentNode)&& e.getEnd().equals(selectedNode)) {
    			return true;
    		}
    		/*else if(e.getStart().equals(currentNode)&& e.getEnd().equals(selectedNode)) {
    			return true;
    		}*/
    	}
    	return false;
    }
    public void reDrawGraph(ArrayList<String> _nodes, String _currentNode, HashMap<String,ArrayList<String>> connections, int width, int height) {
    	generateData(_nodes,_currentNode,connections,width, height);
    	this.repaint();
    }
	public void clearSelection(){
		selectedNode = null;
	}
    public String getSelectedNodeName() {
    	for(String s:nodes.keySet()) {
    		if(nodes.get(s).equals(selectedNode)) {
    			return s;
    		}
    	}
    	return null;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Draw edges
        for (Edge edge : edges.keySet()) {
        	if(edges.get(edge)) {
        		//Duplex kapcsolat
        		g2d.setStroke(new BasicStroke(2.0F));
        		g2d.drawLine(edge.getStart().x, edge.getStart().y, edge.getEnd().x, edge.getEnd().y);
        	}
        	else {
        		//Egy irányú kapcsolat
        		g2d.setStroke(new BasicStroke(2.0F));
        		g2d.drawLine(edge.getStart().x, edge.getStart().y, edge.getEnd().x, edge.getEnd().y);
        		Path2D path = new Path2D.Double();
        		path.moveTo((double)edge.getEnd().x, (double)edge.getEnd().y);
        		double length = Math.sqrt(Math.pow(edge.getEnd().x-edge.getStart().x,2)+Math.pow(edge.getEnd().y-edge.getStart().y,2));
        		double directionvectorX =(edge.getEnd().x-edge.getStart().x)/length;
        		double directionvectorY = (edge.getEnd().y-edge.getStart().y)/length;
        		double normalvectorX = (edge.getEnd().y-edge.getStart().y)/length;
        		double normalvectorY = (edge.getStart().x - edge.getEnd().x)/length;
        		double firstX = (edge.getStart().x+edge.getEnd().x)/2+normalvectorX*10;
        		double firstY = (edge.getStart().y+edge.getEnd().y)/2+normalvectorY*10;
        		double secondX = (edge.getStart().x+edge.getEnd().x)/2-normalvectorX*10;
        		double secondY = (edge.getStart().y+edge.getEnd().y)/2-normalvectorY*10;
        		path.moveTo((double)edge.getEnd().x-(directionvectorX*(length/2-length/10)), (double)edge.getEnd().y-(directionvectorY*(length/2-length/10)));
        		path.lineTo(firstX,firstY);
        		path.lineTo(secondX, secondY);
        		path.lineTo((double)edge.getEnd().x-(directionvectorX*(length/2-length/10)), (double)edge.getEnd().y-(directionvectorY*(length/2-length/10)));
        		path.closePath();
        		g2d.fill(path);
        	}
        }
        // Draw nodes
        for(String name : nodes.keySet()) {
        	Point node = nodes.get(name);
        	if(node.equals(currentNode)) {
            	g2d.setColor(Color.GREEN);
                g2d.fillOval(node.x-20, node.y-20, 40, 40);
            	}
        	else if(node.equals(selectedNode)) {
        		g2d.setColor(Color.ORANGE);
                g2d.fillOval(node.x-10, node.y-10, 20, 20);
                g2d.setColor(Color.RED);
                g2d.drawOval(node.x - 13, node.y - 13, 26, 26);
        	}
        	else {
        		g2d.setColor(Color.ORANGE);
                g2d.fillOval(node.x-10, node.y-10, 20, 20);
        	}   
        }
    }
}