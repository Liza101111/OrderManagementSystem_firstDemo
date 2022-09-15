package model;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private long orderID;
    @Column(name = "order_date")
    private String orderDate;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;


    public long getOrderID() {
        return orderID;
    }

    public void setOrderID(long orderID) {
        this.orderID = orderID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderID +
                ", orderDate='" + orderDate + '\'' +
                ", customer=" + customer +
                '}';
    }
}
