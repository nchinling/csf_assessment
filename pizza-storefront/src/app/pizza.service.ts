import { Observable, Subject, filter, tap } from "rxjs";
import { OrderResponse } from "./models";
import { HttpClient } from "@angular/common/http";
import { Injectable, inject } from "@angular/core";



// Example of full url path  'http://localhost:8080/api/order'

@Injectable()
export class PizzaService {

  http = inject(HttpClient)
  onRequest = new Subject<OrderResponse>()
  // TODO: Task 3
  // You may add any parameters and return any type from placeOrder() method
  // Do not change the method name
  placeOrder(data: FormData) : Observable<OrderResponse> {

    console.info('>>>>>> inside placeOrder()>>>>', data)

    //
    return this.http.post<OrderResponse>('/api/order', data, 
                      { headers: { 'Content-Type': 'application/json' } }).pipe(
      filter((response) => response !== null),
      tap(response => this.onRequest.next(response))
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
  getOrders(email: string): Observable<OrderResponse[]> {

    console.info('>>>>>>>In getAllBundles() >>>>>>>>')
    // return this.http.get<BundleMongoResponse>('http://localhost:8080/bundle/'+`${bundleId}`, {params})
    return this.http.get<OrderResponse[]>('/api/orders'+`${email}`)


  }

  // TODO: Task 7
  // You may add any parameters and return any type from delivered() method
  // Do not change the method name
  delivered(orderId: string):Observable<any> {
    return this.http.get<any>('/api/order/'+`${orderId}`)
  }

}
