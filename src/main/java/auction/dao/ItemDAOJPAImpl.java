package auction.dao;

import auction.dao.ItemDAO;
import auction.domain.Item;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOJPAImpl implements ItemDAO
{
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("auctionPU");
    private EntityManager em = null;
    private List<Item> items;

    public ItemDAOJPAImpl()
    {
        items = new ArrayList<>();
    }

    @Override
    public int count()
    {
        em = emf.createEntityManager();
        Query q = em.createNamedQuery("Item.count", Item.class);
        return ((Long) q.getSingleResult()).intValue();
    }

    @Override
    public void create(Item item)
    {
        em = emf.createEntityManager();
        em.getTransaction().begin();

        try
        {

            em.persist(item);
            em.getTransaction().commit();
        } catch (IllegalStateException e)
        {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }

    @Override
    public void edit(Item item)
    {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(item);
        em.getTransaction().commit();
    }

    @Override
    public Item find(Long id)
    {
        em = emf.createEntityManager();
        Query q = em.createNamedQuery("Item.findById", Item.class);
        q.setParameter("Id", id);
        try
        {
            return (Item) q.getSingleResult();
        } catch (NoResultException e)
        {
            return null;
        }
    }

    @Override
    public List<Item> findAll()
    {
        em = emf.createEntityManager();
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Item.class));
        return em.createQuery(cq).getResultList();
    }

    @Override
    public List<Item> findByDescription(String description)
    {
        em = emf.createEntityManager();
        Query q = em.createNamedQuery("Item.findByDescription", Item.class);
        q.setParameter("Description", description);

        try
        {
            for (int i = 0; i < q.getResultList().size(); i++)
            {
                items.add((Item) q.getSingleResult());
            }
            return items;
        } catch (NoResultException e)
        {
            return null;
        }
    }

    @Override
    public void remove(Item item)
    {
        em = emf.createEntityManager();
        em.remove((em.merge(item)));
    }
}