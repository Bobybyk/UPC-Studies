def ff_online(items, bin_capacity):
    bins = [[]]

    for item in items:
        placed = False
        for bin in bins:
            if sum(bin) + item <= bin_capacity:
                bin.append(item)
                placed = True
                break
        if not placed:
            bins.append([item])
    return bins

def bf_online(element, bin_capacity):
    bins = [[]]
    for item in element:
        placed = False
        best_bin = None
        fill_value = 0
        for bin in bins:
            if sum(bin) + item > fill_value and sum(bin) + item <= bin_capacity:
                fill_value = sum(bin) + item
                best_bin = bin
        if best_bin is not None:
            best_bin.append(item)
            placed = True
        if not placed:
            bins.append([item])
    return bins

def nf_online(element, bin_capacity):
    bins = [[]]
    for item in element:
        index = 0
        if sum(bins[index]) + item <= bin_capacity:
            bins[index].append(item)
        else:
            index += 1
            if item <= bin_capacity:
                bins.append([item])
    return bins

items = [4, 8, 1, 4, 2, 7]
bin_capacity = 10

result = ff_online(items, bin_capacity)
print("Nombre de conteneurs utilisés avec ff:", len(result))
print("Disposition des objets dans les conteneurs:", result)

result = bf_online(items, bin_capacity)
print("Nombre de conteneurs utilisés avec bf:", len(result))
print("Disposition des objets dans les conteneurs:", result)

result = nf_online(items, bin_capacity)
print("Nombre de conteneurs utilisés avec nf:", len(result))
print("Disposition des objets dans les conteneurs:", result)