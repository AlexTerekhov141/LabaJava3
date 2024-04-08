/*
 * Main
 *
 * Version 1.0
 *
 * March 2024
 *
 * Practice work for java
 */

import java.util.Scanner;
import java.util.Calendar;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);// создаем сканнер для input
        System.out.println("Введите Фамилию, Имя, Отчество и дату рождения формат:Фамилия Имя Отчество 00.00.0000");
        String a = in.nextLine();
        try {//исключения если введен текст на английском языке
            if (!a.matches("[а-яА-Я0-9\\s.]+")) {
                throw new IllegalArgumentException("Имя, Фамилия или Отчество содержат недопустимые символы");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }


        String[] lines = a.split(" ");
        try {//исключения если количество элементов недостаточно
            if (lines.length < 4) {
                throw new IllegalArgumentException("Некорректный ввод данных: недостаточно элементов");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        String sex = "";
        int age;

        //проверка на пол
        if(lines[2].charAt(lines[2].length()-1) == 'ч'){
            sex = "М";
        }else if(lines[2].charAt(lines[2].length()-1) == 'а'){
            sex = "Ж";
        }else{// исключение для пола
            throw new IllegalArgumentException("Undefined Gender");
        }

        String date = lines[3];
        String[] birthdate = date.split("\\."); // Разбиваем строку с датой на отдельные части
        java.util.Calendar now = java.util.Calendar.getInstance();
        int year = now.get(java.util.Calendar.YEAR);
        int month = now.get(java.util.Calendar.MONTH) + 1; // добавляем 1, так как месяцы начинаются с 0
        int day = now.get(java.util.Calendar.DAY_OF_MONTH);
        int birthYear, birthMonth, birthDay;


        try {
            if(lines.length < 4 || date.split("\\.").length != 3){
                throw new IllegalArgumentException("Некорректный ввод данных");
            }

            // Исключение для parseInt

            try {
                birthYear = Integer.parseInt(birthdate[2]);
                birthMonth = Integer.parseInt(birthdate[1]);
                birthDay = Integer.parseInt(birthdate[0]);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Ошибка при преобразовании даты рождения в числа");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        try{
            age = year - birthYear;
            if(birthMonth > month || (birthMonth == month && birthDay > day)){
                age--; // Если день рождения еще не наступил в текущем году, уменьшаем возраст на 1
            }
            if(age < 0) {
                throw new IllegalArgumentException("День рождения еще не наступил");
            }
        }catch (NumberFormatException e){
            throw new IllegalArgumentException("Ошибка при вычислении возраста");
        }

        System.out.println(lines[0] + " " + lines[1].charAt(0) +"." +lines[2].charAt(0)+"." + " " + "пол " + sex + " возраст " + age + " лет");// вывод
    }
}
