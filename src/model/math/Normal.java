package model.math;

/**
 * The normal class represents a vector that has a length of 1
 * @author Abdullah Emad
 * @version 1.0
 * @see Vector
 */
public class Normal extends Vector{
    Normal(double x, double y, double z){
         super(x, y, z);
         assert(Math.abs(this.magnitude() - 1) <= 0.000001);
    }
}
