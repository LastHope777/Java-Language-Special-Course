import java.util.Scanner;



class FirstClass{
    public static void main(String[] s) {
        int len1 = 0;
        int len2 = 0;
        double res = 0;
        Vector vector_1, vector_2, vector_3;
        Scanner sc = new Scanner(System.in);

        System.out.println("");
        System.out.print("Введите длину вектора 1: ");
        len1 = sc.nextInt();
        if (len1 == 0){
            len1 = 5;
        }
        vector_1 = new Vector(len1);

        System.out.println("");
        System.out.print("Вектор 1: ");
        for(int i = 0; i < vector_1.getLength(); i++){
            vector_1.SetNumber(i, Math.random() * 10);
            System.out.print( vector_1.GetNumber(i) + "; ");
        }
        System.out.println("");
        System.out.println("Полученная длина вектора 1:" + (vector_1.getLength() +1));

        System.out.println("");
        System.out.println("Элемент вектора 1 с индексом: " + (int)(vector_1.getLength()/2) + ": " + vector_1.GetNumber((int)(vector_1.getLength()/2)));
        System.out.println("");

        System.out.println("");
        System.out.println("Заменим элемент вектора 1 с индексом: " + (int)(vector_1.getLength()/2) + " на 0 ");
        vector_1.SetNumber((int)(vector_1.getLength()/2), 0);
        System.out.println("");

        System.out.print("Вектор 1 после изменения: ");
        for(int i = 0; i < vector_1.getLength(); i++){
            System.out.print( vector_1.GetNumber(i) + "; ");
        }

        System.out.println("");
        System.out.print("Минимальный элемент: " + vector_1.GetMin());
        System.out.println("");

        System.out.println("");
        System.out.print("Максимальный элемент: " + vector_1.GetMax());
        System.out.println("");

        System.out.println("");
        vector_1.Sort();
        System.out.print("Отсортированный по возрастанию вектор 1: ");
        for(int i = 0; i < vector_1.getLength(); i++){
            System.out.print( vector_1.GetNumber(i) + "; ");
        }
        System.out.println("");

        System.out.println("");
        System.out.print("Евклидова норма вектора 1: " + vector_1.GetNorm());
        System.out.println("");

        System.out.println("");
        System.out.print("Вектор после умножения на число 5: ");
        vector_1.MultNumber(5);
        for(int i = 0; i < vector_1.getLength(); i++){
            System.out.print( vector_1.GetNumber(i) + "; ");
        }
        System.out.println("");

        System.out.print("Введите длину вектора 2: ");
        len2 = sc.nextInt();
        while(len2 != len1)
        {
            System.out.print("Длина векторов должна совпадать! Введите длину вектора 2: ");
            len2 = sc.nextInt();
        }
        vector_2 = new Vector(len2);

        System.out.println("");
        System.out.print("Вектор 2: ");
        for(int i = 0; i < vector_2.getLength(); i++){
            vector_2.SetNumber(i,Math.random() * 10);
            System.out.print( vector_2.GetNumber(i) + "; ");
        }
        System.out.println("");

        System.out.println("");
        System.out.print("Вектор-сумма двух векторов 1 и 2: ");
        vector_3 = Vector.Sum(vector_1, vector_2);
        for(int i = 0; i < vector_3.getLength(); i++){
            System.out.print( vector_3.GetNumber(i) + "; ");
        }

        System.out.println("");
        res = Vector.MultVector(vector_1, vector_2);
        if(res != -1){
            System.out.println("Скалярное произведение векторов 1 и 2: "+ res);
        }
        else System.out.println("Умножение векторов разной размерности невозможно.");

    }
}
