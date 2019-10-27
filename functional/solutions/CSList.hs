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
head' Nil = Nothing
head' (Cons h _)  = Just h

tail' :: List a -> Maybe (List a)
tail' Nil = Nothing
tail' (Cons _ t) = Just t

length' :: List a -> Int
length' Nil = 0
length' (Cons _ t) = succ $ length' t

append' :: List a -> List a -> List a
append' l Nil = l 
append' Nil l = l 
append' (Cons x xs) ys = Cons x $ append' xs ys

take' :: Int -> List a -> List a
take' 0 _ = Nil
take' _ Nil = Nil
take' n (Cons h t)
  | n > 0 = Cons h $ take' (n-1) t
  | otherwise = Nil

reverse' :: List a -> List a
reverse' Nil = Nil
reverse' l = reduce' (\acc x -> Cons x acc) Nil l

map' :: (a -> b) -> List a -> List b
map' f Nil = Nil
map' f (Cons h t) = Cons (f h) $ map' f t

filter' :: (a -> Bool) -> List a -> List a
filter' f Nil = Nil
filter' f (Cons h t)
  | f h = Cons h $ filter' f t
  | otherwise = filter' f t

reduce' :: (b -> a -> b) -> b -> List a -> b
reduce' f acc Nil = acc
reduce' f acc (Cons h t) = reduce' f (f acc h) t

zip' :: List a -> List b -> List (a, b)
zip' Nil l = Nil
zip' l Nil = Nil
zip' (Cons x xs) (Cons y ys) = Cons (x, y) $ zip' xs ys 

-- now prove me your list is a monad
-- A functor (more precisely an endofunctor in this case) is a type that you can apply a
-- function (or morphism) on it to produce
instance Functor List where
  -- fmap :: (a -> b) -> List a -> List b
  fmap = map'

instance Applicative List where
  -- pure :: a -> List a
  pure x = Cons x Nil 
  -- (<*>) :: List (a -> b) -> List a -> List b
  Nil <*> _ = Nil
  _ <*> Nil = Nil
  Cons f fs <*> xs = append' (fmap f xs) (fs <*> xs)

instance Monad List where
  -- (>>=) :: List a -> (a -> List b) -> List b
  Nil >>= _ = Nil
  Cons x xs >>= f = append' (f x) (xs >>= f)

