import java.text.DecimalFormat;
import java.util.concurrent.*;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        DecimalFormat df = new DecimalFormat("0.000");
        double startTime= System.nanoTime();
        Scanner input=new Scanner(System.in);
        String response;

        ExecutorService executor = Executors.newSingleThreadExecutor();
        System.out.print("Input word:");
        response=input.nextLine();

        Future<Integer> count = executor.submit(new CountCharacters(response));

        Integer answer = count.get();
        System.out.print("\n"+response+" - "+ answer);
        double endTime = System.nanoTime();
        double executionTime = (endTime-startTime)/1000000000;
        System.out.println("\n");
        System.out.print(df.format(executionTime));

    }

    static class CountCharacters implements Callable<Integer> {
        private String response;

        public CountCharacters(String response){
            this.response=response;
        }

        @Override
        public Integer call() throws Exception{
            return countingfunc();
        }

        private int countingfunc() throws InterruptedException{
            int count=0;

            for (int i=0; i<response.length();i++){
                if(response.charAt(i)!=' ')
                    count++;
            }
            return count;
        }
    }
}
