class Solution:
    def Add(self,r,h):
        while r > 0:
            self.bst[r] = max(self.bst[r],h)
            r -= r & -r

    def Find(self,i):
        h = 0
        while i <= self.INT_MAX:
            h =max(self.bst[i],h)
            i += i & -i
        return h

    def getSkyline(self, buildings):
        ret,high_now,points  = [],0,[]
        for i in range(len(buildings)):
            s,e,h=buildings[i]
            points.append([s,1,i])
            points.append([e,2,i])

        points.sort()

        # points map to unique number (index?)
        indices = {}
        index = 0
        for pt in points:
            if pt[0] not in indices:
                indices[pt[0]] = index
                index += 1
        # max index
        self.INT_MAX = index
        # print indices
        self.bst = [0 for x in range(self.INT_MAX+1)]
        for p,t,i in points:
            # start
            if t == 1:
                _,e,h = buildings[i]
                self.Add(indices[e],h)

            Temp = self.Find(indices[p]+1)
            if Temp != high_now:
                if (len(ret)>0)and(ret[-1][0] == p):
                    ret[-1][1] = max(ret[-1][1],Temp)
                else:
                    ret.append([p,Temp])
                high_now = Temp

        # print [self.Find(i) for i in range(1,len(self.bst))]
        return ret

s = Solution()
print s.getSkyline([[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]])
# print s.getSkyline([[1,3,4],[2,5,6],[6,9,8]])
