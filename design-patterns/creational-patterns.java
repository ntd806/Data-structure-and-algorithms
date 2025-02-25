// +-------------------+------------------------------------------+-----------------------------------------------+
// |      Pattern      |                 Ý nghĩa                  |                  Khi nào dùng?                |
// +-------------------+------------------------------------------+-----------------------------------------------+
// | Singleton         | Đảm bảo 1 lớp chỉ có 1 instance duy nhất | Log, kết nối DB, cấu hình ứng dụng            |
// +-------------------+------------------------------------------+-----------------------------------------------+
// | Factory Method    | Tạo đối tượng mà không chỉ rõ lớp cụ thể | Khi đối tượng được quyết định tại runtime     |
// +-------------------+------------------------------------------+-----------------------------------------------+
// | Abstract Factory  | Tạo nhóm đối tượng liên quan             | Đa nền tảng, UI                               |
// +-------------------+------------------------------------------+-----------------------------------------------+
// | Builder           | Xây dựng đối tượng phức tạp theo từng bước| Khi đối tượng có nhiều cấu hình phức tạp      |
// +-------------------+------------------------------------------+-----------------------------------------------+
// | Prototype         | Sao chép đối tượng hiện có               | Tạo bản sao nhanh chóng, tiết kiệm tài nguyên |
// +-------------------+------------------------------------------+-----------------------------------------------+


// 1. Singleton Pattern
// Giải thích:
// Đảm bảo rằng một lớp chỉ có một đối tượng duy nhất và cung cấp một điểm truy cập toàn cục tới nó.

// Ứng dụng:

// Quản lý kết nối cơ sở dữ liệu (Database connection).
// Logger (ghi log toàn cục).
// Cấu hình ứng dụng (Configuration manager).
// Ví dụ:
public class Singleton {
    private static Singleton instance;

    private Singleton() {}  // Constructor private để ngăn việc khởi tạo từ bên ngoài

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public void showMessage() {
        System.out.println("Hello from Singleton!");
    }
}

// Sử dụng
public class Main {
    public static void main(String[] args) {
        Singleton singleton = Singleton.getInstance();
        singleton.showMessage();
    }
}

// 2. Factory Method Pattern
// Giải thích:
// Cung cấp một phương pháp để tạo đối tượng mà không chỉ rõ lớp cụ thể sẽ được khởi tạo. Lớp con sẽ quyết định đối tượng nào sẽ được tạo.

// Ứng dụng:

// Khi không biết trước đối tượng chính xác sẽ được tạo.
// Khi đối tượng cần được quyết định dựa trên dữ liệu đầu vào.
// Ví dụ:

// Interface sản phẩm
interface Animal {
    void speak();
}

// Các lớp cụ thể
class Dog implements Animal {
    public void speak() {
        System.out.println("Woof!");
    }
}

class Cat implements Animal {
    public void speak() {
        System.out.println("Meow!");
    }
}

// Factory Method
class AnimalFactory {
    public Animal getAnimal(String type) {
        if ("dog".equalsIgnoreCase(type)) return new Dog();
        else if ("cat".equalsIgnoreCase(type)) return new Cat();
        return null;
    }
}

// Sử dụng
public class Main {
    public static void main(String[] args) {
        AnimalFactory factory = new AnimalFactory();
        Animal animal = factory.getAnimal("dog");
        animal.speak();  // Output: Woof!
    }
}

// 3. Abstract Factory Pattern
// Giải thích:
// Cung cấp một giao diện để tạo các họ (families) đối tượng liên quan hoặc phụ thuộc mà không cần chỉ rõ lớp cụ thể.

// Ứng dụng:

// Tạo các hệ sinh thái đối tượng tương thích với nhau (UI, các thành phần giao diện).
// Cấu hình đa nền tảng (Windows, MacOS, Linux).
// Ví dụ:
// Các interface sản phẩm
interface Button {
    void paint();
}

interface Checkbox {
    void paint();
}

