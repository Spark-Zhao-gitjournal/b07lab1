import java.util.ArrayList;
import java.lang.Math;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Polynomial{
    double[] coefficients;
    int[] exponents;
    public Polynomial(){
        this.coefficients = new double[]{0.0};
        this.exponents = new int[]{0};
    }
    public Polynomial(double[] coefficients, int[] exponents){
        this.coefficients = new double[coefficients.length];
        for(int i=0 ; i<coefficients.length ; i++){
            this.coefficients[i] = coefficients[i];
        this.exponents = new int[exponents.length];
        for(int i=0 ; i<exponents.length ; i++){
            this.exponents[i] = exponents[i];
        }
    }
    public Polynomial add(Polynomial p){
        int len1 = p.exponents.length;
        int len2 = this.exponents.length;
        int flag = 0;
        double[] result = new double[len];
        for(int i=0 ; i<len1 ; i++){
            for(int j=0 ; j<len2 ; j++){
                if (p.exponents[i] == this.exponents[j]){
                    this.coefficients[j] += p.coefficients[i];
                    flag = 1;
                    }
            }
            if (flag == 0){
                this.exponents.add(p.exponents[i]);
                this.coefficients.add(p.coefficients[i]);
            }
            flag = 0;
        }
        return this;
    }
    public double evaluate(double x){
        double result = 0.0;
        for(int i=0 ; i<this.coefficients.length ; i++){
            result += this.coefficients[i] * Math.pow(x , this.exponents[i]);
        }
        return result;
    }
    public boolean hasRoot(double x){
        return evaluate(x) == 0;
    }
    public Polynomial multiply(Polynomial p) {
        int len1 = this.coefficients.length;
        int len2 = p.coefficients.length;
        int resultLen = len1 + len2 - 1;
        double[] resultCoefficients = new double[resultLen];
        int[] resultExponents = new int[resultLen];
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                int newExponent = this.exponents[i] + p.exponents[j];
                double newCoefficient = this.coefficients[i] * p.coefficients[j];
                for (int k = 0; k < resultLen; k++) {
                    if (resultExponents[k] == newExponent) {
                        resultCoefficients[k] += newCoefficient;
                        break;
                    } else if (resultExponents[k] == 0) {
                        resultCoefficients[k] = newCoefficient;
                        resultExponents[k] = newExponent;
                        break;
                    }
                }
            }
        }
        return new Polynomial(resultCoefficients, resultExponents);
    }
    public Polynomial(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        reader.close();
        String[] terms = line.split("\\+");
        double[] coefficients = new double[terms.length];
        int[] exponents = new int[terms.length];
        for (int i = 0; i < terms.length; i++) {
            String term = terms[i].trim();
            if (term.isEmpty()) {
                continue;
            }
            String[] parts = term.split("x");
            if (parts.length == 1) {
                coefficients[i] = Double.parseDouble(parts[0]);
                exponents[i] = 0;
            } else if (parts.length == 2) {
                coefficients[i] = Double.parseDouble(parts[0]);
                exponents[i] = (parts[1].isEmpty()) ? 1 : Integer.parseInt(parts[1]);
            } else {
                throw new IllegalArgumentException("Invalid polynomial format.");
            }
        }
        this.coefficients = coefficients;
        this.exponents = exponents;
    }
    public void saveToFile(String fileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        for (int i = 0; i < coefficients.length; i++) {
            if (coefficients[i] != 0.0) {
                if (coefficients[i] > 0 && i != 0) {
                    writer.write("+");
                }
                if (exponents[i] == 0) {
                    writer.write(Double.toString(coefficients[i]));
                } else {
                    writer.write(Double.toString(coefficients[i]) + "x");
                    if (exponents[i] != 1) {
                        writer.write(Integer.toString(exponents[i]));
                    }
                }
            }
        }
        writer.newLine();
        writer.close();
    }
}
public class Driver {
public static void main(String [] args) {
Polynomial p = new Polynomial();
System.out.println(p.evaluate(3));
double [] c1 = {6,0,1};
double [] c2 = {2,1,3};
Polynomial p1 = new Polynomial(c1, c2);
double [] c3 = {1,-2,5};
double [] c4 = {3,5,2};
Polynomial p2 = new Polynomial(c3, c4);
Polynomial s = p1.add(p2);
System.out.println("s(0.1) = " + s.evaluate(0.1));
if(s.hasRoot(1))
System.out.println("1 is a root of s");
else
System.out.println("1 is not a root of s");
}
}