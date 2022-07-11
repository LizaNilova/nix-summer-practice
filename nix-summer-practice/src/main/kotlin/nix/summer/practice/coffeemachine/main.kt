class CoffeeMachine(private var waterReserve : Int,
                    private var milkReserve : Int,
                    private var coffeeBeansReserve : Int,
                    private var countOfPaperCups: Int){
    private var money : Int = 550

    companion object {
        fun automaticMakingCoffee (){
            println("Starting to make a coffee in NIX office\n" +
                    "Grinding coffee beans\n" +
                    "Boiling water\n" +
                    "Mixing boiled water with crushed coffee beans\n" +
                    "Pouring coffee into the cup\n" +
                    "Pouring some milk into the cup\n" +
                    "Coffee is ready! Go to work!\n")
        }
    }

    fun neededCountOfIngredients(count : Int) {
        println("For $count cups of coffee you will need:\n" +
                "${this.waterReserve * count} ml of water\n" +
                "${this.milkReserve * count} ml of milk\n" +
                "${this.coffeeBeansReserve * count} g of coffee beans")
    }

    private fun minCountAdditionalCups(countOfCups: Int) : Int {
        var min = (this.waterReserve / 200) - countOfCups
        if (min >= ((this.milkReserve / 50) - countOfCups)) {
            min = (this.milkReserve / 50) - countOfCups
        }
        if (min >= ((this.coffeeBeansReserve / 15) - countOfCups)) {
            min = (this.coffeeBeansReserve / 15) - countOfCups
        }
        return min
    }

    fun canIMakeThatMuchCoffee(countOfCups : Int) {
        var enoughFlag = true
        if (this.waterReserve < (200 * countOfCups)
                || this.milkReserve < (50 * countOfCups)
                || this.coffeeBeansReserve < (15 * countOfCups)) {
            enoughFlag = false
        }
        if (enoughFlag){
            if (this.waterReserve >= (200 * (countOfCups + 1))
                    && this.milkReserve >= (50 * (countOfCups + 1))
                    && this.coffeeBeansReserve >= (15 * (countOfCups + 1))) {
                println("Yes, I can make that amount of coffee (and even ${minCountAdditionalCups(countOfCups)} more than that)")
            } else {
                println("Yes, I can make that amount of coffee.")
            }
        } else {
            println("No, I can make only ${minCountAdditionalCups(0)} cups of coffee")
        }
    }

    private fun makeSomeCoffee(coffee : CoffeeTypes){
        if (this.waterReserve < coffee.water) {
            println("Sorry, not enough water!")
        } else if (this.milkReserve < coffee.milk) {
            println("Sorry, not enough milk!")
        } else if (this.coffeeBeansReserve < coffee.coffeeBeans) {
            println("Sorry, not enough coffee beans!")
        } else if (this.countOfPaperCups < 1) {
            println("Sorry, not enough paper cups!")
        } else {
            println("I have enough resources, making you a coffee!")
            this.waterReserve -= coffee.water
            this.milkReserve -= coffee.milk
            this.coffeeBeansReserve -= coffee.coffeeBeans
            this.countOfPaperCups--
            this.money += coffee.cost
        }
    }

    fun printCoffeeMachineStatus() {
        println("The coffee machine has: \n" +
                "${this.waterReserve} ml of water\n" +
                "${this.milkReserve} ml of milk\n" +
                "${this.coffeeBeansReserve} g og coffee beans\n" +
                "${this.countOfPaperCups} of disposable cups\n" +
                "${this.money} of money\n")
    }

    fun fillResources() {
        println("Write how many ml of water you want to add:")
        this.waterReserve += retryEnterIntPositiveValue()
        println("Write how many ml of milk you want to add:")
        this.milkReserve += retryEnterIntPositiveValue()
        println("Write how many grams of coffee beans you want to add:")
        this.coffeeBeansReserve += retryEnterIntPositiveValue()
        println("Write how many disposable coffee cups you want to add:")
        this.countOfPaperCups += retryEnterIntPositiveValue()
    }

    fun takeMoney() {
        println("I gave you ${this.money}")
        this.money = 0
    }

    fun chooseCommand(com : String) : Boolean{
        when(com){
            "buy" -> {
                println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, 4 - go back to the previous menu:")
                when (retryEnterIntPositiveValue()){
                    1 -> makeSomeCoffee(CoffeeTypes.ESPRESSO)
                    2 -> makeSomeCoffee(CoffeeTypes.LATTE)
                    3 -> makeSomeCoffee(CoffeeTypes.CAPPUCCINO)
                    4 -> return true
                    else -> {
                        println("Wrong number of command...")
                    }
                }
            }
            "fill" -> {
                fillResources()
            }
            "take" -> {
                takeMoney()
            }
            "exit" -> {
                return false
            }
            "remaining" -> {
                printCoffeeMachineStatus()
            }
            else -> {
                println("Wrong name of command ...")
            }
        }
        return true
    }
}

