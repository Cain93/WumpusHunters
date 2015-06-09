# WumpusHunters
Source Code for Wumpus Hunting King-of-the-hill on StackExchange Codegolf: http://codegolf.stackexchange.com/questions/51235/the-great-wumpus-hunt?noredirect=1#comment122214_51235.

To add a hunter, simply create a new class that extends Hunter, and insert it into the Hunters package. Also, in the MetaManager class
located in the Mechanics package, create a new instance of your hunter at the end of the hunters list.

To run, simply run the MetaManager class

To see more detailed information, uncomment the debug method called in the HuntWumpuses class.
