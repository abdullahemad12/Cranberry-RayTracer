package model.math.transformation;

import model.math.Normal;
import model.math.Point;
import model.math.Vector;

public class Matrix {

    protected double[][] mat;

    protected Matrix(double[][] mat){
        this.mat = mat;
    }
    public Matrix(Matrix m){ mat = m.mat; }

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

    public Vector multiply(Vector vec) {
        double[] vector = new double[]{vec.getX(), vec.getY(), vec.getZ(), 0};
        double[] res = multiply(vector);
        return new Vector(res[0], res[1], res[2]);
    }

    public Point multiply(Point p) {
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

    protected Matrix transpose(){
        double[][] res = new double[4][4];

        for(int i = 0; i < res.length; i++){
            for(int j = 0; j < res.length; j++){
                res[i][j] = this.mat[j][i];
            }
        }
        return new Matrix(res);
    }

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

    public Vector transform(Vector vec){
        return this.multiply(vec);
    }

    public Normal transform(Normal norm){
        Vector v = this.invert().transpose().multiply(norm);
        return new Normal(v.getX(), v.getY(), v.getZ());
    }

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
