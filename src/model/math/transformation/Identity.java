package model.math.transformation;

/**
 * The Identity Matrix
 * @author Abdullah Emad
 * @version 1.0
 * @see Matrix
 */
public class Identity extends Matrix {

    /**
     * Creates the identity Matrix
     */
    public Identity() {
        super(initArray());
    }
    private static double[][] initArray(){
        double[][] mat = new double[4][4];

        for(int i = 0; i < mat.length; i++){
            for(int j = 0; j < mat[i].length; j++){
                mat[i][j] = 0;
            }
        }
        mat[0][0] = 1;
        mat[1][1] = 1;
        mat[2][2] = 1;
        mat[3][3] = 1;

        return mat;
    }
}
