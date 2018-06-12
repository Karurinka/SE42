package auction.domain;

import nl.fontys.util.Money;
import org.eclipse.persistence.annotations.CascadeOnDelete;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
        @NamedQuery(name = "Item.count", query = "SELECT COUNT(i) FROM Item AS i"),
        @NamedQuery(name = "Item.find", query = "SELECT i FROM Item AS i WHERE i.id = :id"),
        @NamedQuery(name = "Item.findAll", query = "SELECT i FROM Item as i"),
        @NamedQuery(name = "Item.findByDescription", query = "SELECT i FROM Item as i WHERE i.description = :description")
})
@Entity
public class Item implements Comparable<Item> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User seller;
    @Embedded
//    @AttributeOverrides(@AttributeOverride(name = "description", column = @Column(name = "columnDescription")))
    private Category category;
    private String description;
    @Embedded
    private Bid highest;

    public Item(User seller, Category category, String description) {
        this.seller = seller;
        this.category = category;
        this.description = description;
    }

    public Item()
    {
    }

    public Long getId() {
        return id;
    }

    public User getSeller() {
        return seller;
    }

    public Category getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public Bid getHighestBid() {
        return highest;
    }

    public Bid newBid(User buyer, Money amount) {
        if (highest != null && highest.getAmount().compareTo(amount) >= 0) {
            return null;
        }
        highest = new Bid(buyer, amount);
        return highest;
    }

    public int compareTo(Item other) {
        return this.description.compareTo(other.description);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id) && Objects.equals(seller, item.seller) && Objects.equals(category, item.category) && Objects.equals(description, item.description) && Objects.equals(highest, item.highest);
    }

    @Override
    public int hashCode()
    {

        return Objects.hash(id, seller, category, description, highest);
    }
}
