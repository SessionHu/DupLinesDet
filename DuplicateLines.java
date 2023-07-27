import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class DuplicateLines {
    public static void main(String[] args) {
        // 创建 Scanner 对象，用于从控制台读取输入
        Scanner scanner = new Scanner(System.in);
        // 提示输入文件名
        System.out.print("File name: ");
        // 读取输入的文件名
        String fileName = scanner.nextLine();
        // 检查文件名是否为空
        if (fileName == null || fileName.trim().isEmpty()) {
            // 如果文件名为空，则输出错误并退出
            System.err.println("fatal: 文件名不能为空");
            System.exit(1);
        }
        // 创建 HashMap 存储每一行及其出现的行号
        Map<String, List<Integer>> lineMap = new HashMap<>();
        // 创建 AtomicInteger 记录当前的行号
        AtomicInteger lineNumber = new AtomicInteger(1);
        try {
            // 读取文件
            Files.lines(Paths.get(fileName))
                    // 跳过空行
                    .filter(line -> !line.trim().isEmpty())
                    // 处理每一行
                    .forEach(line -> {
                        // 将当前行及其行号添加到 HashMap 中
                        lineMap.computeIfAbsent(line, k -> new ArrayList<>()).add(lineNumber.getAndIncrement());
                    });
        } catch (IOException e) {
            // 发生异常时输出错误并退出
            System.err.println("fatal: 无法读取文件 " + fileName);
            System.exit(1);
        }
        // 检查是否有重复行
        if (lineMap.values().stream().noneMatch(list -> list.size() > 1)) {
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
}
