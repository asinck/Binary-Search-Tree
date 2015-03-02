//Adam Sinck
//This program will implement a binary search tree.
//This program was written for my CSC 205 class. The only changes to this file
//have been to the comments.

import java.util.*;
import java.io.*;

public class program7 {
    //scanner for input
    public static final Scanner keyboard = new Scanner( System.in );
    
    //the main method of the program
    public static void main( String[] args ) {
        printWelcome();
        boolean proceed = true;
        do {
            printMenu();
            output("Please select an option. (1..4)  ");
            int option = keyboard.nextInt();
            outputln("");
            
            switch (option) {
                case 1://let the user test a table of MLBPlayers
                    testTables.main(null);
                    break;
                case 2://read a file of MLBPlayers into the binary search tree
                    readFile();
                    break;
                case 3://generate a lot of data to test the binary search tree
                    testManyTables();
                    break;
	        case 4://exit the program
                    outputln("Exiting program.");
                    proceed = false;
                    break;
	    default://if all else fails
                    outputln("Please enter a valid option number.");
                    break;
            }
        } while (proceed);
    }
    //this will will generate a lot of data to test the binary search tree and 
    //help evaluate the efficiency of the table.
    public static void testManyTables () {
        for (int power = 4; power <= 15; power++) {
            int size = (int) Math.pow(2, power);
            Table intTable = new Table();
            Random generator = new Random();
            int maxHeight = 0;
            int averageOfAverages = 0;
            for (int tenTables = 0; tenTables < 10; tenTables++) {
                for (int fill = 0; fill < size; fill++) {
                    int nextInt = generator.nextInt();
                    intTable.insert(new KeyComparableNumber(nextInt));
                }
                if (intTable.getHeight() > maxHeight)
                    maxHeight = intTable.getHeight();
                averageOfAverages += intTable.getAveLevel();
            }
            averageOfAverages /= 10;
            outputln("Worst case of size " + size + ": " + maxHeight);
            outputln("Average case of size " + size + ": " + averageOfAverages);
        }
        
    }
    //this will open a file of players, and put them in the binary search tree
    public static void readFile () {
        Table fileTable = new Table();
        output("What is the name of the file? ");
        String file = keyboard.next();
        try {
            Scanner dataFile = new Scanner (new File(file));
            int jersey;
            do {
                jersey = dataFile.nextInt();
                if (jersey != -1) {
                    String team = dataFile.next();
                    String name = dataFile.next();
                    double ave = dataFile.nextDouble();
                    fileTable.insert(new MLBPlayer(jersey, team, name, ave));
                }
            } while (jersey != -1);
            dataFile.close();
            System.out.println(fileTable);
        }
        catch (IOException e) {
            System.out.println("Error: " + e);
            outputln("Could not open file. Please make sure that");
            outputln("the file name was spelled correctly.\n");
        }
        
    }
    //this will output the welcome
    public static void printWelcome () {
        outputln("Welcome to this program!");
        outputln("There are four options available,");
        outputln("as seen in the Main Menu below.");
    }
    //this will output the main menu
    public static void printMenu () {
        outputln("Main Menu");
        outputln("1 -- Test a table");
        outputln("2 -- sort a file");
        outputln("3 -- test many tables");
        outputln("4 -- Exit program");
        
    }
    //I got tired of typing System.out.println
    public static void output (String s) {
        System.out.print(s);
    }
    public static void outputln (String s) {
        System.out.println(s);
    }
}

//this is the class that lets the user test a binary search tree of MLB players
class testTables {
    public static final Scanner keyboard = new Scanner( System.in );
    
