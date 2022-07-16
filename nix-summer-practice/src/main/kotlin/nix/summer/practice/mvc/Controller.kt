package nix.summer.practice.mvc


class Controller(private val model: CoffeeMachineService) {

    private lateinit var view: View

    fun attachView(_view: View) {
        view = _view
    }

    fun start() {
        view.showInfo(model.info())
    }

    fun takeCommand(com : String) : Boolean{
        when(com) {
            "buy" -> view.canIMakeCoffee(model.makeSomeCoffee(view.buyMenu()))
            "fill" -> fillResources(view.enterResourcesToFill())
            "take" -> model.takeMoney()
            "exit" -> return false
            "remaining" -> {
                view.showInfo(model.info())
                return true
            }
            else -> println("Wrong name of command ...")
        }
        view.showInfo(model.info())
        return true
    }

    private fun fillResources(resources: Resources) {
        model.fillResources(resources)
    }
}
