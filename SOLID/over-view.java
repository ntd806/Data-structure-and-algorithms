// SOLID là 5 nguyên lý thiết kế hướng đối tượng quan trọng giúp viết mã dễ bảo trì, mở rộng và dễ hiểu hơn. Đây là viết tắt của:

// S - Single Responsibility Principle (Nguyên lý trách nhiệm duy nhất)
// O - Open/Closed Principle (Nguyên lý mở đóng)
// L - Liskov Substitution Principle (Nguyên lý thay thế Liskov)
// I - Interface Segregation Principle (Nguyên lý phân tách giao diện)
// D - Dependency Inversion Principle (Nguyên lý đảo ngược sự phụ thuộc)

// 1. Single Responsibility Principle (SRP)
// Một lớp chỉ nên có một lý do để thay đổi – tức là nó chỉ nên đảm nhiệm một trách nhiệm duy nhất.

// Ví dụ:
// Vi phạm SRP: Class này vừa lưu dữ liệu vừa hiển thị dữ liệu
class Employee {
    public void saveToDatabase() {
        // Lưu nhân viên vào database
    }

    public void generateReport() {
        // Tạo báo cáo cho nhân viên
    }
}

// Tuân thủ SRP:
class Employee {
    private String name;
    private String position;
    
    // Constructor và các getter/setter
}

class EmployeeRepository {
    public void save(Employee employee) {
        // Lưu dữ liệu vào database
    }
}

class ReportGenerator {
    public void generate(Employee employee) {
        // Tạo báo cáo
    }
}

// 2. Open/Closed Principle (OCP)
// Một lớp nên được mở rộng nhưng không được sửa đổi. Nghĩa là bạn nên mở rộng hành vi của class thay vì sửa đổi mã nguồn ban đầu.

// Ví dụ:
// Vi phạm OCP
class AreaCalculator {
    public double calculateArea(Object shape) {
        if (shape instanceof Circle) {
            Circle circle = (Circle) shape;
            return Math.PI * circle.radius * circle.radius;
        } else if (shape instanceof Rectangle) {
            Rectangle rectangle = (Rectangle) shape;
            return rectangle.length * rectangle.width;
        }
        return 0;
    }
}

// Tuân thủ OCP
interface Shape {
    double calculateArea();
}

class Circle implements Shape {
    double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}

class Rectangle implements Shape {
    double length, width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    public double calculateArea() {
        return length * width;
    }
}

class AreaCalculator {
    public double calculateArea(Shape shape) {
        return shape.calculateArea();
    }
}

// 3. Liskov Substitution Principle (LSP)
// Lớp con nên có thể thay thế lớp cha mà không làm thay đổi tính đúng đắn của chương trình.

// Ví dụ:
// Vi phạm LSP
class Bird {
    public void fly() {
        System.out.println("Bird is flying");
    }
}

class Ostrich extends Bird {
    @Override
    public void fly() {
        throw new UnsupportedOperationException("Ostrich can't fly");
    }
}

// Tuân thủ LSP
abstract class Bird {
    public abstract void move();
}

class Sparrow extends Bird {
    @Override
    public void move() {
        System.out.println("Sparrow flies");
    }
}

class Ostrich extends Bird {
    @Override
    public void move() {
        System.out.println("Ostrich walks");
    }
}

// 4. Interface Segregation Principle (ISP)
// Không nên ép buộc các lớp phải implement các phương thức mà chúng không sử dụng.

// Ví dụ:
// Vi phạm ISP
interface Worker {
    void work();
    void eat();
}

class Robot implements Worker {
    public void work() {
        System.out.println("Robot is working");
    }

    public void eat() {
        // Robot không cần ăn
    }
}

// Tuân thủ ISP
interface Workable {
    void work();
}

interface Eatable {
    void eat();
}

class Human implements Workable, Eatable {
    public void work() {
        System.out.println("Human is working");
    }

    public void eat() {
        System.out.println("Human is eating");
    }
}

class Robot implements Workable {
    public void work() {
        System.out.println("Robot is working");
    }
}

// 5. Dependency Inversion Principle (DIP)
// Các mô-đun cấp cao không nên phụ thuộc vào các mô-đun cấp thấp. Cả hai nên phụ thuộc vào abstraction (giao diện).

// Ví dụ:
// Vi phạm DIP
class LightBulb {
    public void turnOn() {
        System.out.println("Bulb turned on");
    }

    public void turnOff() {
        System.out.println("Bulb turned off");
    }
}

class Switch {
    private LightBulb bulb;

    public Switch(LightBulb bulb) {
        this.bulb = bulb;
    }

    public void operate() {
        bulb.turnOn();
    }
}

// Tuân thủ DIP
interface Switchable {
    void turnOn();
    void turnOff();
}

class LightBulb implements Switchable {
    public void turnOn() {
        System.out.println("Bulb turned on");
    }

    public void turnOff() {
        System.out.println("Bulb turned off");
    }
}

class Switch {
    private Switchable device;

    public Switch(Switchable device) {
        this.device = device;
    }

    public void operate() {
        device.turnOn();
    }
}
// 🔑 Tóm tắt ngắn gọn:
// S: Mỗi class chỉ làm một nhiệm vụ duy nhất.
// O: Dễ dàng mở rộng chức năng mà không sửa đổi mã gốc.
// L: Lớp con thay thế lớp cha không gây lỗi.
// I: Tách nhỏ giao diện, không ép buộc implement phương thức không cần thiết.
// D: Phụ thuộc vào abstraction, không phụ thuộc vào chi tiết.
