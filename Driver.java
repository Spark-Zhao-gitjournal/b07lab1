public class Driver {
public static void main(String [] args) {
Polynomial p = new Polynomial();
System.out.println(p.evaluate(3));
double [] c1 = {6,0,1};
int [] c2 = {2,1,3};
Polynomial p1 = new Polynomial(c1, c2);
double [] c3 = {1,-2,5};
int [] c4 = {3,5,2};
Polynomial p2 = new Polynomial(c3, c4);
Polynomial s = p1.add(p2);
System.out.println("s(1) = " + s.evaluate(1));
Polynomial x = p1.multiply(p2);
System.out.println("x(1) = " + x.evaluate(1));
if(s.hasRoot(1))
System.out.println("1 is a root of s");
else
System.out.println("1 is not a root of s");
}
}