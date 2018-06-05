package auction.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "AuctionUser")
@NamedQueries(
        {
                @NamedQuery(name = "User.count", query = "select count(u) from User as u"),
                @NamedQuery(name = "User.findByEmail", query = "select u from User as u where u.email = :Email"),
                @NamedQuery(name = "User.getAll", query = "select u from User AS u")
        }
)
public class User
{
    @Id
    private String email;

    @OneToMany(mappedBy = "seller")
    private Set<Item> offeredItems;



    public User()
    {
        offeredItems = new HashSet<>();
    }

    public User(String email)
    {
        this.email = email;
        offeredItems = new HashSet<>();
    }

    public String getEmail()
    {
        return email;
    }

    public Iterator<Item> getOfferedItems()
    {
        return offeredItems.iterator();
    }

    public int numberOfOfferedItems()
    {
        return offeredItems.size();
    }

    public void addItem(Item item)
    {
        offeredItems.add(item);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof User))
        {
            return false;
        }
        User user = (User) o;
        return Objects.equals(email, user.email);
    }

    @Override
    public int hashCode()
    {

        return Objects.hash(email);
    }

    @Override
    public String toString()
    {
        return "User{" + "email='" + email + '}';
    }
}
