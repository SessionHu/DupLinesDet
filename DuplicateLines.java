import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class DuplicateLines {
    
    public static void main(String... args) {
        // 获取文件名及路径
        Path filePath = getFilePath(args);
        // 创建 HashMap 存储每一行及其出现的行号
        Map<String, List<Integer>> lineMap = new HashMap<>();
        // 创建 AtomicInteger 记录当前的行号
        AtomicInteger lineNumber = new AtomicInteger(1);
        try {
            // 检查是否为二进制文件
            if(isBinaryFile(filePath)) {
                // 输出错误并退出
                System.err.println("fatal: 文件 " + filePath + " 是二进制文件");
                System.exit(2);
            }
            // 读取文件
            Files.lines(filePath)
                    // 跳过空行
                    .filter(line -> !line.trim().isEmpty())
                    // 处理每一行
                    .forEach(line -> {
                        // 将当前行及其行号添加到 HashMap 中
                        lineMap.computeIfAbsent(line, k -> new ArrayList<>()).add(lineNumber.getAndIncrement());
                    });
        } catch(IOException e) {
            // 发生异常时输出错误并退出
            System.err.println("fatal: 无法读取文件 " + filePath);
            System.exit(1);
        }
        // 检查是否有重复行
        if(lineMap.values().stream().noneMatch(list -> list.size() > 1)) {
            // 输出提示信息
            System.out.println("info: 没有重复的行");
        } else {
            // 输出重复行及行号
            lineMap.entrySet().stream()
                    .filter(entry -> entry.getValue().size() > 1)
                    .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));
        }
        System.exit(0);
    }
    
    public static Path getFilePath(String... args) {
        try(Scanner scanner = new Scanner(System.in)) {
            // 初始化文件路径变量
            Path inputPath = null;
            // 检测命令行参数
            if(args.length > 0) {
                // 使用第一个参数作为文件名
                inputPath = Paths.get(args[0]);
                // 输出文件名
                System.out.println("File name: " + inputPath);
            } else {
                // 提示输入文件名
                System.out.print("File name: ");
                // 读取输入的文件名
                inputPath = Paths.get(scanner.nextLine());
            }
            // 检查文件名是否为空
            while(inputPath == null || inputPath.toString().trim().isEmpty()) {
                // 输出警告
                System.err.println("warn: 文件名不能为空");
                // 重新提示输入文件名
                System.out.print("File name: ");
                // 读取输入的文件名
                inputPath = Paths.get(scanner.nextLine());
            }
            // 返回文件名及路径
            return inputPath;
        } catch(NoSuchElementException e) {
            // 用户按下 Ctrl+D 的处理
            System.err.println("\nfatal: 文件名不能为空");
            System.exit(1);
            return null; // 用于编译时防止报错
        }
    }
    
    public static boolean isBinaryFile(Path filePath) throws IOException {
        // 使用 Files.newInputStream 方法创建 InputStream 对象, 用于读取文件
        try(InputStream inputStream = Files.newInputStream(filePath)) {
            int ch;
            // 使用 read 方法读取文件中的字符
            while((ch = inputStream.read()) != -1) {
                // 检查是否为非文本字符
                if(ch < 0x09 || (ch > 0x0D && ch < 0x20) || ch == 0x7F) {
                    // 是非文本字符返回true
                    return true;
                }
            }
        }
        // 无非文本字符返回false
        return false;
    }

}
