import com.formdev.flatlaf.themes.FlatMacDarkLaf
import java.awt.Color
import java.awt.Font
import javax.swing.*



class Location(

    val name:String,
    val description:String,
    var death: Boolean = false
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



    FlatMacDarkLaf.setup() // Initialise the LAF
    Game().places[3]

    val game = Game()                // Get an app state object
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

    var currentLocation: Location

    init {
        //making locations-------------------------------------------------------
        val log = Location("Grubby log", "it's horrible in here wet and slim... whats that?")
        val beach = Location("Beach", " you see the water but your not there yet")
        val grass = Location("open plain", " a lot of grass and bees")
        val nest = Location("nest", "a bunch of cracked eggs, you see the oceon and lots of seagulls")
        val win = Location("oceon", " you made it to the oceon", true)
        val lose = Location("not the oceon","you died seagulls gotta hate them", true)
        val water = Location("water", "a lot of water all most there")
        val tree = Location("tree", " A very tall tree or are you just very small you can hear foxes")
        val sand = Location("sand and seagulls","this was a bad idea right now " )
        val bigTree = Location("bigTree", " this one is much bigger that you " )
        val fox =Location("for","you where eatenby the fox",true)

        //adding to list ----------------------------------------------------------
        places.add(log)
        places.add(beach)
        places.add(grass)
        places.add(nest)
        places.add(win)
        places.add(lose)
        places.add(water)
        places.add(tree)
        places.add(sand)
        places.add(bigTree)
        places.add(fox)

        //connecting locations togiver left or right ----------------------------
        nest.connectRight(sand)
        nest.connectLeft(grass)
        sand.connectRight(lose)
        sand.connectLeft(lose)
        grass.connectLeft(tree)
        grass.connectLeft(log)




        //-----------------------------------------------------------------------


        currentLocation = nest
    }

    fun moveLeft() {
        currentLocation = currentLocation.left!!
    }
    fun moveRight() {
        currentLocation = currentLocation.right!!
    }

    fun moveHome() {
        currentLocation = places[3]
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
    private val gametext = JLabel("")
    private val clue = JLabel("")
    private val leftButton = JButton("go left")
    private val rightButton = JButton("go right")
    private val returnButton = JButton("return to start")

    private val end = End(this, game)


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

        titleLabel.setBounds(550, -280, 1200, 600)
        gametext.setBounds( 30, 60, 1200, 600)
        clue.setBounds( 30,120,600,600)
        leftButton.setBounds(30, 150, 100, 50)
        rightButton.setBounds(1070, 150, 100, 50)
        returnButton.setBounds(500, 400, 200, 50)



        panel.add(titleLabel)
        panel.add(gametext)
        panel.add(clue)
        panel.add(leftButton)
        panel.add(rightButton)
        panel.add(returnButton)


    }

    private fun setupStyles() {
        titleLabel.font = Font(Font.SANS_SERIF, Font.BOLD, 50)

        gametext.font = Font(Font.SANS_SERIF, Font.PLAIN, 20)


        leftButton.font = Font(Font.SANS_SERIF, Font.PLAIN, 20)
        leftButton.background = Color(0xcc0055)

        rightButton.font = Font(Font.SANS_SERIF, Font.PLAIN, 20)
        rightButton.background = Color(0xcc0055)

        returnButton.font = Font(Font.SANS_SERIF, Font.PLAIN, 20)
        returnButton.background = Color(0xcc0055)





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
        returnButton.addActionListener { handleClick()}

    }

    private fun handleLeftClick() {

        game.moveLeft()
        updateUI()


    }

    private fun handleRightClick() {
        game.moveRight()
        end.show()

        updateUI()



    }
    private fun handleClick() {
        game.moveHome()

        updateUI()
    }

    fun updateUI() {
        titleLabel.text = game.currentLocation.name
        gametext.text = game.currentLocation.description

        if (game.currentLocation.death == true) {

            returnButton.isEnabled = true

        }
        else{
            returnButton.isEnabled = false
        }


        end.dissplay()

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


class End(val owner: MainWindow, val game: Game) {
    private val dialog = JDialog(owner.frame, "DIALOG TITLE", false)
    private val panel = JPanel().apply { layout = null }

    private val win = JLabel()



    init {
        setupLayout()
        setupStyles()

        setupWindow()
        dissplay()

    }

    private fun setupLayout() {
        panel.preferredSize = java.awt.Dimension(240, 180)

        win.setBounds(30, 30, 180, 60)



        panel.add(win)


    }

    private fun setupStyles() {
        win.font = Font(Font.SANS_SERIF, Font.PLAIN, 16)


    }

    private fun setupWindow() {
        dialog.isResizable = false                              // Can't resize
        dialog.defaultCloseOperation = JDialog.HIDE_ON_CLOSE    // Hide upon window close
        dialog.contentPane = panel                              // Main content panel
        dialog.pack()
    }





    fun dissplay() {
        // Use app properties to display state
        win.text = "<html>User: ${game.currentLocation}</html>"







    }

    fun show() {
        val ownerBounds = owner.frame.bounds          // get location of the main window
        dialog.setLocation(                           // Position next to main window
            ownerBounds.x + ownerBounds.width + 10,
            ownerBounds.y
        )

        dialog.isVisible = true
    }
}