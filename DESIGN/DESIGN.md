###Introduction
(G)it Depends seeks to build a flexible and powerful game creation and playing environment.  This environment will be founded upon a flexible game engine, and a comprehensive game-authoring environment.  The game engine will be able to can interpret and run a wide array of games with different actors, rules, and goals.  The authoring environment will provide an extremely broad amount of flexibility to allow users to create actors and levels with as few limitations pre-defined parameters as possible.  The games created will be run through an easy-to-use and intuitive game player, which will contain a library of previously created games for the user to choose from.  The primary design goals of (G)it Depends are to provide users the most flexibility possible in creating robust, complex games.  

The primary architecture of our design lies in the idea that games can be constructed of Sprites, Conditions, Effects, and Attributes.  A Sprite is anything that appears on the game screen when the game is played, ranging from a soldier, to a tower, to a menu.  Sprites, and the global state of the game, possess Attributes.    Attributes are numerical values that can be used to represent a broad variety of various aspects of Sprites or the global state of the game.  For example, Attributes could represent ammunition remaining for a rifleman, the number of lives the user has remaining, or health.  Effects define how the Sprites interact with each other and the game state, primarily via numerical operations on Attribute values.  Some examples of effects would be decreasing the value of health, moving on the screen, or removing a Sprite from the screen entirely.  Conditions define when Sprites impose effects on one another and the game state.  Together Effects and Conditions define the “rules” for the game.  The game engine will interpret and run games based on these assumptions and the game-authoring environment will allow the user to create games utilizing these objects.  

(G)it Depends has chosen to focus it’s efforts on building an environment suited for Tower Defense games.  There are many unique aspects to the Tower Defense genre as a whole that our environment will need to support.  First off, Tower Defense games allow for the user to modify the state of the level by placing towers and actors on the screen to aid in their defense.  Therefore the state of the game’s level needs to be extremely dynamic.  Tower Defense also depends on waves of enemies moving in a random fashion towards an objective, and conversely having the defense actors and towers be “smart” enough to destroy as many of those enemies as they can in an intelligent fashion.  Finally Tower Defense games are grounded in strategy, so there needs to be a great deal of flexibility in how a user can accomplish reaching the winning condition of a level.




###Overview



###User Interface

###Design Details



###Design Considerations 



####Engine Sub-team Meetings

Over the course of our meetings, the strongest ambiguities and concerns we attempted to address were collision detection, how to classify events or effects in regards to our sprites, and numerous nuances about how the engine would handle clicks and user-input, changing levels, and access management. 

During our initial conversations, we discussed how we wanted to handle the notion of attributes. At first, we discussed maintaining private instance variables that serve as generic attributes such as health and movement. Focusing on generalizing the notion of attributes, we introduced the notion of an external condition manager. These conditions essentially maintain the information involving the sprites or group of sprites, the effect, and any global attributes that may be altered. 

In regards to a few design patterns, we discussed trade-offs between the strategy design pattern and a combination of the composition and visitor pattern to handle how we implement the notion of behavior. We decided that we wanted unilateral dependencies that moved downwards from the game to each individual sprite. We believe this prevented any untraceable altercations that occur if we pass references up the hierarchy and wait for it to trickle back down. We decided to run with the composition design pattern with sprites to attach various modules, such as weapons or movement. 

Additionally, we are strictly adhering to interfaces for our sprites, modules, conditions, and components of the engine. During one of our meetings, we heavily discussed our hierarchy beginning with the player and moving down at a high-level into the game engine and the components below the engine component. We discussed tradeoffs on how we want to pass condition and collision information between our managers, the level, and the sprites and its attributes. We decided that we would create a condition and collision manager that holds a list of all of the users' effects and events for that level. By doing so we would be able to prevent passing information up to the level and back down to the sprites. Our solution holds the affected sprites attributes, a list of effects, a method to check if the condition or collision is met, and where else the condition needs to be applied.

We also held significant discussion about the notion of a level and how we would differentiate what a level and a splash screen entails. We defined splash screens as interstitial screens with texts and bottoms that allow the user to segue between levels. The problems that elucidated from this discussion created issues with our model. We didn't feel comfortable having a renderer in our game engine that rendered Sprites onto the screen and text and buttons. We didn't want to abstract the renderer to account for the differences because we would introduce complications between our level manager and how the authoring environment would create the notion of a splash screen. 



####Authoring Sub-team Meetings


####Overall Considerations

Going forward, the discussions we are having entail input from both sub-groups. One of the topics we are discussing is the notion of paths for sprites and our interpretation of spawners. Addtionally, we are going to have to deliberate on how we the authoring environment constructs the XML files and how the engine team uses reflection and other techniques to interpret the property files for the specified game. 


###Example Games



###Design Considerations