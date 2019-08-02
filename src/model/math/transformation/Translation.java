package model.math.transformation;

public class Translation extends Matrix {
    public Translation(double x, double y, double z){
        super(initArray(x, y, z));
    }

    private static double[][] initArray(double x, double y, double z){
        double[][] mat = new double[4][4];

        for(int i = 0; i < mat.length; i++){
            for(int j = 0; j < mat[i].length; j++){
                mat[i][j] = 0;
            }
        }
        mat[0][0] = 1;
        mat[1][1] = 1;
        mat[2][2] = 1;
        mat[0][3] = x;
        mat[1][3] = y;
        mat[2][3] = z;
        mat[3][3] = 1;

        return mat;
    }
}
