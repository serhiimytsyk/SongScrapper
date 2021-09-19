The program contains REST API and a console client program.
You need to run SongsApplication class to start the backend server with REST API
and run the Client class to run the client.
REST API consists of HTMLParser class which parses HTML content of  https://www.billboard.com/charts/hot-100,
and APIController class which processes GET requests and returns top 100 songs in JSON format.
Song class (contains necessary information about the song) is used in both server and client.
The client part of the program consists of a Gameplay class with methods to process user actions,
SongSelector class with methods to select songs from JSON and to select random 3 songs from the full list,
and the Client class.