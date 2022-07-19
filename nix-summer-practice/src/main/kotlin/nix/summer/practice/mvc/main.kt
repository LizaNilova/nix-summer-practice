package nix.summer.practice.mvc


fun main() {
    val service = CoffeeMachineService()
    val controller = Controller(service)
    val terminalView = TerminalView(controller)
    controller.attachView(terminalView)

    controller.start()

    while(controller.takeCommand(terminalView.enterCommand())) { }

    val swingView = SwingView(controller)
    controller.attachView(swingView)
    controller.start()
}
