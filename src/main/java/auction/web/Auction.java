package auction.web;

import auction.domain.Bid;
import auction.domain.Category;
import auction.domain.Item;
import auction.domain.User;
import auction.service.AuctionMgr;
import auction.service.SellerMgr;
import nl.fontys.util.Money;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.List;

@WebService
public class Auction
{
    private AuctionMgr auctionMgr = new AuctionMgr();
    private SellerMgr sellerMgr = new SellerMgr();

    @WebResult(name = "itemFound")
    @WebMethod(operationName = "getItem")
    public Item getItem(Long id)
    {
        return auctionMgr.getItem(id);
    }

    @WebResult(name = "itemsByDescriptionFound")
    @WebMethod(operationName = "findItemByDescription")
    public List<Item> findItemByDescription(String description)
    {
        return auctionMgr.findItemByDescription(description);
    }

    @WebResult(name = "bidMade")
    @WebMethod(operationName = "newBid")
    public Bid newBid(Item item, User buyer, Money amount)
    {
        return auctionMgr.newBid(item, buyer, amount);
    }

    @WebResult(name = "itemOffered")
    @WebMethod(operationName = "offerItem")
    public Item offerItem(User seller, Category cat, String description)
    {
        return sellerMgr.offerItem(seller, cat, description);
    }

    @WebResult(name = "itemRevoked")
    @WebMethod(operationName = "revokeItem")
    public boolean revokeItem(Item item)
    {
        return sellerMgr.revokeItem(item);
    }

}
