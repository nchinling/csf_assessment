package ibf2022.batch3.assessment.csf.orderbackend.services;


import java.io.StringReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import org.springframework.http.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


import ibf2022.batch3.assessment.csf.orderbackend.models.PizzaOrder;
import ibf2022.batch3.assessment.csf.orderbackend.respositories.OrdersRepository;
import ibf2022.batch3.assessment.csf.orderbackend.respositories.PendingOrdersRepository;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Service
public class OrderingService {

	@Autowired
	private OrdersRepository ordersRepo;

	@Autowired
	private PendingOrdersRepository pendingOrdersRepo;
	
	// TODO: Task 5
	// WARNING: DO NOT CHANGE THE METHOD'S SIGNATURE
	public PizzaOrder placeOrder(PizzaOrder order) throws OrderException {
		String railwayAPI = "https://pizza-pricing-production.up.railway.app";

		// String railwayUrl = UriComponentsBuilder
		// 	.fromUriString(railwayAPI)
		// 	// .queryParam("q", city.replaceAll(" ", "+"))
		// 	// .queryParam("units", unitMeasurement)
		// 	// .queryParam("appId", openWeatherApiKey)
		// 	// .queryParam("lang", language)
		// 	.toUriString();
		

		// RequestEntity<Void> req = RequestEntity.post(railwayUrl+"/order")
		// .accept(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
		// .build();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		// headers.setAccept(Arrays.asList(MediaType.TEXT_PLAIN));
		headers.setAccept(Collections.singletonList(MediaType.TEXT_PLAIN));

		MultiValueMap<String, String> orderDetails= new LinkedMultiValueMap<>();
		orderDetails.add("name",order.getName());
		orderDetails.add("email",order.getEmail());
		orderDetails.add("sauce",order.getSauce());
		orderDetails.add("size",order.getSize().toString());
		orderDetails.add("thickCrust",order.getThickCrust().toString());
		orderDetails.add("toppings",order.getToppings().toString());
		orderDetails.add("comments",order.getSize().toString());

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(orderDetails, headers);
		RestTemplate template = new RestTemplate();
		ResponseEntity<String> resp = null;

		try {
			// resp = template.exchange(railwayAPI+"/order", HttpMethod.POST, String.class);
			resp = template.postForEntity(railwayAPI+"/order", request, String.class);
	
		} catch (RestClientException ex) {
			
			throw new OrderException(ex.getMessage());
		}


		String payload = resp.getBody();
		JsonReader reader = Json.createReader(new StringReader(payload));
		JsonObject data = reader.readObject();

		// split string
		String[] dataArray = payload.split(",");

		order.setOrderId(dataArray[0]);
		Date date = new Date(Long.parseLong(dataArray[0])*1000);
		order.setDate(date);
		order.setTotal(Float.parseFloat(dataArray[2]));

		// JsonReader reader = Json.createReader(new StringReader(payload));
		// JsonObject data = reader.readObject();
		// return data.getJsonArray("weather").stream()
		// 	.map(v -> v.asJsonObject())
		// 	.map(o -> new WeatherInfo(o.getString("main"), o.getString("description"), o.getString("icon")))
		// 	.toList();

		return order;
	}

	// For Task 6
	// WARNING: Do not change the method's signature or its implemenation
	public List<PizzaOrder> getPendingOrdersByEmail(String email) {
		return ordersRepo.getPendingOrdersByEmail(email);
	}

	// For Task 7
	// WARNING: Do not change the method's signature or its implemenation
	public boolean markOrderDelivered(String orderId) {
		
		return ordersRepo.markOrderDelivered(orderId) && pendingOrdersRepo.delete(orderId);
	}


}
