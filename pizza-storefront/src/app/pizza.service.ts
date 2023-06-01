import { Observable, filter } from "rxjs";
import { OrderResponse } from "./models";
import { HttpClient } from "@angular/common/http";
import { Injectable, inject } from "@angular/core";


@Injectable()
export class PizzaService {

  http = inject(HttpClient)
  // TODO: Task 3
  // You may add any parameters and return any type from placeOrder() method
  // Do not change the method name
  placeOrder(data: FormData) : Observable<OrderResponse> {

    console.info('>>>>>> inside placeOrder()>>>>', data)

    //
    return this.http.post<OrderResponse>('http://localhost:8080/api/order', data, 
                      { headers: { 'Content-Type': 'application/json' } }).pipe(
      filter((response) => response !== null)
    )

    //refer to open weather weather-service on mapping to model
        // .pipe(
        //   //onRequest is used for logger service
        //   //observable
        //   tap(resp => this.onRequest.next(resp)),
        //    map(resp => ({ message: resp.message, timestamp: resp.timestamp }))
        // )



  }

  // TODO: Task 5
  // You may add any parameters and return any type from getOrders() method
  // Do not change the method name
  getOrders() {
  }

  // TODO: Task 7
  // You may add any parameters and return any type from delivered() method
  // Do not change the method name
  delivered() {
  }

}
