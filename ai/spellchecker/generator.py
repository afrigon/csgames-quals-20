#!/usr/bin/env python3
import json
import random
import string

REMOVE_LETTER = 0
ADD_LETTER = 1
REPLACE_LETTER = 2
SWAP_LETTERS = 3

def load_words():
    with open('words_alpha.txt') as word_file:
        return set(word_file.read().split())

words = tuple(load_words())
word_count = 1000
word_list = []

for _ in range(word_count):
    word = random.choice(words)
    while len(word) <= 3:
        word = random.choice(words)

    word_list += [random.choice(words)]

def add_typo(word):
    strategy = int(random.random() * 4)
    new_word = list(word)

    if strategy == REMOVE_LETTER:
        index = int(random.random() * len(new_word))
        new_word = new_word[:index] + new_word[index+1:]
    elif strategy == ADD_LETTER:
        index = int(random.random() * len(new_word))
        new_word = new_word[:index] + [random.choice(string.ascii_lowercase)] + new_word[index:]
    elif strategy == REPLACE_LETTER:
        index = int(random.random() * len(new_word))
        new_word = new_word[:index] + [random.choice(string.ascii_lowercase)] + new_word[index+1:]
    elif strategy == SWAP_LETTERS:
        index = int(random.random() * (len(new_word)-1))
        new_word = new_word[:index] + [new_word[index+1]] + [new_word[index]] + new_word[index+2:]

    new_word = "".join(new_word)
    return (word, new_word)

word_tuples = list(map(add_typo, word_list)) 
answer_tuples = { n[1]: n[0] for n in word_tuples }
typos = solution_tuples.values()

f = open("answers.json", "w")
f.write(json.dumps(answer_tuples))
f.close()

f = open("typos.json", "w")
f.write(str(list(typos)))
f.close()

