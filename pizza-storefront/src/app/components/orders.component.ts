import { Component, OnInit, inject } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { OrderResponse } from '../models';
import { PizzaService } from '../pizza.service';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit {
  router = inject(Router)
  allOrdersResponse$!: Observable<OrderResponse[]>
  pizzaSvc = inject(PizzaService)
  activateRoute = inject(ActivatedRoute)
  email = ''
  orderDeleted$!: Observable<any>


  ngOnInit(): void {
    // this.bundleId$ = this.uploadSvc.onRequest

    this.email = this.activateRoute.snapshot.params['email']
    this.allOrdersResponse$ = this.pizzaSvc.getOrders(this.email)

  }



  removeOrder(orderId: string) {
    this.orderDeleted$ = this.pizzaSvc.delivered(orderId)
  }





}
