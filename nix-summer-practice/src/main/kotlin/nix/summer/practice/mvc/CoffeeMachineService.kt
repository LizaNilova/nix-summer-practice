package nix.summer.practice.mvc


data class Resources(val water: Int = 0,
                     val milk: Int = 0,
                     val coffeeBeans: Int = 0,
                     val disposableCups: Int = 0,
                     val money: Int = 0)

data class Response(val answer: String)



enum class CoffeeTypes(val water : Int, val milk : Int, val coffeeBeans : Int, val cost : Int) {
    ESPRESSO(250, 0, 16, 4),
    CAPPUCCINO(200, 100, 12, 6),
    LATTE(350, 75, 20, 7)
}

class CoffeeMachineService (var water : Int = 400,
                            var milk : Int = 540,
                            var coffeeBeans : Int = 120,
                            var disposableCups: Int = 9,
                            private var money : Int = 550) {

    fun canIMakeThatMuchCoffee(countOfCups : Int) : Response{
        var enoughFlag = true
        if (this.water < (200 * countOfCups)
            || this.milk < (50 * countOfCups)
            || this.coffeeBeans < (15 * countOfCups)) {
            enoughFlag = false
        }
        return if (enoughFlag){
            if (this.water >= (200 * (countOfCups + 1))
                && this.milk >= (50 * (countOfCups + 1))
                && this.coffeeBeans >= (15 * (countOfCups + 1))) {
                Response("Yes, I can make that amount of coffee (and even ${minCountAdditionalCups(countOfCups)} more than that)")
//                minCountAdditionalCups(countOfCups) //here minCount > 0
            } else {
                Response("Yes, I can make that amount of coffee.")
//                0 //because there are 0 additional cups
            }
        } else {
            Response("No, I can make only ${minCountAdditionalCups(0)} cups of coffee")
//            -minCountAdditionalCups(0) //returns negative value, which will be converted to positive in controller
        }
    }

    private fun minCountAdditionalCups(countOfCups: Int) : Int {
        var min = (this.water / 200) - countOfCups
        if (min >= ((this.milk / 50) - countOfCups)) {
            min = (this.milk / 50) - countOfCups
        }
        if (min >= ((this.coffeeBeans / 15) - countOfCups)) {
            min = (this.coffeeBeans / 15) - countOfCups
        }
        return min
    }

    fun makeSomeCoffee(coffee : CoffeeTypes?) : Response? {
        if (coffee == null) {
            return null
        } else {
            if (this.water < coffee.water) {
                return Response("Sorry, not enough water!")
            }
            if (this.milk < coffee.milk) {
                return Response("Sorry, not enough milk!")
            }
            if (this.coffeeBeans < coffee.coffeeBeans) {
                return Response("Sorry, not enough coffee beans!")
            }
            if (this.disposableCups < 1) {
                return Response("Sorry, not enough paper cups!")
            } else {
                this.water -= coffee.water
                this.milk -= coffee.milk
                this.coffeeBeans -= coffee.coffeeBeans
                this.disposableCups--
                this.money += coffee.cost
                return Response("I have enough resources, making you a coffee!")
            }
        }
    }


    fun info() : Resources {
        return Resources(this.water, this.milk, this.coffeeBeans, this.disposableCups, this.money)
    }

    fun takeMoney() {
        //println("I gave you ${this.money}")
        this.money = 0
    }

    fun fillResources(resources: Resources) {
        this.water += resources.water
        this.milk += resources.milk
        this.coffeeBeans += resources.coffeeBeans
        this.disposableCups += resources.disposableCups
    }
}
