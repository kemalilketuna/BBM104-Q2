import java.io.*;
import java.util.*;

/**
 * Abstract class representing the general structure and functionality of a classroom.
 */
abstract class Classroom{
    protected String name;
    protected String shape;

    /**
     * Constructor for Classroom.
     *
     * @param name The name of the classroom.
     * @param shape The shape of the classroom.
     */
    public Classroom(String name, String shape){
        this.name = name;
        this.shape = shape;
    }

    /**
     * Calculates the wall area of the classroom.

     * @return The wall area as a float.
     */
    public abstract float calculateWallArea();

    /**
     * Calculates the floor area of the classroom.
     *
     * @return The floor area as a float.
     */
    public abstract float calculateFloorArea();
}

/**
 * Represents a circular classroom that extends the Classroom class.
 */
class CircularClassroom extends Classroom{
    protected float radius;
    protected float height;

    /**
     * Constructor for CircularClassroom.
     *
     * @param name The name of the classroom.
     * @param diameter The diameter of the classroom.
     * @param height The height of the classroom.
     */
    public CircularClassroom(String name, float diameter, float height){
        super(name, "Circular");
        this.radius = diameter / 2;
        this.height = height;
    }

    @Override
    public float calculateWallArea(){
        return 2 * (float) Math.PI * radius * height;
    }

    @Override
    public float calculateFloorArea(){
        return (float) Math.PI * radius * radius;
    }
}

/**
 * Represents a rectangular classroom that extends the Classroom class.
 */
class RectangularClassroom extends Classroom{
    protected float width;
    protected float length;
    protected float height;

    /**
     * Constructor for RectangularClassroom.
     *
     * @param name The name of the classroom.
     * @param width The width of the classroom.
     * @param length The length of the classroom.
     * @param height The height of the classroom.
     */
    public RectangularClassroom(String name, float width, float length, float height){
        super(name, "Rectangular");
        this.width = width;
        this.length = length;
        this.height = height;
    }

    @Override
    public float calculateWallArea(){
        return 2 * (width + length) * height;
    }

    @Override
    public float calculateFloorArea(){
        return width * length;
    }
}

/**
 * Abstract class representing decorations in a classroom.
 */
abstract class Decoration{
    protected String name;
    protected String type;
    protected float unitCost;

    /**
     * Constructor for Decoration.
     *
     * @param name The name of the decoration.
     * @param type The type of the decoration.
     * @param unitCost The cost per unit of the decoration.
     */
    public Decoration(String name, String type, float unitCost){
        this.name = name;
        this.type = type;
        this.unitCost = unitCost;
    }

    /**
     * Calculates the cost of the decoration based on the area.
     *
     * @param area The area to calculate the cost for.
     * @return The cost as a float.
     */
    public abstract float calculateCostByArea(float area);

    /**
     * Counts the number of decorations needed based on the area.
     *
     * @param area The area to calculate the count for.
     * @return The count as an integer.
     */
    public abstract int count(float area);
}

/**
 * Represents a paint decoration, extending the Decoration class.
*/
class Paint extends Decoration{
    /**
     * Constructor for Paint.
     *
     * @param name The name of the paint.
     * @param unitCost The cost per unit of the paint.
     */
    public Paint(String name, float unitCost){
        super(name, "Paint", unitCost);
    }
    
    @Override
    public float calculateCostByArea(float area){
        return (float) Math.ceil(area * unitCost);
    }

    @Override
    public int count(float area){
        return (int) Math.ceil(area);
    }
}

/**
 * Represents a wallpaper decoration, extending the Decoration class.
 */
class Wallpaper extends Decoration{
    /**
     * Constructor for Wallpaper.
     *
     * @param name The name of the wallpaper.
     * @param unitCost The cost per unit of the wallpaper.
     */
    public Wallpaper(String name, float unitCost){
        super(name, "Wallpaper", unitCost);
        this.unitCost = unitCost;
    }

    @Override
    public float calculateCostByArea(float area){
        return (float) Math.ceil(area * unitCost);
    }

    @Override
    public int count(float area){
        return (int) Math.ceil(area);
    }
}

/**
 * Represents a tile decoration, extending the Decoration class.
 */
class Tile extends Decoration{
    protected float areaPerTile;

    /**
     * Constructor for Tile.
     *
     * @param name The name of the tile.
     * @param unitCost The cost per unit of the tile.
     * @param areaPerTile The area per tile.
     */
    public Tile(String name, float unitCost, float areaPerTile){
        super(name, "Tile", unitCost);
        this.areaPerTile = areaPerTile;
    }

    /**
     * Counts the number of tiles needed based on the area.
     *
     * @param area The area to calculate the count for.
     * @return The count as a float.
     */
    public float countTiles(float area){
        return (float) Math.ceil(area / areaPerTile);
    }

    @Override
    public int count(float area){
        return (int) countTiles(area);
    }

    @Override
    public float calculateCostByArea(float area){
        return (float) Math.ceil(countTiles(area) * unitCost);
    }
}

/**
 * Helper class for outputting the decoration information.
 */
class OutputHelper{
    private static final String decorationMessage = "Classroom %s used %d%s for walls and used %d%s for flooring, these costed %dTL.";
    private static final String totalCostMessage = "Total price is: %dTL.";

    private static String getDecorationSuffix(Decoration decoration){
        switch (decoration.type){
            case "Paint":
                return "m2 of Paint";
            case "Wallpaper":
                return "m2 of Wallpaper";
            case "Tile":
                return " Tiles";
            default:
                return "";
        }
    }

