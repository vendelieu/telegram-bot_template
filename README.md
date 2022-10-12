# Deployment

To deploy the bot to a heroku, you need to add parameters to the environment (Settings -> Reveal Config Vars -> ...): \
TOKEN - bots token\
HOST - application address, etc. https://example.herokuapp.com

Then just heroku as remotes and push your first version to heroku!

# Development

Create new controllers, add them to configuration/KoinModules (for some reason the default constructor on the Heroku
requires some parameters and throws a java.lang.IllegalArgumentException: wrong number of arguments at a normal
initialization attempt, I plan to look into this in the future, but for now I decided to just use Koin)

## and lastly

Enjoy!