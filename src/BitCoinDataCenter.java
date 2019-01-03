import javax.ejb.Startup;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.util.Random;

/**
 * BitCoinDataCenter
 *
 * @author Mr Li
 * @date 2019/1/2
 */
@WebServlet(name="BitCoinDataCenter",urlPatterns = "/BitCoinDataCenter",loadOnStartup = 1)
public class BitCoinDataCenter extends HttpServlet implements Runnable {
    public void init(ServletConfig config) {
        startup();
    }
    public void startup(){
        new Thread(this).start();
    }

    @Override
    public void run() {
        int bitPrice=100000;
        while (true){
            //每隔1-3秒就产生一个新价格
            int duration=1000+new Random().nextInt(2000);
            try {
                Thread.sleep(duration);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            float random=1+(float)(Math.random()-0.5);
            //新价格围绕100000左右50%波动
            int newPrice=(int)(bitPrice*random);
            //查看的人越多。价格越高
            int total=ServerManager.getTotal();
            newPrice=newPrice*total;
            String messageFormat="{\"price\":\"%d\",\"total\":%d}";
            String message=String.format(messageFormat,newPrice,total);
            ServerManager.broadCast(message);
        }
    }
}
