-- module (NICHT ÄNDERN!)
module XiangqiBot
    ( getMove
    , listMoves
    ) 
    where

import Data.Char
import Data.List.Split
import System.IO
import Data.List
import Data.String
import Data.Int
import Data.Word
-- More modules may be imported

import Util

--- external signatures (NICHT ÄNDERN!)
getMove :: String -> String
getMove listMoves= "something" 


listMoves :: String -> String
listMoves board = (iterateString board 9 1 "" (blacksTurn board)) ++ (iterateString (reverse board) 9 1 "" (blacksTurn board))



-- YOUR IMPLEMENTATION FOLLOWS HERE
convertColumnToChar :: Int -> Char 
convertColumnToChar columnPos = case columnPos of
    1 -> 'a'
    2 -> 'b'
    3 -> 'c'
    4 -> 'd'
    5 -> 'e'
    6 -> 'f'
    7 -> 'g'
    8 -> 'h'
    9 -> 'i'
    
    

blacksTurn :: String -> Bool 
blacksTurn board = 
    if(last board == 'b')
        then True 
        else False 


iterateString :: String -> Int -> Int -> String -> Bool -> String
iterateString board rowPos colPos allMoves blacksTurn
    |(x == 'r') && blacksTurn = iterateString (tail board) rowPos (colPos+1) (allMoves ++ blackRook (tail board) "" rowPos (colPos+1) rowPos colPos) blacksTurn
    |(x == 'r') && not blacksTurn = iterateString (tail board) rowPos (colPos+1) allMoves blacksTurn
    |x == '/' = iterateString (tail board) (rowPos-1) 0 allMoves blacksTurn
    |x == '9' = iterateString (tail board) rowPos (colPos + digitToInt '9') allMoves blacksTurn
    |(x == 'R') && not blacksTurn = iterateString (tail board) rowPos (colPos+1) (allMoves ++ redRook (tail board) "" rowPos (colPos+1) rowPos colPos) blacksTurn
    |(x == 'R') && blacksTurn = iterateString (tail board) rowPos (colPos+1) allMoves blacksTurn
    |(x == 's') && blacksTurn = blackSoldier
    |(x == 'S') && not blacksTurn = redSoldier
    |(x == ' ') = allMoves
    where x = head board
    

createMove :: Int -> Int -> Int -> Int -> String
createMove rowCounter colCounter rowPos colPos = ',' : (convertColumnToChar colPos) : intToDigit rowPos : '-' : (convertColumnToChar colCounter) : (intToDigit rowCounter) : []

blackSoldier :: String -> String -> Int -> Int -> Int -> Int -> String
blackSoldier rest moves rowCounter colCounter rowPos colPos
        |(rowCounter < 5) && (rowPos >=  )
        |(rowCounter > 4) && (rowPos > )
        |
        |
        |
        |
        |
        |
        |

  


blackRook :: String -> String -> Int -> Int -> Int -> Int -> String
blackRook rest moves rowCounter colCounter rowPos colPos
    |(rowCounter/=rowPos) && (colCounter /= colPos) = blackRook y moves rowCounter (colCounter+1) rowPos colPos
    |(rowCounter == rowPos) && ((x == 'R') || (x =='H') || (x == 'E') || (x == 'A') || (x == 'G') || (x == 'C') || (x == 'S')) = blackRook (recurseToEnd y) (moves ++ (createMove rowCounter colCounter rowPos colPos)) rowCounter colCounter rowPos colPos
    |(rowCounter == rowPos) && ((x == 'r') || (x =='h') || (x == 'e') || (x == 'a') || (x == 'g') || (x == 'c') || (x == 's'))  = blackRook (recurseToEnd y) moves rowCounter 0 rowPos colPos
    |(rowCounter /= rowPos) && ((x == 'R') || (x =='H') || (x == 'E') || (x == 'A') || (x == 'G') || (x == 'C') || (x == 'S')) = moves ++ (createMove rowCounter colCounter rowPos colPos) 
    |(x == '/') = redRook y moves (rowCounter-1) 0 rowPos colPos
    |(x == ' ') = moves
    |otherwise = blackRook y (moves ++ numberRecurse "" (digitToInt x) 0 colCounter rowCounter colPos rowPos) rowCounter (colCounter + digitToInt x) rowPos colPos
    where x = head rest
          y = tail rest
       

redRook :: String -> String -> Int -> Int -> Int -> Int -> String
redRook rest moves rowCounter colCounter rowPos colPos
    
    |(rowCounter/=rowPos) && (colCounter /= colPos) = redRook y moves rowCounter (colCounter+1) rowPos colPos
    |(rowCounter == rowPos) && ((x == 'r') || (x =='h') || (x == 'e') || (x == 'a') || (x == 'g') || (x == 'c') || (x == 's')) = redRook (recurseToEnd y) (moves ++ (createMove rowCounter colCounter rowPos colPos))  rowCounter (colCounter+1) rowPos colPos
    |(rowCounter == rowPos) && ((x == 'R') || (x =='H') || (x == 'E') || (x == 'A') || (x == 'G') || (x == 'C') || (x == 'S')) = redRook (recurseToEnd y) moves rowCounter 0 rowPos colPos
    |(rowCounter /= rowPos) && ((x == 'r') || (x =='h') || (x == 'e') || (x == 'a') || (x == 'g') || (x == 'c') || (x == 's')) = moves ++ (createMove rowCounter colCounter rowPos colPos)
    |(x == '/') = redRook y moves (rowCounter-1) 0 rowPos colPos
    |(x == ' ') = moves
    | otherwise = blackRook y (moves ++ numberRecurse "" (digitToInt x) 0 colCounter rowCounter colPos rowPos) rowCounter (colCounter + digitToInt x) rowPos colPos
    where x = head rest
          y = tail rest  


numberRecurse ::String -> Int -> Int -> Int -> Int -> Int -> Int -> String 
numberRecurse moves freeSpace iterationCounter colCounter rowCounter colPos rowPos = 
    if((iterationCounter+1) /= freeSpace)
        then
         numberRecurse (moves ++ createMove rowCounter colCounter rowPos colPos) freeSpace (iterationCounter+1) (colCounter+1) rowCounter colPos rowPos
        else moves
                               


recurseToEnd :: String -> String 
recurseToEnd rest = 
    if(head rest == '/')
        then rest 
        else recurseToEnd (tail rest)

