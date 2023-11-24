# javaChatApp

this is a chatting app that will try to mimic whatsapp. 

as the app is finished it will allow us to send a recive text(message) and files from one pc to another locally tehn if the project get's funded or i wanna grow it then i will make it work accross the internet so everybody can try it out.


System Design 

- i have designed the system so that if it the first time that the server and the client are connected will exchange their profile pictures and they will save it for their future use.
. Second they will exchange their profile Names. Then the program will start.

  Ports Used
- 53 --- sending the profile picture 
- 12  --- sending the profile names
- 19 ---- sending messages between each-other


  Methods implemented for GUI.class

- isServer ---- you need to set this to true if you are using serverSideApp
- setProfileName ---- we use this method to set the name of the user on the navJpanel(the green bar)
