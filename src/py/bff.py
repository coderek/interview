# https://code.google.com/codejam/contest/4304486/dashboard#s=p2

class Component(object):

    def __init__(self, cycle, tail):
        self.cycle = cycle
        if tail:
            self.tails = tail
        else:
            self.tails = {}

    @property
    def degree(self):
        return len(self.cycle)

    @property
    def maxDegreeTwo(self):
        if self.degree > 2:
            return 0
        else:
            sortedKeys = sorted(self.tails.values())
            return 2 + sum(map(lambda s: len(s), sortedKeys)[:2])

    def combine(self, c):
        for k in c.tails:
            if (k not in self.tails or
                    len(c.tails.get(k)) > len(self.tails.get(k)) > 0):
                self.tails[k] = c.tails.get(k)

    def __eq__(self, o):
        return sorted(o.cycle) == sorted(self.cycle)

    def __repr__(self):
        return str(self.cycle) + ' tails : ' + str(self.tails)


def build_or_find_component(i, bffs, visited):
    chain = []
    s = i
    while s not in chain:
        chain.append(s)
        s = bffs[s]

    for c in chain:
        visited[c] = 1

    i = chain.index(s)
    tail = {}
    if chain[:i]:
        tail[s] = chain[:i]
    return Component(chain[i:], tail)


def solve(bffs):
    n = len(bffs)
    visited = [0 for _ in xrange(n)]

    components = []
    for i in xrange(n):
        if visited[i]:
            continue

        component = build_or_find_component(i, bffs, visited)
        try:
            exist = components.index(component)
            components[exist].combine(component)
        except ValueError:
            components.append(component)

    m = 0
    for c in components:
        if c.degree > 2:
            m = max(m, c.degree)

    m = max(m, sum(map(lambda c:c.maxDegreeTwo, components)))
    return m

# solve([3])
# print solve([6,7,9,9,8,1,8,5,2,2])
# solve([2,2,3,2])

n = int(raw_input())
for i in xrange(n):
    raw_input()
    bffs = map(lambda a: int(a) - 1, raw_input().split(' '))
    print 'Case #{}: {}'.format(i+1, solve(bffs))


