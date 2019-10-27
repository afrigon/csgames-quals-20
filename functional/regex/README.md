# CSGames Qualifications 2020

## Functional - regex

### Implement a Regular Language Parser

The first half of the challenge is to build the AST from a string, like

```haskell
parseRegExpr "a|b*" == Union (Singleton 'a') (Star (Singleton 'b'))
```

The second half of the challenge is to actually run the Regular Language against some input to verify if it is accepted by the language, eg:

```haskell
matchRegExpr "a|b" "a" == True
matchRegExpr "a|b" "c" == False
```

#### Details

- implementation should go in `src/RegParser.hs`
- Run the driver program with `stack run`
- Run the tests with `stack test`
