import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.*;

// Объявляемое исключение для ошибок в бизнес-методах
class BusinessException extends Exception {
    public BusinessException(String message) {
        super(message);
    }
}

// Необъявляемое исключение для некорректных входных данных
class InvalidDataException extends RuntimeException {
    public InvalidDataException(String message) {
        super(message);
    }
}

// Интерфейс с методами доступа и бизнес-методом
interface DataInterface {
    void setData(int[] array, String stringValue, int intValue);
    int[] getDataArray();
    String getStringValue();
    int getIntValue();
    double calculateFunction() throws BusinessException;
}

// Класс "Пример данных"
class SampleData implements DataInterface {
    private int[] numbers;
    private String stringValue;
    private int intValue;

    // Конструкторы
    public SampleData() {}

    public SampleData(int[] numbers, String stringValue, int intValue) {
        this.numbers = numbers;
        this.stringValue = stringValue;
        this.intValue = intValue;
    }

    // Методы доступа
    public void setData(int[] numbers, String stringValue, int intValue) {
        this.numbers = numbers;
        this.stringValue = stringValue;
        this.intValue = intValue;
    }

    public int[] getDataArray() {
        return this.numbers;
    }

    public String getStringValue() {
        return this.stringValue;
    }

    public int getIntValue() {
        return this.intValue;
    }

    // Бизнес-метод: считает сумму элементов массива
    public double calculateFunction() throws BusinessException {
        if (numbers == null || numbers.length == 0) {
            throw new BusinessException("Массив пуст.");
        }
        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        return sum;
    }

    // Переопределение методов класса Object
    @Override
    public String toString() {
        return "SampleData{" +
                "numbers=" + Arrays.toString(numbers) +
                ", stringValue='" + stringValue + '\'' +
                ", intValue=" + intValue +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        SampleData that = (SampleData) obj;
        return intValue == that.intValue &&
                Arrays.equals(numbers, that.numbers) &&
                Objects.equals(stringValue, that.stringValue);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(stringValue, intValue);
        result = 31 * result + Arrays.hashCode(numbers);
        return result;
    }
}

// Класс "Другой пример данных"
class OtherData implements DataInterface {
    private int[] values;
    private String name;
    private int count;

    // Конструкторы
    public OtherData() {}

    public OtherData(int[] values, String name, int count) {
        this.values = values;
        this.name = name;
        this.count = count;
    }

    // Методы доступа
    public void setData(int[] values, String name, int count) {
        this.values = values;
        this.name = name;
        this.count = count;
    }

    public int[] getDataArray() {
        return this.values;
    }

    public String getStringValue() {
        return this.name;
    }

    public int getIntValue() {
        return this.count;
    }

    // Бизнес-метод: считает среднее значение элементов массива
    public double calculateFunction() throws BusinessException {
        if (values == null || values.length == 0) {
            throw new BusinessException("Массив пуст.");
        }
        int sum = 0;
        for (int value : values) {
            sum += value;
        }
        return (double) sum / values.length;
    }

    // Переопределение методов класса Object
    @Override
    public String toString() {
        return "OtherData{" +
                "values=" + Arrays.toString(values) +
                ", name='" + name + '\'' +
                ", count=" + count +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        OtherData otherData = (OtherData) obj;
        return count == otherData.count &&
                Arrays.equals(values, otherData.values) &&
                Objects.equals(name, otherData.name);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, count);
        result = 31 * result + Arrays.hashCode(values);
        return result;
    }
}

public class Main {
    public static void main(String[] args) {
        List<DataInterface> dataBase = new ArrayList<>();
        int flag1 = 0;
        // Заполнение базы объектов в соответствии с желанием пользователя
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Выберите тип объекта для добавления:");
            System.out.println("1. SampleData");
            System.out.println("2. OtherData");
            System.out.println("3. Завершить ввод");

            int choice = scanner.nextInt();
            if (choice == 3) {
                break; // Выход из цикла, если пользователь завершил ввод
            }

            System.out.println("Введите данные для объекта:");

            // Ввод данных для объекта
            System.out.println("Введите размер массива:");
            int size = scanner.nextInt();
            int[] array = new int[size];
            System.out.println("Заполните массив целыми числами:");
            for (int i = 0; i < size; i++) {
                array[i] = scanner.nextInt();
            }
            System.out.println("Введите строковое значение:");
            scanner.nextLine(); // Очистка буфера после nextInt()
            String stringValue = scanner.nextLine();
            System.out.println("Введите целочисленное значение:");
            int intValue = scanner.nextInt();

            // Создание объекта в зависимости от выбора пользователя
            DataInterface newData;
            switch (choice) {
                case 1:
                    flag1++;
                    newData = new SampleData(array, stringValue, intValue);
                    break;
                case 2:
                    flag1++;
                    newData = new OtherData(array, stringValue, intValue);
                    break;
                default:
                    System.out.println("Некорректный выбор. Повторите попытку.");
                    continue; // Возврат к выбору типа объекта
            }

            // Добавление созданного объекта в базу
            dataBase.add(newData);
        }
        if (flag1 == 0)
            System.out.println("Вы не создали ни одного элемента!");
        else {
            // Вывод информации обо всех объектах массива
            System.out.println("Информация обо всех объектах:");
            for (DataInterface data : dataBase) {
                System.out.println(data);
            }

            // Дополнительные действия:

            // Нахождение объектов с одинаковым результатом бизнес-метода и их помещение в другие массивы:
            int flag = 0;
            List<DataInterface> objectsWithSameResult = new ArrayList<>();
            for (DataInterface data : dataBase) {
                try {
                    double result = data.calculateFunction();
                    for (DataInterface otherData : dataBase) {
                        if (data != otherData && result == otherData.calculateFunction()) {
                            flag++;
                            objectsWithSameResult.add(otherData);
                        }
                    }

                } catch (BusinessException e) {
                    System.out.println("Ошибка при выполнении бизнес-метода: " + e.getMessage());
                }
            }
            if (flag == 0)
                System.out.println("Нет объектов с одинаковой суммой элементов или средним значением");
            else
                System.out.println("Объекты с одинаковой суммой элементов или средним значением:\n" + objectsWithSameResult);


            // Разбиение исходного массива на два массива, в которых хранятся однотипные элементы:
            List<SampleData> sampleDataList = new ArrayList<>();
            List<OtherData> otherDataList = new ArrayList<>();
            for (DataInterface data : dataBase) {
                if (data instanceof SampleData) {
                    sampleDataList.add((SampleData) data);
                } else if (data instanceof OtherData) {
                    otherDataList.add((OtherData) data);
                }
            }
            System.out.println("Разбиение массива на два разных по типу:\nSampleData: " + sampleDataList + "\nOtherData: " + otherDataList);
        }
    }
}