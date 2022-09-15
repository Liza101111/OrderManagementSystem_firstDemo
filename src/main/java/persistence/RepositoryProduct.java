package persistence;

import model.Product;
import util.DbUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class RepositoryProduct {
    private EntityManager entityManager;
    public RepositoryProduct(){
        entityManager = DbUtil.getEntityManager();
    }

    public void createProduct(Product product){
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(product);
            entityManager.getTransaction().commit();
        } catch(Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    public List<Product> listAllProducts(){
        return entityManager
                .createQuery("FROM Product", Product.class)
                .getResultList();
    }
}
