import java.util.*;


public class NQueens{


    public ArrayList<entity> gen = new ArrayList<>(), newGen = new ArrayList<>();
    private int genSize, noChanges = -1, genSum, size;
    private Random random = new Random();
    private entity best;



    public NQueens(int size){

        this.size = size;
        genSize = (size*size);
        for(int i = 0; i<genSize; i++) gen.add(new entity(size));
        best = new entity(size);


    }

    private entity crossing(entity f1, entity f2){

        return new entity(f1, f2, size);

    }   

    private void sum(){

        genSum = 0;
        for(entity i: gen) genSum += i.fitness();
        
    }

    private double fitness(entity x){

        return genSum/(x.fitness());

    }

    private void updateBest(){

        entity aux = best;

        for(int i = 0; i<gen.size(); i++) if(gen.get(i).fitness() < aux.fitness()) aux = gen.get(i);

        if(aux.equals(best)) noChanges++;

        best = aux;

    }















    public entity nextGen(){

        if(noChanges > size*size || best.fitness() == 1) return best;

        sum();
        updateBest();
        newGen.clear();
        newGen.add(best);

        // best.showBoard();
        
        for(int i = 0; i<genSize; i++){

            double target1 = random.nextDouble() * genSum, target2 = random.nextDouble() * genSum;
            double cumulativeFit = 0.0;
            entity f1 = null, f2 = null;

            int j = 0;
            while(cumulativeFit < target1 && j < gen.size()) cumulativeFit += fitness(gen.get(j++));
            f1 = gen.get(j-1);
    
            cumulativeFit = j = 0;
            while(cumulativeFit < target2 && j < gen.size()) cumulativeFit += fitness(gen.get(j++));
            f2 = gen.get(j-1);

            newGen.add(new entity(f1, f2, size));


        }gen = new ArrayList<>(newGen);
        return nextGen();

    }

    


    public static void main(String [] args){

        NQueens n = new NQueens(25);
        n.nextGen().showBoard();
        
    }
    

}
