class Solution(object):
    def threeSum(self, nums):
        """
        :type nums: List[int]
        :rtype: List[List[int]]
        """
        # Sorted so dups are grouped together
        nums = sorted(nums)

        # Store the unique negative
        store = {}
        ret = []
        for i in range(len(nums)):
            if i == len(nums) - 1 or nums[i] != nums[i+1]:
                store[-nums[i]] = i

        i = 0
        while i < len(nums) - 2:
            for j in range(i+1, len(nums) - 1):
                if j > i + 1 and nums[j] == nums[j - 1]:
                    continue
                key = nums[i] + nums[j]
                if key in store and store[key] > j:
                    ret.append((nums[i], nums[j], -key))
                if nums[i] == nums[j]:
                    while i < len(nums) - 2 and nums[i+1] == nums[i]:
                        i += 1
                    break
            else:
                i += 1
        return ret


s = Solution()
print s.threeSum([
    -5, 14, 1, -2, 11, 11, -10, 3, -6, 0, 3, -4, -9, -13, -8, -7, 9, 8, -7,
    11, 12, -7, 4, -7, -1, -5, 13, 1, -2, 8, -13, 0, -1, 3, 13, -13, -1, 10,
    5, 1, -13, -15, 12, -7, -13, -11, -7, 3, 13, 1, 0, 2, 1, 11, 10, 8, -8,
    1, -14, -3, -6, -12, 12, 0, 6, 2, 2, -9, -3, 14, -1, -9, 14, -4, -1, 8,
    -8, 7, -4, 12, -14, 3, -9, 2, 0, -13, -13, -1, 3, -12, 11, 4, -9, 8, 11,
    5, -5, -10, 3, -1, -11, -13, 5, -12, -10, 11, 11, -3, -5, 14, -13, -4,
    -5, -7, 6, 2, -13, 0, 8, -3, 4, 4, -14, 2])
