#!/usr/bin/env python3
import json

f = open("answers.json", "r")
answers = json.loads(f.read())
f.close()

f = open("answers.json", "r")
candidate_answers = json.loads(f.read())
f.close()

good_answers = 0
bad_answers = []

for (word, answer) in candidate_answers.items():
    if word in answers and answer in answers[word]:
        a = answers[word]
        if (isinstance(a, str) and answer == a) or answer in a:
            good_answers += 1
        else:
            bad_answers += [(word, answer)]

print(bad_answers)
print(f"{good_answers}/{len(answers.values())}")

