module RegParser where

-- This challenge is about create a Regular Language Parser
-- see tests/Spec.hs for examples

-- A language is a string in the form "ab|c(ab)*", this type is only used for clarity
type Language = String


-- Given this abstract syntax tree
data Expr = Epsilon
          | Singleton Char
          | Union Expr Expr
          | Concat Expr Expr
          | Star Expr
  deriving (Show, Eq)


-- takes a Regular Language and constructs its syntax tree (as defined above)
parseRegExpr :: Language -> Expr
parseRegExpr = undefined

-- tells if the given Regular Language accepts the given string
matchRegExpr :: Language -> String -> Bool
matchRegExpr = undefined
