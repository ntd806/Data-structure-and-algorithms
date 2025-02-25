// +-------------------+------------------------------------------------+---------------------------------------------------------+
// |      Pattern      |                     Ý nghĩa                    |                      Khi nào dùng?                      |
// +-------------------+------------------------------------------------+---------------------------------------------------------+
// | Adapter           | Chuyển đổi giao diện của một lớp thành giao diện khác | Khi muốn tích hợp các lớp không tương thích với nhau    |
// +-------------------+------------------------------------------------+---------------------------------------------------------+
// | Bridge            | Tách phần trừu tượng và phần triển khai riêng biệt  | Khi cần mở rộng độc lập phần giao diện và phần thực thi |
// +-------------------+------------------------------------------------+---------------------------------------------------------+
// | Composite         | Gom nhóm các đối tượng thành cấu trúc dạng cây     | Khi muốn xử lý các đối tượng riêng lẻ và nhóm giống nhau|
// +-------------------+------------------------------------------------+---------------------------------------------------------+
// | Decorator         | Thêm chức năng cho đối tượng mà không thay đổi cấu trúc | Khi cần mở rộng chức năng mà không sửa đổi lớp chính |
// +-------------------+------------------------------------------------+---------------------------------------------------------+
// | Facade            | Cung cấp giao diện đơn giản cho hệ thống phức tạp  | Khi muốn ẩn đi sự phức tạp của hệ thống nội bộ         |
// +-------------------+------------------------------------------------+---------------------------------------------------------+
// | Flyweight         | Giảm thiểu bộ nhớ bằng cách chia sẻ dữ liệu chung | Khi có nhiều đối tượng giống nhau tiêu tốn nhiều bộ nhớ |
// +-------------------+------------------------------------------------+---------------------------------------------------------+
// | Proxy             | Cung cấp đối tượng thay thế để kiểm soát truy cập | Khi cần kiểm soát quyền truy cập hoặc tải chậm đối tượng|
// +-------------------+------------------------------------------------+---------------------------------------------------------+


// 1. Adapter Pattern
// Giải thích:
// Chuyển đổi giao diện của một lớp thành giao diện mà client mong đợi. Giúp các lớp không tương thích có thể làm việc cùng nhau.

// Ứng dụng:

// Tích hợp hệ thống cũ với hệ thống mới.
// Chuyển đổi định dạng dữ liệu giữa các hệ thống khác nhau.
// Ví dụ:

// Giao diện mong muốn
interface MediaPlayer {
    void play(String audioType, String fileName);
}

// Lớp không tương thích
class VLCPlayer {
    public void playVLC(String fileName) {
        System.out.println("Playing VLC file: " + fileName);
    }
}

// Adapter
class MediaAdapter implements MediaPlayer {
    private VLCPlayer vlcPlayer;

    public MediaAdapter() {
        vlcPlayer = new VLCPlayer();
    }

    public void play(String audioType, String fileName) {
        if ("vlc".equalsIgnoreCase(audioType)) {
            vlcPlayer.playVLC(fileName);
        }
    }
}

// Client
class AudioPlayer implements MediaPlayer {
    private MediaAdapter adapter;

    public void play(String audioType, String fileName) {
        if ("mp3".equalsIgnoreCase(audioType)) {
            System.out.println("Playing MP3 file: " + fileName);
        } else if ("vlc".equalsIgnoreCase(audioType)) {
            adapter = new MediaAdapter();
            adapter.play(audioType, fileName);
        }
    }
}

// Sử dụng
public class Main {
    public static void main(String[] args) {
        AudioPlayer player = new AudioPlayer();
        player.play("mp3", "song.mp3");
        player.play("vlc", "video.vlc");
    }
}

// 2. Bridge Pattern
// Giải thích:
// Tách phần trừu tượng và phần triển khai thành hai tầng riêng biệt để chúng có thể thay đổi độc lập.

// Ứng dụng:

// Khi cần mở rộng cả giao diện và chức năng một cách độc lập.
// Hệ thống hỗ trợ đa nền tảng.
// Ví dụ:

// Implementor
interface Device {
    void turnOn();
    void turnOff();
}

// Concrete Implementor
class TV implements Device {
    public void turnOn() {
        System.out.println("TV is ON");
    }

    public void turnOff() {
        System.out.println("TV is OFF");
    }
}

// Abstraction
abstract class Remote {
    protected Device device;

    public Remote(Device device) {
        this.device = device;
    }

    abstract void togglePower();
}

// Refined Abstraction
class BasicRemote extends Remote {
    private boolean on = false;

    public BasicRemote(Device device) {
        super(device);
    }

    public void togglePower() {
        if (on) {
            device.turnOff();
        } else {
            device.turnOn();
        }
        on = !on;
    }
}

// Sử dụng
public class Main {
    public static void main(String[] args) {
        Device tv = new TV();
        Remote remote = new BasicRemote(tv);
        remote.togglePower();  // TV is ON
        remote.togglePower();  // TV is OFF
    }
}

// 3. Composite Pattern
// Giải thích:
// Cho phép xử lý các đối tượng riêng lẻ và tập hợp các đối tượng một cách giống nhau. Thường dùng để xây dựng cấu trúc cây (tree).

// Ứng dụng:

// Hệ thống quản lý file (thư mục chứa các file hoặc thư mục con).
// Tạo menu động.
// Ví dụ:

import java.util.ArrayList;
import java.util.List;