    public static void main( String[] args ) {
        printWelcome();
        boolean proceed = true;
        Table MLBPlayerTable = new Table();
        do {
            printMenu();
            output("Please select an option. (1..10)  ");
            int option = keyboard.nextInt();
            outputln("");
            //the following are general purpose variables
            int jersey;
            String team;
            String name;
            double ave;
            MLBPlayerKey key;
            
            switch (option) {
                case 1://initialize - initialize an empty table
                    MLBPlayerTable = new Table();
                    outputln("The list has been emptied.");
                    break;
                case 2://insert - insert an item in the table, given the item
                    output("What is the jersey number?  ");
                    jersey = keyboard.nextInt();
                    output("What is the team name?  ");
                    team = keyboard.next();
                    output("What is the player's name?  ");
                    name = keyboard.next();
                    output("what is the player's batting average?  ");
                    ave = keyboard.nextDouble();
                    MLBPlayer insert = new MLBPlayer(jersey, team, name, ave);
                    MLBPlayerTable.insert(insert);
                    break;
                case 3://delete - delete an item in the table, given the key
                    output("What is the jersey number?  ");
                    jersey = keyboard.nextInt();
                    output("What is the team name?  ");
                    team = keyboard.next();
                    key = new MLBPlayerKey(jersey, team);
                    MLBPlayerTable.delete(key);
                    outputln("Number " + jersey + "of team " + team +
                             " has been deleted.");
                    break;
                case 4://search - given a key, return the associated data item
                    output("What is the jersey number?  ");
                    jersey = keyboard.nextInt();
                    output("What is the team name?  ");
                    team = keyboard.next();
                    key = new MLBPlayerKey(jersey, team);
                    MLBPlayer test = (MLBPlayer) MLBPlayerTable.search(key);
                    if (test != null)
                        System.out.println(test);
                    else
                        outputln("Search key not found.\n");
                    break;
                case 5://getHeight - return the height of the tree
                    System.out.println("Height: " + MLBPlayerTable.getHeight());
                    break;
                case 6://getSize - return the number of nodes in the tree
                    System.out.println("Size: " + MLBPlayerTable.getSize());
                    break;
                case 7://getAverageLevel - return the average level of the nodes
                       //in the tree
                    System.out.println("Average level:  " +
                                       MLBPlayerTable.getAveLevel());
                    
                    break;
                case 8://showTree - display the contents of the tree in 
                       //tree-like fashion
                    System.out.println(MLBPlayerTable.showTree());
                    break;
                case 9://toString - display the entire contents of the table in 
                       //order by their keys
                    System.out.println(MLBPlayerTable);
                    break;
                case 10:
                    outputln("Exiting program.");
                    proceed = false;
                    break;
                default:
                    outputln("Please enter a valid option number.");
                    break;    //####    visual flag for end
            }                 //####    of do..while loop :)
        } while (proceed);    //
    }                         //
    //print a welcome to this part of the program
    public static void printWelcome () {
        outputln("For testing the program, there are");
        outputln("ten options available, as seen in");
        outputln("the Menu below.");
    }
    //print the menu for the user
    public static void printMenu () {
        output("Press any key to continue.  ");
        keyboard.next();
        outputln("Main Menu -- testing a table");
        outputln("1  -- initialize - " +
                 "replace the current table with an empty one");
        outputln("2  -- insert - " +
                 "insert an item in the table, given the item");
        outputln("3  -- delete - " +
                 "delete an item in the table, given the key");
        outputln("4  -- search - " +
                 "given a key, return the associated data item");
        outputln("5  -- show height - " +
                 "output the height of the tree");
        outputln("6  -- show size - " +
                 "output the number of nodes in the tree");
        outputln("7  -- show average level - " +
                 "output the average level of the nodes\n" +
                 "      in the tree");
        outputln("8  -- output as tree - " +
                 "display the contents of the tree in \n" +
                 "      tree-like fashion");
        outputln("9  -- output - " +
                 "display the entire contents of the table in order\n" +
                 "      by their keys");
        outputln("10 -- Exit testing program");
    }
    //I got tired of typing System.out.println
    public static void output (String s) {
        System.out.print(s);
    }
    public static void outputln (String s) {
        System.out.println(s);
    }
}

