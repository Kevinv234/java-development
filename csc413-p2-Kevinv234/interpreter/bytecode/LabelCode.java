package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class LabelCode extends ByteCode {
    private String label;
    @Override
    public void init(ArrayList arr){
        if(!arr.isEmpty()){
            label = (String) arr.get(0);
        }

    }

    @Override
    public void execute(VirtualMachine vm){}



    public String getLabel(){
        return label;
    }

    @Override
    public String toString (){ return "";}
}
