package game2048;

import java.util.Formatter;
import java.util.Observable;
import java.util.HashMap;


/** The state of a game of 2048.
 *  @author TODO: YOUR NAME HERE
 */
public class Model extends Observable {
    /** Current contents of the board. */
    private Board board;
    /** Current score. */
    private int score;
    /** Maximum score so far.  Updated when game ends. */
    private int maxScore;
    /** True iff game is ended. */
    private boolean gameOver;

    /* Coordinate System: column C, row R of the board (where row 0,
     * column 0 is the lower-left corner of the board) will correspond
     * to board.tile(c, r).  Be careful! It works like (x, y) coordinates.
     */

    /** Largest piece value. */
    public static final int MAX_PIECE = 2048;

    /** A new 2048 game on a board of size SIZE with no pieces
     *  and score 0. */
    public Model(int size) {
        board = new Board(size);
        score = maxScore = 0;
        gameOver = false;
    }

    /** A new 2048 game where RAWVALUES contain the values of the tiles
     * (0 if null). VALUES is indexed by (row, col) with (0, 0) corresponding
     * to the bottom-left corner. Used for testing purposes. */
    public Model(int[][] rawValues, int score, int maxScore, boolean gameOver) {
        int size = rawValues.length;
        board = new Board(rawValues, score);
        this.score = score;
        this.maxScore = maxScore;
        this.gameOver = gameOver;
    }

    /** Return the current Tile at (COL, ROW), where 0 <= ROW < size(),
     *  0 <= COL < size(). Returns null if there is no tile there.
     *  Used for testing. Should be deprecated and removed.
     *  */
    public Tile tile(int col, int row) {
        return board.tile(col, row);
    }

    /** Return the number of squares on one side of the board.
     *  Used for testing. Should be deprecated and removed. */
    public int size() {
        return board.size();
    }

    /** Return true iff the game is over (there are no moves, or
     *  there is a tile with value 2048 on the board). */
    public boolean gameOver() {
        checkGameOver();
        if (gameOver) {
            maxScore = Math.max(score, maxScore);
        }
        return gameOver;
    }

    /** Return the current score. */
    public int score() {
        return score;
    }

    /** Return the current maximum game score (updated at end of game). */
    public int maxScore() {
        return maxScore;
    }

    /** Clear the board to empty and reset the score. */
    public void clear() {
        score = 0;
        gameOver = false;
        board.clear();
        setChanged();
    }

    /** Add TILE to the board. There must be no Tile currently at the
     *  same position. */
    public void addTile(Tile tile) {
        board.addTile(tile);
        checkGameOver();
        setChanged();
    }

    /** Tilt the board toward SIDE. Return true iff this changes the board.
     *
     * 1. If two Tile objects are adjacent in the direction of motion and have
     *    the same value, they are merged into one Tile of twice the original
     *    value and that new value is added to the score instance variable
     * 2. A tile that is the result of a merge will not merge again on that
     *    tilt. So each move, every tile will only ever be part of at most one
     *    merge (perhaps zero).
     * 3. When three adjacent tiles in the direction of motion have the same
     *    value, then the leading two tiles in the direction of motion merge,
     *    and the trailing tile does not.
     * */
    public boolean tilt(Side side) {
        boolean changed;
        changed = false;
        this.board.setViewingPerspective(side);
        for (int c = 0; c < this.board.size(); c++){

            /** tracks tile movement **/
            HashMap<Integer, Integer> movement = new HashMap<Integer,Integer>();
            for (int i = 0; i < this.board.size(); i++){
                movement.put(i,0);
            }
            /** creates array representing column + adds tile values to the column **/
            Tile[] col = new Tile[this.board.size()];
            for (int r = 0; r < this.board.size(); r++){
                col[r] = this.board.tile(c,r);
            }
            /** slide function (only use for initial slide)**/
            movement = slide(col, movement);

            /** check if adjacent exists -> if yes, merge**/
            if (adjacentExists(col)){
                this.score += merge(col, c, movement);
            }
            /** check if board changed **/
            for (int i: movement.values()){
                if (i > 0){
                    changed = true;
                }
            }
            /** move(c,r,t) **/
            moving(this.board, c, movement);

        }

        // TODO: Modify this.board (and perhaps this.score) to account
        // for the tilt to the Side SIDE. If the board changed, set the
        // changed local variable to true.
        this.board.setViewingPerspective(Side.NORTH);
        checkGameOver();
        if (changed) {
            setChanged();
        }
        return changed;
    }

