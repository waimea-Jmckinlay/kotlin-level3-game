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
    fun connectNorth(location: Location) {
        north = location
    }
    fun connectSouth(location: Location) {
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
        val beach = Location("Beach", " you see the water but your not there yet but you ar also pass the seagulls", "images/beach.png")
        val grass = Location("open plain", " a lot of grass and bees you don't fell like turning back", "images/grass.png")
        val grass2 = Location("open plain", " a lot of grass and bees", "images/grass.png")
        val grass3 = Location("open plain", " a lot of garss", "images/grass.png")
        val grass4 = Location("grass", "seams like your out of the forest ", "images/grass.png")
        val nest = Location("nest", "a bunch of cracked eggs, you see the ocean and lots of seagulls lets not go that way right now", "images/nest.png")
        val beachSide = Location("beach side", " the beach looks the same as before but theirs a lot less seagulls and a log maybe that can help you ", "images/beachside.png")
        val win = Location("ocean", " you made it to the ocean. had fun? play again?", "images/ocean.png", true)
        val lose = Location("not the ocean","you died seagulls, gotta hate them", "images/lose.png", true)
        val water = Location("water", "a lot of water all most there", "images/water.png")
        val tree = Location("tree", " A very tall tree or are you just very small", "images/tree.png")
        val tree2 = Location ("tree", "A very tall tree or are you just very small it stands in your way not letting you pass", "images/tree.png")
        val pond = Location ("pond", "A pond of water a not salty enough for you but you can try anyway ", "images/pond.png")
        val sand = Location("sand and seagulls","this was a bad idea right now ", "images/sand.png" )
        val sand2 = Location("sand and seagulls","their are still seaguls on the beach but not as many you could make a run for it ", "images/sand.png")
        val bigTree = Location("bigTree", " this one is very big could be home for other animals be cerfull of foxes  ", "images/biggerTree.png" )
        val fox = Location("fox","you where eaten by the fox they like sneaking up on things be cerfull where you move next time ", "images/fox.png",true)
        val salt = Location("salt"," some turtles can live here but your a Marine Turtle so over time you got sick a died", "images/pond.png",true)
        val footprints = Location( "it's a foot","a muddy footprint lays in the ground fresh and wet","images/footprint.png" )
        val bush = Location("bush", "a berries bush tasty but where are the berries do we investagate?", "images/bush.png")
        val bush2 = Location("bush again","this looks just like the last one how odd", "images/bush.png" )
        val forest = Location ("Forest","a dark forest this is not ment to be where you are going", "images/forest.png")
        val forest2 = Location ("who knows where this is?","your lost you want to go back but where is back excaly?", "images/forest.png",true)
        val Ruins = Location("Ruins","a biunch of standing stones and doorways the foot prints led here.", "images/ruins.png")
        val Backpack = Location ("Backpack", "someone must of been here a long time ago", "images/backpack.png")
        val Bear = Location ("It's a bear", " you got step on you he didn't mean too he just didn't see you shouldn't of investagate", "images/bear.png",true)
        val cave = Location("A dark cave","A big cave their was no way you can gety pass it and it's very scarry", "images/cave.png")
        val cave2 = Location (" a dark place ","it's very cold and you skin frezze . your stuck in place and will died soon but at lest the glow worms on the roof looks cool", "images/cave2.png",true)


        //adding to list ----------------------------------------------------------

        places.add(nest)
        places.add(beachSide)

        places.add(log)

        places.add(beach)

        places.add(grass)
        places.add(grass2)
        places.add(grass3)
        places.add(grass4)

        places.add(win)
        places.add(lose)
        places.add(fox)

        places.add(water)
        places.add(salt)

        places.add(tree)
        places.add(tree2)

        places.add(bush)
        places.add(bush2)

        places.add(sand)
        places.add(sand2)

        places.add(bigTree)

        places.add(pond)
        places.add(footprints)

        places.add(forest)
        places.add(forest2)

        places.add(Ruins)

        places.add(Backpack)

        places.add(Bear)
        places.add(cave)
        places.add(cave2)



        //connecting locations togiver left or right -------------------------------
        nest.connectNorth(sand)
        nest.connectEast(sand)
        nest.connectWest(sand)
        nest.connectSouth(tree)
        //
        sand.connectNorth(lose)
        sand.connectEast(lose)
        sand.connectWest(lose)
        sand.connectSouth(nest)
        //
        tree.connectSouth(nest)
        tree.connectNorth(grass)
        tree.connectWest(grass2)
        tree.connectEast(grass3)
        //
        grass.connectWest(grass2)
        grass.connectEast(grass3)
        grass.connectNorth(bigTree)
        //
        grass2.connectEast(bigTree)
        grass2.connectWest(tree)
        grass2.connectNorth(tree2)
        grass2.connectSouth(grass)
        //
        tree2.connectWest(fox)
        tree2.connectEast(bigTree)
        //
        grass3.connectWest(bigTree)
        grass3.connectNorth(pond)
        grass3.connectEast(tree)
        grass3.connectSouth(grass)
        //
        pond.connectWest(bigTree)
        pond.connectEast(tree)
        pond.connectNorth(salt)
        //
        bigTree.connectEast(footprints)
        bigTree.connectWest(bush)

        bush.connectSouth(fox)
        bush.connectNorth(bush2)
        bush.connectEast(forest)
        bush.connectWest(cave)
        //
        bush2.connectEast(forest)
        bush2.connectWest(cave)
        bush.connectNorth(Bear)

        cave.connectNorth(cave2)
        cave.connectSouth(fox)

        footprints.connectNorth(Ruins)
        footprints.connectSouth(fox)
        footprints.connectWest(forest)
        //
        forest.connectSouth(fox)
        forest.connectNorth(forest2)
        //
        Ruins.connectNorth(fox)
        Ruins.connectSouth(footprints)
        Ruins.connectWest(forest)
        Ruins.connectEast(Backpack)
        //
        Backpack.connectNorth(grass4)
        //
        grass4.connectNorth(beachSide)
        //
        beachSide.connectNorth(sand2)
        //
        sand2.connectNorth(lose)
        sand2.connectSouth(beachSide)
        sand2.connectWest(log)
        sand2.connectEast(lose)
        //
        log.connectEast(beach)
        log.connectSouth(lose)
        //
        beach.connectNorth(water)
        //
        water.connectNorth(win)

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


