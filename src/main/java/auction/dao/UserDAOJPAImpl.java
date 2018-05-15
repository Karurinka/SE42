package auction.dao;

import auction.domain.User;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserDAOJPAImpl implements UserDAO
{
    private HashMap<String, User> users;
    private final EntityManager em;

    public UserDAOJPAImpl(EntityManager em)
    {
        this.em = em;
    }

    @Override
    public int count()
    {
        Query q = em.createNamedQuery("User.count", User.class);
        return ((Long) q.getSingleResult()).intValue();
    }

    @Override
    public void create(User user)
    {
        if (findByEmail(user.getEmail()) != null)
        {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        }
        em.close();
    }

    @Override
    public void edit(User user)
    {
        if (findByEmail(user.getEmail()) == null)
        {
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
        }
        em.close();
    }


    @Override
    public List<User> findAll()
    {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(User.class));
        return em.createQuery(cq).getResultList();
    }

    @Override
    public User findByEmail(String email)
    {
        Query q = em.createNamedQuery("User.findByEmail", User.class);
        q.setParameter("Email", email);
        return (User) q.getSingleResult();
    }

    @Override
    public void remove(User user)
    {
        em.remove((em.merge(user)));
    }
}
