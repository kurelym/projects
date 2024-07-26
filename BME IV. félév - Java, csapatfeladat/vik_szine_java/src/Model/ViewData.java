package Model;

import java.util.HashMap;

import java.util.*;

public class ViewData {
	private String[] names;
    private int round;
    private ArrayList<String> inventory;
    private String currentNode;
    private String curretName;
    private ArrayList<String> items;
    private ArrayList<String> peoples;
    private ArrayList<String> nodes;
    private HashMap<String, ArrayList<String>> connectionBetweennodes;
    public ViewData(String[] _names, String _currentNode,String _currentName,int _round,ArrayList<String> _inventory,ArrayList<String> _items,ArrayList<String> _peoples,ArrayList<String> _nodes, HashMap<String, ArrayList<String>> connenctions){
        names = _names;
        round =_round;
        items = _items;
        inventory = _inventory;
        peoples = _peoples;
        nodes = _nodes;
        curretName = _currentName;
        currentNode = _currentNode;
        connectionBetweennodes = connenctions;
    }
    public String getCurrentNode(){
        return currentNode;
    }
    public String getCurrentName(){
        return curretName;
    }
    public String[] getNames(){
        return names;
    }
    public ArrayList<String> getInventory(){
        return inventory;
    }
    public int getRound(){
        return round;
    }
    public ArrayList<String> getNodes(){
        return nodes;
    }
    public ArrayList<String> getItems(){
        return items;
    }
    public ArrayList<String> getPeoples(){
        return peoples;
    }
    public HashMap<String, ArrayList<String>> getConnections(){
        return connectionBetweennodes;
    }
}
