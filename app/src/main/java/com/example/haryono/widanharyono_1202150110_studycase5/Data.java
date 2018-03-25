package com.example.haryono.widanharyono_1202150110_studycase5;

/**
 * Created by haryono on 3/25/2018.
 */

public class Data {

    String todoName, todoDesc, todoPriority; //inisiai variable

    public Data(String todoName, String todoDesc, String todoPriority){  //constuctor class data
        this.todoName=todoName;
        this.todoDesc=todoDesc;
        this.todoPriority=todoPriority;
    }

    public String getTodoName() {
        return todoName;
    }

    public void setTodoName(String todoName) {
        this.todoName = todoName;
    }

    public String getTodoDesc() {
        return todoDesc;
    }

    public void setTodoDesc(String todoDesc) {
        this.todoDesc = todoDesc;
    }

    public String getTodoPriority() {
        return todoPriority;
    }

    public void setTodoPriority(String todoPriority) {
        this.todoPriority = todoPriority;
    }
}