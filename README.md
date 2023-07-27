# DupLinesDet

- 一个可以快速检测文件中是否存在重复行的工具


## 使用方法

1.  在[Releases](https://github.com/SessionHu/DupLinesDet/releases/)处下载最新的 `JAR` 包

2.  在含有本项目的 `JAR` 包的目录下运行下面任意命令, *`DuplicateLines.jar` 为 `JAR` 包文件名, `file` 为被检测文件*
    ```bash
    java -jar 'DuplicateLines.jar'
    java -jar 'DuplicateLines.jar'  'file'
    ```

3.  若仅显示 `File name: ` 请输入被检测文件名


## 提示信息

- 正常退出
  <details>
  <summary>退出代码 - 0</summary>

  ```
  info: 没有重复的行
  ```

  </details>
    
- 读取失败
  - 未输入文件名
    <details>
    <summary>退出代码 - 1</summary>
    <p>
      
    ```
    fatal: 文件名不能为空
    ```
    
    </p>
    </details>
  - 文件名或路径有误
    <details>
    <summary>退出代码 - 1</summary>
    <p>
      
    ```
    fatal: 无法读取文件
    ```
    
    </p>
    </details>

## 许可证

- 本项目使用 `MIT License` 许可证
