# Definition for singly-linked list.
class ListNode(object):
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution(object):
    def reverseKGroup(self, head, k):
        """
        :type head: ListNode
        :type k: int
        :rtype: ListNode
        """
        count = 0
        cur = head
        while count < k and cur != None:
            cur = cur.next
            count += 1

        if count != k:
            return head

        cur = self.reverseKGroup(cur, k)
        while count != 0:
            tmp = head
            head = tmp.next
            tmp.next = cur
            cur = tmp
            count -= 1
        return cur


def show(h):
    arr = []
    while h!=None:
        arr.append(h.val)
        h = h.next
    print ' '.join(map(str, arr))

a = ListNode(1)
b = ListNode(2)
c = ListNode(3)
d = ListNode(4)
e = ListNode(5)

a.next = b
b.next = c
c.next = d
d.next = e

s = Solution()
h = s.reverseKGroup(None, 1)
show(h)

