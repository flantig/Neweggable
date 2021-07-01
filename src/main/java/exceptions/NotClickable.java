package exceptions;

public class NotClickable extends Exception{

    /**
     * TODO: Add screenshot and logger txt file export
     */
    public NotClickable(){
        super("This element was not clickable within the time span");

    }
}
