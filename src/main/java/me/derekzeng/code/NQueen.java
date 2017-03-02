package me.derekzeng.code;
import java.util.*;
import java.io.*;


public class NQueen {
    String[] board;
    List<List<String>> ret;

    public List<List<String>> solveNQueens(int n) {
        board = new String[n];

        boolean[] rightleft = new boolean[n*2];
        boolean[] leftright = new boolean[n*2];
        boolean[] columns = new boolean[n];

        ret = new ArrayList<>();
        placeQueen(0, rightleft, leftright, columns);
        for (List<String> b: ret) {
            for (String line: b) {
                System.out.println(line);
            }
            System.out.println();
        }
        return ret;
    }

    void placeQueen(int row, boolean[] rightleft, boolean[] leftright, boolean[] columns) {
        if (row == board.length) {
            ret.add(Arrays.asList(board.clone()));
            return;
        }

        for (int col=0;col<board.length;col++) {
            int rli=col + row;
            int lri=row-col+board.length - 1;
            if (!columns[col] && !rightleft[rli] && !leftright[lri]) {
                char[] r = new char[board.length];
                Arrays.fill(r, '.');
                r[col] = 'Q';
                board[row] = new String(r);
                columns[col] = true;
                leftright[lri] = true;
                rightleft[rli] = true;
                placeQueen(row+1, rightleft, leftright, columns);
                columns[col] = false;
                leftright[lri] = false;
                rightleft[rli] = false;
            }
        }
    }

}
