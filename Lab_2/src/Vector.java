public class Vector
{
    private double[] v; //Сам вектор
    public Vector(int length) //Конструктор
    {
        v = new double[length];
    }
    public double GetNumber(int index) //Получение значения
    {
        return v[index];
    }
    public void SetNumber(int index, double number)  //Установка значения
    {
        v[index] = number;
    }
    public int getLength()  //Получение длины массива
    {
        return v.length;
    }
    public double GetMax()  //Максимальное число
    {
        double max = v[0];
        for (int i = 0; i < v.length; i++)
        {
            if (v[i] > max)
                max = v[i];
        }
        return max;
    }
    public double GetMin()  //Минимальное число
    {
        double min = v[0];
        for (int i = 0; i < v.length; i++)
        {
            if (v[i] < min)
                min = v[i];
        }
        return min;
    }
    public void Sort() //Сортировка
    {
        for (int i = 0; i < v.length; i++) {
            double min = v[i];
            int minId = i;
            for (int j = i+1; j < v.length; j++) {
                if (v[j] < min) {
                    min = v[j];
                    minId = j;
                }
            }
            // замена
            double temp = v[i];
            v[i] = min;
            v[minId] = temp;
        }
    }
    public double GetNorm() //Получение длины вектора
    {
        double norm = 0;
        for(int i = 0; i < v.length; i++)
        {
            norm += v[i] * v[i];
        }
        return Math.sqrt(norm);
    }
    public void MultNumber(double number) //Умножение на число
    {
        for(int i = 0; i < v.length; i++)
            v[i] *= number;
    }
    public static Vector Sum(Vector v1, Vector v2) //Сумма векторов
    {
        Vector resultVector = new Vector (v2.getLength());
        for (int i = 0; i< v1.getLength(); i++)
            resultVector.SetNumber(i, v1.GetNumber(i) + v2.GetNumber(i));
        return resultVector;
    }
    public static double MultVector(Vector v1, Vector v2) //Скалярное произведение
    {
        double result = 0;
        for (int i = 0; i < v1.getLength(); i++)
            result += v1.GetNumber(i) * v2.GetNumber(i);
        return result;
    }
}
