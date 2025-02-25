// +-------------------------------+----------------------------------------------------------------+--------------------------------------------------------------+
// | Pattern                       | Ý nghĩa                                                        | Khi nào dùng?                                                |
// +-------------------------------+----------------------------------------------------------------+--------------------------------------------------------------+
// | Observer                      | Tự động thông báo cho các đối tượng liên quan khi có thay đổi  | Hệ thống thông báo, Event Listener                           |
// +-------------------------------+----------------------------------------------------------------+--------------------------------------------------------------+
// | Strategy                      | Thay đổi thuật toán thực thi tại runtime                       | Nhiều phương thức xử lý khác nhau cho một hành động          |
// +-------------------------------+----------------------------------------------------------------+--------------------------------------------------------------+
// | Command                       | Đóng gói yêu cầu thành đối tượng                               | Undo/Redo, Hệ thống điều khiển                               |
// +-------------------------------+----------------------------------------------------------------+--------------------------------------------------------------+
// | Chain of Responsibility       | Chuyển yêu cầu qua chuỗi xử lý                                 | Xử lý yêu cầu tuần tự, Middleware                            |
// +-------------------------------+----------------------------------------------------------------+--------------------------------------------------------------+
// | Mediator                      | Giảm sự phụ thuộc giữa các đối tượng                           | Hệ thống Chat, Tương tác phức tạp giữa nhiều thành phần      |
// +-------------------------------+----------------------------------------------------------------+--------------------------------------------------------------+
// | State                         | Thay đổi hành vi dựa trên trạng thái nội bộ                    | Máy trạng thái, Giao diện đa chế độ                          |
// +-------------------------------+----------------------------------------------------------------+--------------------------------------------------------------+
// | Visitor                       | Thêm chức năng mà không sửa đổi lớp hiện có                    | Trình biên dịch, Xử lý dữ liệu trên cấu trúc phức tạp        |
// +-------------------------------+----------------------------------------------------------------+--------------------------------------------------------------+
// | Template Method               | Xác định cấu trúc thuật toán chung                             | Các thao tác theo khuôn mẫu (template)                       |
// +-------------------------------+----------------------------------------------------------------+--------------------------------------------------------------+

// 1. Observer Pattern
// Giải thích:
// Tạo mối quan hệ giữa các đối tượng, khi một đối tượng thay đổi trạng thái thì tất cả các đối tượng phụ thuộc sẽ được thông báo tự động.

// Ứng dụng:

// Hệ thống thông báo (Notification system).
// Event handling trong GUI.
// Ví dụ:

import java.util.*;
// Subject
class Subject {
    private List<Observer> observers = new ArrayList<>();
    private int state;

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void setState(int state) {
        this.state = state;
        notifyAllObservers();
    }

    public int getState() {
        return state;
    }

    private void notifyAllObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}

// Observer
abstract class Observer {
    protected Subject subject;
    public abstract void update();
}

// Concrete Observer
class BinaryObserver extends Observer {
    public BinaryObserver(Subject subject) {
        this.subject = subject;
        subject.attach(this);
    }

    public void update() {
        System.out.println("Binary: " + Integer.toBinaryString(subject.getState()));
    }
}

// Sử dụng
public class Main {
    public static void main(String[] args) {
        Subject subject = new Subject();
        new BinaryObserver(subject);

        subject.setState(10);
        subject.setState(20);
    }
}

// 2. Strategy Pattern
// Giải thích:
// Cho phép thay đổi thuật toán hoặc hành vi của một đối tượng tại runtime mà không thay đổi mã nguồn của nó.

// Ứng dụng:

// Hệ thống thanh toán (PayPal, Credit Card, etc.).
// Trò chơi có nhiều chiến thuật khác nhau.
// Ví dụ:

// Strategy Interface
interface PaymentStrategy {
    void pay(int amount);
}

// Concrete Strategies
class CreditCardPayment implements PaymentStrategy {
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using Credit Card");
    }
}

class PayPalPayment implements PaymentStrategy {
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using PayPal");
    }
}

// Context
class ShoppingCart {
    private PaymentStrategy paymentStrategy;

    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.paymentStrategy = strategy;
    }

    public void checkout(int amount) {
        paymentStrategy.pay(amount);
    }
}

// Sử dụng
public class Main {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        cart.setPaymentStrategy(new CreditCardPayment());
        cart.checkout(500);

        cart.setPaymentStrategy(new PayPalPayment());
        cart.checkout(200);
    }
}

