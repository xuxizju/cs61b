
public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    static final double G = 6.67e-11;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
        this.xxVel = xV;
        this.xxPos = xP;
        this.yyVel = yV;
        this.yyPos = yP;
        this.mass = m;
        this.imgFileName = img;
    }

    public Planet(Planet p) {
        this.xxVel = p.xxVel;
        this.xxPos = p.xxPos;
        this.yyVel = p.yyVel;
        this.yyPos = p.yyPos;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        double dx_2 = Math.pow(this.xxPos - p.xxPos, 2);
        double dy_2 = Math.pow(this.yyPos - p.yyPos, 2);
        return Math.sqrt(dx_2 + dy_2);
    }

    public double calcForceExertedBy(Planet p) {
        double product = this.mass*p.mass;
        double distance = calcDistance(p);
        if(distance == 0) return 0;
        double result1 = product / distance;
        double result2 = G/distance;
        return result1 * result2;
    }

    public double calcForceExertedByX(Planet p) {
        double force = calcForceExertedBy(p);
        if (force == 0) return 0;
        double r = calcDistance(p);
        double dx = p.xxPos - this.xxPos;
        return force * dx / r;
    }

    public double calcForceExertedByY(Planet p) {
        double force = calcForceExertedBy(p);
        if (force == 0) return 0;
        double r = calcDistance(p);
        double dy = p.yyPos - this.yyPos;
        return force * dy / r;
    }

    public double calcNetForceExertedByX(Planet[] planets) {
        double x_force = 0;
        for (Planet p : planets) {
            x_force += calcForceExertedByX(p);
        }
        return x_force;
    }

    public double calcNetForceExertedByY(Planet[] planets) {
        double y_force = 0;
        for (Planet p : planets) {
            y_force += calcForceExertedByY(p);
        }
        return y_force;
    }

    /**
     *
     * @param time
     * @param xxforce
     * @param yyforce
     */
    public void update(double time, double xxforce, double yyforce) {
        double xx_a = xxforce/this.mass;
        double yy_a = yyforce/this.mass;
        this.xxVel = this.xxVel + xx_a*time;
        this.yyVel = this.yyVel + yy_a*time;
        this.xxPos = this.xxPos + this.xxVel * time;
        this.yyPos = this.yyPos + this.yyVel * time;
    }

}
