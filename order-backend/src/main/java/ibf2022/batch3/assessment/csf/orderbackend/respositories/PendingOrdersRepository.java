package ibf2022.batch3.assessment.csf.orderbackend.respositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;

import ibf2022.batch3.assessment.csf.orderbackend.models.PizzaOrder;
import ibf2022.batch3.assessment.csf.orderbackend.models.PizzaOrderRedis;

public class PendingOrdersRepository {

	//autowired in a bean.
	@Autowired @Qualifier("pending-orders")
	private RedisTemplate<String, String> redisTemplate;
	// TODO: Task 3
	// WARNING: Do not change the method's signature.
	public void add(PizzaOrder order) {

		PizzaOrderRedis orderRedis = new PizzaOrderRedis(order.getOrderId(), order.getDate(),
								order.getTotal(), order.getName(), order.getEmail());
		this.redisTemplate.opsForValue().set(order.getOrderId(), 
		orderRedis.toJSON().toString());

	}

	// TODO: Task 7
	// WARNING: Do not change the method's signature.
	public boolean delete(String orderId) {
		return false;
	}

}
