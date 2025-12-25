package org.example;

public class JEP441 {

    /* Pattern Matching for switch enhances the switch statement/expression to work with patterns,
    allowing you to test values against multiple patterns with more expressive and type-safe code. */

    public static void main(String[] args) {

        String day = "MONDAY";

        String result;

        // old switch - only works with constants
        switch (day) {
            case "MONDAY":
            case "TUESDAY":
            case "WEDNESDAY":
                result = "Weekday";
                break;
            case "SATURDAY":
            case "SUNDAY":
                result = "Weekend";
                break;
            default:
                result = "Unknown";
        }

        System.out.println(result);

        /* Limitations
        Only works with primitive types, enums, Strings
        Cannot match on object types
        Cannot match on conditions
        Requires break statements (error-prone)
        Cannot extract data from objects
        * */

        System.out.println(formatter("Integer.valueOf(3)"));

        System.out.println(formatter2("dsfsdf"));

        System.out.println(process(6));

        System.out.println(process2("salam dunya"));

    }

    // old way - verbose instanceof chain
    static String formatter(Object obj) {
        String result;
        if (obj instanceof Integer) {
            result = "Integer: " + obj;
        } else if (obj instanceof String) {
            result = "String: " + obj;
        } else if (obj instanceof Double) {
            result = "Double: " + obj;
        } else {
            result = "Unknown type";
        }
        return result;
    }

    // new way - using switch case
    static String formatter2(Object obj) {
        return switch (obj) {
            case Integer i -> "Integer v2: " + i;
            case String s  -> "String v2: " + s;
            case Double d  -> "Double v2: " + d;
            default        -> "Unknown type v2";
        };
    }

    // old way - with verbose code
    static String process(Object obj) {
        String result;
        if (obj == null) {
            result = "null";
        } else if (obj instanceof String) {
            String s = (String) obj;
            if (s.isEmpty()) {
                result = "empty string";
            } else if (s.length() > 10) {
                result = "long string";
            } else {
                result = "short string";
            }
        } else if (obj instanceof Integer) {
            Integer i = (Integer) obj;
            if (i > 0) {
                result = "positive";
            } else if (i < 0) {
                result = "negative";
            } else {
                result = "zero";
            }
        } else {
            result = "unknown";
        }
        return result;
    }

    static String process2(Object obj) {
        return switch (obj) {
            case null -> "null";
            case String s when s.isEmpty() -> "empty string";
            case String s when s.length() > 10 -> "long string";
            case String s -> "short string";
            case Integer i when i > 0 -> "positive";
            case Integer i when i < 0 -> "negative";
            case Integer i -> "zero";
            default -> "unknown";
        };
    }

}
