package homework1;

import java.awt.*;

/**
 * ImpossibleSizeException is an exception that is thrown when one is trying to set incompatible size to a shape object
 * a set of properties: {alternativeDim}
 */
public class ImpossibleSizeException extends Exception{

    private Dimension alternativeDim;

    /**
     * @requires dimension - the current size for the shape
     * @modifies Nothing
     * @effects Initializes this with a legal dimension
     */
    public ImpossibleSizeException(Dimension dimension) {
        //FIXME: Not sure we understand this well enough. We expect to get the current size and then return the current
        //FIXME: size as the alternative, "legal" size
        alternativeDim = new Dimension(dimension);
    }

    /**
     * @requires None
     * @modifies Nothing
     * @effects Returns the size we had before trying to change it to an illegal size
     */
    public Dimension getAlternativeDim() {
        Dimension dimToReturn = new Dimension(alternativeDim);
        return dimToReturn;
    }

}
