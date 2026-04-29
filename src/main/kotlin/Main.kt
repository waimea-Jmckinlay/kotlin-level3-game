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
    var west:Location? = null
    var east:Location? = null
    var north:Location? = null
    var South:Location? = null

    fun connectEast(location: Location) {
        east = location

    }
    fun connectWest(location: Location) {
        west = location
    }
    fun connectnorth(location: Location) {
        north = location
    }
    fun connectsouth(location: Location) {
        South = location
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
        val log = Location("Grubby log","it's horrible in here wet and slimy... whats that?", "images/log.png")
        val beach = Location("Beach", " you see the water but your not there yet", "images/beach.png")
        val grass = Location("open plain", " a lot of grass and bees you don't fell like turning back", "images/grass.png")
        val grass2 = Location("open plain", " a lot of grass and bees", "images/grass.png")
        val grass3 = Location("open plain", " a lot of garss", "images/grass.png")
        val nest = Location("nest", "a bunch of cracked eggs, you see the ocean and lots of seagulls lets not go that way right now", "images/nest.png")
        val Nest = Location("nest", "a bunch of cracked eggs, the ocean is as you remember it from before but theirs a lot less seagulls", "images/nest.png")
        val win = Location("ocean", " you made it to the ocean play again?", "images/ocean.png", true)
        val lose = Location("not the ocean","you died seagulls, gotta hate them", "images/lose.png", true)
        val water = Location("water", "a lot of water all most there", "images/water.png")
        val tree = Location("tree", " A very tall tree or are you just very small", "images/tree.png")
        val tree2 = Location ("tree", "A very tall tree or are you just very small it stands in your way not letting you pass", "images/tree.png")
        val pond = Location ("pond", "A pond of water a not salty enough for you but you can try anyway ", "images/pond.png")
        val sand = Location("sand and seagulls","this was a bad idea right now ", "images/sand.png" )
        val bigTree = Location("bigTree", " this one is very big could be home for other animals  ", "images/biggerTree.png" )
        val fox = Location("fox","you where eaten by the fox", "images/fox.png",true)
        val salt = Location("salt"," some turtles can live here but your a Marine Turtle so over time you got sick a died", "images/pond.png",true)
        val footprints = Location( "it's a foot","a muddy footprint lays in the ground fresh and wet","images/footprint.png" )
        val bush = Location("bush", "a berries bush tasty but where are the berries?", "images/bush.png")
        val forest = Location ("Forest","a dark forest this is not ment to be where you are going", "images/forest.png")
        val forest2 = Location ("who knows where this is?","your lost you want to go back but where is back excaly?", "images/forest.png",true)
        val Ruins = Location("Ruins","a biunch of standing stones and doorways the foot prints led here.", "images/ruins.png")
        val Backpack = Location ("Backpack", "someone must of been here a long time ago", "images/backpack.png")
        //get a backpack image in the forest

        //adding to list ----------------------------------------------------------

        places.add(nest)
        places.add(Nest)

        places.add(log)

        places.add(beach)

        places.add(grass)
        places.add(grass2)
        places.add(grass3)

        places.add(win)
        places.add(lose)
        places.add(fox)

        places.add(water)

        places.add(tree)
        places.add(tree2)

        places.add(sand)

        places.add(bigTree)

        places.add(pond)
        places.add(footprints)

        places.add(forest)
        places.add(forest2)

        places.add(Ruins)



        //connecting locations togiver left or right -------------------------------
        nest.connectnorth(sand)
        nest.connectEast(sand)
        nest.connectWest(sand)
        nest.connectsouth(tree)
        //
        sand.connectnorth(lose)
        sand.connectEast(lose)
        sand.connectWest(lose)
        sand.connectsouth(nest)
        //
        tree.connectsouth(nest)
        tree.connectnorth(grass)
        tree.connectWest(grass2)
        tree.connectEast(grass3)
        //
        grass.connectWest(grass2)
        grass.connectEast(grass3)
        grass.connectnorth(bigTree)
        //
        grass2.connectEast(bigTree)
        grass2.connectWest(tree)
        grass2.connectnorth(tree2)
        grass2.connectsouth(grass)
        //
        tree2.connectWest(fox)
        tree2.connectEast(bigTree)
        //
        grass3.connectWest(bigTree)
        grass3.connectnorth(pond)
        grass3.connectEast(tree)
        grass3.connectsouth(grass)
        //
        pond.connectWest(bigTree)
        pond.connectEast(tree)
        pond.connectnorth(salt)
        //
        bigTree.connectEast(footprints)
        bigTree.connectWest(bush)
        //
        footprints.connectnorth(Ruins)
        footprints.connectsouth(fox)
        footprints.connectWest(forest)
        //
        forest.connectsouth(fox)
        forest.connectnorth(forest2)
        //
        Ruins.connectnorth(fox)
        Ruins.connectsouth(footprints)
        Ruins.connectWest(forest)
        Ruins.connectEast(Backpack)
        //-------------------------------------------------------------------------


        currentLocation = nest
    }
    fun moveLeft() {
        currentLocation = currentLocation.west!!
    }
    fun moveRight() {
        currentLocation = currentLocation.east!!
    }
    fun moveSouth(){
        currentLocation = currentLocation.South!!
    }
    fun moveNorth(){
        currentLocation = currentLocation.north!!
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

    private val westButton = JButton("go left")
    private val eastButton = JButton("go right")
    private val southButton = JButton("go backwords")
    private val northButton = JButton("go forward")
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
        westButton.setBounds(20, 630, 200, 50)
        eastButton.setBounds(420, 630, 200, 50)
        southButton.setBounds(220, 650, 200, 50)
        northButton.setBounds(220, 595, 200, 50)
        returnButton.setBounds(220, 630, 200, 50)



        panel.add(titleLabel)
        panel.add(gametext)
        panel.add(piclable)
        panel.add(westButton)
        panel.add(eastButton)
        panel.add(southButton)
        panel.add(northButton)
        panel.add(returnButton)


    }

    private fun setupStyles() {
        titleLabel.font = Font(Font.SANS_SERIF, Font.BOLD, 50)
        titleLabel.horizontalAlignment = SwingConstants.CENTER

        gametext.font = Font(Font.SANS_SERIF, Font.PLAIN, 20)
        gametext.horizontalAlignment = SwingConstants.CENTER


        piclable.font = Font(Font.SANS_SERIF, Font.PLAIN, 20)
        piclable.horizontalAlignment = SwingConstants.CENTER


        westButton.font = Font(Font.SANS_SERIF, Font.PLAIN, 20)
        westButton.background = Color(0xcc00447)

        eastButton.font = Font(Font.SANS_SERIF, Font.PLAIN, 20)
        eastButton.background = Color(0xcc00447)

        northButton.font = Font(Font.SANS_SERIF, Font.PLAIN, 20)
        northButton.background = Color(0xcc00447)

        southButton.font = Font(Font.SANS_SERIF, Font.PLAIN, 20)
        southButton.background = Color(0xcc00447)

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
        westButton.addActionListener { handleLeftClick() }
        eastButton.addActionListener { handleRightClick() }
        southButton.addActionListener { handleSouthClick() }
        northButton.addActionListener { handleNorthClick() }
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
    private fun handleNorthClick() {
        game.moveNorth()
        updateUI()
    }
    private fun handleSouthClick() {
        game.moveSouth()
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

            westButton.isVisible = false
            eastButton.isVisible = false
            southButton.isVisible = false
            northButton.isVisible = false
            returnButton.isVisible = true

        }
        else{
            returnButton.isVisible = false
            eastButton.isVisible = true
            westButton.isVisible = true
            southButton.isVisible = true
            northButton.isVisible = true

            eastButton.isEnabled = game.currentLocation.east != null
            westButton.isEnabled = game.currentLocation.west != null
            southButton.isEnabled = game.currentLocation.South != null
            northButton.isEnabled = game.currentLocation.north != null
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


