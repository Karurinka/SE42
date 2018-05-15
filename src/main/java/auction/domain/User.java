package auction.domain;

import javax.persistence.*;
import java.io.Serializable;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
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

    public long getId() {return id;}
}
