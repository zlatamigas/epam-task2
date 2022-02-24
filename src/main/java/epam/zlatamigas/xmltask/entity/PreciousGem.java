package epam.zlatamigas.xmltask.entity;

import java.time.YearMonth;

public class PreciousGem extends Gem {

    private PreciousType type;

    public PreciousGem() {
    }

    public PreciousGem(String id,
                       String name,
                       GemOrigin origin,
                       YearMonth extractionTime,
                       String color,
                       int transparency,
                       int faces,
                       double mass,
                       PreciousType type) {
        super(id, name, origin, extractionTime, color, transparency, faces, mass);
        this.type = type;
    }

    public PreciousType getType() {
        return type;
    }

    public void setType(PreciousType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        PreciousGem that = (PreciousGem) o;
        return type == that.type;
    }

    @Override
    public int hashCode() {
        return 31 * super.hashCode() + type.hashCode();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PreciousGem{");
        sb.append("id='").append(getId()).append('\'');
        sb.append(", name='").append(getName()).append('\'');
        sb.append(", origin=").append(getOrigin());
        sb.append(", extractionTime=").append(getExtractionTime());
        sb.append(", color='").append(getColor()).append('\'');
        sb.append(", transparency=").append(getTransparency());
        sb.append(", faces=").append(getFaces());
        sb.append(", mass=").append(getMass());
        sb.append(", type=").append(type);
        sb.append('}');
        return sb.toString();
    }
}
