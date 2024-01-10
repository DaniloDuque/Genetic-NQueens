import java.util.*;



public class entity{

    public ArrayList<Integer> board;
    public int size, fitness;
    private Random random = new Random();


    public entity(int size){

        this.size = size;
        board = new ArrayList<>(Collections.nCopies(size, -1));
        for(int i = 0; i<size; i++) board.set(i, random.nextInt(size));
        fitness = Fitness();

    }


    public entity(entity f1, entity f2, int size){

        this.size = size;
        board = new ArrayList<>(Collections.nCopies(size, -1));

        for(int i = 0; i<(size>>1); i++) board.set(i, f1.board.get(i));
        for(int i = size>>1; i<size; i++) board.set(i, f2.board.get(i));

        if(random.nextInt(100) < 5) mutation();
        fitness = Fitness();

    }






    private void mutation(){
        board.set(random.nextInt(size), random.nextInt(size));
    }

    private int Fitness(){

        int fit = 0;
        for(int col = 0; col<size; col++)
            for(int i = col + 1; i<size; i++) 
                if(board.get(i) == board.get(col) || Math.abs(board.get(col) - board.get(i)) == Math.abs(i - col)) fit++; 

        return fit*fit+1;

    }

    public int fitness(){
        return fitness;
    }




    @Override
    public boolean equals(Object obj){

        entity x = (entity) obj;
        
        for(int i = 0; i<size; i++) if(x.board.get(i) != board.get(i)) return false;

        return true;

    }





    public void showBoard(){

        for(int i = 0; i<size; i++){
            for(int j = 0; j<size; j++)
                System.out.print(((board.get(j) == i)? "👑": ((i+j)%2 == 1)? "⬛": "⬜"));
            System.out.println();
        }
        System.out.println("fitness: " + fitness());

    }




}