    /**
     * Prints the decoration information.
     *
     * @param classroom The classroom to decorate.
     * @param decoration1 The first decoration to use.
     * @param count1 The count of the first decoration.
     * @param decoration2 The second decoration to use.
     * @param count2 The count of the second decoration.
     * @param cost The total cost of the decorations.
     */
    public static void printDecoration(Classroom classroom, Decoration decoration1, int count1 ,Decoration decoration2, int count2, int cost){
        String suffix1, suffix2;
        suffix1 = getDecorationSuffix(decoration1);
        suffix2 = getDecorationSuffix(decoration2);
        System.out.println(String.format(decorationMessage, classroom.name, count1, suffix1, count2, suffix2, cost));
    }

    /**
     * Prints the total cost of the decorations.
     *
     * @param cost The total cost of the decorations.
     */
    public static void printTotalCost(int cost){
        System.out.print(String.format(totalCostMessage, cost));
    }
}

/**
 * Manages the decorations and classrooms.
 */
class DecorationManager{
    Map<String, Classroom> classroms = new HashMap<>();
    Map<String, Decoration> decorations = new HashMap<>();
    private int totalCost = 0;

    /**
     * Adds a circular classroom to the manager.
     *
     * @param name The name of the classroom.
     * @param diameter The diameter of the classroom.
     * @param height The height of the classroom.
     */
    public void addCircularClassroom(String name, float diameter, float height){
        classroms.put(name, new CircularClassroom(name, diameter, height));
    }

    /**
     * Adds a rectangular classroom to the manager.
     *
     * @param name The name of the classroom.
     * @param width The width of the classroom.
     * @param length The length of the classroom.
     * @param height The height of the classroom.
     */
    public void addRectangularClassroom(String name, float width, float length, float height){
        classroms.put(name, new RectangularClassroom(name, width, length, height));
    }

    /**
     * Adds a tile decoration to the manager.
     *
     * @param name The name of the tile.
     * @param unitCost The cost per unit of the tile.
     * @param areaPerTile The area per tile.
     */
    public void addTileDecoration(String name, float unitCost, float areaPerTile){
        decorations.put(name, new Tile(name, unitCost, areaPerTile));
    }

    /**
     * Adds a paint decoration to the manager.
     *
     * @param name The name of the paint.
     * @param unitCost The cost per unit of the paint.
     */
    public void addPaintDecoration(String name, float unitCost){
        decorations.put(name, new Paint(name, unitCost));
    }

    /**
     * Adds a wallpaper decoration to the manager.
     *
     * @param name The name of the wallpaper.
     * @param unitCost The cost per unit of the wallpaper.
     */
    public void addWallpaperDecoration(String name, float unitCost){
        decorations.put(name, new Wallpaper(name, unitCost));
    }

    /**
     * Decorates the classroom with the given decorations.
     *
     * @param classroomName The name of the classroom to decorate.
     * @param decoration1Name The name of the first decoration to use.
     * @param decoration2Name The name of the second decoration to use.
     */
    public void decorate(String classroomName, String decoration1Name, String decoration2Name){
        Classroom classroom = classroms.get(classroomName);
        Decoration decoration1 = decorations.get(decoration1Name);
        Decoration decoration2 = decorations.get(decoration2Name);

        float wallArea = classroom.calculateWallArea();
        float floorArea = classroom.calculateFloorArea();

        int count1 = decoration1.count(wallArea);
        int count2 = decoration2.count(floorArea);

        int cost1 = (int) decoration1.calculateCostByArea(wallArea);
        int cost2 = (int) decoration2.calculateCostByArea(floorArea);

        int cost = cost1 + cost2;
        totalCost += cost;
        OutputHelper.printDecoration(classroom, decoration1, count1, decoration2, count2, cost);
    }

    /**
     * Prints the total cost of the decorations.
     */
    public void printTotalCost(){
        OutputHelper.printTotalCost(totalCost);
    }
}


public class Main{
    public static void main(String[] args) throws IOException{
        // original output stream
        PrintStream originalStream = System.out;
        System.setOut(new PrintStream(new FileOutputStream(args[2])));

        // Create a decoration manager
        DecorationManager decorationManager = new DecorationManager();

        // Read the input file for decoration and classroom items
        BufferedReader br = new BufferedReader(new FileReader(args[0]));
        String line;
        while((line = br.readLine()) != null){
            String[] tokens = line.split("\t");
            if(tokens[0].equals("CLASSROOM")){
                if(tokens[2].equals("Circle")){
                    decorationManager.addCircularClassroom(tokens[1], Float.parseFloat(tokens[3]), Float.parseFloat(tokens[5]));
                }else{
                    decorationManager.addRectangularClassroom(tokens[1], Float.parseFloat(tokens[3]), Float.parseFloat(tokens[4]), Float.parseFloat(tokens[5]));
                }
            }else if(tokens[0].equals("DECORATION")){
                if(tokens[2].equals("Tile")){
                    decorationManager.addTileDecoration(tokens[1], Float.parseFloat(tokens[3]), Float.parseFloat(tokens[4]));
                }else if(tokens[2].equals("Paint")){
                    decorationManager.addPaintDecoration(tokens[1], Float.parseFloat(tokens[3]));
                }else if(tokens[2].equals("Wallpaper")){
                    decorationManager.addWallpaperDecoration(tokens[1], Float.parseFloat(tokens[3]));
                }
            }
        }
        br.close();

        // Read the input file for decoration of classrooms
        br = new BufferedReader(new FileReader(args[1]));
        line = null;
        while((line = br.readLine()) != null){
            String[] tokens = line.split("\t");
            decorationManager.decorate(tokens[0], tokens[1], tokens[2]);
        }
        decorationManager.printTotalCost();
        br.close();

        // Reset output stream
        System.setOut(originalStream);
    }   
}