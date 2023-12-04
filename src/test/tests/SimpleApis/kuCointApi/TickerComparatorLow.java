package SimpleApis.kuCointApi;

import java.util.Comparator;
import java.util.stream.Collectors;

// ниже пример дженерика, когжа мы указываем какой тип данных мы используем
public class TickerComparatorLow implements Comparator<TickerData> {

        @Override
        public int compare(TickerData o1, TickerData o2) { // метод compare присутствует у интерфейса
            // TickerData o1, TickerData o2 - объекты. Ниже возвращаем от большего к м. чтобы следовать highToLow
           // поскольку рейты у нас возвращаются в виде строки, а нам надо сравнивать цифры, (процент изменения
            float result = Float.compare(Float.parseFloat(o1.getChangeRate()), Float.parseFloat(o2.getChangeRate()));
          // выше мы сформировали число, где сравниваются больше ли первый объект чем втрой
            return (int)result;
        }


}
