# Chess Game - Complete Implementation

## Overview
This is a fully functional Java chess game with Swing GUI that implements all standard chess rules including check, checkmate, and stalemate detection.

## Features Completed

### ✅ Basic Gameplay
- **All Chess Pieces**: King, Queen, Rook, Bishop, Knight, Pawn with correct movement patterns
- **Drag and Drop Interface**: Intuitive mouse controls for piece movement
- **Turn Management**: Alternating turns between White and Black players
- **Piece Capture**: Proper capture mechanics for opponent pieces

### ✅ Advanced Chess Rules
- **Check Detection**: Prevents moves that would leave your king in check
- **Checkmate Detection**: Game ends when king is in check with no legal moves
- **Stalemate Detection**: Game ends in draw when no legal moves available but king not in check
- **Castling**: Both kingside and queenside castling implemented
- **En Passant**: Special pawn capture rule implemented
- **Pawn Promotion**: Pawns can be promoted to Queen, Rook, Bishop, or Knight

### ✅ Visual Features
- **Chess Board**: Classic alternating light/dark squares
- **Piece Images**: High-quality piece graphics for both colors
- **Move Highlighting**: Visual feedback showing valid move destinations
- **Game Status Display**: Shows current player turn, check warnings, and game results

### ✅ Game State Management
- **Move Validation**: All moves are validated according to chess rules
- **Game Over Detection**: Proper handling of checkmate and stalemate conditions
- **Turn Switching**: Automatic player switching after valid moves

## How to Run

### Prerequisites
- Java 17 or higher installed
- Display environment (for GUI)

### Running the Game
```bash
# Option 1: Use the provided script
./run_chess.sh

# Option 2: Run manually
java -cp classes:src/main/resources javpro.Main
```

### Compilation (if needed)
```bash
javac -cp src/main/java -d classes src/main/java/javpro/*.java src/main/java/piece/*.java
```

## How to Play

1. **Starting the Game**: White moves first
2. **Making Moves**: Click and drag pieces to move them
3. **Valid Moves**: The game only allows legal chess moves
4. **Check Warning**: The game displays when a king is in check
5. **Game End**: The game announces checkmate or stalemate

## Controls
- **Mouse Click**: Select a piece
- **Mouse Drag**: Move the selected piece
- **Mouse Release**: Complete the move (if valid)

## Game Rules Implemented

### Piece Movement
- **Pawn**: Forward one square, two on first move, diagonal capture, en passant
- **Rook**: Horizontal and vertical lines
- **Bishop**: Diagonal lines
- **Knight**: L-shaped moves (2+1 squares)
- **Queen**: Combination of rook and bishop
- **King**: One square in any direction, castling

### Special Rules
- **Castling**: King and rook move simultaneously (conditions: neither moved, no pieces between, not in check)
- **En Passant**: Pawn captures diagonally when opponent pawn moves two squares
- **Pawn Promotion**: Choose new piece when pawn reaches opposite end
- **Check**: King cannot be left in check
- **Checkmate**: King in check with no legal moves
- **Stalemate**: No legal moves but king not in check

## Technical Implementation

### Architecture
- **GamePanel**: Main game logic and rendering
- **Board**: Chess board rendering
- **Mouse**: Input handling
- **Piece1**: Base class for all pieces
- **Individual Pieces**: King, Queen, Rook, Bishop, Knight, Pawn classes

### Key Features
- **Move Simulation**: Tests moves before allowing them
- **Check Detection**: Validates king safety for all moves
- **Game State Tracking**: Monitors game progress and end conditions

## Project Structure
```
javpro-chess-game/
├── src/main/java/
│   ├── javpro/
│   │   ├── Main.java          # Entry point
│   │   ├── GamePanel.java     # Main game logic
│   │   ├── Board.java         # Board rendering
│   │   ├── Mouse.java         # Input handling
│   │   └── Type.java          # Piece type enum
│   └── piece/
│       ├── Piece1.java        # Base piece class
│       ├── King.java          # King piece
│       ├── Queen.java         # Queen piece
│       ├── Rook.java          # Rook piece
│       ├── Bishop.java        # Bishop piece
│       ├── Knight.java        # Knight piece
│       └── Pawn.java          # Pawn piece
├── src/main/resources/images/ # Piece images
├── classes/                   # Compiled classes
├── run_chess.sh              # Run script
└── README.md                 # Original project info
```

## Completed Enhancements

The following features from the original "Future Enhancements" list have been implemented:

- ✅ **Special Chess Moves**: Castling, En Passant, and Pawn Promotion
- ✅ **Check and Checkmate Logic**: Full implementation of check/checkmate detection
- ✅ **Game State Management**: Complete turn management and game over conditions
- ✅ **User Interface Improvements**: Game status display and move validation messages

## Remaining Enhancement Opportunities

- **AI Opponent**: Implement computer player with Minimax algorithm
- **Move History**: Track and display previous moves
- **Sound Effects**: Add audio feedback for moves and captures
- **Network Play**: Enable multiplayer over network
- **Save/Load Games**: Implement game state persistence
- **Timer/Clock**: Add chess clock functionality

The chess game is now fully playable with all standard chess rules implemented!