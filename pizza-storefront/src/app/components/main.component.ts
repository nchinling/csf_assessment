import { Component, OnInit, inject } from '@angular/core';
import { FormGroup, FormBuilder, FormArray, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { PizzaService } from '../pizza.service';

const SIZES: string[] = [
  "Personal - 6 inches",
  "Regular - 9 inches",
  "Large - 12 inches",
  "Extra Large - 15 inches"
]

const PIZZA_TOPPINGS: string[] = [
    'chicken', 'seafood', 'beef', 'vegetables',
    'cheese', 'arugula', 'pineapple'
]

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  form!: FormGroup
  toppingArr!: FormArray
  fb=inject(FormBuilder)
  uploadSvc = inject(PizzaService)
  router = inject(Router)



  pizzaSize = SIZES[0]

  constructor() { }

  updateSize(size: string) {
    this.pizzaSize = SIZES[parseInt(size)]
  }

  ngOnInit(): void{

    this.createForm()

  }


  processForm(){
    const data = this.form.value
    console.info('>>> data: ', data)


  }



  private createForm() {
    this.toppingArr = this.fb.array([Validators.required])
    this.form = this.fb.group({
      name: this.fb.control<string>('', [ Validators.required ]),
      email: this.fb.control<string>('', [ Validators.required ]),
      size: this.fb.control<number>(0, [ Validators.required ]),
      base: this.fb.control<string>('', [ Validators.required ]),
      sauce: this.fb.control<string>('', [ Validators.required ]),
      toppings: this.toppingArr,
      comments: this.fb.control<string>(''),
      
    })
  }





}
