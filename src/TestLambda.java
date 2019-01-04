import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Filter;

/**
 * TestLambda
 *
 * @author Mr Li
 * @date 2019/1/3
 */
public class TestLambda {
    public static void main(String[] args) {
   /*     Random r = new Random();
        List<Hero> heroList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            heroList.add(new Hero("hero " + i, r.nextInt(1000), r.nextInt(100)));
        }
        System.out.println("初始化后的集合");
        System.out.println(heroList);*/
      /*  HeroChecker checker=new HeroChecker() {
            @Override
            public boolean test(Hero o) {
                return (o.hp>100&&o.damage<50);
            }
        };*/
//        filterHero(heroList,h->h.hp>100 && h.damage<50);
        String className = "Hero";
        try {
//            Class pClass1=Class.forName(className);
            Class pClass2=Hero.class;
            Constructor c = pClass2.getConstructor();
            Hero o = (Hero) c.newInstance();
            o.name="盖伦";
            System.out.println(o);
//            Class pClass3=new Hero().getClass();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
       /* System.out.println("筛选出 hp>100 && damange<50的英雄");
        filterHero(heroList);
    }*/
    private static void filterHero(List<Hero> heroList,HeroChecker checker){
       /* for (Hero hero : heroList) {
            if (hero.hp>100&&hero.damage<50){
                System.out.println(hero);
            }
        }*/
        for (Hero hero : heroList) {
            if (checker.test(hero)){
                System.out.println(hero);
            }
        }
    }
}
