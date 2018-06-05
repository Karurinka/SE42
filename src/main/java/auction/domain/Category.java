package auction.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Category {

    @Column(name = "CATEGORY")
    private String description;

    public Category() {
        description = "undefined";
    }

    public Category(String description) {
        this.description = description;
    }

    public String getDiscription() {
        return description;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof Category))
        {
            return false;
        }
        Category category = (Category) o;
        return Objects.equals(description, category.description);
    }

    @Override
    public int hashCode()
    {

        return Objects.hash(description);
    }

    @Override
    public String toString()
    {
        return "Category{" + "description='" + description + '\'' + '}';
    }
}