// 3. Command Pattern
// Giải thích:
// Biến các yêu cầu thành các đối tượng, từ đó dễ dàng lưu trữ, truyền đạt và thực thi lệnh.

// Ứng dụng:

// Hệ thống Undo/Redo.
// Remote Control Systems.
// Ví dụ:

// Command Interface
interface Command {
    void execute();
}

// Receiver
class Light {
    public void turnOn() {
        System.out.println("Light is ON");
    }

    public void turnOff() {
        System.out.println("Light is OFF");
    }
}

// Concrete Command
class TurnOnCommand implements Command {
    private Light light;

    public TurnOnCommand(Light light) {
        this.light = light;
    }

    public void execute() {
        light.turnOn();
    }
}

class TurnOffCommand implements Command {
    private Light light;

    public TurnOffCommand(Light light) {
        this.light = light;
    }

    public void execute() {
        light.turnOff();
    }
}

// Invoker
class RemoteControl {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        command.execute();
    }
}

// Sử dụng
public class Main {
    public static void main(String[] args) {
        Light light = new Light();
        Command turnOn = new TurnOnCommand(light);
        Command turnOff = new TurnOffCommand(light);

        RemoteControl remote = new RemoteControl();
        remote.setCommand(turnOn);
        remote.pressButton();

        remote.setCommand(turnOff);
        remote.pressButton();
    }
}

// 4. Chain of Responsibility Pattern
// Giải thích:
// Chuyển yêu cầu dọc theo một chuỗi các đối tượng xử lý cho đến khi được xử lý.

// Ứng dụng:

// Xử lý lỗi theo chuỗi.
// Hệ thống xử lý yêu cầu trong ứng dụng web.
// Ví dụ:

// Handler
abstract class Logger {
    public static int INFO = 1;
    public static int ERROR = 2;
    protected int level;
    protected Logger nextLogger;

    public void setNextLogger(Logger nextLogger) {
        this.nextLogger = nextLogger;
    }

    public void logMessage(int level, String message) {
        if (this.level <= level) {
            write(message);
        }
        if (nextLogger != null) {
            nextLogger.logMessage(level, message);
        }
    }

    protected abstract void write(String message);
}

// Concrete Handlers
class InfoLogger extends Logger {
    public InfoLogger(int level) {
        this.level = level;
    }

    protected void write(String message) {
        System.out.println("INFO: " + message);
    }
}

class ErrorLogger extends Logger {
    public ErrorLogger(int level) {
        this.level = level;
    }

    protected void write(String message) {
        System.out.println("ERROR: " + message);
    }
}

// Sử dụng
public class Main {
    private static Logger getChainOfLoggers() {
        Logger errorLogger = new ErrorLogger(Logger.ERROR);
        Logger infoLogger = new InfoLogger(Logger.INFO);

        errorLogger.setNextLogger(infoLogger);
        return errorLogger;
    }

    public static void main(String[] args) {
        Logger loggerChain = getChainOfLoggers();

        loggerChain.logMessage(Logger.INFO, "This is an information.");
        loggerChain.logMessage(Logger.ERROR, "This is an error message.");
    }
}

// 5. Mediator Pattern
// Giải thích:
// Tạo một đối tượng trung gian để giảm sự phụ thuộc giữa các đối tượng khác nhau.

// Ứng dụng:

// Chat Room.
// Hệ thống giao tiếp phức tạp.
// Ví dụ:

import java.util.*;
// Mediator Interface
interface ChatMediator {
    void sendMessage(String message, User user);
    void addUser(User user);
}

// Concrete Mediator
class ChatRoom implements ChatMediator {
    private List<User> users = new ArrayList<>();

    public void addUser(User user) {
        users.add(user);
    }

    public void sendMessage(String message, User user) {
        for (User u : users) {
            if (u != user) {
                u.receive(message);
            }
        }
    }
}

// Colleague
abstract class User {
    protected ChatMediator mediator;
    protected String name;

    public User(ChatMediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }

    public abstract void send(String message);
    public abstract void receive(String message);
}

// Concrete Colleague
class ConcreteUser extends User {
    public ConcreteUser(ChatMediator mediator, String name) {
        super(mediator, name);
    }

    public void send(String message) {
        System.out.println(this.name + " sends: " + message);
        mediator.sendMessage(message, this);
    }

    public void receive(String message) {
        System.out.println(this.name + " receives: " + message);
    }
}

// Sử dụng
public class Main {
    public static void main(String[] args) {
        ChatMediator chatRoom = new ChatRoom();

        User user1 = new ConcreteUser(chatRoom, "Alice");
        User user2 = new ConcreteUser(chatRoom, "Bob");

        chatRoom.addUser(user1);
        chatRoom.addUser(user2);

        user1.send("Hello, Bob!");
        user2.send("Hi, Alice!");
    }
}

