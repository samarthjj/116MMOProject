import sqlite3
import json

connect = sqlite3.connect("playerrecord.db")
cur = connect.cursor()


def get_players():
    players = []
    cur.execute("SELECT name  FROM players")
    for row in cur:
        players.append(row)
    return json.dumps(players)


def add_player(name):
    cur.execute("INSERT INTO players VALUES(?,0,0,0)", (name,))
    connect.commit()


def remove_player(name):
    cur.execute("DELETE FROM players WHERE name=?", (name,))
    connect.commit()
