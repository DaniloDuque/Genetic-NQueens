import java.util.*;



public class entity implements Comparable<entity>{

    public int board[];
    public int size, fitness;
    private Random random = new Random();


    public entity(int size){

        this.size = size;
        board = new int[size];
        for(int i = 0; i<size; i++) board[i] = random.nextInt(size);
        Fitness();

    }


    public entity(entity f1, entity f2, int size){

        this.size = size;
        board = new int[size];

        int cross = random.nextInt(size);
        for(int i = 0; i<cross; i++) board[i] = f1.board[i];
        for(int i = cross; i<size; i++) board[i] = f2.board[i];

        if(random.nextInt(100) < 5) mutation();
        Fitness();

    }



    private void mutation(){
        board[random.nextInt(size)] = random.nextInt(size);
    }


    private void Fitness(){

        fitness = 0;
        for(int col = 0; col<size; col++)
            for(int i = col + 1; i<size; i++) 
                if(board[i] == board[col] || Math.abs(col - i) == Math.abs(board[col] - board[i])) fitness++; 
        
        fitness *= fitness;
        fitness++;

    }







    @Override
    public int compareTo(entity x){
        return Integer.compare(fitness, x.fitness);
    }

    @Override
    public boolean equals(Object obj){

        entity x = (entity) obj;
        
        return x.fitness == fitness;

    }


    public void showBoard(){

        for(int i = 0; i<size; i++){
            for(int j = 0; j<size; j++)
                System.out.print(((board[j] == i)? "👑": ((i+j)%2 == 1)? "⬛": "⬜"));
            System.out.println();
        }
        System.out.println("fitness: " + (fitness - 1));

    }





}