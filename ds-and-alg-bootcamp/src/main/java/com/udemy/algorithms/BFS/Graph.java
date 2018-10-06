package com.udemy.algorithms.BFS;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author vsushko
 */
public class Graph {
    private int V;
    // adjacency lists
    private List<Integer>[] adj;

    public Graph(int v) {
        V = v;
        adj = new LinkedList[v];

        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
    }

    // bsf traversal from a given source s
    public void BFS(int s) {
        // mark all the vertices as not visited (false)
        boolean[] visited = new boolean[V];

        // create a queue for BFS
        List<Integer> queue = new LinkedList<>();

        // mark the current node as visited and enqueue it
        visited[s] = true;
        System.out.println("Starting at " + s);
        queue.add(s);

        while (queue.size() != 0) {
            // dequeue a vertex from queue and print it
            s = ((LinkedList<Integer>) queue).poll();
            System.out.println("De-queueing " + s);

            // get all adjacent vertices of the de-queued vertex s
            // if a adjacent has not been visited, then mark it visited and enqueue it
            Iterator<Integer> iterator = adj[s].listIterator();
            while (iterator.hasNext()) {
                int n = iterator.next();
                if (!visited[n]) {
                    visited[n] = true;
                    System.out.println("Queueing " + n);
                    queue.add(n);
                }
            }
        }
    }
}
