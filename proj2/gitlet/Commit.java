package gitlet;

// TODO: any imports you need here
import java.util.HashMap;
import java.util.Date;



/** Represents a gitlet commit object.
 *  TODO: It's a good idea to give a high level description here of this Class
 *  A commit captures a snapshot of changes made and contains:
 *  a commit ID, log message, and references to blobs + parent commits.
 *  @author Sabrina
 */
public class Commit {
    /**
     * TODO: add instance variables here.
     *
     * List all instance variables of the Commit class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided one example for `message`.
     */

    /** The message of this Commit. */
    private String message;
    /** Commit ID, SHA-1 hash value from blob references, parent reference, log message, and commit time*/
    private String ID;
    /** Date/timestamp in the current timezone */
    private Date time;
    /** Reference to parent commit */
    private Commit parent;
    /** Reference to blobs */
    // not sure where this goes yet
    // private HashMap blobs;

    /** main constructor */
    public Commit(String message, Commit parent){
        this.parent = parent;
        this.message = message;
        this.time = new Date();
        // this.blobs = ????
        // this.ID = given Utils sha1 function

    }
    /** constructor for "git init"'s commit */
    public Commit(){
        this.parent = null;
        this.message = "initial commit";
        this.time = new Date(0);
        //this.blobs = null;
        //this.ID = given Utils sha1 function
    }
    /** get instance variable methods */
    public Date getTime(){
        return this.time;
    }
    public String getMessage(){
        return this.message;
    }
    public Commit getParent() {
        return this.parent;
    }
    // public Hashmap getBlobs(){
    //  return this.blobs}
    // public String getID(){
    //  return this.ID;
}
