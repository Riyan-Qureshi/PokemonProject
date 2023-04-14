# My Personal Project

### About This Project
- My project is an application that will simulate a Pokémon battle sequence 
  between two Pokémon, where a player encounters a random Pokémon
  and is then forced to fight it with one of their own Pokémon.

### Intended Audience
- My intended, target audience for this application is casual, or avid, Pokémon enthusiasts
  that love the battling aspect of the Pokémon game, or want to understand the battle mechanics.

### My Inspirations and Aspirations for this Project
- Growing up playing many video games on consoles such as the PS2 and Game Boy, 
  games like Pokémon became a big part of my childhood. 
  Inspired by the games I played I had always wanted to build my own complex game, 
  but never learned the proper tools to do so.
  With this project I hope to achieve my goal by re-creating the most fun aspect that drew me into the game of Pokémon,
  and finally build a game I enjoy.

### User Stories
- As a user, I want to be able to choose up to 6 unique Pokémon to add to my party
- As a user, I want to be able to view the list of moves my current Pokémon can use in battle
- As a user, I want to be able to choose a move from a list of moves to use in battle
- As a user, I want to be able to view my party (list) of Pokémon
- As a user, I want to be able to save my created Trainer and my selected party in their current state
- As a user, I want to be able to load my game from where I last saved my game

# Instructions for Grader

- You can generate the first required action related to adding Xs to a Y by clicking on any of the pokemon names to add 
  to your party
- You can generate the second required action related to adding Xs to a Y by first selecting finished selection, then 
  selecting the view party button, and finally selecting the reverse button
- You can locate my visual component by first selecting finished selection, then selecting either save or load buttons
- You can save the state of my application by first selecting finished selection, then selecting save button
- You can reload the state of my application by first selecting finished selection, then selecting load button

# Phase 4: Task 2 
- (Note: "Added Pokemon to a party" will show doubled because it is added to both trainer and challenger)
Thu Apr 13 14:07:31 PDT 2023
Added Pokemon to a party.
Thu Apr 13 14:07:31 PDT 2023
Added Pokemon to a party.
Thu Apr 13 14:07:32 PDT 2023
Added Pokemon to a party.
Thu Apr 13 14:07:35 PDT 2023
Added Pokemon to a party.
Thu Apr 13 14:07:35 PDT 2023
Added Pokemon to a party.
Thu Apr 13 14:07:35 PDT 2023
Added Pokemon to a party.
Thu Apr 13 14:07:35 PDT 2023
Challenger party created.
Thu Apr 13 14:07:49 PDT 2023
Party order reversed.

# Phase 4: Task 3 
- Ideally if I had more time I would have liked to separate the business logic and the rendering logic that is happening
  within the GUI as I believe the GUI should only display visual elements, and not handle any interactions that are 
  part of the game's logic. This would imply that in our UML diagram we would be able to separate the association of our 
  model components such as trainer, challenger, etc., that are inherently closely tied to the game's logic, from the 
  visual components of the game; I would rather delegate these interactions to a separate panel dedicated for 
  handling the game's logic. The benefit to this approach would be that there would be a clear separation of concern of 
  the different types of entities present within my application. Furthermore, this would allow my application to be a 
  lot more extensible so other contributors would have an easier time modifying or adding to my application.