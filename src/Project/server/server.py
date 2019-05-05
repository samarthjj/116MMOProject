import json
import socket
from threading import Thread

from flask import Flask, send_from_directory, request, render_template
from flask_socketio import SocketIO

import eventlet

eventlet.monkey_patch()

app = Flask(__name__)

socket_server = SocketIO(app)

scala_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
scala_socket.connect(('localhost', 8000))

delimiter = "~"


def listen_to_scala(the_socket):
    buffer = ""
    while True:
        buffer += the_socket.recv(1024).decode()
        while delimiter in buffer:
            message = buffer[:buffer.find(delimiter)]
            buffer = buffer[buffer.find(delimiter) + 1:]
            get_from_scala(message)


def get_from_scala(data):
    socket_server.emit('gameState', data, broadcast=True)


def send_to_scala(data):
    scala_socket.sendall((json.dumps(data) + delimiter).encode())


@app.route('/')
def index():
    return send_from_directory('/Users/connorwilson/Documents/GitHubLab2/src/Project/FrontEnd', 'index.html')


@app.route('/map', methods=['POST'])
def game():
    return send_from_directory('/Users/connorwilson/Documents/GitHubLab2/src/Project/FrontEnd', 'map.html')


@app.route('/<path:filename>')
def static_files(filename):
    return send_from_directory('/Users/connorwilson/Documents/GitHubLab2/src/Project/FrontEnd', filename)


@socket_server.on('disconnect')
def disconnect():
    print(request.sid + " disconnected")
    message = {"username": request.sid, "action": "disconnected"}
    send_to_scala(message)


@socket_server.on('connect')
def got_message():
    print(request.sid + " connected")
    message = {"username": request.sid, "action": "connected"}
    send_to_scala(message)


app.run()

