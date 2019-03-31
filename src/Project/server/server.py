import bottle
import src.Project.server.testserver


@bottle.route('/join', method="POST")
def join():
    user = bottle.request.body.read().decode("utf-8")[9:]
    return src.Project.server.testserver.add_player(user)


@bottle.route('/leave', method="POST")
def leave():
    user = bottle.request.body.read().decode("utf-8")[9:]
    return src.Project.server.testserver.remove_player(user)


@bottle.route('/')
@bottle.route('/players')
def players():
    return src.Project.server.testserver.get_players()


bottle.run(host='localhost', port=8080, debug=True)
