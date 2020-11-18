package Game;
/*
Przychodzi baba do virtualnego lekarza,
a lekarz się scrashował.
 */
import Game.compoments.AABBCompoment;

import java.util.ArrayList;

public class Physics {

    private static ArrayList<AABBCompoment> aabbList = new ArrayList<AABBCompoment>();

    public static void addAABBCompoment(AABBCompoment aabb) {
        aabbList.add(aabb);
    }

    public static void removeAABBCompoment(AABBCompoment aabb) {
        aabbList.remove(aabb);
    }

    public static void update() {
        for (int a = 0; a < aabbList.size(); a++) {
            for (int b = a + 1; b < aabbList.size(); b++) {
                AABBCompoment c0 = aabbList.get(a);
                AABBCompoment c1 = aabbList.get(b);

                if (Math.abs(c0.getCenterX() - c1.getCenterX()) < c0.getHalfWidth() + c1.getHalfWidth()) {
                    if (Math.abs(c0.getCenterY() - c1.getCenterY()) < c0.getHalfHeight() + c1.getHalfHeight()) {

                        //System.out.println("c0: " + c0.getTag() + " c1: " + c1.getTag() + " " + a + " " + b);

                        c0.getParent().collision(c1.getParent(), c1.getGameMenager());
                        c1.getParent().collision(c0.getParent(), c0.getGameMenager());
                    }
                }
            }
        }
        aabbList.clear();
    }

    public static void buttonUpdate(){

    }
}
