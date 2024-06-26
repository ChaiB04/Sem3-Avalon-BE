package individual.individualsem3backend.business;

import individual.individualsem3backend.domain.Order;

import java.util.List;

public interface OrderManager {
    List<Order> getAllOrders(Integer userId);

    Order findOrderById(Integer orderId);

    Order create(Order neworder);

    void delete(Integer orderId);

    Order update(Order updatedOrder);
}
