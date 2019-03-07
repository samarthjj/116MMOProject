import bottle
import Project.server.testserver


@bottle.route('/')
def index():
    return bottle.static_file("map.html", root="")


@bottle.route('/frontend')
def frontend():
    return bottle.static_file("FrontEnd.js", root="")


@bottle.route('/')
def game():
    return bottle.static_file("Game.scala", root="")


@bottle.route('/join/<username>')
def join(username='Guest'):
    Project.server.testserver.add_player(username)


@bottle.route('/leave/<username>')
def leave(username='NOT APPLICABLE'):
    Project.server.testserver.remove_player(username)


@bottle.route('/')
@bottle.route('/players')
def players():
    return Project.server.testserver.get_players()


bottle.run(host='localhost', port=8080, debug=True)
