package persistence;

import model.Order;
import util.DbUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class RepositoryOrder {
    private EntityManager entityManager;
    public RepositoryOrder(){
        entityManager = DbUtil.getEntityManager();
    }

    public void createOrder(Order order){
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(order);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    public List<Order> listAllOrders(){
        return entityManager
                .createQuery("FROM Order", Order.class)
                .getResultList();
    }

    public List<Order> searchOrdersByDate(String orderDate){
      /*  Query query = entityManager.createQuery("SELECT o from Order o WHERE o.orderDate = :date");
        query.setParameter("date",date);
        return query.getResultList();
*/
        String sql = "FROM Order WHERE orderDate = :date";
        return entityManager
                .createQuery(sql, Order.class)
                .setParameter("date", orderDate)
                .getResultList();
    }

    public long countOrders(){
        Query query = entityManager.createQuery("SELECT count(*) FROM Order o");
        return (long) query.getSingleResult();
    }
}
