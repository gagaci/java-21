package org.example;

import java.util.List;
import java.util.Map;

record Circle(Point center, int radius) {}
record Rectangle(Point topLeft, Point bottomRight) {}

record JsonObject(Map<String, Object> fields) {}
record JsonArray(List<Object> items) {}
record JsonString(String value) {}
record JsonNumber(double value) {}


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


        System.out.println(describe(new Circle(new Point(0,0),5)));

        process(new JsonString("{}"));



    }


    static void process(Object json) {
        switch (json) {
            case JsonObject(var fields) -> {
                System.out.println("Object with " + fields.size() + " fields");
                fields.forEach((k, v) -> System.out.println("  " + k + ": " + v));
            }

            case JsonArray(var items) -> {
                System.out.println("Array with " + items.size() + " items");
            }

            case JsonString(var s) -> {
                System.out.println("String: " + s);
            }

            case JsonNumber(var n) -> {
                System.out.println("Number: " + n);
            }

            default -> System.out.println("Unknown JSON type");
        }
    }


    static String describe(Object shape) {
        return switch (shape) {
            case Circle(Point(int x, int y), int r) ->
                    "Circle at (%d,%d) radius %d".formatted(x, y, r);

            case Rectangle(Point(int x1, int y1), Point(int x2, int y2)) ->
                    "Rectangle from (%d,%d) to (%d,%d)".formatted(x1, y1, x2, y2);

            case Point(int x, int y) ->
                    "Point at (%d,%d)".formatted(x, y);

            default -> "Unknown shape";
        };
    }

}