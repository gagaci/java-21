package org.example;

public class JEP440 {
    public static void main(String[] args) {

        Object obj = new Point(10,20);


        // used to declare it in this way
        if(obj instanceof  Point){
            Point p = (Point) obj;
            int x = p.x();
            int y = p.y();
            System.out.println("x: " + x + ", y: " + y);
        }

        // new clearer way
        Object obj2 = new Point(10, 20);

        if (obj2 instanceof Point(int x, int y)) {
            System.out.println("x: " + x + ", y: " + y);
        }

        // type interference
        if (obj2 instanceof Point(var x, var y)) {
            System.out.println("x: " + x + ", y: " + y);
        }

    }
}