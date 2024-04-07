import java.io.*;
import java.util.*;

abstract class Classroom{
    protected String name;
    protected String shape;

    public Classroom(String name, String shape){
        this.name = name;
        this.shape = shape;
    }

    public abstract float calculateWallArea();
    public abstract float calculateFloorArea();
}

class CircularClassroom extends Classroom{
    protected float radius;
    protected float height;

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

class RectangularClassroom extends Classroom{
    protected float width;
    protected float length;
    protected float height;

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

abstract class Decoration{
    protected String name;
    protected String type;
    protected float unitCost;

    public Decoration(String name, String type, float unitCost){
        this.name = name;
        this.type = type;
        this.unitCost = unitCost;
    }

    public abstract float calculateCostByArea(float area);
}

class Paint extends Decoration{
    public Paint(String name, float unitCost){
        super(name, "Paint", unitCost);
    }

    @Override
    public float calculateCostByArea(float area){
        return (float) Math.ceil(area * unitCost);
    }
}

class Wallpaper extends Decoration{
    public Wallpaper(String name, float unitCost){
        super(name, "Wallpaper", unitCost);
        this.unitCost = unitCost;
    }

    @Override
    public float calculateCostByArea(float area){
        return (float) Math.ceil(area * unitCost);
    }
}

class Tile extends Decoration{
    protected float areaPerTile;

    public Tile(String name, float unitCost, float areaPerTile){
        super(name, "Tile", unitCost);
        this.areaPerTile = areaPerTile;
    }

    public float countTiles(float area){
        return (float) Math.ceil(area / areaPerTile);
    }

    @Override
    public float calculateCostByArea(float area){
        return (float) Math.ceil(countTiles(area) * unitCost);
    }
}    

class OutputHelper{
    private static final String decorationMessage = "Classroom %s used %d%s for walls and used %d %s for flooring, these costed %dTL.";
    private static final String totalCostMessage = "Total price is: %dTL.";

    private static String getDecorationSuffix(Decoration decoration){
        switch (decoration.type){
            case "Paint":
                return "m2 of Paint";
            case "Wallpaper":
                return "m2 of Wallpaper";
            case "Tile":
                return "Tiles";
            default:
                return "";
        }
    }

    public static void printDecoration(Decoration decoration1, int count1 ,Decoration decoration2, int count2, int cost){
        String part1, part2;
        part1 = getDecorationSuffix(decoration1);
        part2 = getDecorationSuffix(decoration2);
        System.out.println(String.format(decorationMessage, part1, count1, part1, count2, part2, cost));
    }

    public static void printTotalCost(int cost){
        System.out.println(String.format(totalCostMessage, cost));
    }
}

class DecorationManager{
    Map<String, Classroom> classroms = new HashMap<>();
    Map<String, Decoration> decorations = new HashMap<>();

    public void addCircularClassroom(String name, float diameter, float height){
        classroms.put(name, new CircularClassroom(name, diameter, height));
    }

    public void addRectangularClassroom(String name, float width, float length, float height){
        classroms.put(name, new RectangularClassroom(name, width, length, height));
    }

    public void addTileDecoration(String name, float unitCost, float areaPerTile){
        decorations.put(name, new Tile(name, unitCost, areaPerTile));
    }

    public void addPaintDecoration(String name, float unitCost){
        decorations.put(name, new Paint(name, unitCost));
    }

    public void addWallpaperDecoration(String name, float unitCost){
        decorations.put(name, new Wallpaper(name, unitCost));
    }
}


public class Main{
    public static void main(String[] args) throws IOException{
        // original output stream
        PrintStream originalStream = System.out;
        System.setOut(new PrintStream(new FileOutputStream(args[2])));

        DecorationManager decorationManager = new DecorationManager();

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

        br = new BufferedReader(new FileReader(args[1]));
        line = null;
        while((line = br.readLine()) != null){
            String[] tokens = line.split("\t");
            if(tokens[0].equals("DECORATE")){
                float area = decorationManager.classroms.get(tokens[1]).calculateWallArea();
                float cost = decorationManager.decorations.get(tokens[2]).calculateCostByArea(area);
                System.out.println(tokens[1] + " " + tokens[2] + " " + (int) cost);
            }
        }
        br.close();

        // Reset output stream
        System.setOut(originalStream);
    }   
}

