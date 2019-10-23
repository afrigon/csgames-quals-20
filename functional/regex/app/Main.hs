module Main where

import Control.Monad
import System.IO
import RegParser

matchLoop :: [Char] -> IO ()
matchLoop lang = do
  putStr (lang ++ " >> ") >> hFlush stdout
  line <- getLine
  unless (null line) $ do
    let matched = matchRegExpr lang line
    when matched $ putStrLn "Accepted"
    unless matched $ putStrLn "Rejected"
    matchLoop lang

parseLoop :: IO ()
parseLoop = do
    putStr ">> " >> hFlush stdout
    lang <- getLine
    unless (null lang) $ do
      putStrLn $ "Parsed as: " ++ show (parseRegExpr lang)
      matchLoop lang
      parseLoop

main :: IO ()
main = do
  putStrLn ""
  putStrLn "Regular Language tester"
  putStrLn "Enter a regular language definition (like ab|c*) to start testing matches"
  putStrLn "At any time, enter a blank line to go back or exit"
  parseLoop
