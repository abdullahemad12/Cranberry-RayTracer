package model.math.transformation;

/**
 * Scaling Matrix
 * @author Abdullah Emad
 * @version 1.0
 * @see Matrix
 */
public class Scale extends Matrix{
    /**
     * Creates a scaling matrix with the given translation values that could be used to
     * scale a multidimensional component in the three axes
     * @param x the scale value in the x axis
     * @param y the scale value in the y axis
     * @param z the scale value in the z axis
     */
    public Scale(double x, double y, double z){
        super(initArray(x, y, z));
    }

    private static double[][] initArray(double x, double y, double z){
        double[][] mat = new double[4][4];

        for(int i = 0; i < mat.length; i++){
            for(int j = 0; j < mat[i].length; j++){
                mat[i][j] = 0;
            }
        }
        mat[0][0] = x;
        mat[1][1] = y;
        mat[2][2] = z;
        mat[3][3] = 1;

        return mat;
    }
}
