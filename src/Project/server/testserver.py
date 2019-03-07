import sqlite3
import json


def get_players():
    connect = sqlite3.connect("playerrecord.db")
    cur = connect.cursor()
    players = []
    cur.execute("SELECT * FROM players")
    for row in cur:
        players.append(row)
    return json.dumps(players)


def add_player(name, x, y, token):
    connect = sqlite3.connect("playerrecord.db")
    cur = connect.cursor()
    cur.execute("INSERT INTO players VALUES(?,?,?,?)", (name, x, y, token))
    connect.commit()
    connect.close()


def remove_player(name):
    connect = sqlite3.connect("playerrecord.db")
    cur = connect.cursor()
    cur.execute("DELETE FROM players WHERE name=?", (name,))
    connect.commit()
    connect.close()
