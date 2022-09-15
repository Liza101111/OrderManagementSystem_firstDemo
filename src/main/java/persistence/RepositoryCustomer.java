package persistence;

import model.Customer;
import util.DbUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class RepositoryCustomer {
    private EntityManager entityManager;

    public RepositoryCustomer() {
        entityManager = DbUtil.getEntityManager();
    }

    public void createCustomer(Customer customer){
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(customer);
            entityManager.getTransaction().commit();
        } catch(Exception e){
            entityManager.getTransaction().rollback();
        }
    }

    public List<Customer> listAllCustomers(){
        return entityManager
                .createQuery("FROM Customer",Customer.class)
                .getResultList();
    }

    public Customer findCustomerById(int id) {
        Query query = entityManager.createQuery("SELECT c from Customer c WHERE c.id = :id");
        query.setParameter("id",id);
        return (Customer) query.getSingleResult();

       /* String sql = "FROM Customer WHERE customer_id = :id";
        return entityManager.createQuery(sql, Customer.class)
                .setParameter("id", customerId)
                .getSingleResult();*/
    }


    public void updateCustomer(Customer customer) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(customer);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    public void deleteCustomer(Customer customer) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.merge(customer));
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }


}
