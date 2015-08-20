/*
Given 1 ~ 9, Divide them into 3 groups.
Sum of each group is equal to 15.
How many combinations are qualified?
DFS Stack Version
*/
import java.util.*;

public class EquallyDividedGroups {

    static int num = 24;
    static int groups = 3;
    static int members;
    static int answer;
    static int average;
    static Stack<Integer> stack = new Stack<Integer>();
    static HashSet<Integer> hashSet = new HashSet<Integer>();

    public static void main(String[] args) {
        members = num / groups;
        average = (1 + num) * num / 2 / groups;
        System.out.println("members = " + members + ", average = " + average);
        long startTime = System.currentTimeMillis();
        solve(average);
        long endTime = System.currentTimeMillis();
        System.out.println("answer = " + answer);
        System.out.println("Using " + (endTime - startTime)  / 1000.0 + " sec");
    }

    public static void solve(int resource) {

        int length = hashSet.size();
        int pos = length % members;

        if( length == (num - members) ) {
            answer++;
            //System.out.println(stack);
            return;
        }
        else if( pos == 0 ) {
            int min = 0;
            for(int i = 1; i <= num; i++) {
                if( hashSet.contains(i) ) {
                    continue;
                }
                else {
                    min = i;
                    break;
                }
            }
            stack.push(min);
            hashSet.add(min);
            solve(resource - min);
            int temp = stack.pop();
            hashSet.remove(temp);
        }
        else if( pos > 0 && pos < (members - 1) ) {
            int peek = Integer.valueOf(stack.peek());
            for(int i = peek + 1; i <= num; i++) {
                if( i > resource / 2 ) {
                    return;
                }
                if( hashSet.contains(i) ) {
                    continue;
                }
                stack.push(i);
                hashSet.add(i);
                solve(resource - i);
                int temp = stack.pop();
                hashSet.remove(temp);
            }
        }
        else {
            if( resource > num ) {
                return;
            }
            else if( hashSet.contains(resource) ) {
                return;
            }
            else {
                int peek = Integer.valueOf(stack.peek());
                if( resource < peek ) {
                    return;
                }
                else {
                    stack.push(resource);
                    hashSet.add(resource);
                    solve(average);
                    int temp = stack.pop();
                    hashSet.remove(temp);
                }
            }
        }
        return;
    }

}
