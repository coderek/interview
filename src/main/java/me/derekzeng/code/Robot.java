package me.derekzeng.code;
import java.util.*;
import java.io.*;


public class Robot {
    class Pos {
        int x, y;
        Pos(int _x, int _y) {x=_x;y=_y;}
        public int hashCode() {return x*y;}
        public boolean equals(Object o) {
            Pos op = (Pos) o;
            return op.x==x && op.y==y;
        }
        Pos newPos(int dir) {
            return new Pos(x+dirChanges[dir][0], y+dirChanges[dir][1]);
        }
        public String toString() {
            return "("+x+","+y+")";
        }
    }

    int[][] dirChanges = {
        {-1,0}, {0,1}, {1,0}, {0,-1}
    };

    int curDir = 0;
    char[][] map;
    int w,h;
    Pos curPos;
    Set<Pos> cleaned = new HashSet<>();

    Robot(char[][] map) {
        this.map=map;
        h=map.length;
        w=map[0].length;
        curPos = findStartPos();
        System.out.println("Start at: " + curPos);
    }
    Pos findStartPos() {
        for (int i=0;i<h;i++) {
            for (int j=0;j<w;j++) {
                if (map[i][j] == '^') {
                    return new Pos(i,j);
                }
            }
        }
        return null;
    }

    boolean Move() {
        Pos newPos = curPos.newPos(curDir);
        if (newPos.x>= h || newPos.x<0 || newPos.y>=w || newPos.y <0) {
            return false;
        }
        if (map[newPos.x][newPos.y] == '+') {
            return false;
        }
        curPos = newPos;
        return true;
    }

    void TurnLeft(int k) {
        curDir = (curDir + 4 - k) % 4;
    }

    void TurnRight(int k) {
        curDir = (curDir + k) % 4;
    }

    void Clean() {
        cleaned.add(curPos);
    }

    boolean checkAllCleaned() {
        for (int i=0;i<h;i++) {
            for (int j=0;j<w;j++) {
                if ((map[i][j] == '^' || map[i][j] == '.') && !cleaned.contains(new Pos(i,j))) {
                    System.out.println(i+", " + j);
                    return false;
                }
            }
        }
        return true;
    }

    void explore() {
        // path from the origin to current position, by moving directions
        String path = "";
        // reduce form of cell represented by path
        Set<String> visited = new HashSet<>();
        dps(path, visited);
    }

    String reduced(String path) {
        int[] dirs = new int[4];
        for (char ch: path.toCharArray()) {
            dirs["NESW".indexOf(ch)] += 1;
        }
        int v = Math.min(dirs[0], dirs[2]);
        int h = Math.min(dirs[1], dirs[3]);
        dirs[0]-= v;
        dirs[1]-= h;
        dirs[2]-= v;
        dirs[3]-= h;
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<4;i++) {
            for (int j=0;j<dirs[i];j++) {
                sb.append("NESW".charAt(i));
            }
        }
        return sb.toString();
    }

    void dps(String path, Set<String> visited) {
        String p = reduced(path);
        if (visited.contains(p)) return;
        Clean();
        visited.add(p);

        for (int i=0;i<4;i++) {
            TurnLeft(i);

            if (Move()) {
                TurnRight(i);

                dps(p+"NESW".charAt(i), visited);

                TurnLeft((i+2)%4);
                Move();
                TurnRight((i+2)%4);
            } else {
                TurnRight(i);
            }
        }
    }

    public static void main(String[] args) {
        String[] map = {
            "+++++++++++",
            "+..+.++..++",
            "+.....^...+",
            "+..+......+",
            "+++++++++++",
        };
        char[][] chs = new char[map.length][map[0].length()];
        int i=0;
        for (String s:map) {
            chs[i++] = s.toCharArray();
        }

        Robot robot = new Robot(chs);
//         System.out.println(robot.reduced("EWSN"));
        robot.explore();
        System.out.println(robot.checkAllCleaned());
    }
}
