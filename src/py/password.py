def solve(n, m, probs):
    count = 1<<n
    p = getProbs(probs)
    a = getKeepTyping(n, m)
    # 2d
    b = [getBackspace(n, m, i) for i in range(1,n+1)]
    c = [2+m for _ in range(2**n)]

    expected = [
        sum(a[i]*p[i] for i in range(count)),
        sum(c[i]*p[i] for i in range(count)),
        min(sum(bb[i]*p[i] for i in range(count)) for bb in b),
    ]
    print expected
    m = min(expected)
    return m

def getProbs(probs):
    if not probs:
        return [1]
    rest = getProbs(probs[1:])
    ret = []
    p = probs[0]
    for r in rest:
        ret.append(r*p)

    for r in rest:
        ret.append(r*(1-p))
    return ret

def getKeepTyping(n, m):
    size = 2**n
    worst = m-n+1 + m + 1
    ret = [worst for _ in range(size)]
    ret[0] = m-n+1
    return ret

def getBackspace(n, m, c):
    if n==c:
        return [m+c+1 for _ in range(2**n)]
    ret = []
    ret = [c+m-n+c+1+m+1 for _ in range(2**n)]
    ret[0] = c+m-n+c+1
    ret[2**(n-1)-1] = c+m-n+c+1

    return ret

print solve(3,4,[1,0.9,0.1])
