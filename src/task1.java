
//    Напишите приложение, которое будет запрашивать у пользователя следующие данные в произвольном порядке,
//    разделенные пробелом:
//    Фамилия Имя Отчество дата рождения номер телефона пол
//
//    Форматы данных:
//    фамилия, имя, отчество - строки
//    дата рождения - строка формата dd.mm.yyyy
//    номер телефона - целое беззнаковое число без форматирования
//    пол - символ латиницей f или m.

//    Приложение должно проверить введенные данные по количеству. Если количество не совпадает с требуемым,
//    вернуть код ошибки, обработать его и показать пользователю сообщение, что он ввел меньше и больше данных, чем требуется.
//
//    Приложение должно попытаться распарсить полученные значения и выделить из них требуемые параметры. Если форматы данных
//    не совпадают, нужно бросить исключение, соответствующее типу проблемы. Можно использовать встроенные типы java и создать
//    свои. Исключение должно быть корректно обработано, пользователю выведено сообщение с информацией, что именно неверно.
//
//    Если всё введено и обработано верно, должен создаться файл с названием, равным фамилии, в него в одну строку должны
//    записаться полученные данные, вида
//
//    <Фамилия><Имя><Отчество><датарождения> <номертелефона><пол>
//
//    Однофамильцы должны записаться в один и тот же файл, в отдельные строки.
//
//    Не забудьте закрыть соединение с файлом.
//
//    При возникновении проблемы с чтением-записью в файл, исключение должно быть корректно обработано,
//    пользователь должен увидеть стектрейс ошибки.
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class task1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите данные в формате: Фамилия Имя Отчество дата рождения номер телефона пол: ");
        String input = scanner.nextLine();

        String[] data = input.split(" ");
        if (data.length != 6) {
            System.out.println("Ошибка: неверное количество данных");
            return;
        }
        String lastName = data[0];
        String firstName = data[1];
        String middleName = data[2];
        String birthDateStr = data[3];
        String phoneNumberStr = data[4];
        String genderStr = data[5];

        LocalDate birthDate;
        try {
            birthDate = LocalDate.parse(birthDateStr, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        } catch (DateTimeParseException e) {
            System.out.println("Ошибка: неверный формат даты рождения");
            return;
        }

        long phoneNumber;
        try {
            phoneNumber = Long.parseLong(phoneNumberStr);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: неверный формат номера телефона");
            return;
        }

        char gender;
        if (genderStr.length() != 1 || (genderStr.charAt(0) != 'f' && genderStr.charAt(0) != 'm')) {
            System.out.println("Ошибка: неверный формат пола");
            return;
        } else {
            gender = genderStr.charAt(0);
        }

        try {
            FileWriter writer = new FileWriter(lastName + ".txt", true);
            writer.write(lastName + firstName + middleName + birthDateStr + " " + phoneNumberStr + genderStr + "\n");
            writer.close();
            System.out.println("Данные успешно записаны в файл " + lastName + ".txt");
        } catch (IOException e) {
            System.err.println("Ошибка записи в файл: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

