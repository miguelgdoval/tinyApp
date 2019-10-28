# Tiny Android App (made with Java)
Small Android Java App, created for our "How to Create your first Android App" Talk.

The features it contains are the following ones:

# Branches/Features:
## -Hello_world:
Contains only a small template with a "hello world" message in it.

## -Tiny_form:
Contains two templates: one that permits the user to introduce an input text, an another one that displays that text.

## -Firebase_auth:
Contains the integration with Firebase.
Integrates the Log In with Google, one template with the User Profile, and another template where the users of the app are displayed, (saved inside the SharedPreferences).

## -Feature/Api: 
Brings data from an external API that contains the list of Universities in Spain. 
3 templates: onw that shows the retrieved list of universities, another one wich shows the details of one university, and the last one with our "favourite universities", which contains those that have been clicked with the "Like" button and persisted in the SQLite database.

It has also a DatabaseHelper class that allows us to persist out favourite universities in a SQLite Database.

## -MASTER:
Contains all the previous features together.
