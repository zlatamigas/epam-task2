package epam.zlatamigas.xmltask.entity;

import java.time.YearMonth;

public abstract class Gem {

    public static final String NAME_DEFAULT = "Gem stone";

    private String id;
    private String name;
    private GemOrigin origin;
    private YearMonth extractionTime;
    private String color;
    private int transparency;
    private int faces;
    private double mass;

    public Gem() {
        name = NAME_DEFAULT;
    }

    public Gem(String id,
               String name,
               GemOrigin origin,
               YearMonth extractionTime,
               String color,
               int transparency,
               int faces,
               double mass) {
        this.id = id;
        this.name = (name == null ? NAME_DEFAULT : name);
        this.origin = origin;
        this.extractionTime = extractionTime;
        this.color = color;
        this.transparency = transparency;
        this.faces = faces;
        this.mass = mass;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = (name == null ? NAME_DEFAULT : name);
    }

    public GemOrigin getOrigin() {
        return origin;
    }

    public void setOrigin(GemOrigin origin) {
        this.origin = origin;
    }

    public YearMonth getExtractionTime() {
        return extractionTime;
    }

    public void setExtractionTime(YearMonth extractionTime) {
        this.extractionTime = extractionTime;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getTransparency() {
        return transparency;
    }

    public void setTransparency(int transparency) {
        this.transparency = transparency;
    }

    public int getFaces() {
        return faces;
    }

    public void setFaces(int faces) {
        this.faces = faces;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Gem gem = (Gem) o;

        if (transparency != gem.transparency
                || faces != gem.faces
                || Double.compare(gem.mass, mass) != 0
                || !id.equals(gem.id)
                || !name.equals(gem.name)
                || origin != gem.origin
                || !extractionTime.equals(gem.extractionTime)
                || !color.equals(gem.color)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {

        int result = 1;

        Object[] elements = new Object[]{id, name, origin, extractionTime, color, transparency, faces, mass};

        for (Object element : elements) {
            result = 31 * result + (element == null ? 0 : element.hashCode());
        }

        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Gem{");
        sb.append("id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", origin=").append(origin);
        sb.append(", extractionTime=").append(extractionTime);
        sb.append(", color='").append(color).append('\'');
        sb.append(", transparency=").append(transparency);
        sb.append(", faces=").append(faces);
        sb.append(", mass=").append(mass);
        sb.append('}');
        return sb.toString();
    }
}
