package me.derekzeng.code;
import java.util.*;
import java.io.*;


public class Easy {
    public String reverseString(String s) {
        char[] chs = new char[s.length()];

        for (int i = s.length() - 1; i >=0; i--) {
            chs[i] = s.charAt(i);
        }

        return new String(chs);
    }

    public int minMoves2(int[] nums) {
        int least = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {
            int s = 0;
            boolean overflow = false;
            for (int j = 0; j < nums.length; j++) {
                int newS = s + Math.abs(nums[j]-nums[i]);
                if (newS < s) {
                    overflow = true;
                } else {
                    s = newS;
                }
            }
            if (!overflow) {
                least = Math.min(least, s);
            }
        }
        return least;
    }

    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int ai = a.length() -1;
        int bi = b.length() -1;

        int carry = 0;
        while (ai >=0 && bi >= 0) {
            if (a.charAt(ai) == '0' && b.charAt(bi) == '0') {
                if (carry == 0) {
                    sb.append('0');
                } else {
                    sb.append('1');
                    carry = 0;
                }
            } else if (a.charAt(ai) != b.charAt(bi)) {
                if (carry == 0) {
                    sb.append('1');
                } else {
                    sb.append('0');
                }
            } else {
                if (carry == 0) {
                    sb.append('0');
                    carry = 1;
                } else {
                    sb.append('1');
                }
            }
            ai--;
            bi--;
        }

        while (ai >=0) {
            if (a.charAt(ai) == '0') {
                if (carry == 1) {
                    sb.append('1');
                    carry = 0;
                } else {
                    sb.append('0');
                }
            } else {
                if (carry == 1) {
                    sb.append('0');
                } else {
                    sb.append('1');
                }
            }
            ai--;
        }

        while (bi >=0) {
            if (b.charAt(bi) == '0') {
                if (carry == 1) {
                    sb.append('1');
                    carry = 0;
                } else {
                    sb.append('0');
                }
            } else {
                if (carry == 1) {
                    sb.append('0');
                } else {
                    sb.append('1');
                }
            }
            bi--;
        }

        if (carry == 1) {
            sb.append('1');
        }
        String s = sb.reverse().toString();
        return s;
    }
    public String addStrings(String num1, String num2) {
        int p1 = num1.length()-1;
        int p2 = num2.length()-1;
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        while (p1>=0&&p2>=0) {
            int d1 = num1.charAt(p1) - '0';
            int d2 = num2.charAt(p2) - '0';
            int sum = d1 + d2 + carry;
            carry = sum/10;
            sb.append((char) (sum%10 + '0'));
            p1--;
            p2--;
        }
        while (p1>=0) {
            int sum = num1.charAt(p1) - '0' + carry;
            carry = sum / 10;
            sb.append((char) (sum %10 + '0'));
            p1--;
        }
        while (p2>=0) {
            int sum = num2.charAt(p2) - '0' + carry;
            carry = sum/10;
            sb.append((char) (sum%10+'0'));
            p2--;
        }
        if (carry > 0) {
            sb.append((char) (carry + '0'));
        }
        return sb.reverse().toString();
    }
}
