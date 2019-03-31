import sqlite3


connect = sqlite3.connect("Project.db")


def get_players():
    cur = connect.cursor()
    players = []
    cur.execute("SELECT username FROM players")
    for row in cur:
        players.append(str(row[0]))
    cur.close()

    plays = []
    for num in range(0, len(players) - 1):
        plays.append(players[num] + ", ")
    plays.append(players[len(players) - 1])
    return plays


def add_player(name):
    cur = connect.cursor()
    cur.execute("INSERT INTO players VALUES(?, 0)", [name])
    connect.commit()


def remove_player(name):
    cur = connect.cursor()
    cur.execute("DELETE FROM players WHERE username=?", [name])
    connect.commit()
