package auction.domain;

import nl.fontys.util.FontysTime;
import nl.fontys.util.Money;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.ManyToOne;

@Embeddable
public class Bid {

    private FontysTime time;
    @ManyToOne
    private User buyer;
    private Money amount;

    public Bid(User buyer, Money amount) {
        this.buyer = buyer;
        this.amount = amount;
        time = FontysTime.now();
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
