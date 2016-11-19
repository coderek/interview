import re

def eval(expr):
    tokens = tokenize(expr)
    stack = []
    stack.append([])
    for t in tokens:
        if re.match(r'[\d\+\*]', t):
            stack[-1].append(t)
        elif t == '(':
            stack.append([])
        elif t == ')':
            ar = stack.pop()
            result = calculate(ar)
            print ar, result
            if len(stack) == 0:
                return result
            else:
                stack[-1].append(result)
    return calculate(stack[-1])

def calculate(ar):
    if ar[0] not in ['*', '+']:
        return int(ar[0])

    op = ar[0]
    result = 0 if op == '+' else 1
    for i in range(1, len(ar)):
        if op == '+':
            result = result + int(ar[i])
        else:
            result = result * int(ar[i])
    return result

def tokenize(expr):
    tokens = []
    store = ''
    i = 0
    while i < len(expr):
        c = expr[i]
        if c in ['(', ')', ' '] and store != '':
            tokens.append(store)
            store = ''

        if c == '(' or c == ')':
            tokens.append(c)
        elif c == '*' or c == '+':
            tokens.append(c)
        elif re.match(r'\d', c):
            store = store + c
        i += 1
    return tokens


# print eval('(+ 1 3)')

print eval('(+ 10 223(*34 34 4545))')