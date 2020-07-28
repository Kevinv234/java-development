package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class GotoCode extends ByteCode {
    private String label;
    private int num;
    @Override
    public void init(ArrayList<String> arr){
        if(!arr.isEmpty()){
            label = arr.get(0);
        }
    }
    @Override
    public  void execute(VirtualMachine vm){vm.setPC(num);}
    @Override
    public  String toString (){
        return "GOTO " + label;
    };

    public String getLabel(){
        return label;
    }
    public void replaceAddress(int adrs){
        num = adrs;
    }
}
