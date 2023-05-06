import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        File directory = new File("D://Games");
        List<String> paths = Arrays.asList("src", "res", "savegames", "tems");
        List<String> pathsInRes = Arrays.asList("drawables", "vectors", "icons");
        List<String> pathsInSrc = Arrays.asList("main", "test");
        List<String> files = Arrays.asList("Main.java", "Utils.java", "temp.txt");


        if (directory.mkdir()) {
            writeLogDir(stringBuilder, directory);
            System.out.println("Папка Games создана, на диске D, логи записаны");
            if (directory.exists()) {
                mkdirInGameNew(paths, stringBuilder);
                mkdirInSrc(pathsInSrc, stringBuilder);
                mkdirInRes(pathsInRes, stringBuilder);
                newFiles(files, stringBuilder);
            } else {
                System.out.println("Папка Games не существует");
            }
        } else {
            System.out.println("Папка Games не создана");
        }

        File fileTemp = new File("D://Games/tems/", "temp.txt");
        if (fileTemp.canWrite()) {
            System.out.println("записан файл temp");
        } else {
            System.out.println("файл temp не может быть записан");
        }

        try (FileOutputStream fos = new FileOutputStream("D://Games/tems/temp.txt")) {
            String result = new String(stringBuilder);
            byte[] bytes = result.getBytes(StandardCharsets.UTF_8);
            fos.write(bytes, 0, bytes.length);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void mkdirInGameNew(List<String> paths, StringBuilder stringBuilder) {
        for (String pathGame : paths) {
            File newDirectory = new File("D://Games/" + pathGame);
            if (newDirectory.mkdir()) {
                writeLogDir(stringBuilder, newDirectory);
                System.out.println("Папка " + pathGame + " создана, логи записаны");
            } else {
                System.out.println("Папка " + pathGame + " не создана");
            }
        }

    }

    public static void mkdirInRes(List<String> pathsInRes, StringBuilder stringBuilder) {
        for (String pathNew : pathsInRes) {
            File newDirectory = new File("D://Games/res/" + pathNew);
            if (newDirectory.mkdir()) {
                writeLogDir(stringBuilder, newDirectory);
                System.out.println("Папка " + pathNew + " создана, логи записаны");
            } else {
                System.out.println("Папка " + pathNew + " не создана");
            }
        }
    }

    public static void mkdirInSrc(List<String> pathsInSrc, StringBuilder stringBuilder) {
        for (String pathNew : pathsInSrc) {
            File newDirectory = new File("D://Games/src/" + pathNew);
            if (newDirectory.mkdir()) {
                writeLogDir(stringBuilder, newDirectory);
                System.out.println("Папка " + pathNew + " создана, логи записаны");
            } else {
                System.out.println("Папка " + pathNew + " не создана");
            }
        }
    }

    public static void newFiles(List<String> files, StringBuilder stringBuilder) {
        for (String file : files) {
            File newFile = new File("D://Games/src/main/", file);
            File newFileTemp = new File("D://Games/tems/", file);
            try {
                if (file.equals("temp.txt")) {
                    if (newFileTemp.createNewFile()) {
                        writeLogFile(stringBuilder, newFileTemp);
                        System.out.println("Файл " + file + " создан, логи записаны");
                    } else {
                        System.out.println("Файл " + file + " не создан");
                    }
                } else {
                    if (newFile.createNewFile()) {
                        writeLogFile(stringBuilder, newFile);
                        System.out.println("Файл " + file + " создан, логи записаны");
                    } else {
                        System.out.println("Файл " + file + " не создан");
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void writeLogDir(StringBuilder stringBuilder, File newFir) {
        stringBuilder.append("new dir create: ")
                .append(newFir.exists())
                .append("/")
                .append(" fileName: ")
                .append(newFir.getName())
                .append("/")
                .append(" time: ")
                .append(LocalDateTime.now())
                .append("\r\n");
        ;
    }

    public static void writeLogFile(StringBuilder stringBuilder, File newFile) {
        stringBuilder.append("new file create: ")
                .append(newFile.isFile())
                .append("/")
                .append(" fileName: ")
                .append(newFile.getName())
                .append("/")
                .append(" time: ")
                .append(LocalDateTime.now())
                .append("\r\n");
    }
}