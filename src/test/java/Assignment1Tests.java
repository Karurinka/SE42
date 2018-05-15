import bank.dao.AccountDAOJPAImpl;
import bank.domain.Account;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.DatabaseCleaner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.security.auth.login.AccountNotFoundException;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class Assignment1Tests
{
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("bankPU");
    private EntityManager em;
    private AccountDAOJPAImpl accountDAOJPA;
 
    @Before
    public void Before()
    {
        try
        {
            new DatabaseCleaner(emf.createEntityManager()).clean();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        em = emf.createEntityManager();
        accountDAOJPA = new AccountDAOJPAImpl(em);
    }

    @Test
    public void vraag1()
    {
        Account account = new Account(111L);
        em.getTransaction().begin();
        em.persist(account);
        //TODO: verklaar en pas eventueel aan
        assertNull(account.getId());
        em.getTransaction().commit();
        System.out.println("AccountId: " + account.getId());
        //TODO: verklaar en pas eventueel aan
        assertTrue(account.getId() > 0L);
    }

    @Test
    public void vraag2()
    {
        Account account = new Account(111L);
        em.getTransaction().begin();
        em.persist(account);
        assertNull(account.getId());
        em.getTransaction().rollback();
        // TODO code om te testen dat table account geen records bevat. Hint: bestudeer/gebruik AccountDAOJPAImpl
        Assert.assertEquals(0, accountDAOJPA.count());
    }

    @Test
    public void vraag3()
    {
        Long expected = -100L;
        Account account = new Account(111L);
        account.setId(expected);
        em.getTransaction().begin();
        em.persist(account);
        System.out.println("AccountID: " + account.getId());
        //TODO: verklaar en pas eventueel aan
        em.flush();
        assertNotEquals(expected, account.getId());
        System.out.println("AccountID: " + account.getId());
        //TODO: verklaar en pas eventueel aan
        em.getTransaction().commit();
        assertNotEquals(expected, account.getId());
        //TODO: verklaar en pas eventueel aan
        // de ID wordt in de databasae geregeld, deze zal dus nooit -100 kunnen zijn
    }

    @Test
    public void vraag4()
    {
        Long expectedBalance = 400L;
        Account account = new Account(114L);
        em.getTransaction().begin();
        em.persist(account);
        account.setBalance(expectedBalance);
        em.getTransaction().commit();
        assertEquals(expectedBalance, account.getBalance());
        System.out.println(account.getBalance());
        //TODO: verklaar de waarde van account.getBalance

        Long cid = account.getId();
        account = null;
        EntityManager em2 = emf.createEntityManager();
        em2.getTransaction().begin();
        Account found = em2.find(Account.class, cid);
        //TODO: verklaar de waarde van found.getBalance
        assertEquals(expectedBalance, found.getBalance());
    }

    @Test
    public void vraag5()
    {
        Long expectedBalance = 400L;
        Account account = new Account(114L);
        em.getTransaction().begin();
        em.persist(account);
        account.setBalance(expectedBalance);
        em.getTransaction().commit();
        assertEquals(expectedBalance, account.getBalance());
        System.out.println(account.getBalance());

        Long cid = account.getId();
        EntityManager em2 = emf.createEntityManager();
        em2.getTransaction().begin();

        Account found = em2.find(Account.class, cid);
        em2.persist(found);
        found.setBalance(2L);
        em2.getTransaction().commit();
        em.refresh(account);

        assertEquals(account.getBalance(), found.getBalance());
    }

    @Test
    public void vraag6()
    {
        Account acc = new Account(1L);
        Account acc2 = new Account(2L);
        Account acc9 = new Account(9L);

        // scenario 1
        Long balance1 = 100L;
        em.getTransaction().begin();
        em.persist(acc);
        acc.setBalance(balance1);
        em.getTransaction().commit();
        //TODO: voeg asserties toe om je verwachte waarde van de attributen te verifieren.
        Assert.assertEquals(balance1, acc.getBalance());
        //TODO: doe dit zowel voor de bovenstaande java objecten als voor opnieuw bij de entitymanager opgevraagde objecten met overeenkomstig Id.
        Long idInDatabase = acc.getId();
        Account accountInDatabase = em.find(Account.class, idInDatabase);
        Assert.assertEquals(balance1, accountInDatabase.getBalance());

        // scenario 2
        Long balance2a = 211L;
        acc = new Account(2L);
        em.getTransaction().begin();
        acc9 = em.merge(acc);
        acc.setBalance(balance2a);
        acc9.setBalance(balance2a + balance2a);
        em.getTransaction().commit();
        Long balance2b = balance2a * 2;
        //TODO: voeg asserties toe om je verwachte waarde van de attributen te verifiëren.
        System.out.println(acc.getBalance());
        System.out.println(acc9.getBalance());
        assertEquals(balance2a, acc.getBalance());
        assertEquals(balance2b, acc9.getBalance());
        //TODO: doe dit zowel voor de bovenstaande java objecten als voor opnieuw bij de entitymanager opgevraagde objecten met overeenkomstig Id.
        // HINT: gebruik acccountDAO.findByAccountNr
        Account accountFoundInDatabase = accountDAOJPA.findByAccountNr(acc.getAccountNr());
        assertEquals(balance2b, accountFoundInDatabase.getBalance());

        // scenario 3
        Long balance3b = 322L;
        Long balance3c = 333L;
        acc = new Account(3L);
        em.getTransaction().begin();
        acc2 = em.merge(acc);
        assertFalse(em.contains(acc)); // verklaar
        assertTrue(em.contains(acc2)); // verklaar
        assertNotEquals(acc, acc2);  //verklaar
        acc2.setBalance(balance3b);
        acc.setBalance(balance3c);
        em.getTransaction().commit();
        //TODO: voeg asserties toe om je verwachte waarde van de attributen te verifiëren.
        assertEquals(balance3b, acc2.getBalance());
        //TODO: doe dit zowel voor de bovenstaande java objecten als voor opnieuw bij de entitymanager opgevraagde objecten met overeenkomstig Id.
        accountFoundInDatabase = accountDAOJPA.findByAccountNr(acc.getAccountNr());
        assertEquals(balance3b, accountFoundInDatabase.getBalance());

        // scenario 4
        Account account = new Account(114L);
        account.setBalance(450L);
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(account);
        em.getTransaction().commit();

        Account account2 = new Account(114L);  //x
        Account tweedeAccountObject = account2; //x
        tweedeAccountObject.setBalance(650l);
        assertEquals((Long) 650L, account2.getBalance());  //verklaar
        account2.setId(account.getId());
        em.getTransaction().begin();
        account2 = em.merge(account2);
        assertSame(account, account2);  //verklaar
        assertTrue(em.contains(account2));  //verklaar
        assertFalse(em.contains(tweedeAccountObject));  //verklaar
        tweedeAccountObject.setBalance(850l);
        assertEquals((Long) 650L, account.getBalance());  //verklaar
        assertEquals((Long) 650L, account2.getBalance());  //verklaar
        em.getTransaction().commit();
        em.close();
    }

    @Test
    public void vraag7()
    {
        Account acc1 = new Account(77L);
        em.getTransaction().begin();
        em.persist(acc1);
        em.getTransaction().commit();
        //Database bevat nu een account.

        // scenario 1
        Account accF1;
        Account accF2;
        accF1 = em.find(Account.class, acc1.getId());
        accF2 = em.find(Account.class, acc1.getId());
        assertSame(accF1, accF2);

        // scenario 2
        accF1 = em.find(Account.class, acc1.getId());
        em.clear();
        accF2 = em.find(Account.class, acc1.getId());
        assertNotSame(accF1, accF2);
        //TODO verklaar verschil tussen beide scenario’s
    }

    @Test
    public void vraag8()
    {
        Account acc1 = new Account(88L);
        em.getTransaction().begin();
        em.persist(acc1);
        em.getTransaction().commit();
        Long id = acc1.getId();
        //Database bevat nu een account.

        em.remove(acc1);
        assertEquals(id, acc1.getId());
        Account accFound = em.find(Account.class, id);
        assertNull(accFound);
        //TODO: verklaar bovenstaande asserts
    }
}
