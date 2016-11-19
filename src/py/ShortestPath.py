# Enter your code here. Read input from STDIN. Print output to STDOUT
# from Queue import PriorityQueue
from heapq import heapify
INF = 1000000000


class Node(object):
    dist = INF

    def __init__(self, idx):
        self.index = idx

    def __cmp__(self, other):
        return cmp(self.dist, other.dist)

    def __repr__(self):
        # return "{index}: {dist}".format(index=self.index, dist=self.dist)
        return str(self.dist)


def get_line():
    return map(lambda a: int(a), raw_input().split())


def dijikstra(adj, n, start):
    unchanged = [Node(i) for i in range(n)]
    unchanged[start].dist = 0

    nodes = [unchanged[i] for i in range(n)]

    while len(nodes):
        heapify(nodes)
        u = nodes.pop(0)

        for v, w in enumerate(adj[u.index]):
            if w == INF:
                continue
            node = unchanged[v]
            if node.dist > u.dist + w:
                node.dist = u.dist + w

    print ' '.join([
        str(node.dist) if node.dist != INF else '-1'
        for node in unchanged if node.index != start])


q = get_line()[0]
for _ in range(q):
    (n, m) = get_line()

    adj = []
    weights = {}
    for i in range(n):
        adj.append([INF for _ in range(n)])

    for _ in range(m):
        (s, t, w) = get_line()

        s = s - 1
        t = t - 1
        adj[s][t] = min(adj[s][t], w)
        adj[t][s] = min(adj[t][s], w)


    start = get_line()[0] - 1

    dijikstra(
        adj, n, start)

