package auction.webService;

import auction.service.NegativeNumberException;
import auction.service.PositiveCalculatorService;

import javax.jws.WebService;

@WebService
public class WebCalculator {
    
    private PositiveCalculatorService positiveCalculatorService = new PositiveCalculatorService();

    public int add(int x, int y) throws NegativeNumberException
    {
        return positiveCalculatorService.add(x, y);
    }

    public int minus(int x, int y) throws NegativeNumberException {
        return positiveCalculatorService.minus(x, y);
    }

    public int times(int x, int y) throws NegativeNumberException {
        return positiveCalculatorService.times(x, y);
    }
}