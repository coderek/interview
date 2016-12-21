class Solution(object):
    def solveSudoku(self, config):
        cconfig = [[
            a if a == '.' else int(a) for a in list(row)] for row in config]
        # 9 x 9
        fill = []
        for i in xrange(9):
            for j in xrange(9):
                if cconfig[i][j] == '.':
                    fill.append((i,j,))
        dps(cconfig, fill, 0)
        for i in xrange(9):
            config[i] = ''.join(map(str, cconfig[i]))

def dps(config, fill, p):
    if p >= len(fill):
        return True
        
    i,j = fill[p]
    
    for k in range(1, 10):
        config[i][j] = k
        if check(config, i, j) and dps(config, fill, p+1):
            return True
        config[i][j] = '.'
    return False
            
def check(config, i, j):
    # next try
    n = [0 for _ in xrange(10)]
    for k in xrange(9):
        m = config[k][j]
        if m !='.':
            if n[m] > 0:
                return False
            n[m] += 1

    n = [0 for _ in xrange(10)]
    for k in xrange(9):
        m = config[i][k]
        if m != '.':
            if n[m] > 0:
                return False
            n[m] += 1

    n = [0 for _ in xrange(10)]
    for a in xrange(i/3*3, i/3*3+3):
        for b in xrange(j/3*3, j/3*3+3):
            m = config[a][b]
            if m != '.':
                if n[m] > 0:
                    return False
                n[m] += 1
    return True
    
s = Solution()
print s.solveSudoku(["..9748...","7........",".2.1.9...","..7...24.",".64.1.59.",".98...3..","...8.3.2.","........6","...2759.."])
