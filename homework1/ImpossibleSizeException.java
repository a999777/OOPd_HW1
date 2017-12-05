package homework1;

import java.awt.*;

/**
 * ImpossibleSizeException is an exception that is thrown when one is trying to set incompatible size to a shape object
 * a set of properties: {_alternativeDim}
 */
public class ImpossibleSizeException extends Exception{ //FIXME: Maybe something more specific?

    private Dimension _alternativeDim;
    /**
     * @requires dimension is not null
     * @effects Initializes this with a a given dimension
     */

    public ImpossibleSizeException(Dimension dimension) {
        _alternativeDim = new Dimension(dimension);
        if(dimension.height < 0){
            _alternativeDim.height = 0;
        }
        if(dimension.width < 0){
            _alternativeDim.width = 0;
        }

        //FIXME: This way the exception will always suggest the same size. Is it alright?
    }

    /**
     *
     * @return default dimension
     */
    public Dimension getAlternativeDim() {
        Dimension dimToReturn = new Dimension(_alternativeDim);
        return dimToReturn;
    }

}
