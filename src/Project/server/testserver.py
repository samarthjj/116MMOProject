import sqlite3
import json


def get_players():
    connect = sqlite3.connect("Project.db")
    cur = connect.cursor()
    players = []
    cur.execute("SELECT username FROM players")
    for row in cur:
        players.append(row)

    return json.dumps(players)


def add_player(name):
    connect = sqlite3.connect("Project.db")
    cur = connect.cursor()
    cur.execute("INSERT INTO players VALUES(?, 0)", (name,))
    connect.commit()
    connect.close()


def remove_player(name):
    connect = sqlite3.connect("Project.db")
    cur = connect.cursor()
    cur.execute("DELETE FROM players WHERE username=?", (name,))
    connect.commit()
    connect.close()
