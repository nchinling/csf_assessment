
export interface FormData {
    name: string
    email: string
    size: number
    base: string
    sauce: string
    toppings: string[]
    comments: string

}

export interface OrderResponse {
    orderId: string
    // date: Date
    date: string
    name: number
    email: string
    total: number

}

