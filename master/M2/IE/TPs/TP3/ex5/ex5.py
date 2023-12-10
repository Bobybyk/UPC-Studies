def trouver_chemins(graph, start, end, path=[]):
    path = path + [start]
    if start == end:
        return [path]
    if start not in graph:
        return []
    paths = []
    for node in graph[start]:
        if node not in path:
            newpaths = trouver_chemins(graph, node, end, path)
            for newpath in newpaths:
                paths.append(newpath)
    return paths

# les clefs sont les noeuds et les valeurs associées sont les noeuds voisins
graph_ex4 = {
    '1': ['2', '3'],
    '2': ['1', '3'],
    '3': ['1'],
}

graph_ex3 = {
    '1': ['2'],
    '2': ['4', '3'],
    '3': ['2'],
    '4': [],
}

start_node = '1'
end_node = '3'
print("Chemins premiers (graph exercice 4) de {} à {} :".format(start_node, end_node))
for path in trouver_chemins(graph_ex4, start_node, end_node):
    print(path)

start_node = '1'
end_node = '4'
print("Chemins premiers (graph exercice 3) de {} à {} :".format(start_node, end_node))
for path in trouver_chemins(graph_ex3, start_node, end_node):
    print(path)