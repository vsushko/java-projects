package vsushko.func;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class NonFuncSubsetsSolution {

    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(1, 4, 9);
        List<List<Integer>> subsets = subsets(integers);

        subsets.forEach(subset -> System.out.println(
                subset.stream()
                        .map((Integer i) -> Integer.toString(i))
                        .collect(Collectors.joining(", ")))
        );
    }

    private static List<List<Integer>> subsets(List<Integer> list) {
        // if the input list is empty, it has exactly one subset, the empty list itself
        if (list.isEmpty()) {
            List<List<Integer>> ans = new ArrayList<>();
            ans.add(Collections.emptyList());
            return ans;
        }

        Integer first = list.get(0);
        List<Integer> rest = list.subList(1, list.size());

        // otherwise take one element out, first and find all subsets of the rest to
        // give subans; subans forms half of the answer
        List<List<Integer>> subans = subsets(rest);
        // the other half of the answer, subans2, conists of all the lists in subans but adjusted
        // by prefixing each of these element lists with first
        List<List<Integer>> subans2 = insertAll(first, subans);
        // then concatenate the two subanswers
        return concat(subans, subans2);
    }

    private static List<List<Integer>> insertAll(Integer first, List<List<Integer>> lists) {
        List<List<Integer>> result = new ArrayList<>();

        // copy the list to allow you to add to it. You wouldn't copy the lower-lever
        // structure even if it were mutable (integers are not)
        for (List<Integer> list : lists) {
            List<Integer> copyList = new ArrayList<>();
            copyList.add(first);
            copyList.addAll(list);
            result.add(copyList);
        }
        return result;
    }

    // relies on the fact the after the call concat(subans, subans2)
    // no one refers to the value of subans ever again
    private static List<List<Integer>> concat(List<List<Integer>> a, List<List<Integer>> b) {
        a.addAll(b);
        return a;
    }

    // is a pure function, returns a result based on its arguments and modifies neither of them
    private static List<List<Integer>> concat2(List<List<Integer>> a, List<List<Integer>> b) {
        List<List<Integer>> r = new ArrayList<>(a);
        r.addAll(b);
        return r;
    }

}
