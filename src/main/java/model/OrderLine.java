package model;

import javax.persistence.*;

@Entity
@Table(name = "orderLine")
public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderLine_id")
    private long orderLineID;
    @Column(name = "quantity")
    private long quantity;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public long getOrderLineID() {
        return orderLineID;
    }

    public void setOrderLineID(long orderLineID) {
        this.orderLineID = orderLineID;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "OrderLine{" +
                "orderLineID=" + orderLineID +
                ", quantity=" + quantity +
                ", order=" + order +
                ", product=" + product +
                '}';
    }
}
