package model.math.transformation;

public class Scale extends Matrix{
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
