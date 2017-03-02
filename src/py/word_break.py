import pdb
def break_word(dictionary, text):
    dp = []
    l = len(text)
    for i in range(l+1):
        dp.append([None for _ in range(l+1)])
        for j in range(i+1, l+1):
            if text[i:j] in dictionary:
                dp[i][j] = True
            else:
                dp[i][j] = None

    res = detect(text, dp, 0, len(text))
    print(dp)
    return res


def detect(text, dp, start, end):
    if dp[start][end] != None:
        return dp[start][end]

    for i in range(start+1, end):
        if dp[start][i] == None:
            dp[start][i] = detect(text, dp, start, i)
        if dp[i][end] == None:
            dp[i][end] = detect(text, dp, i, end)

        if dp[start][i] and dp[i][end]:
            dp[start][end] = True
            return True

    dp[start][end] = False
    return False

def break_word2(text, wordDict):
    dp = []
    wd = set(wordDict)
    l = len(text)
    for i in range(l):
        dp.append([0 for _ in range(l+1)])
        for j in range(i+1, l+1):
            if text[i:j] in wd:
                dp[i][j] = 2
    for d in range(1, l+1):
        for s in range(0, l-d+1):
            if dp[s][s+d] == 2:
                continue

            for m in range(1, d):
                if dp[s][s+m] > 0 and dp[s+m][s+d] > 0:
                    dp[s][s+d] = 1
                    break
    if dp[0][l] == 0:
        return []
    return collect(text, dp, 0)


def collect(text, dp, s):
    ret = []
    for i in range(s+1, len(text)+1):
        word = text[s:i]
        if dp[s][i] == 2: 
            if i==len(text):
                ret.append([word])
            elif dp[i][len(text)] > 0:
                rest = collect(text, dp, i)
                for _r in rest:
                    _r.insert(0, word)
                    ret.append(_r)
    return ret


print break_word2('catsanddog', ['cat', 'cats', 'sand', 'and', 'dog', 'an', 'ddog'])
# 
# 
# 
# 
# 
# print(break_word2('leetcodecatsanddog', ['leet', 'code', 'cat', 'and', 'dog']))
# print break_word2('a', ['a'])
# print break_word2('ab', ['a', 'b'])
