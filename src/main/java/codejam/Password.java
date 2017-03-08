package me.derekzeng.codejam;
import java.util.*;
import java.io.*;


public class Password {
    double solve(int A, int B, double[] probs) {
        double min = Double.MAX_VALUE;

        // keep typing
        double allRight = Arrays.stream(probs).reduce((a,b)->a*b).getAsDouble();
        double expAllright = allRight * (B-A+1) + (1-allRight) * (B-A+1+B+1);
        min = Math.min(min, expAllright);

        // press enter now
        double expEnterNow = B+2;
        min = Math.min(min, expEnterNow);

        double allRightProb = 1;
        for (int i=A;i>=1;i--) {
            double back = allRightProb * (i*2+B-A+1) + (1-allRightProb) * (i*2+B-A+1+B+1);
            min = Math.min(min, back);
            allRightProb *= probs[A-i];
        }
        return min;
    }

    public static void main(String[] args) {
        Password pw = new Password();
        try {
            Scanner in = new Scanner(new File("input.dat"));
            FileWriter out = new FileWriter(new File("output.dat"));
            int n = in.nextInt();
            in.nextLine();
            for (int i=0;i<n;i++) {
                int A = in.nextInt();
                int B = in.nextInt();
                double[] probs = new double[A];
                for (int j=0;j<A;j++) {
                    probs[j] = in.nextDouble();
                }
                out.write(String.format("Case #%s: %s\n", i+1, pw.solve(A,B,probs)));
            }
            out.flush();
        } catch (Exception e) {
        }
//         System.out.println(pw.solve(3,4,new double[]{1, 0.9, 0.1}));
    }
}
