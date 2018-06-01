package auction.domain;

import javax.persistence.*;
import java.util.Objects;

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

    public User()
    {

    }

    /**
     * @param email
     */
    public User(String email)
    {
        this.email = email;
    }

    public String getEmail()
    {
        return email;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(email);
    }
}
