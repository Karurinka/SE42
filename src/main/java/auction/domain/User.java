package auction.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity (name = "AuctionUser")
@NamedQueries(
        {
                @NamedQuery(name = "User.count", query = "select count(u) from AuctionUser as u"),
                @NamedQuery(name = "User.findByEmail", query = "select u from AuctionUser as u where u.email = :Email")
        }
)
public class User implements Serializable
{
    @Id
    private String email;

    public User()
    {

    }

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
