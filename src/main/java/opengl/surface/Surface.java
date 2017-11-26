package opengl.surface;

import lombok.Getter;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

@Getter
public class Surface {
    private double x, y, z = 0;
    private static final double R = 1;
    private static final double C = 1;
    private static final double D = 5;
    private static final double SIGMA = Math.PI/5;
    private static final double ALFA_START = 0;

    public Surface(double alfa, double t) {
        countX(alfa, t);
        countY(alfa, t);
        countZ(t);
    }

    private double countX(double alfa, double t) {
        x = R * cos(alfa) - (R * (ALFA_START - alfa) + t * cos(SIGMA) - C * sin(D * t) * sin(SIGMA)) * sin(alfa);
        return x;
    }

    private double countY(double alfa, double t) {
        y = R * sin(alfa) + (R * (ALFA_START - alfa) + t * cos(SIGMA) - C * sin(D * t) * sin(SIGMA)) * cos(alfa);
        return y;
    }

    private double countZ(double t) {
        z = t * sin(SIGMA) + C * sin(D * t) * cos(SIGMA);
        return z;
    }

}
