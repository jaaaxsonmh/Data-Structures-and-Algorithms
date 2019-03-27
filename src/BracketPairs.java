package src;

import java.util.Stack;

public class BracketPairs {


    public static void main(String[] args) {
        String test1 = "{((2 x 5)+(3*-2 + 5))}";
        String test2 = "{ (2 x 5)+(3*-2 + 5))}";
        String test3 = "List<List<E>>";
        String test4 = "List<List<E>";
        String test5 = "{(<<eeeek>>){}{…}(e(e)e){hello}}";
        String test6 = "{(< eeeek>>){}{…} e(e)e){hello}";
        String test7 = "";

        BracketPairs pairing = new BracketPairs();

        System.out.println("Testing: " + test1 + "\nResult: " + pairing.CheckPairs(test1));
        System.out.println("Testing: " + test2 + "\nResult: " + pairing.CheckPairs(test2));
        System.out.println("Testing: " + test3 + "\nResult: " + pairing.CheckPairs(test3));
        System.out.println("Testing: " + test4 + "\nResult: " + pairing.CheckPairs(test4));
        System.out.println("Testing: " + test5 + "\nResult: " + pairing.CheckPairs(test5));
        System.out.println("Testing: " + test6 + "\nResult: " + pairing.CheckPairs(test6));
        System.out.println("Testing: " + test7 + "\nResult: " + pairing.CheckPairs(test7));
    }

    private boolean CheckPairs(String str) {

        // if the string has no value (not in a test case)
        // then return false as there are no brackets.
        if(str.isEmpty()) {
            return true;
        }

        Stack<Character> stack = new Stack<>();

        for(int i = 0; i < str.length(); i++) {
            // grab the string as a character at the current place.
            char current =  str.charAt(i);

            // opening pairs of brackets then push onto the stack.
            if(current == '(' || current == '{' || current == '<' || current == '['){
                stack.push(current);
            }

            //closing pairs of brackets then check if matching, or empty.
            if(current == ')' || current == '}' || current == '>' || current == ']'){

                //if stack has no values then there is a closing bracket without a matching bracket.
                if(stack.isEmpty()){
                    return false;
                }


                //if stack has values then the previous value has to match the current value bracket - pairs: () {} <> []
                char prev = stack.peek();
                if(prev == '(' && current == ')' || prev == '{' && current == '}' || prev == '<' && current == '>' || prev == '[' && current == ']') {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}

