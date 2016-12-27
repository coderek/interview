class Solution(object):
    def reverse(self, x):
        """
        :type x: int
        :rtype: int
        """
        s = str(x)
        if s == '':
            return None
        negative = False
        if s[0] == '-':
            s = s[1:]
            negative = True
        rev = ''.join(reversed(list(s)))
        if negative:
            rev = '-' + rev
        ret = int(rev)
        if ret >= (1 << 32) - 1 or ret < -(1 << 32):
            return 0
        else:
            return ret


s = Solution()
print s.reverse(-15342)
