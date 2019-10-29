#!/usr/bin/env python3
import json
import sys

f = open("answers.json", "r")
answers = json.loads(f.read())
f.close()

f = open(sys.argv[1], "r")
candidate_answers = json.loads(f.read())
f.close()
print(candidate_answers)

good_answers = 0
bad_answers = []

for (word, answer) in candidate_answers.items():
    if word not in answers:
        continue

    a = answers[word]
    if (isinstance(a, str) and answer == a) or (not isinstance(a, str) and answer in a):
        good_answers += 1
    else:
        bad_answers += [(word, answer)]

print(bad_answers)
print(f"{good_answers}/{len(answers.values())}")

