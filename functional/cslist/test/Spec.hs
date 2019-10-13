import Test.Hspec
import Test.QuickCheck
import Data.Function( on )

import CSList

-- test case for CSList
-- run with `stack test`

main :: IO ()
main = hspec $ do
  describe "CSList.head'" $ do
    it "behaves like standard head on any integer list" $ do
      property $ \l -> (== (head l :: Int)) <$> head' (fromStdList l)

  describe "CSList.tail'" $ do
    it "behaves like standard tail on any integer list" $ do
      property $ \l -> (== fromStdList (tail (l :: [Int]))) <$> tail' (fromStdList l)

  describe "CSList.length'" $ do
    it "behaves like standard length on any integer list" $ do
      property $ \l ->
        length' (fromStdList l) == length (l :: [Int])

  describe "CSList.append'" $ do
    it "behaves like standard (++) on any two integer list" $ do
      property $ \xs ys ->
        (append' `on` fromStdList) xs ys == fromStdList (xs ++ (ys :: [Int]))

  describe "CSList.take'" $ do
    it "behaves like standard take on any integer list" $ do
      property $ \l i ->
        take' i (fromStdList l) == fromStdList (take i (l :: [Int]))

  describe "CSList.reverse'" $ do
    it "behaves like standard take on any integer list" $ do
      property $ \l ->
        reverse' (fromStdList l) == fromStdList (reverse (l :: [Int]))

  describe "CSList.map'" $ do
    it "behaves like standard map on any integer list, and Int -> Char" $ do
      property $ \l (Fn f) ->
        map' f (fromStdList l) == fromStdList (map (f :: Int -> Char) (l :: [Int]))

  describe "CSList.filter'" $ do
    it "behaves like standard filter on any integer list and predicate" $ do
      property $ \l (Fn p) ->
        filter' p (fromStdList l) == fromStdList (filter p (l :: [Int]))

  describe "CSList.reduce'" $ do
    it "behaves like standard foldl (+) 0 on any integer list (sum)" $ do
      property $ \l ->
        reduce' (+) 0 (fromStdList l) == foldl (+) 0 (l :: [Int])

    it "behaves like standard foldl (*) 1 on any integer list (product)" $ do
      property $ \l ->
        reduce' (*) 1 (fromStdList l) == foldl (*) 1 (l :: [Int])
