package persistence;

import model.OrderLine;
import util.DbUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class RepositoryOrderLine {
    private EntityManager entityManager;
    public RepositoryOrderLine(){
        entityManager = DbUtil.getEntityManager();
    }

    public void createOrderLine(OrderLine orderLine){
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(orderLine);
            entityManager.getTransaction().commit();
        } catch (Exception e){
            entityManager.getTransaction().rollback();
        }
    }

    public List<OrderLine> listAllOrderLines(){
        return entityManager
                .createQuery("FROM OrderLine", OrderLine.class)
                .getResultList();
    }



}
