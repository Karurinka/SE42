package auction.service;

import auction.domain.Bid;
import auction.domain.Category;
import auction.domain.Item;
import auction.domain.User;
import nl.fontys.util.Money;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import util.DatabaseCleaner;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class BidirectionalTest
{
    final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("auctionPU");
    private AuctionMgr auctionMgr;
    private RegistrationMgr registrationMgr;
    private SellerMgr sellerMgr;

    @Before
    public void setUp()
    {
        registrationMgr = new RegistrationMgr();
        auctionMgr = new AuctionMgr();
        sellerMgr = new SellerMgr();
    }

    @After
    public void tearDown() throws Exception
    {
        new DatabaseCleaner(entityManagerFactory.createEntityManager()).clean();
    }

    @Test
    public void TestBidirectionalRelationship()
    {
        String email = "test@nl";
        String omsch = "test item";
        User user = registrationMgr.registerUser(email);
        Category cat = new Category("test cat");

        Item item = new Item(user, cat, omsch);
        //item heeft nog geen bid toegewezen
        assertNull(item.getHighestBid());
        Money amount = new Money(1000L, "eur");
        item.newBid(user, amount);
        //item heeft een nieuwe bid
        assertNotNull(item.getHighestBid());

        Bid bid = new Bid(user, amount, null);
        //bid heeft nog geen item toegewezen
        assertNull(bid.getItem());
        bid.setItem(item);
        assertNotNull(bid.getItem());
        assertEquals(bid.getItem(), item);
        assertEquals(item.getHighestBid(), bid);
    }
}
