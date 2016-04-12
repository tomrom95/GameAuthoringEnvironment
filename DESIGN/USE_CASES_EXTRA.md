Use Cases
====

###Extension Sprint 

During this sprint, we aspire to implement more dynamic and challenging components to our tower defense game to bring the game engine and game dynamics to life. To do this, we will bolster the codebase and features from each of our sub-teams.

41 use cases 

Written by: Dhrumil Patel and Jin An

####Authorship

*Wave Creation* (3)
We let users control each wave within levels by them specify what sprites and how fast they are being spawned.

1. User specifies enemy spawn rate
2. User designates what sprites are spawned
3. User defines how much time delay is between waves

*Win / Lose Condition* (2)
Conditions are specified by an object, action, and effect triple. The object is either a Sprite or group of Sprites, the action is picked from a discrete list of semi-customizable default actions, Use resource to allow attribute to have min / max / isGlobal values. .or the effect in this case is either winning/losing/next level.

1. User specifies winning condition for each level
2. User specifies whether each winning / losing conditions are global or local.

*Mid-Game Editing* (4) 
The user can return to the authoring environment to alter and enhance current game objects, interactions, and events

1. User can go back and alter the modules the enemies hold
2. User can remove or alter the path of an enemy 
3. User can simplify the win condition
4. User can create new conditions to enhance the gameplay dynamic 

*Terrains* (2)
Author creates a terrain object where users can specify on which terrain game players can create tower or on which terrain enemies can move.

1. User creates terrain object that contains profile (name, description, and image) and its functionality
2. User adds terrain objects in scene building tab

*Sprite Creation* (2) 
The creation of the objects in the game within the authoring environment 

1. Dynamic forms based on the user input during creation of sub-components 
2. Allow user to select AI modules for projectiles and enemies


*Global Attributes* (1) Global attributes are things like lives left, health, etc. The heads up display is generated automatically from the global attributes

1. Reflect changes in the global attributes in the HUD

*Resources* (2)
Packages of information that manage important attributes in the game and the functionality of the interactions 

1. Author creates new Resources and ties them to Sprites dynamically
2. Author sets maximum/minimum value for each attribute

*Upgrades* (1) Author creates upgrade relationships between Sprites

1. Player can upgrade Sprites. Upon clicking a Sprite it is added to the "actively clicked" panel of the view in which information about the Sprite is given and an upgrade option is provided (if the Sprite was specified to be upgradeable). 


####Play

*Menu Options* (3) 
Simple game utility options where the user can actively select to quit, reset, or return to the authoring environment

1. Player can quit the game 
2. Player can reset the level 
3. Player can launch the authoring environment reflecting the current state of the game 

####Utility
 
*Utility* (8) Facebook API utility that allows users to deploy high scores, simple user profiles, and Facebook posts

1. Player can log into a user account using Facebook
2. Player can post to Facebook about their high score
3. Player can post to Facebook about creating a new game
4. App can send notifications to users for the game
5. App can register a high score to save to the user
6. App can display the high score board for users
7. App can save the users as xml files
8. App can load users from xml files

####Errors
*Error-Handling* (3) A LoadErrorException has been created, but we would like to add additional functionality to the error and throw the error in the appropriate places and instances while the program is running.

1. Author types a non-double value into an attribute
    Alert user to type in a double

2. Invalid naming
Author enters a name for a sprite or attribute that already exists. This includes if the author creates a resource called Health and then tries to create an attribute called minHealth (or maxHealth, currentHealth, resetHealth), since those attributes are created upon generation of the resource.

3. Author defines impossible winning conditions


####Engine 
*Modules* (3)
Modules are equipped to sprites and provide dynamic or static functionality to sprites

1. Heat-Seeking misses are launched automatically
2. Missiles fire based on a user-defined time interval
3. Smart mover that progresses the path avoiding projectiles

*Resource Management* (2)
These are interactions that effect attribute values to objects in the game.
1. Enhance attribute max levels
2. Adding sprites to the game reduces global costs

*Level Management* (2) 
Manage the sequential flow from one level to another 
1. Proceed to the next level after winning a game
2. Display a splash screen in between levels

*Effects* (1) 
These are interactions that effect attribute values to objects in the game 
1. Activate AOE effects in the game

####Program User
*Game Library* (2)
This screen will render first on the game to show the possible games that already exist and can be played 

1. User can select from a collection of XMLs to start a game
2. User high scores are stored in an XML file
