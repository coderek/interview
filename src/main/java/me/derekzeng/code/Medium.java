package me.derekzeng.code;
import java.util.*;
import java.util.regex.*;
import java.io.*;


public class Medium {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int lenl1 = 0;
        int lenl2 = 0;
        ListNode tmp=l1, makeup=null, makeuptail=null;
        while (tmp != null) {lenl1++;tmp=tmp.next;}
        tmp = l2;
        while (tmp != null) {lenl2++;tmp=tmp.next;}
        ListNode shorter = null;
        if (lenl1>lenl2) shorter = l2;
        if (lenl1<lenl2) shorter = l1;
        if (shorter != null) {
            makeup = new ListNode(0);
            makeuptail= makeup;
            int diff = Math.abs(lenl1-lenl2)-1;
            while (diff>0) {
                makeuptail.next = new ListNode(0);
                makeuptail=makeuptail.next;
                diff--;
            }
        }

        ListNode res = new ListNode(0);
        int carry = recur(l1, l2, res);
        if (carry > 0) {
            ListNode withCarry = new ListNode(carry);
            withCarry.next = res;
            return withCarry;
        } else {
            return res;
        }
    }

    int recur(ListNode l1, ListNode l2, ListNode res) {
        int carry = 0;

        if (l1.next != null || l2.next != null) {
            res.next = new ListNode(0);
            carry = recur(l1.next, l2.next, res.next);
        }

        int sum = carry;
        if (l1 != null) {
            sum += l1.val;
        }
        if (l2 != null) {
            sum += l2.val;
        }
        carry = sum / 10;
        res.val = sum % 10;
        return carry;
    }

    class Position {
        int r;
        int c;
        int dir; /// [right, down, left, up]
        Position(int _r, int _c, int _dir) {
            r = _r;
            c = _c;
            dir = _dir;
        }
        Position next(boolean[][] visited, int w, int h) {
            switch (dir) {
                case 0:
                    if (c < w-1 && !visited[r][c+1]) return new Position(r,c+1,0);
                    break;
                case 1:
                    if (r < h-1 && !visited[r+1][c]) return new Position(r+1,c,1);
                    break;
                case 2:
                    if (c>0 && !visited[r][c-1]) return new Position(r,c-1,2);
                    break;
                case 3:
                    if (r>0 && !visited[r-1][c]) return new Position(r-1,c,3);
                    break;
            }

            if (c < w-1&& !visited[r][c+1]) {
                return new Position(r, c+1,0);
            }
            if (r < h-1&&!visited[r+1][c]) {
                return new Position(r+1, c,1);
            }
            if (c>0 && !visited[r][c-1]) {
                return new Position(r, c-1,2);
            }
            if (r>0 && !visited[r-1][c]) {
                return new Position(r-1, c,3);
            }
            return null;
        }
    }
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> out = new ArrayList<>();

        int h = matrix.length;
        if (h==0) return out;

        int w = matrix[0].length;
        if (w==0) return out;

        boolean[][] visited = new boolean[h][w];
        Position nextPos = new Position(0,0,0);

        while (nextPos!=null) {
            out.add(matrix[nextPos.r][nextPos.c]);
            visited[nextPos.r][nextPos.c] = true;
            nextPos = nextPos.next(visited, w, h);
        }
        return out;
    }

    public boolean validUtf8(int[] data) {
        int i=0;
        int currentType = 0;
        while (i< data.length) {
            int n = data[i];
            if (currentType == 0) {
                if (((n >> 7) & 0b1) == 1) {
                    if (((n >> 5) & 0b111) == 0b110) {
                        currentType = 2;
                    } else if (((n >> 4) & 0b1111) == 0b1110) {
                        currentType = 3;
                    } else if (((n >> 3) & 0b11111) == 0b11110) {
                        currentType = 4;
                    } else {
                        return false;
                    }
                }
                i++;
            } else {
                System.out.println(currentType);
                if (((n >> 6) & 0b11) != 0b10) {
                    return false;
                }
                i++;

                switch (currentType) {
                    case 2:
                        currentType = 0;
                        break;
                    case 3:
                        if (i == data.length || ((data[i] >> 6) & 0b11) != 0b10) return false;
                        i++;
                        currentType = 0;
                        break;
                    case 4:
                        if (i == data.length || ((data[i] >> 6) & 0b11) != 0b10) return false;
                        i++;
                        if (i == data.length || ((data[i] >> 6) & 0b11) != 0b10) return false;
                        i++;
                        currentType = 0;
                        break;
                }
            }
        }
        return currentType == 0;
    }

    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> map = new HashMap<>();

        for(int i=0; i<C.length; i++) {
            for(int j=0; j<D.length; j++) {
                int sum = C[i] + D[j];
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }

        int res=0;
        for(int i=0; i<A.length; i++) {
            for(int j=0; j<B.length; j++) {
                res += map.getOrDefault(-1 * (A[i]+B[j]), 0);
            }
        }

        return res;
    }

    public static String reverseWords(String s) {
        char[] chs = s.toCharArray();
        int l=0;
        int r=s.length()-1;
        reverse(chs, l,r);
        l=0;
        while (l<s.length()) {
            if (chs[l]!=' ') {
                r=l;
                while (r+1<s.length() && chs[r+1] != ' ') {r++;}
                int we = r+1;
                reverse(chs, l, r);
                l = we;
            } else {
                l++;
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i=0;i<chs.length;i++) {
            System.out.print(chs[i] + "("+i+") ");
        }
        System.out.println();
        for (int i=0;i<chs.length;i++) {

            if (chs[i] != ' ') {
                sb.append(chs[i]);
            } else if (i>0 && chs[i-1]!=' ') {
                System.out.println("space at: " + i);
                sb.append(chs[i]);

            }

        }
        return sb.toString().trim();
    }

    static void reverse(char[] chs, int l, int r) {
        System.out.format("Reverse: %s %s\n", l, r);
        while (l<r) {
            char tmp = chs[l];
            chs[l] = chs[r];
            chs[r] = tmp;
            l++;
            r--;
        }
    }

    static String longestFilePath(String input) {
        LinkedList<String> stack = new LinkedList<>();
        String longest = "";
        int longestLen=0;
        int curCount=0;

        for (int i=0;i<input.length();) {
            int j=i;
            while (j<input.length() && input.charAt(j)!='\\') {
                j++;
            }
            String str = input.substring(i,j);
            stack.push(str);
            curCount+=j-i;

            int level=0;
            if (j<input.length()-1) {
                j+=2; // skip \n
                while (j<input.length()-1 && input.charAt(j)=='\\' && input.charAt(j+1)=='t') {
                    j+=2;
                    level++;
                }
            }
            if (level <= stack.size()) {
                int dot = stack.peek().indexOf(".");
                if (dot!=-1 && dot < stack.peek().length()-1 
                        && longestLen < curCount + stack.size()-1) {
                    curCount=0;
                    List<String> s = new ArrayList<>(stack);
                    Collections.reverse(s);
                    longest=String.join("/", s);
                    longestLen=longest.length();
                }
                while (stack.size() > 0 && level < stack.size()) {
                    stack.pop();
                }
            }
            i=j;
        }
        return longest;
    }

    static Set<Integer> potentialHappyNumbers = new HashSet<>();
    static boolean isHappy(int n) {
        if (n==1) return true;
        if (potentialHappyNumbers.contains(n)) return false;

        boolean ret = false;

        String[] digits = (""+n).split("");
        int m = 0;
        for (String ds:digits) {
            int d = Integer.valueOf(ds);
            m += d * d;
        }
        potentialHappyNumbers.add(n);
        return isHappy(m);
    }

    static String validIPAddress(String IP) {
        String[] parts;
        if (IP.contains(".")) {
            if (IP.startsWith(".") || IP.endsWith(".")) return "Neither";
            parts = IP.split("\\.");
            if (parts.length!=4) return "Neither";
            Optional<String> badString = Arrays.stream(parts).filter(a->{
                boolean isNumber = Pattern.matches("^\\d{1,3}$", a);
                if (!isNumber) return true;
                int num = Integer.valueOf(a);
                if (num>255 || a.charAt(0)=='0' && a.length()>1) {
                    return true;
                }
                return false;
            }).findAny();

            if (!badString.isPresent()) {
                return "IPv4";
            } else {
                return "Neither";
            }
        } else if (IP.contains(":")) {
            IP = IP.toLowerCase();
            if (IP.startsWith(":") || IP.endsWith(":")) return "Neither";
            parts = IP.split(":");
            System.out.println(Arrays.toString(parts));
            if (parts.length!=8) return "Neither";
            Optional<String> badString = Arrays.stream(parts).filter(a->{
                boolean isHex = Pattern.matches("^[0-9a-f]{1,4}$", a);
                if (!isHex) return true;
                return false;
            }).findAny();
            if (!badString.isPresent()) {
                return "IPv6";
            }
            return "Neither";
        } else {
            return "Neither";
        }
    }
    static int splitArray(int[] nums, int m) {
        int max = 0; long sum = 0;
        for (int num : nums) {
            max = Math.max(num, max);
            sum += num;
        }
        if (m == 1) return (int)sum;
        //binary search
        long l = max; long r = sum;
        while (l <= r) {
            long mid = (l + r)/ 2;
            if (valid(mid, nums, m)) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return (int)l;
    }
    static boolean valid(long target, int[] nums, int m) {
        int count = 1;
        long total = 0;
        for(int num : nums) {
            total += num;
            if (total > target) {
                total = num;
                count++;
                if (count > m) {
                    return false;
                }
            }
        }
        return true;
    }
    public static void main(String[] args) {
        System.out.println(splitArray(new int[]{4,4,1}, 3));
    }
}
