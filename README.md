

        <span style="color:black;">***Java Chess Game***</span>
    
                <span style="color:green;">'__Project Overview__'</span>
                    This is *a classic 2D* chess game developed in Java using Swing for the graphical user interface. The game features interactive piece movement,
                    basic game rules, and a clear visual representation of the chessboard and pieces. It's a foundational project aimed at demonstrating core 
                    game development principles, particularly event handling and object-oriented design in Java.

                <span style="color:red;">*Features*</span>
                    Standard Chess Pieces: All six chess piece types (King, Queen, Rook, Bishop, Knight, Pawn) are implemented.
                    Intuitive Mouse Controls: Drag and drop pieces across the board with ease.
                    Basic Movement Rules:
                    King: Moves one square in any direction (horizontal, vertical, or diagonal).
                    Queen: Moves any number of squares along horizontal, vertical, or diagonal lines.
                    Rook: Moves any number of squares along horizontal or vertical lines.
                    Bishop: Moves any number of squares along diagonal lines.
                    Knight: Moves in an 'L' shape (two squares in one direction, then one square perpendicular). Knights can jump over other pieces.
                    Pawn: Moves one square forward, two squares forward on its first move, and captures diagonally.
                    Piece Capture: Pieces can capture opponent's pieces by moving to their square.
                    Turn Management: Alternating turns for White and Black players.
                    Visual Feedback: Highlights the square where the piece is being dragged, indicating potential moves.

                <span style="color:red;">*Technologies Used*</span>
                    Java: The core programming language.
                    Swing: Java's GUI toolkit for creating the game window, board, and rendering pieces.
                    AWT (Abstract Window Toolkit): Used by Swing for basic graphics and event handling.


                <span style="color:red;">*File Structure*</span>
                    javpro-chess-game/
                    ├── src/
                    │   ├── javpro/
                    │   │   ├── GamePanel.java      # Main game logic, JFrame setup, game loop
                    │   │   ├── Board.java          # Renders the chessboard squares
                    │   │   └── Mouse.java          # Handles mouse input (clicks, drags, movement)
                    │   └── piece/
                    │       ├── Piece1.java         # Base class for all chess pieces
                    │       │                       # Contains common properties and methods (e.g., getImage, getX, getY, isValidSquare, etc.)
                    │       ├── King.java
                    │       ├── Queen.java
                    │       ├── Rook.java
                    │       ├── Bishop.java
                    │       ├── Knight.java
                    │       └── Pawn.java
                    └── images/                     # Directory for piece images (e.g., kw.png, kb.png, etc.)
                        ├── bw.png
                        ├── bb.png
                        ...
                    └── README.md


                <span style="color:red;">*Future Enhancements*</span>
                    This game provides a solid foundation, and there are many exciting features that could be added:
                        Special Chess Moves: Implement Castling, En Passant, and Pawn Promotion.
                        Check and Checkmate Logic: Detect when a King is in check and determine checkmate/stalemate.
                        Game State Management: Keep track of turns, game over conditions, and a move history.
                        AI Opponent: Develop an AI (e.g., using Minimax algorithm) to play against.
                        User Interface Improvements: Add score display, captured pieces list, move validation messages.
                        Sound Effects: Add audio cues for moves, captures, and check.
                        Network Play: Allow two players to play over a network.