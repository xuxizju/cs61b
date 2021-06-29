public class NBody {
    public static void main(String[] args) {
        /**
         * read the universe radius
         * read the planets
         */
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);
        int size = planets.length;
        StdDraw.enableDoubleBuffering();

        for (int time = 0; time < T; time += dt) {
            double[] x_forces = new double[size];
            double[] y_forces = new double[size];
            /**
             * calculate forces
             */
            for (int i = 0; i < size; i++) {
                x_forces[i] = planets[i].calcNetForceExertedByX(planets);
                y_forces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            /**
             * update planets info
             */
            for (int i = 0; i < size; i++) {
                planets[i].update(dt,x_forces[i], y_forces[i]);
            }
            /**
             * draw background
             */
            String backgroundFile = "./images/starfield.jpg";
            StdDraw.setScale(-1*radius, radius);
            StdDraw.clear();
            StdDraw.picture(0, 0, backgroundFile);

            /**
             * draw planets
             */
            for (Planet p :planets) {
                StdDraw.picture(p.xxPos, p.yyPos, "images/"+p.imgFileName);
            }

            StdDraw.show();
            StdDraw.pause(10);
        }

        StdOut.printf("%d\n", size);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < size; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
    /**
     * read the number of second line which is the radius of the universe
     * @param path: the path of the planets info file
     *
     */
    public static double readRadius(String path) {
        In in = new In(path);
        in.readInt();
        return in.readDouble();
    }

    /**
     *
     * @param path the path of the planets info file
     * @return an array of five planets
     */
    public static Planet[] readPlanets(String path) {
        In in = new In(path);
        int size = in.readInt();
        in.readDouble();
        Planet[] planets = new Planet[size];
        for (int i = 0; i < size; i++) {
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double mass = in.readDouble();
            String img = in.readString();
            planets[i] = new Planet(xP, yP, xV, yV, mass, img);
        }
        return planets;
    }
}
