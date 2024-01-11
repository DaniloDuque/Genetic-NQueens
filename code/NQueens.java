import java.util.*;


public class NQueens{


    private entity gen[], newGen[];
    private int genSize, noChanges = 0, size;
    private double genSum;
    private Random random = new Random();
    private entity best;




    public NQueens(int size){

        this.size = size;
        genSize = (size*size)>>1;
        gen = new entity[genSize];
        newGen = new entity[genSize];
        for(int i = 0; i < genSize; i++) gen[i] = new entity(size);
        best = getBest();

    }




    private double fitness(entity x){
        return genSum/(x.fitness);
    }

    private entity getBest(){

        entity curr = gen[0];

        for(int i = 0; i<size; i++) if(curr.fitness > gen[i].fitness) curr = gen[i];

        return curr;

    }

    private void updateBest(){

        entity aux = getBest();

        noChanges = (aux.equals(best))? noChanges + 1: 0;

        best = aux;

    }















     public entity nextGen(){

        while (noChanges < size*size && best.fitness > 1){

            genSum = 0.0;
            for (int i = 0; i<size; i++) genSum += fitness(gen[i]);
            updateBest();
            newGen[0] = best;
            
            for (int i = 1; i<genSize; i++){

                double target1 = random.nextDouble() * genSum, target2 = random.nextDouble() * genSum;
                double cumulativeFit = 0.0;
                entity f1 = null, f2 = null;

                int j = 0;
                while (cumulativeFit < target1 && j < genSize) cumulativeFit += fitness(gen[j++]);
                f1 = gen[Math.max(j-1, 0)];

                cumulativeFit = j = 0;
                while (cumulativeFit < target2 && j < genSize) cumulativeFit += fitness(gen[j++]);
                f2 = gen[Math.max(j-1, 0)];

                newGen[i] = new entity(f1, f2, size);

            }for(int i = 0; i<size; i++) gen[i] = newGen[i];


        }return best;

    }

    


    public static void main(String [] args){

        NQueens n = new NQueens(4);
        n.nextGen().showBoard();
        
    }


    

}