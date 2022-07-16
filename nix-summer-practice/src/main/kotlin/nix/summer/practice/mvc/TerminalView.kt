package nix.summer.practice.mvc

class TerminalView(override var controller: Controller) : View {

    override fun showInfo(info: Resources) {
        println("The coffee machine has: \n" +
                "${info.water} ml of water\n" +
                "${info.milk} ml of milk\n" +
                "${info.coffeeBeans} g og coffee beans\n" +
                "${info.disposableCups} of disposable cups\n" +
                "${info.money} of money\n")
    }

    fun enterCommand(): String {
        println("Write action (buy, fill, take, remaining, exit): ")
        return readln()
    }

    override fun buyMenu() : CoffeeTypes?{
        println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, 4 - go back to the previous menu:")
        return when (retryEnterIntPositiveValue()) {
            1 -> CoffeeTypes.ESPRESSO
            2 -> CoffeeTypes.LATTE
            3 -> CoffeeTypes.CAPPUCCINO
            4 -> null
            else -> {
                println("Wrong number of command...")
                null
            }
        }
    }

    override fun canIMakeCoffee(message: Response?) {
        if (message != null)
            println(message.answer)
    }

    override fun enterResourcesToFill() : Resources{
        println("Write how many ml of water you want to add:")
        val water = retryEnterIntPositiveValue()
        println("Write how many ml of milk you want to add:")
        val milk = retryEnterIntPositiveValue()
        println("Write how many grams of coffee beans you want to add:")
        val coffeeBeans = retryEnterIntPositiveValue()
        println("Write how many disposable coffee cups you want to add:")
        val cups = retryEnterIntPositiveValue()
        return Resources(water, milk, coffeeBeans, cups)
    }

    private fun retryEnterIntPositiveValue() : Int {
        var value : Int? = readln().toIntOrNull()
        while (value == null || value < 0) {
            println("Enter only positive numbers. Please, try again...")
            value = readln().toIntOrNull()
        }
        return value
    }
}