class Table {
    //the data member
    private TreeNode _root;
    //initialize the root
    public Table () {
        _root = null;
    }
    //insert an item in the table
    //this will set the root to the item if the root is null, or call 
    //a helper function otherwise
    public void insert (KeyComparable item) {
        if (_root == null) {
            _root = new TreeNode(item);
        }
        else
            insert(_root, item);
    }
    //the helper function for insert
    //it will insert an item into the table
    private void insert (TreeNode currentRoot, KeyComparable item) {
        KeyComparable compare = null;
        if (currentRoot._data instanceof KeyComparable) {
            compare = (KeyComparable) currentRoot._data;
        }
        int comp = item.keyCompareTo(compare);
        if (comp < 0) {
            if (currentRoot.left == null) {
                currentRoot.left = new TreeNode(item);
            }
            else {
                insert(currentRoot.left, item);
            }
        }
        else if (comp > 0) {
            if (currentRoot.right == null) {
                currentRoot.right = new TreeNode(item);
            }
            else {
                insert(currentRoot.right, item);
            }
        }
    }
    //this will delete an item from the table by calling the helper function
    public void delete (KeyComparable key) {
        _root = delete(key, _root);
    }
    //this will delete an item from the table
    private TreeNode delete (KeyComparable key, TreeNode currentRoot) {
        if (currentRoot == null)//not found
            return null;
        KeyComparable compare = (KeyComparable) currentRoot._data;
        int comp = key.keyCompareTo(compare);
        if (comp < 0) {//key is smaller than the current node
            currentRoot.left = delete(key, currentRoot.left);
            return currentRoot;
        }
        else if (comp > 0) {//key is larger than the current node
            currentRoot.right = delete(key, currentRoot.right);
            return currentRoot;
        }
        else {//key is equal to the current node
            if (currentRoot.left == null && currentRoot.right == null)
                return null;//no children
            else if (currentRoot.left == null)//has one child, on the right
                return currentRoot.right;
            else if (currentRoot.right == null)//has one child, on the left
                return currentRoot.left;
            else {//has two children
                TreeNode findEnd = currentRoot.right;//finding the minimum of 
                while (findEnd.left != null) {       //the right subtree as a
                    findEnd = findEnd.left;          //replacement
                }
                delete((KeyComparable) findEnd._data);
                currentRoot._data = findEnd._data;
                return currentRoot;
            }
        }
    }
    //this will look for an item by its key and return it, using a helper function
    public Object search (KeyComparable key) {
        return search(_root, key);
    }
    //this is the helper function for search
    private Object search (TreeNode currentRoot, KeyComparable key) {
        if (currentRoot != null && currentRoot._data instanceof KeyComparable) {
            KeyComparable compare = (KeyComparable) currentRoot._data;
            int comp = key.keyCompareTo(compare);
            if (comp == 0)
                return currentRoot._data;
            else if (comp < 0)
                return search(currentRoot.left, key);
            else
                return search(currentRoot.right, key);
        }
        return null;
    }
    //get the height of the tree with a helper function
    public int getHeight () {
        if (_root == null)
            return 0;
        return getHeight(_root, 1);
    }
    //this is the helper function for getHeight
    private int getHeight (TreeNode currentRoot, int currentMax) {
        if (currentRoot == null)
            return 0;
        int max = 0;
        int left = getHeight(currentRoot.left, max);
        int right = getHeight(currentRoot.right, max);
        if (left > right)
            max = left;
        else
            max = right;
        return 1 + max;
    }
    //get the size of the tree with a helper function
    public int getSize () {
        return getSize(_root);
    }
    //this is the helper function for getHeight
    private int getSize (TreeNode currentRoot) {
        if (currentRoot == null)
            return 0;
        else
            return 1 + getSize(currentRoot.left) + getSize(currentRoot.right);
    }
    //this will return the average level of nodes in the tree, with a helper function
    public double getAveLevel () {
        double ave = 0;
        if (_root != null) {
            int levels = getSumOfLevels(_root, 1);
            int size = getSize();
            ave = (levels * 1.0) / (size * 1.0);
        }
        return ave;
    }
    //this is the helper function for getAveLevel
    private int getSumOfLevels (TreeNode currentRoot, int depth) {
        if (currentRoot == null)
            return 0;
        int left = getSumOfLevels(currentRoot.left, depth + 1);
        int right = getSumOfLevels(currentRoot.right, depth + 1);
        return depth + left + right;
    }
    //this will show the tree, with a helper function
    public String showTree () {
        if (_root == null)
            return "No Items";
        return showTree(_root, 1);
    }
    //this will show the tree sideways
    private String showTree(TreeNode currentRoot, int depth) {
        if (currentRoot == null)
            return "";
        int spaces = 8;
        spaces *= (depth - 1);
        String s = "";
        s += showTree(currentRoot.right, depth + 1);
        KeyComparable compare = (KeyComparable) currentRoot._data;
        for (int i = 0; i < spaces; i++)
            s += ' ';
        s += compare.toStringKey() + "\n";
        s += showTree(currentRoot.left, depth + 1);
        return s;
    }
    //toString for the tree
    public String toString () {
        if (_root == null)
            return "No Items";
        return toString(_root);
    }
    //a helper function for toString
    private String toString (TreeNode currentRoot) {
        if (currentRoot == null)
            return "";
        String s = toString(currentRoot.left);
        s += currentRoot._data + "\n";
        s += toString(currentRoot.right);
        return s;
    }
}
//the object that the tree is working with
class MLBPlayerKey implements KeyComparable {
    //two data members
    private int _jersey;
    private String _team;
    
