# CSGames Qualifications 2020

## Artificial Intelligence - Spell Checker

For this challenge you'll have to implement the basics of a spell checker. The `typos.json` file contains a list of misspelled words and the `words_alpha.txt` contains the words list used to create the input file.

### Typos

Here are the 5 possible alteration applied to the words:

1. A character is missing from the string
2. A character has been added in the string
3. A character is wrong
4. Two consecutive character have been swapped
5. The word doesn't have any typo

### Format

The output format is a json file with the following structure:

```json
{
    "exampl": "example",
    "lword": ["sword", "word"]
    ...
}
```

The keys of the json file are the misspelled words and the values are the correct words. The values can be a string or an array of correctly spelled words if there is more than one possibility.

