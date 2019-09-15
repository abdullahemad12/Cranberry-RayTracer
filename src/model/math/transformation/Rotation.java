package model.math.transformation;

/**
 * Rotation Matrix
 * @author Abdullah Emad
 * @version 1.0
 * @see Matrix
 */
public class Rotation extends Matrix{
    /**
     * Creates a rotation matrix with the given translation values that could be used to
     * rotate a multidimensional component about a given vector
     * @param x the x value of the vector
     * @param y the y value of the vector
     * @param z the z value of the vector
     * @param deg the value of rotation in degrees
     */
    public Rotation(double x, double y, double z, double deg){
        super(initArray(x, y, z, deg));
    }

    private static Matrix initArray(double x, double y, double z, double deg){
        double rad = Math.toRadians(deg);
        double cos = Math.cos(rad);
        double ncosp1 = 1 - cos;
        double sin = Math.sin(rad);

        double[][] mat1 = new double[4][4];

        mat1[0][0] = cos;
        mat1[1][1] = cos;
        mat1[2][2] = cos;
        mat1[3][3] = 1;
        Matrix m1 = new Matrix(mat1);


        double[][] mat2 = new double[4][4];

        mat2[0][0] = x * x * ncosp1;
        mat2[0][1] = x * y * ncosp1;
        mat2[0][2] = x * z * ncosp1;
        mat2[0][3] = 0;
        mat2[1][0] = x * y * ncosp1;
        mat2[1][1] = y * y * ncosp1;
        mat2[1][2] = y * z * ncosp1;
        mat2[1][3] = 0;
        mat2[2][0] = x * z * ncosp1;
        mat2[2][1] = y * z * ncosp1;
        mat2[2][3] = 0;
        mat2[3][0] = 0;
        mat2[3][1] = 0;
        mat2[3][2] = 0;
        mat2[3][3] = 1;

        Matrix m2 = new Matrix(mat2);


        double[][] mat3 = new double[4][4];


        mat3[0][0] = 0;
        mat3[0][1] = -z * sin;
        mat3[0][2] = y * sin;
        mat3[0][3] = 0;
        mat3[1][0] = z * sin;
        mat3[1][1] = 0;
        mat3[1][2] = -x * sin;
        mat3[1][3] = 0;
        mat3[2][0] = -y * sin;
        mat3[2][1] = x * sin;
        mat3[2][2] = 0;
        mat3[2][3] = 0;
        mat3[3][0] = 0;
        mat3[3][1] = 0;
        mat3[3][2] = 0;
        mat3[3][3] = 1;

        Matrix m3 = new Matrix(mat3);
        return m1.add(m2).add(m3);
    }
}
