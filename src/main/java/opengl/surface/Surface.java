package opengl.surface;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.vecmath.Vector3d;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

@Getter
@NoArgsConstructor
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

    public Vector3d getNormal(double alfa, double t, double step) {
        Vector3d vector1 = new Vector3d();
        Vector3d vector2 = new Vector3d();
        Vector3d vector3 = new Vector3d();
        vector1.x = (countX(alfa + step, t) - countX(alfa, t)) / step;
        vector1.y = (countY(alfa + step, t) - countY(alfa, t)) / step;
        vector1.z = (countZ(t) - countZ(t)) / step;
        vector2.x = (countX(alfa, t + step) - countX(alfa, t)) / step;
        vector2.y = (countY(alfa, t + step) - countY(alfa, t)) / step;
        vector2.z = (countZ(t + step) - countZ(t)) / step;
        vector3.cross(vector1, vector2);
        vector3.normalize();
        return vector3;
    }

}
