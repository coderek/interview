def solve(terrain, sealevel):

    maxes = []

    # from left to right
    currentmax = 0
    for i in range(len(terrain)):
        h = terrain[i]
        if h == -1:
            currentmax = 0
        else:
            currentmax = min(max(currentmax, h), sealevel)

        maxes[i] = currentmax

    # from right to left
    # need to consider previously calculated value
    currentmax = 0
    for i in range(len(terrain)):
        h = terrain[len(terrain) - i - 1]

        if h == -1:
            currentmax = 0
        else:
            currentmax = min(max(currentmax, h), sealevel, maxes[i])

        maxes[i] = currentmax

    # now we can calculate bodies of water
    # using the solution from part 1