// Các lớp sản phẩm cụ thể cho Windows
class WindowsButton implements Button {
    public void paint() {
        System.out.println("Rendering a Windows button");
    }
}

class WindowsCheckbox implements Checkbox {
    public void paint() {
        System.out.println("Rendering a Windows checkbox");
    }
}

// Các lớp sản phẩm cụ thể cho Mac
class MacButton implements Button {
    public void paint() {
        System.out.println("Rendering a Mac button");
    }
}

class MacCheckbox implements Checkbox {
    public void paint() {
        System.out.println("Rendering a Mac checkbox");
    }
}

// Abstract Factory
interface GUIFactory {
    Button createButton();
    Checkbox createCheckbox();
}

// Concrete Factory cho Windows
class WindowsFactory implements GUIFactory {
    public Button createButton() {
        return new WindowsButton();
    }
    public Checkbox createCheckbox() {
        return new WindowsCheckbox();
    }
}

// Concrete Factory cho Mac
class MacFactory implements GUIFactory {
    public Button createButton() {
        return new MacButton();
    }
    public Checkbox createCheckbox() {
        return new MacCheckbox();
    }
}

// Sử dụng
public class Main {
    public static void main(String[] args) {
        GUIFactory factory = new WindowsFactory();
        Button button = factory.createButton();
        button.paint();  // Output: Rendering a Windows button
    }
}

// 4. Builder Pattern
// Giải thích:
// Tách việc xây dựng một đối tượng phức tạp ra khỏi biểu diễn của nó, cho phép từng bước xây dựng đối tượng.

// Ứng dụng:

// Khi đối tượng có nhiều tùy chọn cấu hình phức tạp.
// Dùng khi đối tượng bất biến (immutable).
// Ví dụ:

class Car {
    private String engine;
    private int wheels;
    private boolean airConditioner;

    private Car(CarBuilder builder) {
        this.engine = builder.engine;
        this.wheels = builder.wheels;
        this.airConditioner = builder.airConditioner;
    }

    public static class CarBuilder {
        private String engine;
        private int wheels;
        private boolean airConditioner;

        public CarBuilder setEngine(String engine) {
            this.engine = engine;
            return this;
        }

        public CarBuilder setWheels(int wheels) {
            this.wheels = wheels;
            return this;
        }

        public CarBuilder setAirConditioner(boolean airConditioner) {
            this.airConditioner = airConditioner;
            return this;
        }

        public Car build() {
            return new Car(this);
        }
    }

    @Override
    public String toString() {
        return "Car with " + engine + " engine, " + wheels + " wheels, AC: " + airConditioner;
    }
}

// Sử dụng
public class Main {
    public static void main(String[] args) {
        Car car = new Car.CarBuilder()
                .setEngine("V8")
                .setWheels(4)
                .setAirConditioner(true)
                .build();
        System.out.println(car);
    }
}

// 5. Prototype Pattern
// Giải thích:
// Tạo đối tượng mới bằng cách sao chép một đối tượng hiện có thay vì khởi tạo mới từ đầu.

// Ứng dụng:

// Khi việc khởi tạo đối tượng phức tạp hoặc tốn kém tài nguyên.
// Tạo nhiều đối tượng giống nhau một cách nhanh chóng.
// Ví dụ:

interface Prototype extends Cloneable {
    Prototype clone();
}

class ConcretePrototype implements Prototype {
    private String data;

    public ConcretePrototype(String data) {
        this.data = data;
    }

    @Override
    public Prototype clone() {
        return new ConcretePrototype(this.data);
    }

    @Override
    public String toString() {
        return "Prototype with data: " + data;
    }
}

// Sử dụng
public class Main {
    public static void main(String[] args) {
        ConcretePrototype prototype1 = new ConcretePrototype("Original");
        ConcretePrototype prototype2 = (ConcretePrototype) prototype1.clone();

        System.out.println(prototype1);  // Output: Prototype with data: Original
        System.out.println(prototype2);  // Output: Prototype with data: Original
    }
}