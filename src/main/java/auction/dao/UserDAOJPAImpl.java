package auction.dao;

import auction.domain.User;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserDAOJPAImpl implements UserDAO
{
    private HashMap<String, User> users;
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("auctionPU");
    private EntityManager em = null;

    public UserDAOJPAImpl()
    {

    }

    @Override
    public int count()
    {
        em = emf.createEntityManager();
        Query q = em.createNamedQuery("User.count", User.class);
        return ((Long) q.getSingleResult()).intValue();
    }

    @Override
    public void create(User user)
    {
        em = emf.createEntityManager();
        em.getTransaction().begin();

        try
        {
            em.persist(user);
            em.getTransaction().commit();
        } catch (Exception e)
        {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }

    @Override
    public void edit(User user)
    {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
    }


    @Override
    public List<User> findAll()
    {
        em = emf.createEntityManager();
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(User.class));
        return em.createQuery(cq).getResultList();
    }

    @Override
    public User findByEmail(String email)
    {
        em = emf.createEntityManager();
        Query q = em.createNamedQuery("User.findByEmail", User.class);
        q.setParameter("Email", email);
        try
        {
            return (User) q.getSingleResult();
        } catch (NoResultException e)
        {
            return null;
        }
    }

    @Override
    public void remove(User user)
    {
        em = emf.createEntityManager();
        em.remove((em.merge(user)));
    }
}
