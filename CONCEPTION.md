# Mini Text-based RPG

*Ce document n'est pas explicite en ce qui concerne les attributs et fonctions / méthodes des classes.*  
*Il m'a permis de définir les relations entre les classes et les fonctionnalité de base nécessaires.*  

## Title

***WoodChop***

## Requirements

- Un joueur peut acheter des outils à couper le bois
- Ils sont disponibles dans un magasin
- Ils coupent du bois plus ou moins efficacement en fonction de leur prix

## Interfaces

### Paintable

- Methods
    - `public abstract String paint()`


## Classes

*All top level classes implement the Paintable interface*

### Choppable

- Attributes
    - creditValue
- Methods
    - `public int getChopped(int toolEfficiency)`

### Bush (extends Choppable)

### Tree (extends Choppable)

- Attributes
    - height <-- the maximum number of pieces the tree can be cut into
    - chopsNb <-- number of times the tree's been chopped
- Methods
    - `public int chopsLeft()`

### Tool

- Attributes
    - PRICE
- Methods
    - `public abstract void use(Choppable thing)` 
- Child Classes
    - Axe
        - PRICE --> 150c
    - Saw
        - Hand Saw
            - PRICE --> 190c
        - Chain Saw
            - PRICE --> 230c

### Store
- Attributes
    - tools
- Methods
    - `public Tool buyFrom(String toolType, Player p)`
    - `public int sellTo(Tool t, Player p)`

### Player

- Attributes
    - name
    - credits
    - tools
- Methods
    - `public int pay(int amount)`
    - `public void getPayed(int amount)`
    - `public void play()`
- Child classes
    - ForestMan
        - credits --> 200c
        - tools --> Axe
    - Woodcutter
        - credits --> 150c
        - tools --> Axe, HandSaw
    - Lumberjack
        - credits --> 75c
        - tools --> ChainSaw, HandSaw

### MapCell

- Attributes
    - x, y (position)
    - content (Paintable)
- Methods
    - `public boolean isAt(int x, int y)`

### GameMap

- Attributes
    - playerPosition
    - cells[]
    - nbObstacles
- Methods
    - `private void genMap()` <-- Randomly fills cells with concrete Choppable objects
    - `public void clearScreen()`
    - `public void render()`
    - `public void emptyCell(int x, int y)`
    - `public void putAt(int x, int y, Paintable p)`
