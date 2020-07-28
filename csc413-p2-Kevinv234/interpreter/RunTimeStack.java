package interpreter;

import java.util.ArrayList;
import java.util.Stack;
import java.util.Vector;

public class RunTimeStack {

    private ArrayList<Integer> runTimeStack;
    private Stack<Integer> framePointer;

    // Add initial Frame Pointer, main is the entry
    // point of our language, so its frame pointer is 0.
    public RunTimeStack() {
        runTimeStack = new ArrayList<>();
        framePointer = new Stack<>();

        framePointer.add(0);
    }

    public void dump(){

    }

    //returns the top of the stack without removing anything
    public int peek(){
        if(runTimeStack.isEmpty()){
            return 0;
        }
        int top = runTimeStack.get(runTimeStack.size() - 1);
        return top;
    }

    //remove an item from the top of the stack and return it
    public int pop(){

        if(runTimeStack.isEmpty()){
            return 0;
        }
        int top = runTimeStack.remove(runTimeStack.size() - 1);
        return top;
    }

    public int push(int val){
        runTimeStack.add(val);
        return val;
    }

    // creates a new frame in the RuntimeStack class.
    public void newFrameAt(int offset){
        int newFrame = runTimeStack.size() - offset;
        framePointer.push(newFrame);
    }

    // 1.pop the top frame and save the value
    // 2.pop the top frame and then
    // 3.push the return value back onto the stack
    public void popFrame(){
        //1
        int top = runTimeStack.remove(runTimeStack.size() - 1);

        //2
        while (runTimeStack.size() > framePointer.peek()){
            runTimeStack.remove(runTimeStack.size() - 1);
        }

        //3
        framePointer.pop();
        runTimeStack.add(top);
    }

    // used to store values into variables
    // will pop the top value of the stack and replace it the value at the
    // given offset in the current frame
    // the stores is returned
    public int store(int offset){
        int top = this.pop();
        runTimeStack.set(framePointer.peek() + offset,top);
        return runTimeStack.get(framePointer.peek() + offset);
    }

    // used to load variables onto the RuntimeStack from a given offset within current frame
    public int load(int offset){
        int value = framePointer.peek() + offset;
        runTimeStack.add(runTimeStack.get(value));
        return this.peek();
    }

    //Used to load literal values onto the RuntimeStack
    public Integer push(Integer val){
        return push((int)val);
    }


    //Used to get the current frame
    public int getCurrentFrame(){
        return runTimeStack.size() - framePointer.peek();
    }

    public int peekFrame(){return framePointer.peek();}

    //getter for the runtimestack
    public int get(int index){
        if (runTimeStack.isEmpty() || (index >= runTimeStack.size()) ) {
            return 0;
        }
        int valueAt = runTimeStack.get(index);
        return valueAt;
    }
}
