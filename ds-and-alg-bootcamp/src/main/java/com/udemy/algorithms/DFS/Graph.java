package com.udemy.algorithms.DFS;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author vsushko
 */
public class Graph {
    private int V;
    private LinkedList<Integer>[] adj;

    public Graph(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i) {
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
    }

    public void DFS(int v) {
        boolean visited[] = new boolean[V];

        Stack<Integer> stack = new Stack<>();
        stack.add(v);

        visited[v] = true;

        while (!stack.isEmpty()) {
            int current = stack.pop();
            System.out.print(current + " ");

            Iterator<Integer> iterator = adj[current].listIterator();
            while (iterator.hasNext()) {
                int n = iterator.next();

                if (!visited[n]) {
                    stack.add(n);
                    visited[n] = true;
                }
            }
        }
    }
}
