package ibf2022.batch3.assessment.csf.orderbackend.respositories;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.RedisTemplate;

import ibf2022.batch3.assessment.csf.orderbackend.models.PizzaOrder;

public class OrdersRepository {

	
    //autowired in a bean.
    @Autowired @Qualifier("pending-orders")
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    MongoTemplate mongoTemplate;
	// TODO: Task 3
	// WARNING: Do not change the method's signature.
	// Write the native MongoDB query in the comment below
	//   Native MongoDB query here for add()
	 /*
	 * db.posts.insert({
	 * 	_id: postId,
	 * 	title: "title",
	 * 	content: "content",
	 * })
	 */
	public void add(PizzaOrder order) {
		Document doc = new Document();
        doc.put("_id", order.getOrderId());
		doc.put("date", order.getDate());
		doc.put("total", order.getTotal());
		doc.put("name", order.getName());
		doc.put("email", order.getEmail());
		doc.put("sauce", order.getSauce());
		doc.put("size", order.getSize());
        doc.put("crust", order.getThickCrust());
        doc.put("comments", order.getComments());
		doc.put("toppings", order.getComments());

        mongoTemplate.insert(doc, "orders");
	}
	
	// TODO: Task 6
	// WARNING: Do not change the method's signature.
	// Write the native MongoDB query in the comment below
	//   Native MongoDB query here for getPendingOrdersByEmail()
	public List<PizzaOrder> getPendingOrdersByEmail(String email) {

		return null;
	}

	// TODO: Task 7
	// WARNING: Do not change the method's signature.
	// Write the native MongoDB query in the comment below
	//   Native MongoDB query here for markOrderDelivered()
	public boolean markOrderDelivered(String orderId) {

		return false;
	}


}
