package ibf2022.batch3.assessment.csf.orderbackend.controllers;

import java.io.IOException;
import java.io.StringReader;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ibf2022.batch3.assessment.csf.orderbackend.models.PizzaOrder;
import ibf2022.batch3.assessment.csf.orderbackend.services.OrderException;
import ibf2022.batch3.assessment.csf.orderbackend.services.OrderingService;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Controller
@RequestMapping
//removed for backend server deploy,ent
// @CrossOrigin(origins="*")
public class OrderController {


	@Autowired
	private OrderingService orderSvc;


	// TODO: Task 3 - POST /api/order

	@PostMapping(path="/api/order", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> postOrderData(@RequestBody String payload){

		JsonReader reader = Json.createReader(new StringReader(payload));
        JsonObject req = reader.readObject();

		Boolean base = false;
		String thickCrustFromClient = req.getString("base");
		if (thickCrustFromClient == "thick"){
			base = true;
		}

		String name = req.getString("name");
		String email = req.getString("email");
		String sauce = req.getString("sauce");
		String comments = req.getString("comments");;
		Integer size = req.getInt("size");
		// Boolean isThickCrust  = req.getBoolean("base");
		JsonArray jsonArray = req.getJsonArray("toppings");
		List<String> toppings = new LinkedList<>();
		Integer listSize = jsonArray.size();
        Integer i = 0;
        while (i < listSize) {
            toppings.add(jsonArray.get(i).toString());
            i++;
        }

		PizzaOrder order = new PizzaOrder();
		order.setName(name);
		order.setEmail(email);
		order.setSauce(sauce);
		order.setSize(size);
		order.setThickCrust(base);
		order.setToppings(toppings);
		order.setComments(comments);

		JsonObject resp = null;
		// JsonObject errorResp = null;
		PizzaOrder orderWithId;
		try {
			orderWithId = orderSvc.placeOrder(order);
			resp = Json.createObjectBuilder()
			.add("orderId", orderWithId.getOrderId())
			.add("date", orderWithId.getDate().toString())
			.add("name", orderWithId.getName())
			.add("total", orderWithId.getTotal())
			.build();
	
		} catch (OrderException ex) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			return ResponseEntity.status(400)
			.body(
				Json.createObjectBuilder()
					.add("error", ex.getMessage())
					.build().toString()
			);
		}

		System.out.printf(">>> Successfully placed order >>>>>\n");

		return ResponseEntity
		.status(HttpStatus.ACCEPTED)
		.contentType(MediaType.APPLICATION_JSON)
		.body(resp.toString());

	}


	// TODO: Task 6 - GET /api/orders/<email>

	@GetMapping(path="/api/orders/{email}", produces = MediaType.APPLICATION_JSON_VALUE )
    @ResponseBody
    public ResponseEntity<List<PizzaOrder>> getAllPendingOrdersByEmail(@PathVariable String email) {
    List<PizzaOrder> pizzaOrderList = orderSvc.getPendingOrdersByEmail(email);
    return ResponseEntity.ok(pizzaOrderList);


	}


	// TODO: Task 7 - DELETE /api/order/<orderId>

	@DeleteMapping(path="/api/order/{orderId}", consumes = MediaType.APPLICATION_JSON_VALUE )
    @ResponseBody
    public ResponseEntity<String> deleteOrdersbyOrderId(@PathVariable String orderId) {
    
	
	Boolean isDeleted = orderSvc.markOrderDelivered(orderId);

	if(!isDeleted){
		return ResponseEntity.status(404)
		.body(
			Json.createObjectBuilder()
				.add("error", "Delete Order failed")
				.build().toString()
		);

	}
	return ResponseEntity.ok("{}");

	
	}







}





