# voogasalad
Duke CompSci 308 Game Authoring Engine Project
Team Members: Dhrumil Patel, Jeremy Schreck, Seung Jin An, Joe Timko, Joe Lilien, David Maydew, Ryan St. Pierre, Tommy Romano, Jonathan Im
Start Date: March 20th 2016
End Date: May 2nd, 2016
Roles: 
Rough team divisions:
	Frontend: Jeremey, Jin, Joe Lilien, Tommy
	Backend: Dhrumil, Joe Timko, David, Ryan, Jonathan
	
Roles:	Jeremey: SFC SFV (subformview and controller classes for controlling construction), frontend
		Jin: placeable area, farmville game, frontend
		Joe Lilien: SFC SFV, farmville game, frontend
		Tommy: Social Networking utility, frontend
		Dhrumil: Tracking/range firing modules, movement modules, general refactoring, grahics/image generation for demos
		Joe Timko: Tracking/range firing modules, movement modules, Shooter demo game implementation
		David: Backend engine framework code,  Conditions implementation, engine framework code, various modules, general refactoring/debugging, 
		Ryan: Backend Engine framework code, conditions tab in front end, TowerDefense demo game generation, renderer code
		Jonathan: Renderer code, A* smart ai pathing code, engine framework code, conditions refactoring. 
		
To start our project: please go to src.application and either select LaunchAuthoring.java which will bring you straight into the authoring environment, or Main.java which will start the launch screen with the options to view the game library or start the authoring environment

Files used to test the project: There exists a src.engine subpackage but it was not actively used, testing was largely done by hand. A custom environment was built to test engine objects untill the authoring environment became sufficiently stable as to be useful in this regard. 

Required resource files or data: It is possible for the user to select images that are not contained in the repository or eclipse directory, which can and has led to issues regarding different environments with different path names trying to use the same saved games. We used xtream to save our games into an automatically generated xml file. Other image resources used by the game are stored in the src.images subpackage. 

There are occasional bugs that pop up in the authoring environment that result due to stale references or failure to set and send values to other related parts of the editing and authoring environment. These problems are all minor in nature as we were able to finish creation of our games, with the exception of editing the filepaths of the images used for compatability across systems by hand entirely in our authoring environment. 

