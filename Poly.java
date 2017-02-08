import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by Dmitri on 08.02.2017.
 */
public class Poly {
    ArrayList<Double> x = new ArrayList<>();
    ArrayList<Double> y = new ArrayList<>();

    double func(double x) {
        double res = Math.cos(x) - Math.sin(x);
        return res;
    }

    void initXY(double a, double b, double n) {
        double h = (b - a) / n;
        System.out.println("h = " + h);
        for (int i = 0; i <= n; i++) {
            x.add(a + i * h);
            y.add((double) Math.round(func(x.get(i))));
            System.out.println("x[" + i + "] = " + x.get(i) + "; y[" + i + "] = " + y.get(i));
        }
    }

    double Lagrange(double xx, double n) {
        double li;
        double result = 0.0;
        for (int i = 0; i < n; i++) {
            li = 1;
            for (int j = 0; j < n; j++) {
                if (i != j) li *= (xx - x.get(j)) / (x.get(i) - x.get(j));
            }
            result += li * y.get(i);
        }
        return result;
    }

    double Newton(double xx, double n) {
        double result = y.get(0);
        double F;
        double den;
        for (int i = 1; i < n; i++) {
            F = 0;
            for (int j = 0; j <= i; j++) {//следующее слагаемое полинома
                den = 1;
//считаем знаменатель разделенной разности
                for (int k = 0; k <= i; k++) {
                    if (k != j) den *= (x.get(j) - x.get(k));
                }
//считаем разделенную разность
                F += y.get(j) / den;
            }
//домножаем разделенную разность на скобки (x-x[0])...(x-x[i-1])
            for (int k = 0; k < i; k++) F *= (xx - x.get(k));
            result += F;//полином
        }
        return result;
    }

    public static void main(String[] args) {
        double a = -Math.PI / 2;
        double b = Math.PI / 2;
        double n = 2.0;
        Poly p = new Poly();
        p.initXY(a, b, n);
        System.out.println("Лагранж в точке а: " + p.Lagrange(a, n));
        System.out.println("Лагранж в точке b: " + p.Lagrange(b, n));
        System.out.println("Ньютон в точке а: " + p.Newton(a, n));
        System.out.println("Ньютон в точке b: " + p.Newton(b, n));
    }
}
