import java.util.Map;
import java.util.Stack;

//Использование
//RPN.solve("3+4*2/(1-5)")
public class RPN {
    private static Map<String, Integer> priority = Map.ofEntries(
            Map.entry("(", 0),
            Map.entry(")", 0),
            Map.entry("+", 1),
            Map.entry("-", 1),
            Map.entry("*", 2),
            Map.entry("/", 2)
    );

    //Передается обычное выражение, например:
    //3 + 4 * 2 / (1 − 5)
    //Функция переводит выражение в ОПН
    //Результат:
    //3 4 2 * 1 5 - / +
    //Алгоритм: https://ru.wikipedia.org/wiki/%D0%90%D0%BB%D0%B3%D0%BE%D1%80%D0%B8%D1%82%D0%BC_%D1%81%D0%BE%D1%80%D1%82%D0%B8%D1%80%D0%BE%D0%B2%D0%BE%D1%87%D0%BD%D0%BE%D0%B9_%D1%81%D1%82%D0%B0%D0%BD%D1%86%D0%B8%D0%B8
    private static String convert(String s) {
        s = addSpaces(s);
        String[] ss = s.split(" ");
        StringBuilder result = new StringBuilder();
        Stack<String> opsStack = new Stack<>();

        for (int i = 0; i < ss.length; i++) {
            if (isNumber(ss[i])) {
                result.append(ss[i]);
                result.append(" ");
            }
            else if (ss[i].equals("(")) {
                opsStack.push(ss[i]);
            }
            else if (ss[i].equals(")")) {
                String op = opsStack.pop();
                while(!op.equals("(")){
                    result.append(op);
                    result.append(" ");
                    op = opsStack.pop();
                }
            }
            else {
                while(!opsStack.empty() && !opsStack.peek().equals("(") && priority.get(opsStack.peek()) >= priority.get(ss[i])) {
                    result.append(opsStack.pop());
                    result.append(" ");
                }
                opsStack.push(ss[i]);
            }
        }
        while(!opsStack.empty()) {
            result.append(opsStack.pop());
            result.append(" ");
        }
        //удаляем последний пробел
        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }

    //Функция добавляет пробелы между числами и операциями, например:
    //из "3+4*2/ (1-5)" должно получиться "3 + 4 * 2 / ( 1 - 5 )"
    private static String addSpaces(String s) {
        s = s.replaceAll(" ", "");
        StringBuilder t = new StringBuilder(s);
        for (int i = t.length() - 2; i >= 0; i--) {
            if (!Character.isDigit(t.charAt(i)) || !Character.isDigit(t.charAt(i + 1))) {
                t.insert(i + 1, ' ');
            }
        }
        if (t.charAt(t.length() - 1) == ' ')
            t.deleteCharAt(t.length() - 1);
        if (t.charAt(0) == '-') {
            t.deleteCharAt(1);
        }
        return t.toString();
    }

    private static boolean isNumber(String s) {
        int i = 0;
        if (s.equals("-")) {
            return false;
        }
        if (s.startsWith("-")) {
            i = 1;
        }
        for (;i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    //Передается обычное выражение, например:
    //3+4* 2/(1 −5)
    //Функция вычисляет результат
    //Алгоритм: https://ru.wikipedia.org/wiki/%D0%9E%D0%B1%D1%80%D0%B0%D1%82%D0%BD%D0%B0%D1%8F_%D0%BF%D0%BE%D0%BB%D1%8C%D1%81%D0%BA%D0%B0%D1%8F_%D0%B7%D0%B0%D0%BF%D0%B8%D1%81%D1%8C
    public static double solve(String s) {
        Stack<Double> stack = new Stack<>();
        s = s.replaceAll("\\((\\ )?-", "\\(0-");
        s = convert(s);
        String[] ss = s.split(" ");
        for (int i = 0; i < ss.length; i++) {
            if (isNumber(ss[i])) {
                stack.push(Double.parseDouble(ss[i]));
            }
            else {
                double b = stack.pop();
                double a = stack.pop();
                double c;
                switch (ss[i]) {
                    case "+":
                        c = a + b;
                        break;
                    case "-":
                        c = a - b;
                        break;
                    case "*":
                        c = a * b;
                        break;
                    case "/":
                        c = a / b;
                        break;
                    default:
                        c = 0;
                        break;
                }
                stack.push(c);
            }
        }
        return stack.pop();
    }
}
