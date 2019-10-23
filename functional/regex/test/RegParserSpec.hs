module RegParserSpec where

import Control.Monad
import RegParser
import Test.Hspec

data TestCase = TestCase { language :: Language
                         , expected :: Expr
                         , accepted :: [String]
                         , rejected :: [String]
                         }

testcase :: [TestCase]
testcase =
  [ TestCase { language = "a"
             , expected = Singleton 'a'
             , accepted = ["a"]
             , rejected = ["", "b", "ab", "c"]
             }
  , TestCase { language = "((a))"
             , expected = Singleton 'a'
             , accepted = ["a"]
             , rejected = ["", "b", "ab", "c"]
             }
  , TestCase { language = "ε|ab"
             , expected = Union Epsilon (Concat (Singleton 'a') (Singleton 'b'))
             , accepted = ["", "ab"]
             , rejected = ["a", "b", "c", "aa", "bb"]
             }
  , TestCase { language = "εbε"
             , expected = Singleton 'b'
             , accepted = ["b"]
             , rejected = ["a", "c", "aa", "bb", ""]
             }
  , TestCase { language = "ab"
             , expected = Concat (Singleton 'a') (Singleton 'b')
             , accepted = ["ab"]
             , rejected = ["", "a", "b", "ba", "abb", "abc"]
             }
  , TestCase { language = "a|b"
             , expected = Union (Singleton 'a') (Singleton 'b')
             , accepted = ["a", "b"]
             , rejected = ["", "ab", "ba", "abb", "abc"]
             }
  , TestCase { language = "(ab)"
             , expected = Concat (Singleton 'a') (Singleton 'b')
             , accepted = ["ab"]
             , rejected = ["", "a", "b", "ba", "abb", "abc"]
             }
  , TestCase { language = "a(b)"
             , expected = Concat (Singleton 'a') (Singleton 'b')
             , accepted = ["ab"]
             , rejected = ["", "a", "b", "ba", "abb", "abc"]
             }
  , TestCase { language = "a(b)*"
             , expected = Concat (Singleton 'a') (Star (Singleton 'b'))
             , accepted = ["a", "ab", "abb", "abbb"]
             , rejected = ["", "b", "bb", "bbb", "bba", "abba"]
             }
  , TestCase { language = "a(ab)*"
             , expected =
                 Concat (Singleton 'a') (Star (Concat (Singleton 'a') (Singleton 'b')))
             , accepted = ["a", "aab", "aabab", "aababab"]
             , rejected = ["", "b", "ab", "abab", "aba"]
             }
  , TestCase { language = "a|b*"
             , expected = Union (Singleton 'a') (Star (Singleton 'b'))
             , accepted = ["a", "", "b", "bb", "bbb"]
             , rejected = ["aa", "ab", "abb", "bba"]
             }
  , TestCase { language = "aa|bb|cc"
             , expected = Union
                          (Concat (Singleton 'a') (Singleton 'a'))
                          (Union
                           (Concat (Singleton 'b') (Singleton 'b'))
                           (Concat (Singleton 'c') (Singleton 'c'))
                          )
             , accepted = [ [x,y] | x <- ['a'..'c'], y <- ['a'..'c'], x == y ]
             , rejected = [ [x,y] | x <- ['a'..'c'], y <- ['a'..'c'], x /= y ]
                          ++ ["", "a", "b", "c"]
             }
  , TestCase { language = "a*|b*"
             , expected = Union (Star (Singleton 'a')) (Star (Singleton 'b'))
             , accepted = ["", "a", "aa", "aaa", "b", "bb", "bbb"]
             , rejected = ["ab", "ba", "aab", "bba", "aba"]
             }
  , TestCase { language = "((ab)*c)*"
             , expected = Star (Concat
                                (Star (Concat (Singleton 'a') (Singleton 'b')))
                                (Singleton 'c'))
             , accepted = ["", "abc", "c", "abc", "ababc", "cc", "ababcccc", "abcababc"]
                          ++ ["cabc", "ccabcababccababccc"]
             , rejected = ["ab", "cab", "ba", "cba", "ccabacc", "ac", "aba", "abababababa"]
             }
  ]

spec :: Spec
spec = do
  describe "Lib.parseRegExpr" $ do
    forM_ testcase $ \test -> do
      it ("should recognize regular language: " ++ language test) $ do
        parseRegExpr (language test) `shouldBe` (expected test)

  describe "Lib.matchRegExpr" $ do
    forM_ testcase $ \test -> do
      forM_ (accepted test) $ \s ->
        it ("should accept " ++ show s ++ " from language " ++ language test) $ do
          matchRegExpr (language test) s `shouldBe` True
      forM_ (rejected test) $ \s ->
        it ("should reject " ++ show s ++ " from language " ++ language test) $ do
          matchRegExpr (language test) s `shouldBe` False
