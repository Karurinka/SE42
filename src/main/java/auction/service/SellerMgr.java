package auction.service;

import auction.dao.ItemDAO;
import auction.dao.ItemDAOJPAImpl;
import auction.dao.UserDAO;
import auction.dao.UserDAOJPAImpl;
import auction.domain.*;

public class SellerMgr
{
    private ItemDAO itemDAO = new ItemDAOJPAImpl();
    private UserDAO userDAO = new UserDAOJPAImpl();

    /**
     * @param seller
     * @param cat
     * @param description
     *
     * @return het item aangeboden door seller, behorende tot de categorie cat en met de beschrijving description
     */
    public Item offerItem(User seller, Category cat, String description)
    {
        Item item = new Item(seller, cat, description);
        try
        {
            itemDAO.create(item);
            seller.addItem(item);

            userDAO.edit(seller);
        }
        catch (IllegalStateException ignored)
        {
        }
        return item;
    }

    public Item offerFurniture(User seller, Category cat, String description, String material)
    {
        Item item = new Furniture(seller, cat, description, material);
        try
        {
            itemDAO.create(item);
            seller.addItem(item);

            userDAO.edit(seller);
        }
        catch (IllegalStateException ignored)
        {
        }
        return item;
    }

    public Item offerPainting(User seller, Category cat, String description, String title, String painter)
    {
        Item item = new Painting(seller, cat, description, title, painter);
        try
        {
            itemDAO.create(item);
            seller.addItem(item);

            userDAO.edit(seller);
        }
        catch (IllegalStateException ignored)
        {

        }
        return item;
    }

    /**
     * @param item
     *
     * @return true als er nog niet geboden is op het item. Het item word verwijderd. false als er al geboden was op het
     * item.
     */
    public boolean revokeItem(Item item)
    {
        if (item.getHighestBid() != null)
        {
            return false;
        }
        itemDAO.remove(item);
        return true;
    }


}
