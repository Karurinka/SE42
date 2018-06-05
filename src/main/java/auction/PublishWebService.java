package auction;

import auction.webService.WebCalculator;

import javax.xml.ws.Endpoint;


public class PublishWebService {

    private static final String url = "http://localhost:8080/WebCalculator";

    public static void main(String[] args) {
        Endpoint.publish(url, new WebCalculator());
    }
}
