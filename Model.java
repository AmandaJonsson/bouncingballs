/**
 * Created by amandajonsson on 2017-04-15.
 */
package bouncing_balls;

/**
 * The physics model.
 *
 * This class is where you should implement your bouncing balls model.
 *
 * The code has intentionally been kept as simple as possible, but if you wish, you can improve the design.
 *
 * @author Simon Robillard
 *
 */
class Model {

    double areaWidth, areaHeight;

    Ball [] balls;

    Model(double width, double height) {
        areaWidth = width;
        areaHeight = height;

        // Initialize the model with a few balls
        balls = new Ball[2];
        balls[0] = new Ball(width / 3, height * 0.9, 1.2, 1.6, 0.2, 0.5);
        balls[1] = new Ball(2 * width / 3, height * 0.7, -0.6, 0.6, 0.3, 0.9);
    }

    void step(double deltaT) {


        // TODO this method implements one step of simulation with a step deltaT
        for (Ball b : balls) {
            // detect collision with the border
            if (b.x < b.radius || b.x > areaWidth - b.radius) {
                b.vx *= -1;
                // change direction of ball
            }
            if (b.y < b.radius || b.y > areaHeight - b.radius) {
                b.vy *= -1;
            }

            // compute new position according to the speed of the ball
            b.x += deltaT * b.vx;
            b.y += deltaT * b.vy;

        }
        double dx = Math.abs(balls[0].x - balls[1].x);
        double dy = Math.abs(balls[0].y - balls[1].y);


        double hypo = balls[0].radius + balls[1].radius;
        double distance = Math.sqrt((dx * dx) + (dy * dy) );

        double v0 = balls[0].vx+balls[0].vy;
        double v1 = balls[1].vx+balls[1].vy;

        double I = balls[0].mass*v0 + balls[1].mass*v1;
        double R = -(v0-v1);


        if ( distance <= hypo ){

            balls[0].vx = balls[0].vx * -1;
            balls[0].vy = balls[0].vy * -1;
            balls[1].vx = balls[1].vx * -1;
            balls[1].vy = balls[1].vy * -1;
        }

        //if(distance<=hypo && balls[0].x < balls[1].x) {

        // balls[0].vx = 2.54545;

            //balls[1].vx = -0.254545;
        //}




        }



    /**
     * Simple inner class describing balls.
     */
    class Ball {

        Ball(double x, double y, double vx, double vy, double r, double m) {
            this.x = x;
            this.y = y;
            this.vx = vx;
            this.vy = vy;
            this.radius = r;
            this.mass = m;
        }

        /**
         * Position, speed, and radius of the ball. You may wish to add other attributes.
         */
        double x, y, vx, vy, radius, mass;
    }
}
