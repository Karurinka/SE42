package auction.web;

import auction.domain.User;
import auction.service.RegistrationMgr;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
public class Registration
{
    private RegistrationMgr registrationMgr = new RegistrationMgr();

    @WebResult(name = "userRegistered")
    @WebMethod(operationName = "registerUser")
    public User registerUser(String email)
    {
        return registrationMgr.registerUser(email);
    }

    @WebResult(name = "foundUser")
    @WebMethod(operationName = "getUser")
    public User getUser(String email)
    {
        return registrationMgr.getUser(email);
    }

}
