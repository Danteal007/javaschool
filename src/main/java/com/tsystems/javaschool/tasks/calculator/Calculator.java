package com.tsystems.javaschool.tasks.calculator;


import java.util.EmptyStackException;
import java.util.Stack;

public class Calculator {

    /**
     * Evaluate statement represented as string.
     *
     * @param statement mathematical statement containing digits, '.' (dot) as decimal mark,
     *                  parentheses, operations signs '+', '-', '*', '/'<br>
     *                  Example: <code>(1 + 38) * 4.5 - 1 / 2.</code>
     * @return string value containing result of evaluation or null if statement is invalid
     */
    public String evaluate(String statement) {
        // TODO: Implement the logic here
        try {
            if (statement != null && TransformToPolishNotation(statement).peek() != null) {
                Stack PN = Reverse(TransformToPolishNotation(statement));
                return SolvePolishNotation(PN);
            }
        }
        catch (EmptyStackException e) {
            return null;
        }
        return null;
    }

    public String SolvePolishNotation(Stack<String> pn){
        Stack<Double> stack = new Stack();
        double a,b;
        System.out.println(pn.empty());
        while (!pn.empty()){
            switch (pn.peek()){
                case "+":
                    pn.pop();
                    b = stack.pop();
                    a = stack.pop();
                    stack.push(a+b);
                    break;
                case "-":
                    pn.pop();
                    b = stack.pop();
                    a = stack.pop();
                    stack.push(a-b);
                    break;
                case "*":
                    pn.pop();
                    b = stack.pop();
                    a = stack.pop();
                    stack.push(a*b);
                    break;
                case "/":
                    pn.pop();
                    b = stack.pop();
                    a = stack.pop();
                    if(b != 0) {
                        stack.push(a / b);
                    }
                    else {
                        return null;
                    }
                    break;
                default:
                    try {
                        stack.push(Double.parseDouble(pn.pop()));
                    }
                    catch (NumberFormatException e){
                        return null;
                    }
                    break;
            }
        }
        double s = stack.pop();
        if(s - (int)s > 0){
            return Double.toString(s);
        }
        return Integer.toString((int) s);
    }

    public Stack Reverse(Stack pn){
        Stack reverse = new Stack();
        while (!pn.empty())
            reverse.push(pn.pop());
        return reverse;
    }

    public Stack<String> TransformToPolishNotation(String state){
        Stack<String> result = new Stack();
        Stack<Character> tempStack = new Stack();
        for (int i = 0; i < state.length(); i++){
            char charsymbol = state.charAt(i);
            String symbol = Character.toString(charsymbol);
            switch (charsymbol){
                case '.':
                case '0':                            //если лексема — это число или переменная, то добавляем её в результирующий список;
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    if(i != state.length()-1) {
                        for (int j = i + 1; j < state.length(); j++) {
                            char temp = state.charAt(j);
                            if ((temp >= '0' && temp <= '9') || temp == '.') {
                                symbol = symbol + Character.toString(temp);
                                i++;
                            }
                            else{
                                break;
                            }
                        }
                    }
                    result.push(symbol);
                    break;
                case '(':                           //если лексема — это открывающая скобка, то добавляем её в стэк;
                    tempStack.push(charsymbol);
                    break;
                case ')':                           //если лексема — это закрывающая скобка, то:
                    while (!tempStack.peek().equals('(')){    //помещаем элементы из стэка в результирующую строку
                        if (!tempStack.empty()) {             //пока не встретим открывающую скобку, притом открывающая
                            result.push(Character.toString(tempStack.pop()));     //скобка удаляется из стэка, но в результирующую строку не добавляется;
                        }
                        else
                        {
                            result.push(null);
                            return result;
                        }
                    }
                    tempStack.pop();
                    break;
                case '+':
                case '-':
                case '*':
                case '/':

                    if(i == state.length()-1){
                        result.push(null);
                        return result;
                    }
                    else {
                        if (charsymbol == '-'){
                            tempStack.push(charsymbol);
                        }
                        else {
                            if (!tempStack.empty()) {

                                while (GetPriority(charsymbol) <= GetPriority((char) tempStack.peek())) {
                                    result.push(Character.toString(tempStack.pop()));
                                    if(tempStack.empty()){
                                        break;
                                    }

                                }
                            }
                            tempStack.push(charsymbol);
                        }

                    }
                    break;
                default:
                    result.push(null);
                    return result;
            }
        }
        while (!tempStack.empty()){
            if(!(tempStack.peek() == '+' ||
                    tempStack.peek() == '-' ||
                    tempStack.peek() == '*' ||
                    tempStack.peek() == '/')){
                result.push(null);
                return result;
            }
            result.push(Character.toString(tempStack.pop()));
        }
        return result;
    }
    private byte GetPriority(char s)
    {
        switch (s)
        {
            case '(':
            case ')':
                return 0;
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return 3;
        }
    }


}
