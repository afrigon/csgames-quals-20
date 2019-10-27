module RegParser where

import Data.Char

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
parseRegExpr = fst . parse

-- tells if the given Regular Language accepts the given string
matchRegExpr :: Language -> String -> Bool
matchRegExpr l s = let (matched, rest) = match (parseRegExpr l) s in matched && null rest

-- instance Show Expr where
--   show Epsilon = ""
--   show (Singleton c) = [c]
--   show (Union x y) = show x ++ "|" ++ show y
--   show (Concat x y) = show x ++ show y
--   show (Star (Singleton x)) = x : "*"
--   show (Star x) = "(" ++ show x ++ ")*"

instance Semigroup Expr where
  x <> y = case (x, y) of
    (Epsilon, _) -> y
    (_, Epsilon) -> x
    _ -> Concat x y

parse :: String -> (Expr, String)
parse s = go Epsilon s
  where
    go expr "" = (expr, "")
    go expr (h:t)
      | isLetter h = let (e, r) = star (atom h) t in
          go (expr <> e) r
      | h == '|'   = let (e, r) = parse t in
          go (Union expr e) r
      | h == '('   = let (e, r) = parse t in
          go (expr <> e) r
      | h == ')'   = let (e, r) = star expr t in (e, r)

    star e ('*':r) = (Star e, r)
    star e r       = (e, r)

    atom 'ε' = Epsilon
    atom c = Singleton c

match :: Expr -> String -> (Bool, String)
match Epsilon s = (True, s)
match (Singleton _) "" = (False, "")
match (Singleton x) (h:t)
  | h == x    = (True, t)
  | otherwise = (False, h:t)
match (Concat x y) s =
  case match x s of
    (True, r) -> match y r
    _ -> (False, s)
match (Union x y) s =
  case match x s of
    (True, []) -> (True, [])
    _ -> match y s
match (Star _) "" = (True, "")
match (Star x) s =
  case match x s of
    (True, r) -> match (Star x) r
    (False, _) -> (True, s)
