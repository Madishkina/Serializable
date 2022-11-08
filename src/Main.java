import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void main(String[] args) {

        GameProgress[] gameProgress = {     // создадим массив класса сохраненной игры(можно через List/Arraylist)
                new GameProgress(94, 10, 2, 254.32),
                new GameProgress(84, 15, 3, 300.22),
                new GameProgress(74, 13, 4, 350.22)
        };
        List<String> savedFiles = new ArrayList<>();//для создания и записи данных в файл dat используем List
        for (int i = 0; i < gameProgress.length; i++) {
            String str = "C:/Games/savegames/save3.dat";//
            String datFile = str + "save" + i + ".dat";
            saveGame(datFile, gameProgress[i]);
            savedFiles.add(datFile);
        }
        zipFiles(savedFiles);
        deleteFiles(savedFiles);
    }

    private static void deleteFiles(List<String> savedFiles) {
        for (String del : savedFiles) {
            File fileToDel = new File(del);
            if (fileToDel.delete()) {
                System.out.println("Удаление файла: " + del);
            } else {
                System.out.println("Ошибка удаления файла: " + del);
            }
        }
    }

    private static void saveGame(String str, GameProgress gameProgress) {
        try (FileOutputStream fos = new FileOutputStream(str);//откроем выходной поток для записи в файл
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameProgress);//запишем экземпляр класса в файл
            System.out.println("Запись в директории: " + str);
        } catch (Exception ex) {
            System.out.println("Ошибка записи: " + ex.getMessage());
        }
    }

    private static void zipFiles(List<String> savedFiles) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream("zip"))) {
            for (String sf : savedFiles) {
                try (FileInputStream fis = new FileInputStream(sf)) {
                    ZipEntry entry = new ZipEntry(new File(sf).getName());
                    zout.putNextEntry(entry);
                    byte[] buffer = new byte[fis.available()];// считываем содержимое файла в массив byte
                    zout.write(buffer);// добавляем содержимое к архиву
                    zout.closeEntry();// закрываем текущую запись для новой записи
                    System.out.println("Файл " + sf + " добавлен в архив!");
                } catch (IOException ex) {
                    System.out.println("Ошибка при добавлении файла в архив: " + ex.getMessage());
                }
            }
        } catch (Exception ex) {
            System.out.println("Ошибка при создании архива: " + ex.getMessage());
        }
    }
}
