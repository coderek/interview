def solve(A, B):
    n = len(A)
    m = len(B)
    matrix = [[0 for _ in range(m)] for _ in range(n)]

    i = 0
    j = 0

    while i < n and j < m:
        if A[i] == B[j]:
            matrix[i][j] = 1 + matrix[i - 1][j - 1]
            i += 1
            j += 1
        else:
            ii = matrix[i - 1][j]
            jj = matrix[i][j - 1]
            matrix[i][j] = max(ii, jj)

            if ii > jj:
                j += 1
            else:
                i += 1

    return matrix[-1][-1]



print solve('abcdefsdfsdfsd', 'def')



