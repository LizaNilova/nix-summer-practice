package nix.summer.practice.mvc

import java.awt.FlowLayout
import java.awt.GridLayout
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField
import kotlin.system.exitProcess


class SwingView(override var controller: Controller) : JFrame(), View {

    private lateinit var mainFrame: JFrame
    private lateinit var controlPanel: JPanel
    private lateinit var fillPanel: JPanel
    private lateinit var takePanel: JPanel
    private lateinit var infoLabel: JLabel
    private lateinit var numInput: JTextField
    private lateinit var numInput2: JTextField
    private lateinit var numInput3: JTextField
    private lateinit var numInput4: JTextField
    private lateinit var errorLabel: JLabel
    private var typeMakingCoffee: CoffeeTypes? = null
    init {
        createUI()
    }

    private fun createUI() {
        title = SwingView::class.java.toString()
        controlPanel = JPanel().apply { layout = FlowLayout() }
        fillPanel = JPanel().apply { layout = GridLayout(5, 2, 5, 5) }
        takePanel = JPanel().apply { layout = FlowLayout(FlowLayout.CENTER) }

        mainFrame = JFrame("Swing View").apply {
            setSize(700,620)
            layout = GridLayout(3,1)
            addWindowListener(object: WindowAdapter() {
                override fun windowClosing(e: WindowEvent?) {
                    exitProcess(0)
                }
            })
            add(controlPanel)
            add(fillPanel)
            add(takePanel)
            isVisible = true
        }

        createControlPanel()
        createFillPanel()
        takePanel.add(JButton("Take money and run").apply {
            actionCommand = "take"
            addActionListener(ButtonClick(null))
        })
        errorLabel = JLabel("")
        takePanel.add(errorLabel)
        mainFrame.isVisible = true
    }


    private fun createControlPanel() {
        infoLabel = JLabel("-----------------")
        controlPanel.add(infoLabel)

        val espressoButton = JButton("Make Espresso").apply {
            actionCommand = "buy"
            addActionListener(ButtonClick(CoffeeTypes.ESPRESSO))
        }
        val cappuccinoButton = JButton("Make Cappuccino").apply {
            actionCommand = "buy"
            addActionListener(ButtonClick(CoffeeTypes.CAPPUCCINO))
        }
        val latteButton = JButton("Make Latte").apply {
            actionCommand = "buy"
            addActionListener(ButtonClick(CoffeeTypes.LATTE))
        }

        controlPanel.add(espressoButton)
        controlPanel.add(cappuccinoButton)
        controlPanel.add(latteButton)
    }

    private fun createFillPanel() {
        numInput = JTextField("0", 1)
        val labelWater = JLabel("        Water")
        numInput2 = JTextField("0", 1)
        val labelMilk = JLabel("        Milk")
        numInput3 = JTextField("0", 1)
        val labelCoffeeBeans = JLabel("        Coffee beans")
        numInput4 = JTextField("0", 1)
        val labelCups = JLabel("        Cups")
        fillPanel.add(labelWater)
        fillPanel.add(numInput)
        fillPanel.add(labelMilk)
        fillPanel.add(numInput2)
        fillPanel.add(labelCoffeeBeans)
        fillPanel.add(numInput3)
        fillPanel.add(labelCups)
        fillPanel.add(numInput4)

        val fillButton = JButton("Fill").apply {
            actionCommand = "fill"
            addActionListener(ButtonClick(null))
        }
        fillPanel.add(fillButton)
    }

    inner class ButtonClick (private val type : CoffeeTypes?) : ActionListener {
        override fun actionPerformed(e: ActionEvent?) {
            if (e != null) {
                if (type != null){
                    typeMakingCoffee = type
                }
                controller.takeCommand(e.actionCommand)
            }
        }
    }

    override fun canIMakeCoffee(message: Response?) {
        if (message != null) {
            errorLabel.text = message.answer
        }
    }

    override fun enterResourcesToFill(): Resources {
        return Resources(numInput.text.toInt(), numInput2.text.toInt(), numInput3.text.toInt(), numInput4.text.toInt(), 0)
    }
    override fun buyMenu()  : CoffeeTypes?{
        return typeMakingCoffee
    }

    override fun showInfo(info: Resources) {
        infoLabel.text = "<html>The coffee machine has: <br><ul>" +
                "<li>${info.water} ml of water</li><br>" +
                "<li>${info.milk} ml of milk</li><br>" +
                "<li>${info.coffeeBeans} g og coffee beans</li><br>" +
                "<li>${info.disposableCups} of disposable cups</li><br>" +
                "<li>${info.money} of money</li>"
    }
}
