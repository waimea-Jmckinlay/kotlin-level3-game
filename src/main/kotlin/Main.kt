import com.formdev.flatlaf.themes.FlatMacDarkLaf
import java.awt.Color
import java.awt.Font
import javax.swing.*



class Location(

    val name:String,
    val description:String
){
    var left:Location? = null
    var right:Location? = null

    fun connectRight(location: Location) {
        right = location
    }
    fun connectLeft(location: Location) {
        left = location
    }

}



/**
 * Application entry point
 */
fun main() {
    FlatMacDarkLaf.setup()          // Initialise the LAF

    val game = Game()                 // Get an app state object
    val window = MainWindow(game)    // Spawn the UI, passing in the app state

    SwingUtilities.invokeLater { window.show() }
}


/**
 * Manage app state
 *
 * @property name the user's name
 * @property score the points earned
 */
class Game {
    var name = "Test"
    val places: MutableList<Location> = mutableListOf<Location>()

    init {
        //making locations-------------------------------------------------------
        val log = Location("Grubby log", "it's horrible in here")
        val beach = Location("Beach", "I see the water")
        val grass = Location("open plain", " a lot of grass")

        //adding to list ----------------------------------------------------------
        places.add(log)
        places.add(beach)
        places.add(grass)

        //connecting locations togiver left or right ----------------------------
        log.connectRight(beach)
        log.connectLeft(grass)

    }





}


/**
 * Main UI window, handles user clicks, etc.
 *
 * @param app the app state object
 */
class MainWindow(val game: Game) {
    val frame = JFrame("WINDOW TITLE")
    private val panel = JPanel().apply { layout = null }

    private val titleLabel = JLabel("Turtle game")
    private val gametext = JLabel("ergrnejwjng...")
    private val leftButton = JButton("go left")
    private val rightButton = JButton("go right")


    // Pass app state to dialog too

    init {
        setupLayout()
        setupStyles()
        setupActions()
        setupWindow()
        updateUI()
    }

    private fun setupLayout() {
        panel.preferredSize = java.awt.Dimension(1200, 600)

        titleLabel.setBounds(500, -280, 1200, 600)
        gametext.setBounds( 30, 60, 600, 600)
        leftButton.setBounds(30, 150, 100, 50)
        rightButton.setBounds(1070, 150, 100, 50)



        panel.add(titleLabel)
        panel.add(gametext)
        panel.add(leftButton)
        panel.add(rightButton)


    }

    private fun setupStyles() {
        titleLabel.font = Font(Font.SANS_SERIF, Font.BOLD, 32)


        leftButton.font = Font(Font.SANS_SERIF, Font.PLAIN, 20)
        leftButton.background = Color(0xcc0055)

        rightButton.font = Font(Font.SANS_SERIF, Font.PLAIN, 20)
        rightButton.background = Color(0xcc0055)




    }

    private fun setupWindow() {
        frame.isResizable = false                           // Can't resize
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE  // Exit upon window close
        frame.contentPane = panel                           // Define the main content
        frame.pack()
        frame.setLocationRelativeTo(null)                   // Centre on the screen
    }

    private fun setupActions() {
        leftButton.addActionListener { handleLeftClick() }
        rightButton.addActionListener { handleRightClick() }

    }

    private fun handleLeftClick() {



    }

    private fun handleRightClick() {

    }

    fun updateUI() {


    }

    fun show() {
        frame.isVisible = true
    }
}


/**
 * Info UI window is a child dialog and shows how the
 * app state can be shown / updated from multiple places
 *
 * @param owner the parent frame, used to position and layer the dialog correctly
 * @param app the app state object
 */
