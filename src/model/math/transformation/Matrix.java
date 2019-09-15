package model.math.transformation;

import model.math.Normal;
import model.math.Point;
import model.math.Vector;

/**
 * The Matrix class represents a matrix that supports different matrices operations
 * such as matrix-matrix multiplication, matrix-vector multiplication, inverse matrix
 * transpose matrix
 * @author  Abdullah Emad
 * @version 1.0
 */
public class Matrix {

    protected double[][] mat;

    protected Matrix(double[][] mat){
        this.mat = mat;
    }

    /**
     * Creates a copy of the given matrix (the copy is not a deep copy)
     * @param m the matrix to be copied
     */
    public Matrix(Matrix m){ mat = m.mat; }

    /**
     * <b>EFFECTS: <b/>performs matrix matrix addition and returns the result as a new Matrix
     * @param mat the matrix to be added to this matrix
     * @return the result of the addition as a matrix
     */
    protected Matrix add(Matrix mat){
        double[][] res = new double[4][4];

        for(int i = 0; i < res.length - 1; i++){
            for(int j = 0; j < res[i].length - 1; j++){
                res[i][j] = this.mat[i][j] + mat.mat[i][j];
            }
        }
        res[3][3] = 1;
        return new Matrix(res);
    }

    /**
     *  <b>EFFECTS: <b/>performs matrix matrix subtraction and returns the result as a new Matrix
     * @param mat the matrix to be added to this matrix
     * @return the result of the subtraction as a matrix
     */
    public Matrix subtract(Matrix mat){
        double[][] res = new double[4][4];

        for(int i = 0; i < res.length - 1; i++){
            for(int j = 0; j < res[i].length - 1; j++){
                res[i][j] = this.mat[i][j] - mat.mat[i][j];
            }
        }
        res[3][3] = 1;
        return new Matrix(res);
    }

    /**
     * <b>EFFECTS: <b/> performs matrix-matrix multiplication
     * @param mat the Matrix to be multiplied with this Matrix
     * @return the result of the multiplication as a Matrix
     */
    public Matrix multiply(Matrix mat){
        double[][] res = new double[4][4];

        for(int i = 0; i < res.length; i++){
            for(int j = 0; j < mat.mat[i].length; j++){
                double sum = 0;
                for(int k = 0; k < res[i].length; k++){
                    sum += (mat.mat[k][i] * this.mat[j][k]);
                }
                res[j][i] = sum;
            }
        }

        return new Matrix(res);
    }

    /**
     * <b>EFFECTS: <b/> performs Matrix-Vector Multiplication
     * @param vec the vector to multiplied with this Matrix
     * @return the result of the multiplication as vector
     */
    private Vector multiply(Vector vec) {
        double[] vector = new double[]{vec.getX(), vec.getY(), vec.getZ(), 0};
        double[] res = multiply(vector);
        return new Vector(res[0], res[1], res[2]);
    }

    /**
     * <b>EFFECTS: </b> performs Matrix-Point multiplication
     * @param p the point to be multiplied with this Matrix
     * @return the result of the multiplication as a Point
     */
    private Point multiply(Point p) {
        double[] point = new double[]{p.getX(), p.getY(), p.getZ(), 1};

        double[] res = multiply(point);

        for(int i = 0; i < res.length; i++) {
            res[i] = res[i] / res[res.length - 1];
        }

        return new Point(res[0], res[1], res[2]);
    }

    private double[] multiply(double[] arr){
        double[] res = new double[4];
        for(int i = 0; i < mat.length; i++){
            double sum = 0;
            for(int j = 0; j < mat[i].length; j++){
                sum += (arr[j] * mat[i][j]);
            }
            res[i] = sum;
        }
        return res;
    }

    /**
     * <b>EFFECTS: <b/> Transposes this Matrix
     * @return the result of the transpose operation as a Matrix
     */
    protected Matrix transpose(){
        double[][] res = new double[4][4];

        for(int i = 0; i < res.length; i++){
            for(int j = 0; j < res.length; j++){
                res[i][j] = this.mat[j][i];
            }
        }
        return new Matrix(res);
    }

    /**
     * <b>EFFECTS: <b/> calculates the inverse of this Matrix
     * @return the result of the inverse operation as a Matrix
     */
    public Matrix invert(){

        double[][] matrix = new double[4][8];
        for(int i = 0; i < matrix.length; i++){
            System.arraycopy(this.mat[i], 0, matrix[i], 0, 4);
        }

        matrix[0][4] = 1;
        matrix[1][5] = 1;
        matrix[2][6] = 1;
        matrix[3][7] = 1;


        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix.length; j++){
                if(j == i){
                    continue;
                }

                double[] row = copyRow(matrix, i);


                double factor = - (matrix[j][i] / matrix[i][i]);

                multiplyRow(row, factor);

                addToRow(matrix, row, j);

            }
            normalizeRow(matrix, i);
        }
        double[][] tmp = matrix;
        matrix = new double[4][4];
        for(int i = 0; i < tmp.length; i++){
            if (tmp[i].length - 3 >= 0) System.arraycopy(tmp[i], 4, matrix[i], 0, tmp[i].length - 4);
        }

        return new Matrix(matrix);
    }

    private static void normalizeRow(double[][] matrix, int i){
        double factor = (1 / matrix[i][i]);
        multiplyRow(matrix[i], factor);
    }
    private static void addToRow(double[][] matrix, double[] row, int i){
        assert(row.length == matrix[i].length);
        for(int j = 0; j < matrix[i].length; j++){
            matrix[i][j] += row[j];
        }
    }
    private static void multiplyRow(double[] row, double factor){
        for(int i = 0; i < row.length; i++){
            row[i] = row[i] * factor;
        }
    }
    private static double[] copyRow(double[][] matrix, int i){
        double[] row = new double[matrix[i].length];
        System.arraycopy(matrix[i], 0, row, 0, matrix[i].length);
        return row;
    }

    /**
     * Applies the current Matrix transformation to vec
     * @param vec the vector to be transformed
     * @return the transformed vector
     */
    public Vector transform(Vector vec){
        return this.multiply(vec);
    }

    /**
     * Applies the current Matrix transformation to point
     * @param point the ponit to be transformed
     * @return the transformed point
     */
    public Point transform(Point point){
        return this.multiply(point);
    }

    /**
     * Applies the normal transformation as Follow (this.T)^-1 . norm
     * The same translation that was used to transform the associated point should be
     * used to transform this normal since this transformation multiplies the normal by
     * the inverse transpose of this Matrix
     * @param norm the normal to be transformed
     * @return the transformed Normal
     */
    public Normal transform(Normal norm){
        Vector v = this.invert().transpose().multiply(norm);
        return v.normalize();
    }

    /**
     * Checks if the given Matrix is equals to this Matrix by comparing all
     * the elements in both matrices
     * @param m Matrix to be compared
     * @return true if both Matrices contain the same elements, false otherwise
     */
    public boolean equals(Matrix m){
        double[][] mat = m.mat;
        if(mat.length != this.mat.length){
            return false;
        }
        for(int i = 0; i < mat.length; i++){
            if(mat[i].length != this.mat[i].length){
                return false;
            }
            for(int j = 0; j < mat[i].length; j++){
                if(Math.abs(mat[i][j] - this.mat[i][j]) >= 0.00001){
                    return false;
                }
            }
        }
        return true;
    }
}
