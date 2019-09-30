#!/usr/bin/env python3
import signal
from random import shuffle, randrange

QUESTION_COUNT = 100
TIMEOUT = 5
FORMAT = "n, n, e, e, s, s, w"
MAZE_WIDTH = 36
MAZE_HEIGHT = 12

def win():
    f = open("./flag")
    flag = f.read()
    print(flag)

def fail():
    print("WRONG")
    exit()

def timeout(signum, frame):
    print("TOO SLOW")
    exit()

def parse(got):
    try:
        return got.split(", ")
    except:
        print("Invalid input! format is \"{}\"".format(FORMAT))
        exit()

def validate(expected, got):
    return expected == got


def generate(w, h):
    vis = [[0] * w + [1] for _ in range(h)] + [[1] * (w + 1)]
    ver = [["# "] * w + ['#'] for _ in range(h)] + [[]]
    hor = [["##"] * w + ['#'] for _ in range(h + 1)]

    def walk(x, y):
        vis[y][x] = 1

        d = [(x - 1, y), (x, y + 1), (x + 1, y), (x, y - 1)]
        shuffle(d)
        for (xx, yy) in d:
            if vis[yy][xx]: continue
            if xx == x: hor[max(y, yy)][x] = "# "
            if yy == y: ver[y][max(x, xx)] = "  "
            walk(xx, yy)

    walk(randrange(w), randrange(h))

    s = ""
    for (a, b) in zip(hor, ver):
        s += ''.join(a + ['\n'] + b + ['\n'])
    return s

signal.signal(signal.SIGALRM, timeout)

print("I hope u like ascii art. You'll need to tell me what number this is. The format sucks I know :)")
print("Your input should be a list of cardinal values {}".format(FORMAT))

maze = generate(MAZE_WIDTH, MAZE_HEIGHT)
entry = (randrange(MAZE_WIDTH-2), MAZE_HEIGHT - 1)
end = (randrange(MAZE_WIDTH-2), 0)

print(maze[entry[1]])
list(maze[entry[1]])[entry[0]] = "s"
list(maze[end[1]])[end[0]] = "e"

print(maze)

signal.alarm(TIMEOUT)
got = input()
signal.alarm(0)

got = parse(got)

if not validate(maze, got):
    fail()

print("GOOD")

win()

