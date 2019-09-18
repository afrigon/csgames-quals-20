import qualified Control.Exception as E
import qualified Data.ByteString.Char8 as C
import Network.Socket
import Network.Socket.ByteString (recv, sendAll)
import Data.List.Split

resolve host port = do
    let hints = defaultHints { addrSocketType = Stream }
    addr:_ <- getAddrInfo (Just hints) (Just host) (Just port)
    return addr

open addr = do
    sock <- socket (addrFamily addr) (addrSocketType addr) (addrProtocol addr)
    connect sock $ addrAddress addr
    return sock

main :: IO ()
main = withSocketsDo $ do
    addr <- resolve "127.0.0.1" "8100"
    E.bracket (open addr) close solve

solve sock = do
    -- this is where the magic happens

    question <- recv sock 1024

    C.putStrLn question
    sendAll sock question
    
