package interpreter.bytecode;
import interpreter.VirtualMachine;

import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;


public class ReadCode extends ByteCode{
    /* READ
       READ; Read an integer; prompt the user for input
       and push the value to the stack. Make sure the input
       is validated.
       ex: READ
     */
    @Override
    public void init(ArrayList arr) {

    }
    @Override
    public void execute(VirtualMachine vm){
        Scanner reader = new Scanner(System.in);
        System.out.println("Please enter a number:" );
        int value = reader.nextInt();
        vm.pushRS(value);
    }

    @Override
    public String toString (){
        return "READ";
    }
}
