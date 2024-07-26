package Model;


import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;



import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class GameView extends JFrame {
    private ImageTextListModel listOfPeoples;
    private ImageTextListModel listOfItems;
    private ImageTextListModel listOfInventory;
    
    private JLabel player1label;
    private JLabel player2label;
    private JLabel player3label;
    private JLabel player4label;
    private JLabel player5label;
    private JLabel roundlabel;
    private JLabel itemslabel;
    private JLabel inventorylabel;
    private JLabel peopleslabel;

    private JList<ImageTextItem> items;
    private JList<ImageTextItem> peoples;
    private JList<ImageTextItem> inventory;
    
    private JScrollPane scrollPaneToItems;
    private JScrollPane scrollPaneToPeoples;
    private JScrollPane scrollPaneToInventory;
    
    private JButton pickupButton = new JButton("PICKUP");
    private JButton dropButton = new JButton("DROP");
    private JButton goToRoomButton = new JButton("GOTOROOM");
    private JButton useButton = new JButton("USE");
    private JButton activateButton = new JButton("ACTIVATE");
    private JButton skipButton = new JButton("SKIP");

    private JPanel mainPanel;
    private JPanel buttonsPanel;
    private JPanel labelPanel;
    private JPanel listPanel;

    private GameViewController gameviewcontroller;
    private Graph graph;
    private static Object lock;

    public GameView(ViewData startData, GameViewController gvc, Object _lock){
        
        //JFrame
        super("vik_szine_java");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setResizable(false);
        setSize(1200,770);
		
        //init
        gameviewcontroller = gvc;
        graph = new Graph(startData.getNodes(),startData.getCurrentNode(),startData.getConnections(),900,690);
        graph.setPreferredSize(new Dimension(900,690));
        lock = _lock;
        //Borders
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        Border border2 = BorderFactory.createLineBorder(Color.WHITE, 2);
        //Lists
        listOfInventory = new ImageTextListModel(startData.getInventory());
        inventory = new JList<>(listOfInventory);
        inventory.setBackground(Color.LIGHT_GRAY);
        inventory.setFont(new Font("Serif", Font.BOLD, 15));
        inventory.setLayoutOrientation(JList.HORIZONTAL_WRAP); // Display items horizontally in a wrap layout
        inventory.setVisibleRowCount(-1); // Display as many rows as needed
        inventory.setFixedCellWidth(90); // Set fixed width for each cell
        inventory.setFixedCellHeight(50); // Set fixed height for each cell
        inventory.setCellRenderer(new ImageTextListCellRenderer());
        inventory.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        scrollPaneToInventory = new JScrollPane(inventory);
        scrollPaneToInventory.setPreferredSize(new Dimension(300, 200));
        scrollPaneToInventory.setBackground(Color.LIGHT_GRAY);
        
        listOfPeoples =new ImageTextListModel(startData.getPeoples());
        peoples = new JList<>(listOfPeoples);
        peoples.setBackground(Color.LIGHT_GRAY);
        peoples.setFont(new Font("Serif", Font.BOLD, 15));
        peoples.setEnabled(false);
        peoples.setLayoutOrientation(JList.HORIZONTAL_WRAP); // Display items horizontally in a wrap layout
        peoples.setVisibleRowCount(-1); // Display as many rows as needed
        peoples.setFixedCellWidth(90); // Set fixed width for each cell
        peoples.setFixedCellHeight(50); // Set fixed height for each cell
        peoples.setCellRenderer(new ImageTextListCellRenderer());
        scrollPaneToPeoples = new JScrollPane(peoples);
        scrollPaneToPeoples.setPreferredSize(new Dimension(300, 200));
        scrollPaneToPeoples.setBackground(Color.LIGHT_GRAY);
        
        listOfItems = new ImageTextListModel(startData.getItems());
        items = new JList<>(listOfItems);
        items.setBackground(Color.LIGHT_GRAY);
        items.setFont(new Font("Serif", Font.BOLD, 15));
        items.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        items.setLayoutOrientation(JList.HORIZONTAL_WRAP); // Display items horizontally in a wrap layout
        items.setVisibleRowCount(-1); // Display as many rows as needed
        items.setFixedCellWidth(90); // Set fixed width for each cell
        items.setFixedCellHeight(50); // Set fixed height for each cell
        items.setCellRenderer(new ImageTextListCellRenderer());
        scrollPaneToItems = new JScrollPane(items);
        scrollPaneToItems.setPreferredSize(new Dimension(300, 200));
        scrollPaneToItems.setBackground(Color.LIGHT_GRAY);
        //JLabel
        player1label = new JLabel ("---",JLabel.CENTER);
        player2label = new JLabel ("---",JLabel.CENTER);
        player3label = new JLabel ("---",JLabel.CENTER);
        player4label = new JLabel ("---",JLabel.CENTER);
        player5label = new JLabel ("---",JLabel.CENTER);
        roundlabel = new JLabel ("Round:"+startData.getRound(),JLabel.CENTER);
        itemslabel = new JLabel ("Items in the current room:",JLabel.CENTER);
        inventorylabel = new JLabel ("Inventory:",JLabel.CENTER);
        peopleslabel = new JLabel ("People:",JLabel.CENTER);
        
        player1label.setForeground(Color.BLACK);
        player1label.setFont(new Font("Serif",Font.BOLD,20));
        player1label.setBorder(border);
        player1label.setPreferredSize(new Dimension(180,30));
        
        player2label.setForeground(Color.BLACK);
        player2label.setFont(new Font("Serif",Font.BOLD,20));
        player2label.setBorder(border);
        player2label.setPreferredSize(new Dimension(180,30));
        
        player3label.setForeground(Color.BLACK);
        player3label.setFont(new Font("Serif",Font.BOLD,20));
        player3label.setBorder(border);
        player3label.setPreferredSize(new Dimension(180,30));
        
        player4label.setForeground(Color.BLACK);
        player4label.setFont(new Font("Serif",Font.BOLD,20));
        player4label.setBorder(border);
        player4label.setPreferredSize(new Dimension(180,30));
        
        player5label.setForeground(Color.BLACK);
        player5label.setFont(new Font("Serif",Font.BOLD,20));
        player5label.setBorder(border);
        player5label.setPreferredSize(new Dimension(180,30));
        
        roundlabel.setForeground(Color.WHITE);
        roundlabel.setFont(new Font("Serif",Font.BOLD,20));
        roundlabel.setBorder(border2);
        roundlabel.setPreferredSize(new Dimension(300,30));
        
        itemslabel.setForeground(Color.BLACK);
        itemslabel.setFont(new Font("Serif",Font.BOLD,20));
        itemslabel.setPreferredSize(new Dimension(200,30));
        
        inventorylabel.setForeground(Color.BLACK);
        inventorylabel.setFont(new Font("Serif",Font.BOLD,20));
        inventorylabel.setPreferredSize(new Dimension(200,30));
        
        peopleslabel.setForeground(Color.BLACK);
        peopleslabel.setFont(new Font("Serif",Font.BOLD,20));
        peopleslabel.setPreferredSize(new Dimension(200,30));

        //JButtons
        ButtonsListener listener = new ButtonsListener( items, inventory, gameviewcontroller,graph);
        //pickup
		pickupButton.setForeground(Color.DARK_GRAY);
		pickupButton.setBackground(Color.GRAY);
        pickupButton.setOpaque(true);
        pickupButton.setFont(new Font("Serif",Font.BOLD,20));
        pickupButton.setPreferredSize(new Dimension(180,50));
        pickupButton.setActionCommand("pickup");
        pickupButton.addActionListener(listener);
        pickupButton.setFocusPainted(false);
        //drop
		dropButton.setForeground(Color.DARK_GRAY);
		dropButton.setBackground(Color.GRAY);
        dropButton.setOpaque(true);
        dropButton.setFont(new Font("Serif",Font.BOLD,20));
        dropButton.setPreferredSize(new Dimension(180,50));
        dropButton.setActionCommand("drop");
        dropButton.addActionListener(listener);
        dropButton.setFocusPainted(false);
        //gotoroom
		goToRoomButton.setForeground(Color.DARK_GRAY);
		goToRoomButton.setBackground(Color.GRAY);
        goToRoomButton.setOpaque(true);
        goToRoomButton.setFont(new Font("Serif",Font.BOLD,20));
        goToRoomButton.setPreferredSize(new Dimension(180,50));
        goToRoomButton.setActionCommand("gotoroom");
        goToRoomButton.addActionListener(listener);
        goToRoomButton.setFocusPainted(false);
        //use
		useButton.setForeground(Color.DARK_GRAY);
		useButton.setBackground(Color.GRAY);
        useButton.setOpaque(true);
        useButton.setFont(new Font("Serif",Font.BOLD,20));
        useButton.setPreferredSize(new Dimension(180,50));
        useButton.setActionCommand("use");
        useButton.addActionListener(listener);
        useButton.setFocusPainted(false);
        //activate
		activateButton.setForeground(Color.DARK_GRAY);
		activateButton.setBackground(Color.GRAY);
        activateButton.setOpaque(true);
        activateButton.setFont(new Font("Serif",Font.BOLD,20));
        activateButton.setPreferredSize(new Dimension(180,50));
        activateButton.setActionCommand("activate");
        activateButton.addActionListener(listener);
        activateButton.setFocusPainted(false);
        //skip
        skipButton.setForeground(Color.DARK_GRAY);
		skipButton.setBackground(Color.GRAY);
        skipButton.setOpaque(true);
        skipButton.setFont(new Font("Serif",Font.BOLD,20));
        skipButton.setPreferredSize(new Dimension(300,50));
        skipButton.setActionCommand("skip");
        skipButton.addActionListener(listener);
        skipButton.setFocusPainted(false);
        //JPanel
        buttonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonsPanel.setBackground(Color.GRAY);
        buttonsPanel.add(pickupButton);
        buttonsPanel.add(dropButton);
        buttonsPanel.add(goToRoomButton);
        buttonsPanel.add(useButton);
        buttonsPanel.add(activateButton);
        buttonsPanel.add(skipButton);
        
        labelPanel = new JPanel();
        labelPanel.setBackground(Color.GRAY);
        labelPanel.add(player1label);
        labelPanel.add(player2label);
        labelPanel.add(player3label);
        labelPanel.add(player4label);
        labelPanel.add(player5label);
        labelPanel.add(roundlabel);
        
        listPanel = new JPanel();
        listPanel.setBackground(Color.GRAY);
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.PAGE_AXIS));
        listPanel.setPreferredSize(new Dimension(300,690));
        listPanel.add(peopleslabel);
        listPanel.add(scrollPaneToPeoples);
        listPanel.add(itemslabel);
        listPanel.add(scrollPaneToItems); 
        listPanel.add(inventorylabel);
        listPanel.add(scrollPaneToInventory);
        
        mainPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        mainPanel.setBackground(Color.GRAY);
        mainPanel.setPreferredSize(new Dimension(1200,690));
        mainPanel.add(graph);
        mainPanel.add(listPanel);
        getContentPane().add(mainPanel, BorderLayout.CENTER);
        getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
        getContentPane().add(labelPanel, BorderLayout.NORTH);
        pack();
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON3) {
                    inventory.clearSelection();
                    items.clearSelection();
                } 
            }
        });
        update(startData);
        setVisible(true);
        gvc.start();
    }
    public void update(ViewData newData){
        //Round Label
        roundlabel.setText("Round: "+ newData.getRound());
        //Player Labels
        int i = 0;
        for(String s: newData.getNames()){
            switch(i){
                case 0:
                player1label.setText(s);
                if(s.equals(newData.getCurrentName())){
                    player1label.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
                    player1label.setForeground(Color.GREEN);
                }
                else{
                    player1label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                    player1label.setForeground(Color.BLACK);
                }
                player1label.repaint();
                i++;
                break;
                case 1:
                player2label.setText(s);
                if(s.equals(newData.getCurrentName())){
                    player2label.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
                    player2label.setForeground(Color.GREEN);
                }
                else{
                    player2label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                    player2label.setForeground(Color.BLACK);
                }
                i++;
                break;
                case 2:
                player3label.setText(s);
                if(s.equals(newData.getCurrentName())){
                    player3label.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
                    player3label.setForeground(Color.GREEN);
                }
                else{
                    player3label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                    player3label.setForeground(Color.BLACK);
                }
                i++;
                break;
                case 3:
                player4label.setText(s);
                if(s.equals(newData.getCurrentName())){
                    player4label.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
                    player4label.setForeground(Color.GREEN);
                }
                else{
                    player4label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                    player4label.setForeground(Color.BLACK);
                }
                i++;
                break;
                case 4:
                player5label.setText(s);
                if(s.equals(newData.getCurrentName())){
                    player5label.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
                    player5label.setForeground(Color.GREEN);
                }
                else{
                    player5label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                    player5label.setForeground(Color.BLACK);
                }
                i++;
                break;
            }
        }
        items.setCellRenderer(new ImageTextListCellRenderer());
        peoples.setCellRenderer(new ImageTextListCellRenderer());
        inventory.setCellRenderer(new ImageTextListCellRenderer());
        listOfItems.changeList(newData.getItems());
        listOfPeoples.changeList(newData.getPeoples());
        listOfInventory.changeList(newData.getInventory());
        //Graph
        graph.reDrawGraph(newData.getNodes(),newData.getCurrentNode(),newData.getConnections(),900,690);
        this.repaint();
    }
    public void warning(int i) {
    	switch(i) {
    	case 0:
    		JOptionPane.showMessageDialog(this, "Nem választottál ki semmilyen tárgyat!", "Figyelmeztetés", JOptionPane.WARNING_MESSAGE);
    		break;
    	case 1:
    		JOptionPane.showMessageDialog(this, "Nem választottál ki semmilyen tárgyat!", "Figyelmeztetés", JOptionPane.WARNING_MESSAGE);
    		break;
    	case 2:
    		JOptionPane.showMessageDialog(this, "Nem választottál ki semmilyen szobát!", "Figyelmeztetés", JOptionPane.WARNING_MESSAGE);
    		break;
    	case 3:
    		JOptionPane.showMessageDialog(this, "Nem választottál ki semmilyen tárgyat!", "Figyelmeztetés", JOptionPane.WARNING_MESSAGE);
    		break;
    	case 4:
    		JOptionPane.showMessageDialog(this, "Nem jól választottad ki a tranzisztorokat!", "Figyelmeztetés", JOptionPane.WARNING_MESSAGE);
    		break;
        case 5:
    		JOptionPane.showMessageDialog(this, "Vége a játéknak!", "Figyelmeztetés", JOptionPane.WARNING_MESSAGE);
    		break;
        case 6:
    		JOptionPane.showMessageDialog(this, "A tranzisztorok már párosítva vannak!", "Figyelmeztetés", JOptionPane.WARNING_MESSAGE);
    		break;
    	}
    }
    public class ButtonsListener implements ActionListener{
        JList<ImageTextItem> cbItems;
        JList<ImageTextItem> inventoryL;
        GameViewController gvc;
        Graph G;
        public ButtonsListener( JList<ImageTextItem> _cbI, JList<ImageTextItem> _iL, GameViewController _gvc, Graph g){
            gvc = _gvc;
            G=g;
            cbItems = _cbI;
            inventoryL = _iL;
        }
        public void actionPerformed(ActionEvent ae) {
            SwingUtilities.invokeLater(() -> {
            if (ae.getActionCommand().equals("pickup")) {
            	if(cbItems.getSelectedValue()!=null) 
            	{
                    synchronized (lock) {
                        gvc.PickUp((String)cbItems.getSelectedValue().getText());
            		    cbItems.clearSelection();
                        gvc.setButtonPressed();
            	        lock.notify();
                    }
            	}
            	else{
            		warning(0);
            	}
			}
            else if(ae.getActionCommand().equals("drop")){
            	if(inventoryL.getSelectedValue()!=null) 
            	{
                    synchronized (lock) {
                        gvc.Drop((String)inventoryL.getSelectedValue().getText());
            		    inventoryL.clearSelection();
                        gvc.setButtonPressed();
            	        lock.notify();
                    }
            	}
            	else{
            		warning(1);
            	}
            }
            else if(ae.getActionCommand().equals("gotoroom")){
            	if(G.getSelectedNodeName()!=null) 
            	{
                    synchronized (lock) {
                        gvc.goToRoom(G.getSelectedNodeName());
                        G.clearSelection();
                        gvc.setButtonPressed();
            	        lock.notify();
                    }
            	}
            	else{
            		warning(2);
            	}
            }
            else if(ae.getActionCommand().equals("use")){
            	if(inventoryL.getSelectedIndex()!=-1) 
            	{
                    synchronized (lock) {
                        gvc.Use(inventoryL.getSelectedIndex());
            		    inventoryL.clearSelection();
                        gvc.setButtonPressed();
            	        lock.notify();
                    }
            	}
            	else{
            		warning(3);
            	}
            }
            else if(ae.getActionCommand().equals("activate")){
            	List<ImageTextItem> temp = (List<ImageTextItem>) inventoryL.getSelectedValuesList();
            	if(temp.size()==2) 
            	{
                    synchronized (lock) {
                        gvc.Activate(temp.get(0).getText(), temp.get(1).getText());
            		    inventoryL.clearSelection();
                        gvc.setButtonPressed();
            	        lock.notify();
                    }
            	}
            	else{
            		warning(4);
            	}
            }
            else if(ae.getActionCommand().equals("skip")){
                synchronized (lock) {
                    gvc.setButtonPressed();
                    lock.notify();
                }
            }
        });
        }
    }
}