import java.io.*;
import java.util.*;

// Abstract class for Classroom
abstract class Classroom {
    protected String name;
    protected String shape;
    protected float width;
    protected float length;
    protected float height;

    public Classroom(String name, String shape, float width, float length, float height) {
        this.name = name;
        this.shape = shape;
        this.width = width;
        this.length = length;
        this.height = height;
    }

    public abstract float calculateWallArea();
    public abstract float calculateFloorArea();
}

// Concrete class for Circular Classroom
class CircularClassroom extends Classroom {
    public CircularClassroom(String name, float radius, float height) {
        this.name = name;
        this.shape = "Circular";
        this.width = radius;
        this.length = radius;
        this.height = height;
    }

    @Override
    public float calculateWallArea() {
        // Formula: 2 * pi * radius * height
        return 2 * (float) Math.PI * width * height;
    }

    @Override
    public float calculateFloorArea() {
        // Formula: pi * radius^2
        return (float) Math.PI * width * width;
    }
}

// Concrete class for Rectangular Classroom
class RectangularClassroom extends Classroom {
    public RectangularClassroom(String name, float width, float length, float height) {
        super(name, "Rectangular", width, length, height);
    }

    @Override
    public float calculateWallArea() {
        // Formula: 2 * (width + length) * height
        return 2 * (width + length) * height;
    }

    @Override
    public float calculateFloorArea() {
        // Formula: width * length
        return width * length;
    }
}

// Abstract class for Decoration
abstract class Decoration {
    protected String name;
    protected String type;
    protected float price;

    public Decoration(String name, String type, float price) {
        this.name = name;
        this.type = type;
        this.price = price;
    }
    
    public abstract float calculateCost(float area);
}

// Concrete class for Tile Decoration
class TileDecoration extends Decoration {
    private float areaPerTile;

    public TileDecoration(String name, float price, float areaPerTile) {
        super(name, "Tile", price);
        this.areaPerTile = areaPerTile;
    }

    public float calculateCost(float area) {
        float numberOfTiles = area / areaPerTile;
        return numberOfTiles * price;
    }
}

// Concrete class for Paint and Wallpaper Decoration
class PaintWallpaperDecoration extends Decoration {
    public PaintWallpaperDecoration(String name, float price) {
        super(name, "Wallpaper", price);
    }

    public float calculateCost(float area) {
        return area * price;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Map<String, Classroom> classrooms = new HashMap<>();
        Map<String, Decoration> decorations = new HashMap<>();
        
        // Read items.txt and create classroom and decoration objects
        BufferedReader br = new BufferedReader(new FileReader(args[0]));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split("\t");
            if (parts[0].equals("CLASSROOM")) {
                Classroom classroom = parts[2].equals("Circle") ?
                    new CircularClassroom(parts[1], float.parsefloat(parts[3]), float.parsefloat(parts[5])) :
                    new RectangularClassroom(parts[1], float.parsefloat(parts[3]), float.parsefloat(parts[4]), float.parsefloat(parts[5]));
                classrooms.put(parts[1], classroom);
            } else if (parts[0].equals("DECORATION")) {
                Decoration decoration = parts[2].equals("Tile") ?
                    new TileDecoration(parts[1], float.parsefloat(parts[3]), float.parsefloat(parts[4])) :
                    new PaintWallpaperDecoration(parts[1], parts[2], float.parsefloat(parts[3]));
                decorations.put(parts[1], decoration);
            }
        }

        // Read decoration.txt and calculate costs
        br = new BufferedReader(new FileReader(args[1]));
        line = null;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split("\t");
            Classroom classroom = classrooms.get(parts[0]);
            Decoration wallDecoration = decorations.get(parts[1]);
            Decoration floorDecoration = decorations.get(parts[2]);

            float wallArea = classroom.calculateWallArea();
            float floorArea = classroom.calculateFloorArea();

            float wallCost = wallDecoration.calculateCost(wallArea);
            float floorCost = floorDecoration.calculateCost(floorArea);

            float totalCost = wallCost + floorCost;

            System.out.println("Total Cost for " + classroom.name + ": " + Math.ceil(totalCost));
        }
    }
}