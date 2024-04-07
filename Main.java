import java.io.*;
import java.util.*;

/**
 * Represents a Classroom with specific characteristics like name, shape, dimensions, etc.
 * It utilizes the Builder pattern for object construction and provides methods to calculate
 * wall and floor areas based on the classroom's shape.
 */
class Classroom {
    private final String name;
    private final String shape;
    private final float width;
    private final float length;
    private final float diameter;
    private final float height;
    
    /**
     * Private constructor for Classroom.
     * @param builder The Builder object providing the necessary data to construct a Classroom instance.
     */
    private Classroom(Builder builder) {
        this.name = builder.name;
        this.shape = builder.shape;
        this.width = builder.width;
        this.length = builder.length;
        this.diameter = builder.diameter;
        this.height = builder.height;
    }

    /**
     * Provides a Builder instance to construct a Classroom.
     * @return A new Builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing Classroom instances.
     */
    public static class Builder {
        private String name;
        private String shape;
        private float width;
        private float length;
        private float diameter;
        private float height;

        /**
         * Sets the name of the Classroom.
         * @param name The name to set.
         * @return The Builder instance for chaining.
         */
        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets the shape of the Classroom.
         * @param shape The shape to set.
         * @return The Builder instance for chaining.
         */
        public Builder setShape(String shape) {
            this.shape = shape;
            return this;
        }

        /**
         * Sets the width of the Classroom.
         * @param width The width to set.
         * @return The Builder instance for chaining.
         */
        public Builder setWidth(float width) {
            this.width = width;
            return this;
        }

        /**
         * Sets the length of the Classroom.
         * @param length The length to set.
         * @return The Builder instance for chaining.
         */
        public Builder setLength(float length) {
            this.length = length;
            return this;
        }

        /**
         * Sets the diameter of the Classroom.
         * @param diameter The diameter to set.
         * @return The Builder instance for chaining.
         */
        public Builder setDiameter(float diameter) {
            this.diameter = diameter;
            return this;
        }

        /**
         * Sets the height of the Classroom.
         * @param height The height to set.
         * @return The Builder instance for chaining.
         */
        public Builder setHeight(float height) {
            this.height = height;
            return this;
        }

        /**
         * Builds and returns a Classroom instance with the set properties.
         * @return A new Classroom instance.
         */
        public Classroom build() {
            return new Classroom(this);
        }
    }

    /**
     * Calculates the wall area of the Classroom based on its shape and dimensions.
     * @return The calculated wall area.
     */
    public float calculateWallArea() {
        if (shape.equals("Circle")) {
            return (float) (Math.PI * diameter * height);
        } else {
            return 2 * height * (width + length);
        }
    }

    /**
     * Calculates the floor area of the Classroom based on its shape and dimensions.
     * @return The calculated floor area.
     */
    public float calculateFloorArea() {
        if (shape.equals("Circle")) {
            return (float) (Math.PI * Math.pow(diameter / 2, 2));
        } else {
            return width * length;
        }
    }

    /**
     * Returns the name of the Classroom.
     * @return The name of the Classroom.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the shape of the Classroom.
     * @return The shape of the Classroom.
     */
    public String getShape() {
        return shape;
    }
}

/**
 * Represents a Decoration with properties like name, type, unit cost, and area per unit.
 * It includes a Builder for creating instances and methods for calculating the required number of units and total cost.
 */
class Decoration {
    private String name;
    private String type;
    private float unitCost;
    private float areaOfUnit;

    /**
     * Private constructor for Decoration.
     * @param builder The Builder object providing the data to construct a Decoration instance.
     */
    private Decoration(Builder builder) {
        this.name = builder.name;
        this.type = builder.type;
        this.unitCost = builder.unitCost;
        this.areaOfUnit = builder.areaPerUnit;
    }

    /**
     * Provides a Builder instance to construct a Decoration.
     * @return A new Builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing Decoration instances.
     */
    public static class Builder {
        private String name;
        private String type;
        private float unitCost;
        private float areaPerUnit;

