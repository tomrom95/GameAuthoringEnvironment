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
  
  11)-Author sets a background image
  
  12)-Author places spawners
  
  13)--Author creates waves
    
  14)--Author potentially defines path used for AI movement
   	
  15) -User defines winning/losing/next level conditions
  	 
  16) -User defines global attributes
  
  Heads up Display is generated automatically from the global attributes
  
17)+Author creates new Attribute types and ties them to Sprites dynamically

No panel will exist for defining Attributes.  When the author creates a Sprite it can select previously specified attributes from a drop-down provided or possibly create new ones.  If the author creates a new Attribute that will be stored and display in the drop-down menu during the creation of other Sprites.

18)+Author creates new Resources and ties them to Sprites dynamically

19)+Author creates new conditions

i.e Author chooses from predefined list of conditions (equal, less-than, greater than, on-collision, distance from, time), tying them to specific Attributes that a specific Sprite or groups of Sprites hold, firing an effect when the condition is met.  Collisions will be handled in this manner.

20)+Author creates a weapon.  A weapon is tied to a Sprite 

**Play**


**Code extensibility**


