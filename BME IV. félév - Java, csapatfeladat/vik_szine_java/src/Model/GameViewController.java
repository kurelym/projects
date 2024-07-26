package Model;

import java.io.IOException;
import java.io.*;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import javax.swing.*;

public class GameViewController extends Thread {
    private Game game;
    private GameView view;
    private List<Student> players;
    private Student CurrentPlayer;
    private String[] names;
    private boolean buttonPressed;
    private boolean gameOver;
    private static final Object lock = new Object();
    public GameViewController(){}
    public GameViewController(String[] _names){
        String filePath = "Model/map.txt";
        game = new Game();
        names = _names;
        buttonPressed = false;
        players = new ArrayList<>();
        try{
            game.buildGame(filePath);
            for(String name: names){
                Student newStudent = new Student(game.findRoomByName("Room_1"), new PrintStream(new FileOutputStream("game.txt", false)));
                newStudent.setNameByUser(name);
                game.addStudent(newStudent);
                players.add(newStudent);
            }
            CurrentPlayer = players.get(0);
            refreshViewData();
            
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    public void run(){
        gameOver = false;
        while(!gameOver){
            for(Student cp : players){
                CurrentPlayer = cp;
                refreshViewData();
                for(int i =0; i<3;i++){
                    synchronized(lock){
                        try{
                            while (!buttonPressed) {
                                lock.wait(); // Pause the thread until button is pressed
                            }
                        }
                        catch(InterruptedException ex){
                            ex.printStackTrace();
                        }
                    }
                    buttonPressed=false;
                    if(CurrentPlayer.isDazed()){
                        break;
                    }
                    gameOver = game.betweenRounds(gameOver);
                    if(gameOver){
                        break;
                    }
                    refreshViewData();
                }
            }
        gameOver = game.incrementRound(gameOver);
        refreshViewData();
        }
        SwingUtilities.invokeLater(() -> {
        view.warning(5);
    });
    }
    public void setButtonPressed(){
        synchronized(lock){
        buttonPressed = true;
        }
    }
    public void goToRoom(String pickedRoom){
        
            if(!gameOver){
                CurrentPlayer.goToRoom(game.findRoomByName(pickedRoom));
                refreshViewData();
            }
            else{
                SwingUtilities.invokeLater(() -> {
                    view.warning(5);
                });
            }
    }

    public void Activate(String first, String second){
        if(!gameOver){
            try{
                Transistor t1 = (Transistor)game.findItemByName(first);
                Transistor t2 = (Transistor)game.findItemByName(second);
                if(!t1.isActive()&&!t2.isActive()){
                    t1.pairing(t2);
                    refreshViewData();
                }
                else{
                    SwingUtilities.invokeLater(() -> {
                        view.warning(6);
                    });
                }
            }
            catch(Exception e){
                SwingUtilities.invokeLater(() -> {
                view.warning(4);
            });
            }
        }
        else{
            SwingUtilities.invokeLater(() -> {
                view.warning(5);
            });
        }
    }
    public void Use(int idx){
        if(!gameOver){
            CurrentPlayer.useItem(idx);
            refreshViewData();
        }
        else{
            SwingUtilities.invokeLater(() -> {
                view.warning(5);
            });
        }
    }
    public void Drop(String pickedItem){
        if(!gameOver){
        CurrentPlayer.dropItem(game.findItemByName(pickedItem));
        refreshViewData();
        }
        else{
            SwingUtilities.invokeLater(() -> {
                view.warning(5);
            });
        }
    }
    public void PickUp(String pickedItem){
            if(!gameOver){
                CurrentPlayer.pickUpItem(game.findItemByName(pickedItem));
                refreshViewData();
                }
                else{
                    SwingUtilities.invokeLater(() -> {
                        view.warning(5);
                    });
                }
    }
    public void refreshViewData(){
            int round = game.getRound();
            Room room = CurrentPlayer.getRoom();
            List<Using> inventory = CurrentPlayer.getInventory();
            List<Room> rooms = game.getRooms();
            List<Character> peoples = room.getCharacters();
            List<Using> items = room.getItems();
            ArrayList<String> roomsName = new ArrayList<>();
            ArrayList<String> peoplesName = new ArrayList<>();
            ArrayList<String> itemsName = new ArrayList<>();
            ArrayList<String> inventoryNames = new ArrayList<>();
            HashMap<String,ArrayList<String>> connections = new HashMap<String,ArrayList<String>>();
            for(Room r:rooms){
                roomsName.add(r.getName());
            }
            for(Character c: peoples){
                peoplesName.add(c.getName());
            }
            for(Using i: items){
                itemsName.add(i.getName());
            }
            for(Using i: inventory){
                inventoryNames.add(i.getName());
            }
            for(Room r:rooms){
                ArrayList<String> tempNames = new ArrayList<>();
                for(Room neighbour:r.getNeighbours()){
                    tempNames.add(neighbour.getName());
                }
                connections.put(r.getName(), tempNames);
            }
            ViewData newData = new ViewData(names, room.getName(),CurrentPlayer.getNameByUser(), round,inventoryNames, itemsName, peoplesName, roomsName, connections);
            if(view==null){
                SwingUtilities.invokeLater(() -> {
                view = new GameView(newData, this, lock);
            });
            }
            else{
                SwingUtilities.invokeLater(() -> {
                view.update(newData);
            });
            }
    }
}