Use Cases
====

### Users 

We only have one actor that interacts with the program, called a *user*.  We will distinguish between program functionality by calling a *user* an *author* when he/she is creating a game and a *player* when he/she is playing a game.

**Authorship**

*Sprite Creation*

Each defaulted type of Sprite has its own tab. Within this tab there is a list of previously created Sprites and a form to create a new Sprite.  Each of the following are stored as generic Sprites on the backend, but are grouped in this tab format for user ease.  This form will include different sub-forms that could differ on the type of Sprite (default values and options presented) and will be specified in a Resource file.  The created Sprites are stored in observable lists to immediately propagate throughout the display.  

All Sprites have an attached movement Module that defines movement (AI, User-controlled, simple algorithm, immmobile).  This is presented to the user as a sub-form.  

1) +Author creates an enemy 

2) +Author creates a defenders 

3) +Author creates a hero player

Necessary Modules: 
* User-Controlled Movement

4) +Author creates a custom Sprite 

5) +Author creates a terrain object 

6) +Author creates an obstacle 

*Future Creation Environment Functionality*

7) +Author creates a weapon 

There is a weapons tab in the front-end.  This is different from the other tabs in that a weapon is **not** a Sprite, it is a firing module.  This information will propagate to some Sprite tabs to be added to certain Sprites, as sub-form. 

*Level/Game Creation*

8) +Author defines collisions

This is done in its own section.  User can tie Sprites into groups and define collisions on a group-to-group basis for the ease of creation. Collisions will specify the effects to fire tied to specific Sprite/Group held attributes upon occurrence. 

9) +Author creates a new level

This is done in a different section than Sprite creation.

  10)-Author places Sprites on screen
  
  The author can choose from a list of Sprites created in the Sprite creation section. These Sprites are visually categorized by the categories above. The author can click back to the Sprite Creation section at any time to create, edit, or delete Sprites, and this will alter the Sprites he/she can choose from in the Game Creation section. The author will drag and drop Sprites on the screen, or enter x y coordinates. We are still deciding if there will be specific grid locations to drop the Sprite or if they will be able to drop them anywhere.
  
  11)-Author sets a background image
  
  The user is prompted to upload a file.
  
  12)-Author places spawners
  	
  	Spawners have a list of waves. The author can specify Sprites to appear during game-play at these specified spawn points.
   
   13)--Author creates waves
   
   The author specifies which sprites will appear, the rate of the wave, and which Spawner is associated with the wave.
    
   14)--Author potentially defines path used for path movement
   
   The author can draw a path on the screen to specify a path. This path will be stored in the backend as a discrete list of points. The path will be associated with a sprite or group of Sprites. If the Sprites do not start at the beginning of the path, then they will move to the start of the path upon game-play (or we will make it so that the Sprites must be placed at the beginning of the path).
   	
  15) -User defines winning/losing/next level conditions
  
  Conditions are specified by an object, action, and effect triple. The object is either a Sprite or group of Sprites, the action is picked from a discrete list of semi-customizable default actions, and the effect in this case is either winning/losing/next level.
  	 
  16) -User defines global attributes
  
  Global attributes are things like lives left, health, etc. The heads up Display is generated automatically from the global attributes
  
17)+Author creates new Attribute types and ties them to Sprites dynamically

No panel will exist for defining Attributes. This will be one of the sub-forms during Sprite creation. When the author creates a Sprite it can select previously specified attributes from a drop-down provided or possibly create new ones.  If the author creates a new Attribute that will be stored and display in the drop-down menu during the creation of other Sprites.

18)+Author creates new Resources and ties them to Sprites dynamically

This will be done in a similar way to how Attributes are handled. The difference is that Attributes contain a double with a string name, whereas Resources contain a set of 4 string-double pairs (min, max, current, reset).

*Upgrades*

19)+Author creates upgrade relationships between Sprites

The author does this during Sprite Creation, as one of the sub-forms. The upgrades will be stored as a linked list of Sprites.  

*Conditions*

Author chooses from predefined list of conditions (equal, less-than, greater than, on-collision, distance from, time), tying them to specific Attributes that a specific Sprite or groups of Sprites hold, firing an effect when the condition is met.  Collisions will be handled in this manner.

20)+Author creates new conditions

Conditions make checks on attributes and fire effects as necessary

*Screen* 

21)+The user defines how clicks and button inputs affect the game

This is separate from the clicks and inputs that define movement and collisions. Examples of what this use case is talking about is clicking on a Sprite and having an upgrade window pop up.

22)+User defines what possible Sprites to put in the side panel available for the user to add during game-play.

**Play**

23)+Player selects game to play

This is selected from a library of loaded games from XML format. 

24)+Player can pause the game

This will be prompted by a button click specified in the authoring environment corresponding to "pause."

*Menu Options*

25)+Player can quit the game 

26)+Player can reset the level 

27)+Player can launch the authoring environment reflecting the current state of the game 

*Other functionality*

28)+Player can add Sprites to the screen

This is made possible by a side panel

29)+Player can upgrade Sprites

Upon clicking a Sprite it is added to the "actively clicked" panel of the view in which information about the Sprite is given and an upgrade option is provided (if the Sprite was specified to be upgradeable). 
+Player can control Sprites 

This is allowed for Sprites in which user defined movement was specified in the authoring environment.

30)+Player can post to Facebook (extension)

31)+Player can access level statistics (extension)

**Errors** 

32)+User loads a library with an invalid XML format

Parser signals an error, and an Alert is displayed to the user.

33)+User loads a game with an invalid XML format (the library contained correct XML format, but one of the XML file of the game it referenced had an invalid XML format)

Parser signals an error, and an Alert is displayed to the user.

34)+Author types a non-double value into an attribute

Alert user to type in a double

35)+Invalid naming

Author enters a name for a sprite or attribute that already exists. This includes if the author creates a resource called Health and then tries to create an attribute called minHealth (or maxHealth, currentHealth, resetHealth), since those attributes are created upon generation of the resource.

36)+Author provides an image filepath of invalid type.

We only let them choose images.

**Logical errors in game design**
We do not deal with this "errors". It is up to the game author to make sure the game will work properly.

37)+Author defines impossible winning conditions

38)+Author defines effects that act on Sprites that were created but not placed in the level.


**Program User**

39) Author creates game using game authoring environment.

Saved as XML and added to library. 

40) Screen comes up that allows the user to select creating a new game or play game.  If they do create new game the authoring environment pops up and is rendered.   To play a game they load a library of games to play (loading games from XMLs).  The library has images associated with each game in the library and allows the user to select a game.




