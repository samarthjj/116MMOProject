import sqlite3
conn = sqlite3.connect("chinook.db")
cur = conn.cursor()


def create_table_players():
    cur.execute("CREATE TABLE IF NOT EXISTS players (player, x, y, token)")


def add_record(player, xcord, ycord, tokencount):
    cur.execute("INSERT INTO players VALUES (?,?,?)", (player, xcord, ycord, tokencount))
    conn.commit()


def get_records():
    array = []
    for i in cur.execute("SELECT * FROM players"):
        array.append(i)
    return array
