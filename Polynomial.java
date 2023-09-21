import java.lang.Math;
public class Polynomial{
    double[] coefficients;
    public Polynomial(){
        this.coefficients = new double[]{0.0};
    }
    public Polynomial(double[] coefficients){
        this.coefficients = new double[coefficients.length];
        for(int i=0 ; i<coefficients.length ; i++){
            this.coefficients[i] = coefficients[i];
        }
    }
    public Polynomial add(Polynomial p){
        int len = Math.max(this.coefficients.length, p.coefficients.length);
        double[] result = new double[len];
        for(int i=0 ; i<len ; i++){
            double a = (i < this.coefficients.length) ? this.coefficients[i] : 0.0;
            double b = (i < p.coefficients.length) ? p.coefficients[i] : 0.0;
            result[i] = a + b;
        }
        return new Polynomial(result);
    }
    public double evaluate(double x){
        double result = 0.0;
        for(int i=0 ; i<this.coefficients.length ; i++){
            result += this.coefficients[i] * Math.pow(x , i);
        }
        return result;
    }
    public boolean hasRoot(double x){
        return evaluate(x) == 0;
    }
}