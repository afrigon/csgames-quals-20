module CSList where

-- given data structure, do not modify
data List a = Nil
            | Cons a (List a)
  deriving (Show, Eq)

-- given utility function, do not modify
fromStdList :: [a] -> List a
fromStdList [] = Nil
fromStdList (h:t) = Cons h (fromStdList t)

-- challenge starts here
-- implement the following methods
-- refer to test/Spec.hs for test cases
head' :: List a -> Maybe a
head' = undefined

tail' :: List a -> Maybe (List a)
tail' = undefined

length' :: List a -> Int
length' = undefined

append' :: List a -> List a -> List a
append' = undefined

take' :: Int -> List a -> List a
take' = undefined

reverse' :: List a -> List a
reverse' = undefined

map' :: (a -> b) -> List a -> List b
map' = undefined

filter' :: (a -> Bool) -> List a -> List a
filter' = undefined

reduce' :: (b -> a -> b) -> b -> List a -> b
reduce' = undefined
