import com.formdev.flatlaf.themes.FlatMacDarkLaf
import java.awt.Color
import java.awt.Font
import javax.swing.*



class Location(

    val name:String,
    val description:String,
    val imageFile:String,
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
    Game().places[0]

    val game = Game()                // Get an app state object
    val window = MainWindow(game)    // Spawn the UI, passing in the app state

    SwingUtilities.invokeLater { window.show() }
}




/**
 * Manage app state
 *
 * @property name the user's name
 * @property Game the game
 */
class Game {
    var name = "Test"
    val places: MutableList<Location> = mutableListOf<Location>()

    var currentLocation: Location

    init {
        //making locations-------------------------------------------------------
        val log = Location("Grubby log","it's horrible in here wet and slim... whats that?", "images/log.png")
        val beach = Location("Beach", " you see the water but your not there yet", "images/beach.png")
        val grass = Location("open plain", " a lot of grass and bees", "images/grass.png")
        val nest = Location("nest", "a bunch of cracked eggs, you see the oceon and lots of seagulls lets not go that way right now", "images/nest.png")
        val Nest = Location("nest", "a bunch of cracked eggs, the oceon is as you rember it from before but theirs a lot less seagulls", "images/nest.png")
        val win = Location("oceon", " you made it to the oceon play again?", "images/ocean.png", true)
        val lose = Location("not the oceon","you died seagulls, gotta hate them", "images/log.png", true)
        val water = Location("water", "a lot of water all most there", "images/water.png")
        val tree = Location("tree", " A very tall tree or are you just very small you can hear foxes", "images/tree.png")
        val sand = Location("sand and seagulls","this was a bad idea right now ", "images/sand.png" )
        val bigTree = Location("bigTree", " this one is much bigger that you ", "images/biggerTree.png" )
        val fox = Location("fox","you where eaten by the fox", "images/fox.png",true)

        //adding to list ----------------------------------------------------------

        places.add(nest)
        places.add(Nest)
        places.add(log)
        places.add(beach)
        places.add(grass)
        places.add(win)
        places.add(lose)
        places.add(water)
        places.add(tree)
        places.add(sand)
        places.add(bigTree)
        places.add(fox)

        //connecting locations togiver left or right -------------------------------
        nest.connectRight(sand)
        nest.connectLeft(grass)
        sand.connectRight(lose)
        sand.connectLeft(lose)
        grass.connectLeft(tree)
        grass.connectRight(log)
        tree.connectLeft(bigTree)
        tree.connectRight(log)
        bigTree.connectLeft(fox)
        bigTree.connectRight(log)
        log.connectRight(Nest)
        log.connectLeft(bigTree)
        Nest.connectLeft(beach)
        Nest.connectRight(grass)
        beach.connectLeft(lose)
        beach.connectRight(water)
        water.connectLeft(beach)
        water.connectRight(win)
        //-------------------------------------------------------------------------


        currentLocation = nest
    }
    fun moveLeft() {
        currentLocation = currentLocation.left!!
    }
    fun moveRight() {
        currentLocation = currentLocation.right!!
    }
    fun moveHome() {
        currentLocation = places[0]
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
    private val piclable =JLabel()

    private val leftButton = JButton("go left")
    private val rightButton = JButton("go right")
    private val returnButton = JButton("return to start")




    // Pass app state to dialog too

    init {
        setupLayout()
        setupStyles()
        setupActions()
        setupWindow()
        updateUI()
    }

    private fun setupLayout() {
        panel.preferredSize = java.awt.Dimension(640, 700)

        titleLabel.setBounds(20, 20, 600, 50)
        gametext.setBounds( 20, 510, 600, 100)
        piclable.setBounds( 20, 90, 600, 480)
        leftButton.setBounds(20, 630, 200, 50)
        rightButton.setBounds(420, 630, 200, 50)
        returnButton.setBounds(220, 630, 200, 50)



        panel.add(titleLabel)
        panel.add(gametext)
        panel.add(piclable)
        panel.add(leftButton)
        panel.add(rightButton)
        panel.add(returnButton)


    }

    private fun setupStyles() {
        titleLabel.font = Font(Font.SANS_SERIF, Font.BOLD, 50)
        titleLabel.horizontalAlignment = SwingConstants.CENTER

        gametext.font = Font(Font.SANS_SERIF, Font.PLAIN, 20)
        gametext.horizontalAlignment = SwingConstants.CENTER


        piclable.font = Font(Font.SANS_SERIF, Font.PLAIN, 20)
        piclable.horizontalAlignment = SwingConstants.CENTER


        leftButton.font = Font(Font.SANS_SERIF, Font.PLAIN, 20)
        leftButton.background = Color(0xcc00447)

        rightButton.font = Font(Font.SANS_SERIF, Font.PLAIN, 20)
        rightButton.background = Color(0xcc00447)

        returnButton.font = Font(Font.SANS_SERIF, Font.PLAIN, 20)
        returnButton.background = Color(0x0029CCFF)






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


        updateUI()



    }
    private fun handleClick() {
        game.moveHome()

        updateUI()
    }


    fun updateUI() {
        titleLabel.text = game.currentLocation.name
        gametext.text = "<html><center>" + game.currentLocation.description

        val image = game.currentLocation.imageFile
        val icon = ImageIcon(ClassLoader.getSystemResource(image))

        piclable.icon = icon

        if (game.currentLocation.death == true) {

            leftButton.isVisible = false
            rightButton.isVisible = false
            returnButton.isVisible = true

        }
        else{
            returnButton.isVisible = false
            rightButton.isVisible = true
            leftButton.isVisible = true
        }




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


