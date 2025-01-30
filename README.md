# Connect Four - Java Implementation

A **terminal-based** Connect Four game in Java, designed for **two players** with a structured **object-oriented approach**. Players take turns dropping discs into a **7-column, 6-row grid**, aiming to connect **four in a row** (horizontally, vertically, or diagonally). The game features **input validation**, **real-time win detection**, and **save/load functionality** to resume progress at any time.

## Features

- ✅ **Object-Oriented Design** – Modular classes (`Disc`, `Player`, `Grid`) for clean and scalable code.
- ✅ **Game Loop & Input Validation** – Ensures smooth and fair gameplay.
- ✅ **Win Detection Algorithm** – Checks all possible directions for a winning streak.
- ✅ **Save & Load Functionality** – Uses Java **Serialization** to store and retrieve game states.
- ✅ **Formatted Grid Display** – Provides a clear and structured game board in the console.
- ✅ **Error Handling** – Prevents invalid moves (e.g., full columns, out-of-range inputs).

## How to Play

1. Run the game and enter player names.
2. Take turns selecting a column (0-6) to drop your disc.
3. The game announces a winner if four discs align!
4. Choose to **save progress** and resume later.

## Running the Game

1. **Compile the code:**
   ```bash
   javac ConnectFour.java
   ```
2. **Run the game:**
   ```bash
   java ConnectFour
   ```
3. **Load a saved game** (optional) or start a new match.

## Future Enhancements

- 🔹 Implement an **AI opponent** for solo play.
- 🔹 Expand with a **GUI version** using JavaFX or Swing.
- 🔹 Add **custom grid sizes** for varied gameplay.

🚀 **Run in the terminal, challenge a friend, and enjoy!**

