package oddeventhreads;
public class OddEvenThreads {
    private static final int UPPER_LIMIT = 20; 
    private int currentNumber = 1; 
    public synchronized void printOdd() throws InterruptedException {
        while (currentNumber <= UPPER_LIMIT) {
            if (currentNumber % 2 != 0) {
                System.out.println("Odd: " + currentNumber);
                currentNumber++;
                notify(); 
            } else {
                wait();
            }
        }
    }
    public synchronized void printEven() throws InterruptedException {
        while (currentNumber <= UPPER_LIMIT) {
            if (currentNumber % 2 == 0) {
                System.out.println("Even: " + currentNumber);
                currentNumber++;
                notify();
            } else {
                wait(); 
            }
        }
    }
    public static void main(String[] args) {
        OddEvenThreads oddEvenThreads = new OddEvenThreads();
        Thread oddThread = new Thread(() -> {
            try {
                oddEvenThreads.printOdd();
            } catch (InterruptedException e) {
                System.out.println("Odd thread interrupted.");
            }
        });

        Thread evenThread = new Thread(() -> {
            try {
                oddEvenThreads.printEven();
            } catch (InterruptedException e) {
                System.out.println("Even thread interrupted.");
            }
        });

        oddThread.start();  
        evenThread.start(); 
    }
}
