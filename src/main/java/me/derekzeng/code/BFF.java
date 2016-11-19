package me.derekzeng.code;
import java.util.*;


class BFF {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();

        for (int i = 0; i < n; i ++) {
            int m = s.nextInt();
            Node[] bffs = new Node[m];
            for (int j = 0; j < m; j ++) {
                bffs[j] = new Node(j, s.nextInt() - 1);
            }
            int ans = solve(bffs);
            System.out.format("Case #%s: %s\n", i+1, ans);
        }
    }

    private static class Node {
        int cycleIndex;
        final int index;
        final int nextIndex;

        Node(int index, int nextIndex) {
            this.index = index;
            this.nextIndex = nextIndex;
        }

        public boolean equals(Object o) {
            Node other = (Node) o;
            return other.index == this.index;
        }

        public String toString() {
            return String.format("%s %s %s", this.index + 1, this.nextIndex + 1, this.cycleIndex);
        }
    }

    private static int solve(Node[] bffs) {
        int m = 0;

        int[] refCount = new int[bffs.length];
        for (Node node: bffs) {
            refCount[node.nextIndex] += 1;
        }

        for (Node node: bffs) {
            int currentIndex = node.index;
            int lastIndex = -1;
            Set<Node> s = new HashSet<>();

            int n = 0;
            while (n < bffs.length && !s.contains(bffs[currentIndex])) {
                Node nod = bffs[currentIndex];

                // update cycleIndex
                nod.cycleIndex = n;

                s.add(nod);

                lastIndex = currentIndex;
                currentIndex = nod.nextIndex;
                n++;
            }

            if (n <= bffs.length) {
                int cycle = s.size() - bffs[currentIndex].cycleIndex;
                if (cycle == 2) {
                    cycle = s.size();

                    if (refCount[lastIndex] > 1) {
                        cycle += 1;
                    }
                }
                m = Math.max(m, cycle);
            }
        }
        return m;
    }
}