        /**
         * Sets the name of the Decoration.
         * @param name The name to set.
         * @return The Builder instance for chaining.
         */
        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets the type of the Decoration.
         * @param type The type to set.
         * @return The Builder instance for chaining.
         */
        public Builder setType(String type) {
            this.type = type;
            return this;
        }

        /**
         * Sets the unit cost of the Decoration.
         * @param unitCost The unit cost to set.
         * @return The Builder instance for chaining.
         */
        public Builder setUnitCost(float unitCost) {
            this.unitCost = unitCost;
            return this;
        }

        /**
         * Sets the area of one unit of the Decoration.
         * @param areaOfTile The area to set for one unit.
         * @return The Builder instance for chaining.
         */
        public Builder setAreaOfUnit(float areaPerUnit) {
            this.areaPerUnit = areaPerUnit;
            return this;
        }

        /**
         * Builds and returns a Decoration instance with the set properties.
         * @return A new Decoration instance.
         */
        public Decoration build() {
            return new Decoration(this);
        }
    }

    /**
     * Gets the name of the Decoration.
     * @return The name of the Decoration.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the type of the Decoration.
     * @return The type of the Decoration.
     */
    public String getType() {
        return type;
    }

    /**
     * Calculates and returns the number of units required to cover a given area.
     * @param area The area to cover with the Decoration.
     * @return The number of units required.
     */
    public int count(float area) {
        return (int) Math.ceil(area / areaOfUnit);
    }

    /**
     * Calculates the total cost to cover a given area with the Decoration.
     * @param area The area to cover.
     * @return The total cost.
     */
    public int calculateCostByArea(float area) {
        if (type.equals("Tile")) {
            return (int) (unitCost * count(area));
        } else {
            return (int) (Math.ceil(area / areaOfUnit * unitCost));
        }
    }
}

/**
 * Helper class for outputting the decoration information.
 */
class OutputHelper{
    private static final String decorationMessage = "Classroom %s used %d%s for walls and used %d%s for flooring, these costed %dTL.";
    private static final String totalCostMessage = "Total price is: %dTL.";

    private static String getDecorationSuffix(Decoration decoration){
        switch (decoration.getType()){
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
        System.out.println(String.format(decorationMessage, classroom.getName(), count1, suffix1, count2, suffix2, cost));
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
    Map<String, Classroom> classrooms = new HashMap<>();
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
        classrooms.put(name, Classroom.builder().setName(name).setShape("Circle").setDiameter(diameter).setHeight(height).build());
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
        classrooms.put(name, Classroom.builder().setName(name).setShape("Rectangle").setWidth(width).setLength(length).setHeight(height).build());
    }

    /**
     * Adds a tile decoration to the manager.
     *
     * @param name The name of the tile.
     * @param unitCost The cost per unit of the tile.
     * @param areaPerTile The area per tile.
     */
    public void addTileDecoration(String name, float unitCost, float areaPerUnit){
        decorations.put(name, Decoration.builder().setName(name).setType("Tile").setUnitCost(unitCost).setAreaOfUnit(areaPerUnit).build());
    }

    /**
     * Adds a paint decoration to the manager.
     *
     * @param name The name of the paint.
     * @param unitCost The cost per unit of the paint.
     */
    public void addPaintDecoration(String name, float unitCost){
        decorations.put(name, Decoration.builder().setName(name).setType("Paint").setUnitCost(unitCost).setAreaOfUnit(1).build());
    }

    /**
     * Adds a wallpaper decoration to the manager.
     *
     * @param name The name of the wallpaper.
     * @param unitCost The cost per unit of the wallpaper.
     */
    public void addWallpaperDecoration(String name, float unitCost){
        decorations.put(name, Decoration.builder().setName(name).setType("Wallpaper").setUnitCost(unitCost).setAreaOfUnit(1).build());
    }

    /**
     * Decorates the classroom with the given decorations.
     *
     * @param classroomName The name of the classroom to decorate.
     * @param decoration1Name The name of the first decoration to use.
     * @param decoration2Name The name of the second decoration to use.
     */
    public void decorate(String classroomName, String decoration1Name, String decoration2Name){
        Classroom classroom = classrooms.get(classroomName);
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