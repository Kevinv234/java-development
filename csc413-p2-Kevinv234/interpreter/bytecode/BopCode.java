package interpreter.bytecode;
import interpreter.VirtualMachine;
import java.util.ArrayList;

public class BopCode extends ByteCode {
    /* BOP
       BOP <binary op>, pop top 2 levels of the stack and perform
       the indicated operation.
       Operations are " + - / * == != <= > >= < | &"
       | and & are logical operators not bitwise operators.
       Lower level is the first operand
       Eg: <second-level> + <top-level>
       Ex: BOP +
           BOP -
           BOP /
     */

    private String operator;

    @Override
    public void init(ArrayList arr) {
        if (!arr.isEmpty()) {
            operator = (String) arr.get(0);
        }
    }

    // this function will execute all operations and logic operators
    @Override
    public void execute(VirtualMachine vm) {
        // two operand we are going to be working with
        int valA = vm.popRS();
        int valB = vm.popRS();

        // Switch statement for operations in BOP
        switch (operator) {
            case "+":
                vm.pushRS(valA + valB);
                break;
            case "-":
                vm.pushRS(valA - valB);
                break;
            case "/":
                vm.pushRS(valA / valB);
                break;
            case "*":
                vm.pushRS(valA * valB);
                break;
            case "==":
                if (valA == valB) {
                    vm.pushRS(1);
                } else {
                    vm.pushRS(0);
                }
                break;
            // logic statements 1 - true 0 - false
            case "!=":
                if (valA != valB) {
                    vm.pushRS(1);
                } else {
                    vm.pushRS(0);
                }
                break;
            case "<=":
                if (valA <= valB) {
                    vm.pushRS(1);
                } else {
                    vm.pushRS(0);
                }
                break;
            case ">":
                if (valA > valB) {
                    vm.pushRS(1);
                } else {
                    vm.pushRS(0);
                }
                break;
            case ">=":
                if (valA >= valB) {
                    vm.pushRS(1);
                } else {
                    vm.pushRS(0);
                }
                break;
            case "<":
                if (valA < valB) {
                    vm.pushRS(1);
                } else {
                    vm.pushRS(0);
                }
                break;
            case "|":
                if (valA == 1 || valB == 1) {
                    vm.pushRS(1);
                } else {
                    vm.pushRS(0);
                }
                if (valA == 0 || valB == 0) {
                    vm.push(0);
                } else {
                    vm.push(1);

                }
                break;
            case "&":
                if (valA == 1 && valB == 1) {

                    vm.pushRS(1);
                } else {
                    vm.pushRS(0);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public String toString() {
        return "BOP " + operator;
    }
}
