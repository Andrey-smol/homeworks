package ru.avalon.javapp.devj140.userGUI.BusinessLogic;

import java.util.ArrayList;
import java.util.List;

public class SharedResource {

    private List<?> buf = new ArrayList<>();
    private boolean dataIsReady = false;
    private String command;
    private boolean comIsReady = false;

    public synchronized void setDataRead(boolean dataIsReady) {
        this.dataIsReady = dataIsReady;
        notify();
    }

    public int getLengthBuf(){
        return buf.size();
    }

    /**
     * запись данных в буфер program
     * @param buf
     * @param
     * @param
     * @throws InterruptedException
     */
    public synchronized void writeDataBuf(List<?> buf) throws InterruptedException {
        if(dataIsReady)
            wait();
        this.buf = buf;
        dataIsReady = true;
        notify();
    }

    /**
     * чтение данных из буфера GUI, заполненного program
     * @return
     * @throws InterruptedException
     */
    public  synchronized List<?> readDataBuf() throws InterruptedException{
        if(!dataIsReady)
            wait();
        return buf;
    }
    //это читает program
    public synchronized String readCommand() throws InterruptedException{
        if(!comIsReady)
            wait();
        comIsReady = false;
        return command;
    }

    //записывает свою команду GUI
    public synchronized void writeCommand(String com){
        command = com;
        comIsReady = true;
        dataIsReady = false;
        notify();
    }
}
