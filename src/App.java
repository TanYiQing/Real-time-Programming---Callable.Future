import java.io.*;
import java.text.DecimalFormat;
import java.util.concurrent.*;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        DecimalFormat df = new DecimalFormat("0.000");

        Scanner input=new Scanner(System.in);
        String response;
        ExecutorService executor = Executors.newSingleThreadExecutor();
        System.out.print("Input word:");
        response=input.nextLine();

        double startTime= System.nanoTime();

        Future<Integer> count = executor.submit(new CountCharacters(response));
        Integer answer = count.get();

        System.out.println();
        System.out.print(response+" - "+ answer);

        double endTime = System.nanoTime();
        double executionTime = (endTime-startTime)/1000000000;

        System.out.println("\n");
        System.out.print(df.format(executionTime)+ " seconds");
    }

    static class CountCharacters implements Callable<Integer> {
        private final String response;

        public CountCharacters(String response){
            this.response=response;
        }

        @Override
        public Integer call() throws IOException {
            return countingfunc();
        }

        private int countingfunc() throws IOException {
            int count=0;
            File file=new File("src/RossBeresford.txt");
            String [] words;

            FileReader fileReader=new FileReader(file);
            BufferedReader bufferedReader=new BufferedReader(fileReader);
            String content;
            while((content=bufferedReader.readLine())!=null){
                words=content.split(" ");
                for(String word : words){
                    word=word.replace(",","");
                    word=word.replace(".","");
                    if(word.equals(response)){
                        count++;
                    }
                }
            }
            fileReader.close();
            return count;
        }
    }
}
