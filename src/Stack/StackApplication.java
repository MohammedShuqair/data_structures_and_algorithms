package Stack;
import java.util.HashMap;
import java.util.Scanner;

public class StackApplication {
  static   HashMap<String,String []> rateTable = new HashMap<>();
  static  String[] operators;
  static boolean searchFor(String[] array,String input){
      for (String item:array){
          if(item.compareTo(input)==0){
              return true;
          }
      }
      return false;
  }

    public static String infixToPostfix(String infix){
        Stack<String> stack=new Stack<String>();
        String[] tokens=infix.split("");
        StringBuilder output= new StringBuilder();
        for(String token:tokens){
            System.out.println("Token "+token);
            if(token.compareTo("(")==0){
                stack.push(token);
                System.out.println("Token "+token+" pushed");
            }else if(token.compareTo(")")==0){
                while (stack.peek().compareTo("(")!=0){
                    System.out.println("Add to output "+stack.peek()+" and pop");
                    output.append(stack.pop());
                }
                stack.pop();
                System.out.println("Token ( poped");
            } else if (searchFor(operators,token)) {
                boolean done =false;
                while (!done){
//                    System.out.println("Enter operation loop");
                    if(stack.isEmpty()||(!stack.isEmpty()&&stack.peek().compareTo("(")==0)){
                        stack.push(token);
                        System.out.println("push to stack "+token);
                        done=true;
                    }else if(rateTable.containsKey(token)&&searchFor(rateTable.get(token),stack.peek())){
                        stack.push(token);
                        System.out.println("Token "+token+" pushed because top of stack is lower than it");
                        done=true;
                    }else {
                        System.out.println("Top of stack is equal or higher than "+token+" so output and pop");
                        output.append(stack.pop());
                    }
                }
            }else{
                output.append(token);
            }
        }
        while (!stack.isEmpty()){
            output.append(stack.pop());
        }
        return  output.toString();
    }

    public static double postfixEvaluation(String postfix){
      Stack<Double> stack=new Stack<Double>();
      for(String ch :postfix.split("")){
          if(searchFor(operators,ch)){
              double op1=stack.pop();
              double op2=stack.pop();
              double result=0;
              if(ch.compareTo("+")==0){
                  result=op1+op2;
              } else if (ch.compareTo("-") == 0) {
                  result=op2-op1;
              }
               else if (ch.compareTo("*") == 0) {
                  result=op1*op2;
              }
               else if (ch.compareTo("/") == 0) {
                  result= op2 /op1;
              }
               else if (ch.compareTo("^") == 0) {
                  result= Math.pow(op2,op1);
              }
              stack.push(result);
          }else{
              stack.push(Double.parseDouble(ch));
          }
      }
      return stack.pop();
    }

    public static void main(String[] args) {
        rateTable.put("*", new String[]{"+","-"});
        rateTable.put("/", new String[]{"+","-"});
        rateTable.put("^", new String[]{"^","/","*","+","-"});
        operators= new String[]{"+","-","*","/","^"};
        String input=new Scanner(System.in).next();
        String postFix=infixToPostfix(input);
        System.out.println(postFix);
        System.out.println(postfixEvaluation(postFix));


    }
}
