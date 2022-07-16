package nix.summer.practice.mvc


interface View {

    var controller: Controller

    fun showInfo(info: Resources)

    fun buyMenu() : CoffeeTypes?

    fun canIMakeCoffee(message : Response?)

    fun enterResourcesToFill() : Resources
}