    //initialize an instance of the MLBPlayerKey
    public MLBPlayerKey (int jersey, String team) {
        _jersey = jersey;
        _team = team;
    }
    //accessor methods
    public int getJersey () {
        return _jersey;
    }
    public String getTeam () {
        return _team;
    }
    //a method for comparing the MLBPlayerKeys
    public int keyCompareTo (KeyComparable other) {
        MLBPlayer _other = null;
        if (other instanceof MLBPlayer) {
            _other = (MLBPlayer) other;
            if (!_team.equals(_other.getTeam())) {
                return _team.compareTo(_other.getTeam());
            }
            else {
                return _jersey - _other.getJersey();
            }
        }
        return -42;
    }
    public String toStringKey () {
        String s = _team.substring(0, 3);
        return s + " " + _jersey;
    }
    //toString
    public String toString () {
        return "Player number " + _jersey + " of team " + _team + ":\n";
    }
}
//more on MLBPlayers
class MLBPlayer extends MLBPlayerKey implements KeyComparable {
    private String _name;
    private double _battingAverage;
    //initialize an instance of the MLBPlayer
    public MLBPlayer (int jersey, String team, String name,
                      double battingAverage) {
        super(jersey, team);
        _name = name;
        _battingAverage = battingAverage;
    }
    //accessor methods
    public String getName () {
        return _name;
    }
    public double getBattingAverage () {
        return _battingAverage;
    }
    //toString
    public String toString () {
        return super.toString() + _name + " with a batting average of " +
               _battingAverage + "\n";
    }
}
//a class for comparing players
class KeyComparableNumber implements KeyComparable {
    private int _number;
    //initialize a Keycomparablenumber instance
    public KeyComparableNumber (int number) {
        _number = number;
    }
    //accessor method
    public int getNumber () {
        return _number;
    }
    //for comparing two instances of KeyComparables
    public int keyCompareTo(KeyComparable other) {
        int compare = 0;
        if (other instanceof KeyComparableNumber) {
            KeyComparableNumber _other = (KeyComparableNumber) other;
            compare = (int) _other.getNumber();
        }
        return _number - compare;
    }
    //toString
    public String toString() {
        String s = "";
        s += _number;
        return s;
    }
    //more toString
    public String toStringKey() {
        String s = "";
        s += _number;
        return s;
    }
}
//a tree node for the binary search tree
class TreeNode {
    //data members
    public Object _data;
    public TreeNode left;
    public TreeNode right;
    //initialize a node
    public TreeNode (Object data) {
        _data = data;
        left = null;
        right = null;
    }
}
//an interface
interface KeyComparable {
    int keyCompareTo(KeyComparable other);
    String toStringKey();
}