// 6. State Pattern
// Giải thích:
// Cho phép một đối tượng thay đổi hành vi khi trạng thái nội bộ của nó thay đổi.

// Ứng dụng:

// Máy bán hàng tự động (vending machine).
// Quản lý trạng thái trò chơi (chơi, tạm dừng, kết thúc).
// Ví dụ:

// State interface
interface State {
    void doAction(Context context);
}

// Concrete States
class StartState implements State {
    public void doAction(Context context) {
        System.out.println("Player is in start state");
        context.setState(this);
    }

    public String toString() {
        return "Start State";
    }
}

class StopState implements State {
    public void doAction(Context context) {
        System.out.println("Player is in stop state");
        context.setState(this);
    }

    public String toString() {
        return "Stop State";
    }
}

// Context
class Context {
    private State state;

    public Context() {
        state = null;
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }
}

// Sử dụng
public class Main {
    public static void main(String[] args) {
        Context context = new Context();

        State startState = new StartState();
        startState.doAction(context);
        System.out.println(context.getState());

        State stopState = new StopState();
        stopState.doAction(context);
        System.out.println(context.getState());
    }
}

// 7. Visitor Pattern
// Giải thích:
// Cho phép thêm các thao tác mới vào một đối tượng mà không thay đổi lớp của nó.

// Ứng dụng:

// Trình biên dịch (compiler).
// Xử lý dữ liệu trên cấu trúc phức tạp như cây hoặc đồ thị.
// Ví dụ:

// Visitor interface
interface Visitor {
    void visit(Book book);
    void visit(Fruit fruit);
}

// Element interface
interface ItemElement {
    void accept(Visitor visitor);
}

// Concrete Elements
class Book implements ItemElement {
    private int price;
    private String isbn;

    public Book(int price, String isbn) {
        this.price = price;
        this.isbn = isbn;
    }

    public int getPrice() {
        return price;
    }

    public String getIsbn() {
        return isbn;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

class Fruit implements ItemElement {
    private int pricePerKg;
    private int weight;
    private String name;

    public Fruit(int priceKg, int weight, String name) {
        this.pricePerKg = priceKg;
        this.weight = weight;
        this.name = name;
    }

    public int getPricePerKg() {
        return pricePerKg;
    }

    public int getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

// Concrete Visitor
class ShoppingCartVisitorImpl implements Visitor {
    public void visit(Book book) {
        System.out.println("Book ISBN: " + book.getIsbn() + " Cost: " + book.getPrice());
    }

    public void visit(Fruit fruit) {
        int cost = fruit.getPricePerKg() * fruit.getWeight();
        System.out.println(fruit.getName() + " Cost: " + cost);
    }
}

// Sử dụng
public class Main {
    public static void main(String[] args) {
        ItemElement[] items = new ItemElement[]{
            new Book(50, "1234"), new Fruit(10, 2, "Banana")
        };

        Visitor visitor = new ShoppingCartVisitorImpl();
        for (ItemElement item : items) {
            item.accept(visitor);
        }
    }
}

// 8. Template Method Pattern
// Giải thích:
// Xác định một khung của một thuật toán trong phương thức, cho phép các lớp con định nghĩa lại một số bước mà không thay đổi cấu trúc của thuật toán.

// Ứng dụng:

// Tạo template cho các thao tác có cấu trúc giống nhau.
// Hệ thống xử lý giao dịch (transaction processing).
// Ví dụ:

// Abstract Class
abstract class Game {
    abstract void initialize();
    abstract void startPlay();
    abstract void endPlay();

    // Template method
    public final void play() {
        initialize();
        startPlay();
        endPlay();
    }
}

// Concrete Classes
class Cricket extends Game {
    void initialize() {
        System.out.println("Cricket Game Initialized!");
    }

    void startPlay() {
        System.out.println("Cricket Game Started. Enjoy the game!");
    }

    void endPlay() {
        System.out.println("Cricket Game Finished!");
    }
}

class Football extends Game {
    void initialize() {
        System.out.println("Football Game Initialized!");
    }

    void startPlay() {
        System.out.println("Football Game Started. Enjoy the game!");
    }

    void endPlay() {
        System.out.println("Football Game Finished!");
    }
}

// Sử dụng
public class Main {
    public static void main(String[] args) {
        Game game = new Cricket();
        game.play();

        System.out.println();

        game = new Football();
        game.play();
    }
}