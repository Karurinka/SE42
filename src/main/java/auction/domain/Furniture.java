package auction.domain;

import javax.persistence.Entity;
import java.util.Objects;

@Entity
public class Furniture extends Item
{
    private String material;

    public Furniture(User seller, Category category, String description, String material)
    {
        super(seller, category, description);
        this.material = material;
    }

    public Furniture()
    {
    }

    public String getMaterial()
    {
        return material;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof Furniture))
        {
            return false;
        }
        if (!super.equals(o))
        {
            return false;
        }
        Furniture furniture = (Furniture) o;
        return Objects.equals(material, furniture.material);
    }

    @Override
    public int hashCode()
    {

        return Objects.hash(super.hashCode(), material);
    }

    @Override
    public String toString()
    {
        return "Furniture{" + super.toString() + "material='" + material + '\'' + '}';
    }
}
