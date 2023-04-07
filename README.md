# Build-a-Mon

## A Custom *Pokémon* Editor

This project is based on the popular Nintendo video game franchise, *Pokémon*, which involves capturing and fighting
with creatures. Growing up, I **loved** *Pokémon* games, often designing and drawing my own ideas for *Pokémon* on 
pieces of scrap paper. However, I would have trouble keeping track of all my *Pokémon* and often lost my 
designs since there was no convenient way to organize them. 

This application digitalizes the aforementioned process, allowing avid *Pokémon* fans to implement their ideas for new
*Pokémon* with just the click of a few buttons. Users can easily customize all attributes associated with a *Pokémon*,
including their name and stats. This application thus allows *Pokémon* lovers to experience these games without
having to own a video game console.

## User Stories

- As a user, I want to be able to add a new *Pokémon* to a list of the ones I have already created
- As a user, I want to be able to delete *Pokémon* from my collection
- As a user, I want to be able to view a summary of my *Pokémon* collection
- As a user, I want to be able to select a *Pokémon* and edit its attributes
- As a user, I want to be able to save my *Pokémon* collection, if I so choose
- As a user, I want to be able to load my saved *Pokémon* collection, if I so choose

## Instructions for Grader
- You can generate the first required action related to adding new *Pokémon* to the list by entering attributes in the
attribute fields (Name, Type 1...) and clicking "Add"
- You can generate the second required action, deleting a *Pokémon* from the collection, by enetering the name of the 
*Pokémon* and clicking "Remove"
- You can locate my visual component at the top of the *Pokémon* editor (Picture from 
https://pixabay.com/vectors/pokemon-icon-design-symbol-sign-4657023/)
- You can save the state of my application by clicking "File" at the top and then "Save"
- You can reload the state of my application by clicking "File" at the top and then "Load"
 
## Phase 4: Task 3
If I were to refactor my design, I would remove the nested classes inside GUI (AddListener, RemoveListener,
SaveListener, and LoadListener) and instead put the declaration where I add the action listeners to their buttons since
they don't have enough functionality to warrant a separate class declaration. I would also make the panels in GUI into
their own classes so that the GUI fulfills the Single Responsibility Principle, since it has many responsibilities at 
the moment.