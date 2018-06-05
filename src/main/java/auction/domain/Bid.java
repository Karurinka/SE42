package auction.domain;

import nl.fontys.util.FontysTime;
import nl.fontys.util.Money;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    private FontysTime time;
    @ManyToOne
    private User buyer;
    private Money amount;

    @OneToOne
    @JoinColumn(nullable = false)
    private Item item;

    public Bid(User buyer, Money amount, Item item) {
        this.buyer = buyer;
        this.amount = amount;
        this.item = item;
        time = FontysTime.now();
    }

    public Bid()
    {
    }

    public Item getItem()
    {
        return item;
    }

    public void setItem(Item item)
    {
        this.item = item;
    }

    public FontysTime getTime() {
        return time;
    }

    public User getBuyer() {
        return buyer;
    }

    public Money getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof Bid))
        {
            return false;
        }
        Bid bid = (Bid) o;
        return Id == bid.Id && Objects.equals(time, bid.time) && Objects.equals(buyer, bid.buyer) &&
               Objects.equals(amount, bid.amount) && Objects.equals(item, bid.item);
    }

    @Override
    public int hashCode()
    {

        return Objects.hash(Id, time, buyer, amount);
    }
}