// Component
interface Employee {
    void showEmployeeDetails();
}

// Leaf
class Developer implements Employee {
    private String name;

    public Developer(String name) {
        this.name = name;
    }

    public void showEmployeeDetails() {
        System.out.println("Developer: " + name);
    }
}

// Composite
class Manager implements Employee {
    private List<Employee> employees = new ArrayList<>();

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void showEmployeeDetails() {
        for (Employee emp : employees) {
            emp.showEmployeeDetails();
        }
    }
}

// Sử dụng
public class Main {
    public static void main(String[] args) {
        Developer dev1 = new Developer("John");
        Developer dev2 = new Developer("Jane");

        Manager manager = new Manager();
        manager.addEmployee(dev1);
        manager.addEmployee(dev2);

        manager.showEmployeeDetails();
    }
}

// 4. Decorator Pattern
// Giải thích:
// Thêm chức năng cho đối tượng mà không thay đổi cấu trúc của nó.

// Ứng dụng:

// Thêm các tính năng động vào đối tượng (ví dụ: scroll bar trong GUI).
// Ghi log hoặc kiểm tra bảo mật.
// Ví dụ:

// Component
interface Coffee {
    String getDescription();
    double cost();
}

// Concrete Component
class BasicCoffee implements Coffee {
    public String getDescription() {
        return "Basic Coffee";
    }

    public double cost() {
        return 2.0;
    }
}

// Decorator
abstract class CoffeeDecorator implements Coffee {
    protected Coffee coffee;

    public CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }
}

// Concrete Decorators
class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    public String getDescription() {
        return coffee.getDescription() + ", Milk";
    }

    public double cost() {
        return coffee.cost() + 0.5;
    }
}

class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    public String getDescription() {
        return coffee.getDescription() + ", Sugar";
    }

    public double cost() {
        return coffee.cost() + 0.2;
    }
}

// Sử dụng
public class Main {
    public static void main(String[] args) {
        Coffee coffee = new BasicCoffee();
        coffee = new MilkDecorator(coffee);
        coffee = new SugarDecorator(coffee);

        System.out.println(coffee.getDescription() + " - $" + coffee.cost());
    }
}

// 5. Facade Pattern
// Giải thích:
// Cung cấp giao diện đơn giản để che giấu hệ thống phức tạp bên trong.

// Ứng dụng:

// Ẩn sự phức tạp của hệ thống bên trong.
// Xây dựng giao diện người dùng đơn giản.
// Ví dụ:

// Subsystems
class CPU {
    public void start() {
        System.out.println("CPU started");
    }
}

class Memory {
    public void load() {
        System.out.println("Memory loaded");
    }
}

class HardDrive {
    public void read() {
        System.out.println("Reading from Hard Drive");
    }
}

// Facade
class Computer {
    private CPU cpu;
    private Memory memory;
    private HardDrive hardDrive;

    public Computer() {
        cpu = new CPU();
        memory = new Memory();
        hardDrive = new HardDrive();
    }

    public void start() {
        cpu.start();
        memory.load();
        hardDrive.read();
    }
}

// Sử dụng
public class Main {
    public static void main(String[] args) {
        Computer computer = new Computer();
        computer.start();
    }
}

// 6. Flyweight Pattern
// Giải thích:
// Giảm thiểu việc sử dụng bộ nhớ bằng cách chia sẻ các đối tượng giống nhau.

// Ứng dụng:

// Game có nhiều đối tượng giống nhau (cây, lính, ...).
// Hệ thống text editor (chữ cái giống nhau chỉ lưu một lần).
// Ví dụ:

import java.util.HashMap;
import java.util.Map;

// Flyweight
class TreeType {
    private String name;
    private String color;

    public TreeType(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public void display() {
        System.out.println("Tree: " + name + ", Color: " + color);
    }
}

// Flyweight Factory
class TreeFactory {
    private static final Map<String, TreeType> treeTypes = new HashMap<>();

    public static TreeType getTreeType(String name, String color) {
        String key = name + "-" + color;
        treeTypes.putIfAbsent(key, new TreeType(name, color));
        return treeTypes.get(key);
    }
}

// Sử dụng
public class Main {
    public static void main(String[] args) {
        TreeType oakTree = TreeFactory.getTreeType("Oak", "Green");
        TreeType pineTree = TreeFactory.getTreeType("Pine", "Dark Green");

        oakTree.display();
        pineTree.display();
    }
}

// 7. Proxy Pattern
// Giải thích:
// Cung cấp một đối tượng thay thế để kiểm soát quyền truy cập đến đối tượng thực.

// Ứng dụng:

// Bảo vệ truy cập.
// Tải chậm (Lazy initialization).
// Ví dụ:

// Subject
interface Image {
    void display();
}

// Real Subject
class RealImage implements Image {
    private String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromDisk();
    }

    private void loadFromDisk() {
        System.out.println("Loading " + fileName);
    }

    public void display() {
        System.out.println("Displaying " + fileName);
    }
}

// Proxy
class ProxyImage implements Image {
    private RealImage realImage;
    private String fileName;

    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    public void display() {
        if (realImage == null) {
            realImage = new RealImage(fileName);
        }
        realImage.display();
    }
}

// Sử dụng
public class Main {
    public static void main(String[] args) {
        Image image = new ProxyImage("photo.jpg");
        image.display();  // Lần đầu sẽ load và hiển thị
        image.display();  // Lần sau chỉ hiển thị
    }
}