enum class CoffeeTypes(val water : Int, val milk : Int, val coffeeBeans : Int, val cost : Int) {
    ESPRESSO(250, 0, 16, 4),
    CAPPUCCINO(200, 100, 12, 6),
    LATTE(350, 75, 20, 7)
}

fun retryEnterIntPositiveValue() : Int {
    var value : Int? = readln().toIntOrNull()
    while (value == null || value < 0) {
        println("Enter only positive numbers. Please, try again...")
        value = readln().toIntOrNull()
    }
    return value
}

fun main() {
//    ----------------------------------------------------------------
//    1-st stage
//    ----------------------------------------------------------------
//    CoffeeMachine.automaticMakingCoffee()

//    ----------------------------------------------------------------
//    2-nd stage
//    ----------------------------------------------------------------
//    val coffeeMachine1 = CoffeeMachine(200, 50, 15, 0)
//    println("Write how many cups of coffee you will need:")
//    var countOfCups : Int? = readln().toIntOrNull()
//    while (countOfCups == null || countOfCups < 0) {
//        println("Enter only positive numbers. Please, try again...")
//        countOfCups = readln().toIntOrNull()
//    }
//    coffeeMachine1.neededCountOfIngredients(countOfCups)

//    ----------------------------------------------------------------
//    3-rd stage
//    ----------------------------------------------------------------
//    println("Write how many ml of water the coffee machine has: ")
//    val waterReserve : Int = retryEnterIntPositiveValue()
//
//    println("Write how many ml of milk the coffee machine has: ")
//    val milkReserve : Int = retryEnterIntPositiveValue()
//
//    println("Write how many grams of coffee beans the coffee machine has: ")
//    val coffeeBeansReserve : Int = retryEnterIntPositiveValue()
//
//    val coffeeMachine2 = CoffeeMachine(waterReserve, milkReserve, coffeeBeansReserve, 0)
//
//    println("Write how many cups of coffee you will need: ")
//    val countOfCups1 : Int = retryEnterIntPositiveValue()
//
//    coffeeMachine2.canIMakeThatMuchCoffee(countOfCups1)

//    ----------------------------------------------------------------
//    4-th stage
//    ----------------------------------------------------------------
//    val coffeeMachine3 = CoffeeMachine(400, 540, 120, 9)
//    coffeeMachine3.printCoffeeMachineStatus()
//    println("Write action (buy, fill, take):")
//    when (readln()) {
//        "buy" -> {
//            println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:")
//            when (retryEnterIntPositiveValue()){
//                1 -> coffeeMachine3.buyCoffee(CoffeeTypes.ESPRESSO)
//                2 -> coffeeMachine3.buyCoffee(CoffeeTypes.LATTE)
//                3 -> coffeeMachine3.buyCoffee(CoffeeTypes.CAPPUCCINO)
//                else -> {
//                    println("Wrong number of command...")
//                }
//            }
//        }
//        "fill" -> {
//            coffeeMachine3.fillResources()
//        }
//        "take" -> {
//            coffeeMachine3.takeMoney()
//        }
//        else -> {
//            println("Wrong name of command ...")
//        }
//    }
//    coffeeMachine3.printCoffeeMachineStatus()

//    ----------------------------------------------------------------
//    5-th stage
//    ----------------------------------------------------------------
    val coffeeMachine = CoffeeMachine(400, 540, 120, 9)
    var flag = true

    while (flag) {
        println("Write action (buy, fill, take, remaining, exit):")
        flag = coffeeMachine.chooseCommand(readln())
    }
}
