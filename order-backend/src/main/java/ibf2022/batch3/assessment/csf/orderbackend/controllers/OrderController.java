package ibf2022.batch3.assessment.csf.orderbackend.controllers;

import java.io.IOException;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ibf2022.batch3.assessment.csf.orderbackend.services.OrderException;
import jakarta.json.Json;
import jakarta.json.JsonObject;

@Controller
@RequestMapping(path="", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {

	// TODO: Task 3 - POST /api/order

	@PostMapping(path="/api/order", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> postOrderData(@RequestBody String payload){

		JsonObject resp = null;

		//implementTryCatch
		try{

			resp = Json.createObjectBuilder()
			.add("orderId", postId)
			.add("date", (new Date()).toString())
			.add("name", name)
			.add("total", total)
			.build();
	

		} catch (OrderException ex){
			ex.printStackTrace();
			// if (bookings.isEmpty())
			// return ResponseEntity
			// 		.status(HttpStatus.NOT_FOUND)
			// 		.contentType(MediaType.APPLICATION_JSON)
			// 		.body("{error_msg: record for booking "+ q +" not found :)}");
		}

		return ResponseEntity
		.status(HttpStatus.ACCEPTED)
		.contentType(MediaType.APPLICATION_JSON)
		.body(resp.toString());


	}


	// TODO: Task 6 - GET /api/orders/<email>


	// TODO: Task 7 - DELETE /api/order/<orderId>

}
