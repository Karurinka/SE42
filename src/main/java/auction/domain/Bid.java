package auction.domain;

import nl.fontys.util.FontysTime;
import nl.fontys.util.Money;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class Bid {

    @Embedded
    private FontysTime time;
    private User buyer;
    @Embedded
    @Column(name = "Money")
    private Money amount;

    public Bid(User buyer, Money amount) {
        //TODO
    }

    public Bid()
    {
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
}
