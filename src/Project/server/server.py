import json
import socket
from threading import Thread

from flask import Flask, send_from_directory, request, render_template
from flask_socketio import SocketIO

import eventlet

eventlet.monkey_patch()

app = Flask(__name__)
#socket_server = SocketIO(app)

#scala_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
#scala_socket.connect(('localhost', 8000))


@app.route('/')
def index():
    return send_from_directory('/Users/connorwilson/Documents/GitHubLab2/src/Project/FrontEnd', 'index.html')


@app.route('/map', methods=['POST'])
def game():
    return send_from_directory('/Users/connorwilson/Documents/GitHubLab2/src/Project/FrontEnd', 'map.html')


@app.route('/<path:filename>')
def static_files(filename):
    return send_from_directory('/Users/connorwilson/Documents/GitHubLab2/src/Project/FrontEnd', filename)


app.run()
