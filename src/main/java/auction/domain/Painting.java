package auction.domain;

import javax.persistence.Entity;
import java.util.Objects;

@Entity
public class Painting extends Item
{
    private String title;
    private String painter;

    public Painting(User seller, Category category, String description, String title, String painter)
    {
        super(seller, category, description);
        this.title = title;
        this.painter = painter;
    }

    public Painting()
    {
    }

    public String getTitle()
    {
        return title;
    }

    public String getPainter()
    {
        return painter;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof Painting))
        {
            return false;
        }
        if (!super.equals(o))
        {
            return false;
        }
        Painting painting = (Painting) o;
        return Objects.equals(title, painting.title) && Objects.equals(painter, painting.painter);
    }

    @Override
    public int hashCode()
    {

        return Objects.hash(super.hashCode(), title, painter);
    }

    @Override
    public String toString()
    {
        return "Painting{"+ super.toString() + "title='" + title + '\'' + ", painter='" + painter + '\'' + '}';
    }
}
