# Hexagonal Coordinate System
Hexagonal Coordinate System is a very basic board game with Hexagonal block.


## Features

- MVC design pattern with Board system
- Board placement with Odd-r algorithumn
- Basic action such as movement and shoot
- Accquireing neighbour node information 
- Console input check and re-input

## Installation

The system requires Java 8 (or later) to be installed. If it's not installed you can get it here.

## Version 0.1
This project is very first stage developer preview version.(for RoyalCardGame)
Improved version has been worked with Unity 3D and found [here](https://github.com/soemyatmin/hexagonal-coordinate-unity).

## Usage
Run on any java IDE with java maven setup. (There is no maven feature in it.)

#### Controls

Request         | Input     | Example
--------------- | --------- | ---------
Enter Placement | x,y       | 2,2
Enter Action    | 0 or 1    | 0
Enter Direction | E         | E

#### Direction input are as follow

Meaning    | Symbol
---------- | --------
East       |  "E"
NorthEast  |  "NE"
SouthEast  |  "SE"
West       |  "W"
NorthWest  |  "NW"
SouthWest  |  "SW"

#### Actions input are as follow

Meaning    | Symbol
---------- | --------
Attack     | 0 
Move       | 1

## Screenshots

![BoardDisplay](https://user-images.githubusercontent.com/17348039/202329558-7a30e21f-dc3f-4e4f-81c7-9f48d742ce4d.JPG)
![inputController Check](https://user-images.githubusercontent.com/17348039/202329574-e3621d12-3f5b-49fc-865c-4de2cfa93685.JPG)

## Credits
The following artical are

- [redblob](https://www.redblobgames.com/grids/hexagons/)
- [Martomate/Hexacraft](https://github.com/Martomate/Hexacraft)

Personally, I had learned a lot.

## License
This project goes under the [MIT](https://choosealicense.com/licenses/mit/) Licence.
