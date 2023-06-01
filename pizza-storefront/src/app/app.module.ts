import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { MainComponent } from './components/main.component';
import { OrdersComponent } from './components/orders.component';
import { HttpClientModule } from '@angular/common/http'
import { ReactiveFormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { PizzaService } from './pizza.service';

const appRoutes: Routes = [
  { path: '', component: MainComponent, title: 'Main Page' },

  { path: 'orders/:email', component: OrdersComponent, title: 'Orders'},
  { path: '**', redirectTo: '/', pathMatch: 'full' }
]

@NgModule({
  declarations: [
    AppComponent,
    MainComponent,
    OrdersComponent,
  ],
  imports: [
    BrowserModule, ReactiveFormsModule, HttpClientModule,
    RouterModule.forRoot(appRoutes, { useHash: true})
  ],

  providers: [PizzaService],
  bootstrap: [AppComponent]
})
export class AppModule { }
