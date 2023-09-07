import java.util.Stack;

public class Expresiones {
    public static int precedencia(char c) {

        if (c == '*' || c == '/') {
            return 3;
        }
        if (c == '+' || c == '-') {
            return 4;
        }
        return Integer.MAX_VALUE;
    }
    public static boolean esOperando(char c) {

        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9');
    }

    public static String infijoSufijo(String infijo) {

        if (infijo == null || infijo.length() == 0) {
            return infijo;
        }

        Stack<Character> stack = new Stack<>();

        String sufijo = "";

        for (char c: infijo.toCharArray()) {

            if (c == '(') {
                stack.push(c);

            } else if (c == ')') {
                while (stack.peek() != '(') {
                    sufijo += stack.pop();
                }
                stack.pop();

            } else if (esOperando(c)) {
                sufijo += c;

            } else {
                while (!stack.isEmpty() && precedencia(c) >= precedencia(stack.peek())) {
                    sufijo += stack.pop();
                }
                stack.push(c);
            }

        }
        while (!stack.isEmpty()) {
            sufijo += stack.pop();
        }
        return sufijo;
    }

    public static void main(String[] args) {

        String cadena = "(A+B)*C/(A*B)";
        String sufijo = infijoSufijo(cadena);

        System.out.println(sufijo);
    }
}