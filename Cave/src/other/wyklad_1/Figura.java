package other.wyklad_1;

public abstract class Figura implements Wyrysowywalna{
    int x,y;
    public Figura(){
        x = (int) (Math.random()*200);
        y = (int) (Math.random()*200);
    }

}
