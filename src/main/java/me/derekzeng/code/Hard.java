package me.derekzeng.code;
import java.util.*;
import java.util.regex.*;
import java.io.*;

public class Hard {

    public static class Point {
        int x;
        int y;
        Point() { x = 0; y = 0; }
        Point(int a, int b) { x = a; y = b; }
    }


    class Line {
        String type;
        double k;
        double m;
        double x;
        double y;

        Line(Point p1, Point p2) {
            if (p1.x == p2.x) {
                type = "h";
                x = p1.x;
            } else if (p1.y == p2.y) {
                type = "v";
                y = p1.y;
            } else {
                type = "n";
                k = (p1.y - p2.y) * 1.0 / (p1.x - p2.x);
                m = p1.y - k * p1.x;
            }
        }

        public String toString() {
            return String.format("(%s: %.2f %.2f %.2f %.2f)", type, k, m, x, y);
        }

        public boolean equals(Object _o) {
            Line o = (Line) _o;
            return (o.type.equals(type) &&
                    Double.compare(o.y, y) == 0 &&
                    Double.compare(o.x, x) == 0 &&
                    Double.compare(k, o.k) == 0 &&
                    Double.compare(m, o.m) == 0);
        }

        public int hashCode() {
            return 1;
        }
    }


    public int maxPoints(Point[] points) {
        int l = points.length;

        Integer[] dup = new Integer[l];
        if (l < 2) return l;
        Map<Line, Set<Point>> lines = new HashMap<>();

        for (int i = 0; i < l; i++) {
            dup[i] = 0;
            for (int j = i + 1; j < l; j++) {
                Point p1 = points[i];
                Point p2 = points[j];

                if (p1.x == p2.x && p1.y == p2.y) {
                    dup[i] += 1;
                } else {
                    Line line = new Line(p1, p2);
                    if (!lines.containsKey(line)) {
                        lines.put(line, new HashSet<>());
                    }

                    lines.get(line).add(p1);
                    lines.get(line).add(p2);
                }
            }
        }


        int max = 0;
        for (Set<Point> pts: lines.values()) {
            max = Math.max(max, pts.size());
        }
        return Math.max(max, Collections.max(Arrays.asList(dup)) + 1);
    }

    private boolean isInteger(String s) {
        if (s.isEmpty()) return false;
        if (s.startsWith("+") || s.startsWith("-")) {
            s = s.substring(1);
        }

        Pattern invalidPattern = Pattern.compile("[^0-9]");
        Matcher m = invalidPattern.matcher(s);
        if (m.find()) return false;
        return true;
    }

    private boolean isDecimal(String s) {
        if (s.isEmpty()) return false;
        if (s.startsWith("+") || s.startsWith("-")) {
            s = s.substring(1);
        }
        Pattern invalidPattern = Pattern.compile("[^0-9.]");
        Matcher m = invalidPattern.matcher(s);
        if (m.find()) return false;

        int idx = s.indexOf('.');
        if (idx == 0) {
            return isInteger(s.substring(1));
        } else if (idx == s.length() -1 ) {
            return isInteger(s.substring(0, s.length() -1));
        } else if (idx != -1) {
            return isInteger(s.substring(0, idx)) && isInteger(s.substring(idx+1));
        } else {
            return isInteger(s);
        }
    }

    public boolean isNumber(String s) {
        String trimmedStr = s.trim();
        if (trimmedStr.contains(" ")) {
            return false;
        }
        int idx = trimmedStr.indexOf('e');
        if (idx != -1) {
            return isInteger(trimmedStr.substring(idx + 1)) &&
                isDecimal(trimmedStr.substring(0, idx));
        }
        return isDecimal(trimmedStr);
    }


    public int integerReplacement(int n) {
        int count = 0;

        while (n != 1) {
            System.out.println(n);
            if ((n &1) == 0) {
                n >>>=1;
            } else if (n == 3 || ((n >>> 1) & 1) == 0) {
                n --;
            } else {
                n++;
            }
            count ++;
        }
        return count;

    }
}
