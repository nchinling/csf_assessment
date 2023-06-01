package ibf2022.batch3.assessment.csf.orderbackend.respositories;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.mongodb.client.result.UpdateResult;

import ibf2022.batch3.assessment.csf.orderbackend.models.PizzaOrder;

@Repository
public class OrdersRepository {

	


    @Autowired
    MongoTemplate mongoTemplate;
	// TODO: Task 3
	// WARNING: Do not change the method's signature.
	// Write the native MongoDB query in the comment below
	//   Native MongoDB query here for add()
	 /*
	 * db.orders.insert({
	 * 	_id: "orderId",
	 * 	date: "date",
	 * 	total: "total",
	* 	name: "Ng Chin Ling",
	* 	email: "nchinling@gmail.com",
	* 	sauce: "signature",
	* 	size: 1,
	* 	crust: "true/false/thick/thin",
	* 	comments: "comments",
	* 	toppings: [topping1, topping2..]
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
		doc.put("toppings", order.getToppings());

        mongoTemplate.insert(doc, "orders");
	}
	
	// TODO: Task 6
	// WARNING: Do not change the method's signature.
	// Write the native MongoDB query in the comment below
	//   Native MongoDB query here for getPendingOrdersByEmail()
	 /*
	 * db.orders.find(
	 * {delivered: 'false'},
	 * {_id: 1, total: 1, date: 1}
	 * ).sort({date: -1})
	 */

	public List<PizzaOrder> getPendingOrdersByEmail(String email) {
		// Criteria criteria = Criteria.where("delivered").regex(title, "i");
		Criteria criteria = Criteria.where("delivered").is("false");
		Query query = Query.query(criteria)
				.with(Sort.by(Direction.ASC, "date"));
		query.fields()
			.include("_id", "total", "date");
		List<PizzaOrder> pizzaOrderList = mongoTemplate.find(query, PizzaOrder.class, 
		"orders");

		return pizzaOrderList;
		
	}

	// TODO: Task 7
	// WARNING: Do not change the method's signature.
	// Write the native MongoDB query in the comment below
	//   Native MongoDB query here for markOrderDelivered()
	/* db.orders.update(
	* {_id: orderId},
	* {$set: {delivered: "true"}}
	* )
	*/
	public boolean markOrderDelivered(String orderId) {
			Criteria criteria = Criteria.where("_id").is(orderId);
			Query query = Query.query(criteria);
	
			Update updateOps = new Update()
				.push("delivered", true);
			UpdateResult updateResult = mongoTemplate
										.updateFirst(query, updateOps,
										Document.class, "orders");
			return true;
	
		}
		
	


}
