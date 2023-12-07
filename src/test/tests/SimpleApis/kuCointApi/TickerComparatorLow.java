package SimpleApis.kuCointApi;

import org.apache.logging.log4j.core.util.JsonUtils;

import java.util.Comparator;
import java.util.stream.Collectors;
//https://www.youtube.com/watch?v=Ob2mdng_dMI&list=PLS-CH047rQ3U8iIUIb9gm3cyf9kbOSasP&index=10&ab_channel=OlehPendrak
// ниже пример дженерика, когжа мы указываем какой тип данных мы используем
public class TickerComparatorLow implements Comparator<TickerData> {

        @Override
        public int compare(TickerData o1, TickerData o2) { // метод compare присутствует у интерфейса
            // TickerData o1, TickerData o2 - объекты. Ниже возвращаем от большего к м. чтобы следовать highToLow
           // поскольку рейты у нас возвращаются в виде строки, а нам надо сравнивать цифры, (процент изменения
            float result = Float.compare(Float.parseFloat(o1.getChangeRate()), Float.parseFloat(o2.getChangeRate()));
          // выше мы сформировали число, где сравниваются больше ли первый объект чем втрой
//            System.out.println("Сравниеваем флоат" + result);
//            System.out.println("Сравниеваем int" + (int) result);
            return (int)result;
        }


}