    private static HashMap<Integer, Integer> slide(Tile[] col, HashMap<Integer, Integer> movement){
        int spaces = 0;

        for (int i = col.length - 1; i >= 0; i--){
            if(col[i] == null){
                spaces++;
            } else {
                if(spaces > 0) {
                    col[i + spaces] = col[i];
                    col[i] = null;
                    movement.put(i,movement.get(i) + spaces);
                }
            }
        }
        return movement;
    }

    private static int merge(Tile[] col, int column, HashMap<Integer, Integer> movement){
        int scoreChange = 0;
        /** maps to original position**/
        HashMap<Integer, Integer> newPosition = new HashMap<>();
        for (int x = 0; x < col.length; x++) {
            if (movement.get(x) > 0) {
                newPosition.put(x + movement.get(x), x);
            }
        }
        for (int i = col.length - 1; i >= 1; i--) {
            if (col[i] == null || col[i - 1] == null) {
            } else if (col[i].value() == col[i - 1].value()) {
                scoreChange += col[i].value() * 2;
                int prev = newPosition.getOrDefault(i - 1, i - 1);
                movement.put(prev, movement.getOrDefault(prev, 0) + 1);
                i--;
                for (int j = i - 1; j >= 0; j--) {
                    if (col[j] != null) {
                        prev = newPosition.getOrDefault(j, j);
                        movement.put(prev, movement.get(prev) + 1);
                    }
                }
            }
        }
        return scoreChange;
    }


    private static void moving(Board b, int column, HashMap<Integer, Integer> movement){
        for (int r = b.size() - 1; r >= 0; r--){
            if(movement.get(r) > 0){
                Tile t = b.tile(column,r);
                b.move(column, (r + movement.get(r)), t);
            }
        }
    }

    /** Checks if the game is over and sets the gameOver variable
     *  appropriately.
     */
    private void checkGameOver() {
        gameOver = checkGameOver(board);
    }

    /** Determine whether game is over. */
    private static boolean checkGameOver(Board b) {
        return maxTileExists(b) || !atLeastOneMoveExists(b);
    }

    /** Returns true if at least one space on the Board is empty.
     *  Empty spaces are stored as null.
     * */
    public static boolean emptySpaceExists(Board b) {
        for (int r = 0; r < b.size(); r++){
            for (int c = 0; c < b.size(); c++){
                if (b.tile(c,r) == null){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns true if any tile is equal to the maximum valid value.
     * Maximum valid value is given by MAX_PIECE. Note that
     * given a Tile object t, we get its value with t.value().
     */
    public static boolean maxTileExists(Board b) {
        for (int r = 0; r < b.size(); r++){
            for (int c = 0; c < b.size(); c++){
                if (b.tile(c,r) == null){
                    continue;
                } else if (b.tile(c,r).value() == MAX_PIECE){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns true if there are any valid moves on the board.
     * There are two ways that there can be valid moves:
     * 1. There is at least one empty space on the board.
     * 2. There are two adjacent tiles with the same value.
     */
    public static boolean atLeastOneMoveExists(Board b) {
        for (int r = 0; r < b.size(); r++){
            Tile[] row = new Tile[b.size()];
            for (int c = 0; c < b.size(); c++){
                row[c] = b.tile(c,r);
            }
            if (adjacentExists(row)){
                return true;
            }
        }
        for (int c = 0; c < b.size(); c++){
            Tile[] col = new Tile[b.size()];
            for (int r = 0; r < b.size(); r++){
                col[r] = b.tile(c,r);
            }
            if (adjacentExists(col)){
                return true;
            }
        }

        if (emptySpaceExists(b)) {
            return true;
        }
        return false;
    }

    private static boolean adjacentExists(Tile[] line){
        for (int i = 0; i < line.length-1; i++){
            if (line[i] == null || line[i+1] == null){
                continue;
            } else if (line[i].value() == line[i+1].value()){
                return true;
            }
        }
        return false;
    }


    @Override
     /** Returns the model as a string, used for debugging. */
    public String toString() {
        Formatter out = new Formatter();
        out.format("%n[%n");
        for (int row = size() - 1; row >= 0; row -= 1) {
            for (int col = 0; col < size(); col += 1) {
                if (tile(col, row) == null) {
                    out.format("|    ");
                } else {
                    out.format("|%4d", tile(col, row).value());
                }
            }
            out.format("|%n");
        }
        String over = gameOver() ? "over" : "not over";
        out.format("] %d (max: %d) (game is %s) %n", score(), maxScore(), over);
        return out.toString();
    }

    @Override
    /** Returns whether two models are equal. */
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else if (getClass() != o.getClass()) {
            return false;
        } else {
            return toString().equals(o.toString());
        }
    }

    @Override
    /** Returns hash code of Model’s string. */
    public int hashCode() {
        return toString().hashCode();
    }
}
