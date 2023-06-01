package ibf2022.batch3.assessment.csf.orderbackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ibf2022.batch3.assessment.csf.orderbackend.models.PizzaOrder;
import ibf2022.batch3.assessment.csf.orderbackend.respositories.OrdersRepository;
import ibf2022.batch3.assessment.csf.orderbackend.respositories.PendingOrdersRepository;

public class OrderingService {

	@Autowired
	private OrdersRepository ordersRepo;

	@Autowired
	private PendingOrdersRepository pendingOrdersRepo;
	
	// TODO: Task 5
	// WARNING: DO NOT CHANGE THE METHOD'S SIGNATURE
	public PizzaOrder placeOrder(PizzaOrder order) throws OrderException {
		String railwayAPI = "https://pizza-pricing-production.up.railway.app";

		String railwayUrl = UriComponentsBuilder
			.fromUriString(openWeatherUrl)
			.queryParam("q", city.replaceAll(" ", "+"))
			.queryParam("units", unitMeasurement)
			.queryParam("appId", openWeatherApiKey)
			.queryParam("lang", language)
			.toUriString();





		return null;
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
