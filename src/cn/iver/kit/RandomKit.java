package cn.iver.kit;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * Author: iver
 * Date: 13-4-1
 */
public class RandomKit {
    private static final int LIMIT = 12;
    private static final RandomKit RANDOM_KIT = new RandomKit();

    private RandomKit(){

    }

    public static RandomKit getRandomKit(){
        return RANDOM_KIT;
    }

    public int getNumber(){
        return new Random().nextInt(LIMIT);
    }
}
