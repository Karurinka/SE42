package auction.bootstrap;

import auction.web.Auction;
import auction.web.Registration;
import javax.xml.ws.Endpoint;

public class AuctionServer
{
    public static void main(String[] args)
    {
        Endpoint.publish("http://localhost:8080/Auction", new Auction());
        Endpoint.publish("http://localhost:8080/Registration", new Registration());
    }
}
