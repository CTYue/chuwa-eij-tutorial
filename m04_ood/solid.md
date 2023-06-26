# OOD

## Composition vs. Aggregation vs Inheritance

Java 中的组合(Composition)、聚合(Aggregation)和继承(Inheritance)是面向对象编程的三种主要关系。以下是这三种关系的区别：

1. **组合(Composition)**：组合是一种强关系，表示"是"的关系。它是一种包含关系，当包含的对象被销毁时，被包含的对象也会被销毁。比如一个车(Composite)由引擎(Part)组成，当车被销毁时，引擎也随之被销毁。

2. **聚合(Aggregation)**：聚合是一种弱关系，表示"有"的关系。它也是一种包含关系，但当包含的对象被销毁时，被包含的对象不会被销毁。比如一个班级(Aggregate)有学生，但当班级解散时，学生依然存在。

3. **继承(Inheritance)**：继承表示"是一种"的关系。它允许子类继承父类的属性和方法。比如狗(Dog)是一种动物(Animal)，狗继承了动物的一些共有的属性和行为。

如何选择这三种关系取决于你所处理的问题和你的设计需求。通常，组合和聚合用于表达对象之间的关系，继承用于表达对象的种类和分类。

以下是代码示例：

```java
// 组合关系
class Engine {
    // ...
}

class Car {
    // 引擎是车的一部分
    private Engine engine;
    Car() {
        engine = new Engine();
    }
    // ...
}

// 聚合关系
class Student {
    // ...
}

class Classroom {
    // 学生是班级的一部分，但是班级解散后，学生依然存在
    private List<Student> students;
    Classroom() {
        students = new ArrayList<>();
    }
    // ...
}

// 继承关系
class Animal {
    // ...
}

class Dog extends Animal {
    // 狗是一种动物
    // ...
}
```

以上代码展示了组合、聚合和继承关系的基本概念和使用方式。需要注意的是，这些关系并不是互相排斥的，而是可以结合使用的。例如，你可以在一个使用了聚合关系的类中使用组合关系，或者在一个继承关系的类中使用聚合关系。

## Solid

SOLID 是五个面向对象编程和设计的基本原则的首字母缩略词，它们分别是：

1. **单一功能原则（Single Responsibility Principle, SRP）**：一个类应该只有一个引起它变化的原因。这意味着一个类只应该有一项工作。

   例如：

    ```java
    class User {
        String name;
        String email;
        // ... 
    }

    class UserManager {
        void createUser(User user) {
            // 创建用户
        }
    }
    ```

   在上面的例子中，`User` 类只是一个纯数据类，而 `UserManager` 是处理创建用户的逻辑。每个类都只负责一项任务。

2. **开放封闭原则（Open-Closed Principle, OCP）**：软件实体（类，模块，函数等等）应该可以扩展，但是不可修改。

   例如：

    ```java
    interface Shape {
        double area();
    }

    class Rectangle implements Shape {
        double length;
        double width;

        // ...

        public double area() {
            return length * width;
        }
    }

    class Circle implements Shape {
        double radius;

        // ...

        public double area() {
            return Math.PI * Math.pow(radius, 2);
        }
    }

    class AreaCalculator {
        double totalArea(Shape[] shapes) {
            double total = 0;
            for(Shape shape : shapes) {
                total += shape.area();
            }
            return total;
        }
    }
    ```

   在这个例子中，如果我们想添加更多的形状，我们只需创建一个实现了 `Shape` 接口的新类。我们无需修改 `AreaCalculator`。

3. **里氏替换原则（Liskov Substitution Principle, LSP）**：子类型必须能够替换掉它们的基类型。

   例如：

    ```java
    class Bird {
        void fly() {
            // ...
        }
    }

    class Duck extends Bird {
        // ...
    }
    ```

   这个例子中，`Duck` 是 `Bird` 的子类型。因为鸭子（Duck）是鸟（Bird），所以在任何需要鸟的地方，我们都可以使用鸭子来代替。

4. **接口隔离原则（Interface Segregation Principle, ISP）**：客户端不应该强迫依赖于它们不使用的接口。

   例如：

    ```java
    interface Flyable {
        void fly();
    }

    interface Quackable {
        void quack();
    }

    class Duck implements Flyable, Quackable {
        public void fly() {
            // ...
        }

        public void quack() {
            // ...
        }
    }
    ```

   这个例子中，我们将飞行（Flyable）和嘎嘎叫（Quackable）的行为分离到各自的接口中，而不是将它们放在一个大接口中。这样，任何不需要飞行或嘎嘎叫行为的类都可以避免
依赖这些方法。
5. **依赖反转原则（Dependency Inversion Principle, DIP）**：高层模块不应该依赖低层模块，二者都应该依赖抽象。抽象不应该依赖细节，细节应该依赖抽象。

   例如：

    ```java
    interface Database {
        void save(String data);
        String get(int id);
    }

    class MySQLDatabase implements Database {
        // ...
    }

    class UserManager {
        private Database database;
        
        UserManager(Database database) {
            this.database = database;
        }

        // ...
    }
    ```

   在这个例子中，`UserManager` 不直接依赖 `MySQLDatabase`，而是依赖 `Database` 这个抽象接口。这样我们就可以随时更换数据库，只需要提供一个新的实现了 `Database` 接口的类即可。

### SRP: Single Responsibility Principle

单一功能原则（Single Responsibility Principle, SRP）意味着一个类应该只有一个引起它变化的原因，即一个类只应该有一项工作。
> https://www.educative.io/courses/grokking-the-low-level-design-interview-using-ood-principles/YQ3953D0L4n

#### 不遵守 SRP 的例子：

```java
class User {
    String name;
    String email;

    void saveUser() {
        // 保存用户
    }

    void deleteUser() {
        // 删除用户
    }

    void printUser() {
        // 打印用户信息
    }
}
```
在这个例子中，User 类负责用户信息，也负责保存和删除用户，以及打印用户信息。如果需要修改任何这些行为，都需要修改 User 类，这违反了单一职责原则。

#### 遵守 SRP 的例子：

```java
class User {
    String name;
    String email;
}

class UserManager {
    void saveUser(User user) {
        // 保存用户
    }

    void deleteUser(User user) {
        // 删除用户
    }
}

class UserPrinter {
    void printUser(User user) {
        // 打印用户信息
    }
}
```

在遵守了 SRP 的例子中，User 类只负责存储用户信息，UserManager 类负责保存和删除用户，UserPrinter 类负责打印用户信息。如果需要修改保存、删除或打印的逻辑，只需要修改对应的类，而不会影响其他的类。这样就减少了类之间的耦合度，使得代码更易于理解和维护，更容易进行单元测试。

#### One more example

当然，除了存储数据的场景，还有很多其他场景也可以应用单一职责原则。以下是一个不涉及数据存储的例子：

**不遵守 SRP 的例子**：

```java
class Car {
    void startEngine() {
        // 启动引擎
    }

    void stopEngine() {
        // 停止引擎
    }

    void turnLeft() {
        // 左转
    }

    void turnRight() {
        // 右转
    }

    void playMusic(String musicName) {
        // 播放音乐
    }

    void stopMusic() {
        // 停止播放音乐
    }
}
```

在上述代码中，`Car` 类处理了太多职责，包括驾驶和音乐播放。

**遵守 SRP 的例子**：

```java
class Car {
    void startEngine() {
        // 启动引擎
    }

    void stopEngine() {
        // 停止引擎
    }

    void turnLeft() {
        // 左转
    }

    void turnRight() {
        // 右转
    }
}

class MusicPlayer {
    void playMusic(String musicName) {
        // 播放音乐
    }

    void stopMusic() {
        // 停止播放音乐
    }
}
```

在遵守 SRP 的例子中，`Car` 类只负责驾驶相关的职责，而 `MusicPlayer` 类负责音乐播放的职责。这样就使得每个类的职责更加清晰，当我们需要修改驾驶逻辑时，只需要更改 `Car` 类；当我们需要修改音乐播放逻辑时，只需要更改 `MusicPlayer` 类。这大大降低了代码的复杂性，提高了代码的可读性和可维护性。

### OCP: Open-Closed Principle

开放封闭原则（Open-Closed Principle, OCP）是面向对象设计中的一项重要原则，它要求软件实体（如类、模块、函数等等）应该对扩展开放，对修改封闭。这就意味着我们的代码应该允许在不修改已有代码的情况下添加新功能。

> https://www.educative.io/courses/grokking-the-low-level-design-interview-using-ood-principles/m7MBZv08393

One might think of OCP as inheritance, but remember that inheritance is only one of the OCP techniques. We use the interface because it is open for extension and closed for modification. Therefore, OCP is also defined as polymorphic OCP.

#### 不遵守 OCP 的例子：

```java
class Shape {
    String type;
}

class AreaCalculator {
    double calculateArea(Shape[] shapes) {
        double totalArea = 0;
        for (Shape shape : shapes) {
            if ("Rectangle".equals(shape.type)) {
                totalArea += 20; // 计算矩形的面积
            } else if ("Circle".equals(shape.type)) {
                totalArea += 20; // 计算圆的面积
            }
        }
        return totalArea;
    }
}
```

在这个例子中，如果我们需要添加更多的形状，我们需要修改 `AreaCalculator` 类，这违反了开放封闭原则。

#### 遵守 OCP 的例子：

```java
interface Shape {
    double area();
}

class Rectangle implements Shape {
    double length;
    double width;

    public double area() {
        return length * width;
    }
}

class Circle implements Shape {
    double radius;

    public double area() {
        return Math.PI * Math.pow(radius, 2);
    }
}

class AreaCalculator {
    double totalArea(Shape[] shapes) {
        double total = 0;
        for (Shape shape : shapes) {
            total += shape.area();
        }
        return total;
    }
}
```

在遵守了 OCP 的例子中，如果我们需要添加更多的形状，我们只需要创建一个新的类实现 `Shape` 接口，然后在新的类中实现 `area` 方法。我们无需修改 `AreaCalculator` 类，因此这个设计更符合开放封闭原则。

开放封闭原则的优点是提高了软件系统的可复用性和可维护性，使得软件更易于扩展，同时也降低了对现有系统引入错误的风险。

### LSP: Liskov Substitution Principle

里氏替换原则（Liskov Substitution Principle，LSP）是面向对象设计的一个重要原则，它表述为“子类型必须能够替换掉它们的基类型”。

这个原则的核心思想是，如果一个程序使用基类型的地方，都可以使用其子类型代替，而不改变程序的正确性。也就是说，子类对象能够替换父类对象，而不会导致程序出错。

> https://www.educative.io/courses/grokking-the-low-level-design-interview-using-ood-principles/N0Qqxkw5ynm

#### 不遵守 Liskov 替换原则的例子：

```java
class Bird {
    void fly() {
        System.out.println("I can fly");
    }
}

class Sparrow extends Bird {
    // 由于 Sparrow 是 Bird 的子类，因此它可以替换 Bird 
}

class Penguin extends Bird {
    // 由于 Penguin（企鹅）不能飞，因此它违反了 Liskov 替换原则
    @Override
    void fly() {
        throw new UnsupportedOperationException("Penguins can't fly");
    }
}

class BirdKeeper {
    void letItFly(Bird bird) {
        bird.fly();
    }
}
```

在这个例子中，`BirdKeeper` 类在调用 `letItFly` 方法时，期望传入的是一个会飞的鸟类。对于 `Sparrow` 类（麻雀），它是 `Bird` 的子类，因此可以替换 `Bird` 类而不会出现问题。但对于 `Penguin` 类（企鹅），由于企鹅不能飞，因此如果我们试图将 `Penguin` 类的对象传递给 `letItFly` 方法，那么程序就会抛出异常，因此 `Penguin` 类违反了 Liskov 替换原则。

遵守 Liskov 替换原则可以增强程序的可扩展性，提高程序的稳定性，因为子类型可以替换基类型，使得程序对新类的适应性增强。

#### 遵守 Liskov 替换原则的例子
为了解决这个问题，我们可以将飞翔的行为抽象为一个接口，然后只有那些能飞的鸟类才实现这个接口。这样，我们就能保证只有能飞的鸟类才会被传递给 `letItFly` 方法，从而遵守了 Liskov 替换原则。

改进的代码如下：

```java
interface Flyable {
    void fly();
}

class Bird {
    // 其他通用的鸟类行为
}

class Sparrow extends Bird implements Flyable {
    public void fly() {
        System.out.println("Sparrow can fly");
    }
}

class Penguin extends Bird {
    // Penguins don't implement Flyable because they can't fly
}

class BirdKeeper {
    void letItFly(Flyable bird) {
        bird.fly();
    }
}
```

在这个例子中，`BirdKeeper` 类的 `letItFly` 方法只接受 `Flyable` 类型的参数，因此我们只能将实现了 `Flyable` 接口的对象传递给 `letItFly` 方法。这样，我们就可以确保传递给 `letItFly` 方法的对象一定是会飞的，避免了企鹅这种不能飞的鸟类导致的问题。

此外，如果以后有新的鸟类能飞，我们只需要让新的鸟类实现 `Flyable` 接口，然后在新的鸟类中实现 `fly` 方法，就可以将新的鸟类的对象传递给 `letItFly` 方法，无需修改 `BirdKeeper` 类的代码，这样就增加了代码的可扩展性。

### ISP: Interface Segregation Principle

接口隔离原则（Interface Segregation Principle，ISP）指的是客户端不应该被迫依赖于它不使用的接口。这个原则的目的是将臃肿的接口分解为更小的和更具体的接口，以便客户端只需要知道它们感兴趣的方法。

> https://www.educative.io/courses/grokking-the-low-level-design-interview-using-ood-principles/m7g5O3xmqgA

#### 不遵守 ISP 的例子：

```java
interface Worker {
    void work();
    void eat();
}

class WorkerImpl implements Worker {
    public void work() {
        // ....工作
    }

    public void eat() {
        // ....吃饭
    }
}

class Robot implements Worker {
    public void work() {
        // ....工作
    }

    public void eat() {
        // 机器人不需要吃饭，但由于接口定义了 eat 方法，所以不得不实现
    }
}
```

在这个例子中，Robot 类实现了 Worker 接口，但 Robot 不需要 eat 方法。这就是一个典型的不遵守接口隔离原则的例子。

#### 遵守 ISP 的例子：

```java
interface Workable {
    void work();
}

interface Eatable {
    void eat();
}

class WorkerImpl implements Workable, Eatable {
    public void work() {
        // ....工作
    }

    public void eat() {
        // ....吃饭
    }
}

class Robot implements Workable {
    public void work() {
        // ....工作
    }
    // Robot 不需要实现 eat 方法
}
```

在这个例子中，我们把 Worker 接口拆分成了两个接口：Workable 和 Eatable。WorkerImpl 类需要实现这两个接口，而 Robot 类只需要实现 Workable 接口。这样，Robot 类就不再需要实现它不需要的 eat 方法了，这就遵守了接口隔离原则。

接口隔离原则可以降低类之间的耦合度，提高系统的可维护性，增强系统的可扩展性，同时也增强了系统的灵活性。

### DIP: Dependency Inversion Principle

依赖反转原则（Dependency Inversion Principle，DIP）是面向对象设计的一个重要原则，它的主要思想是依赖于抽象而不是依赖于具体。换句话说，高级模块不应该依赖于低级模块，它们都应该依赖于抽象。

> https://www.educative.io/courses/grokking-the-low-level-design-interview-using-ood-principles/39lyNKQNOKn

#### 不遵守 DIP 的例子：

```java
class LightBulb {
    void turnOn() {
        // 开灯
    }

    void turnOff() {
        // 关灯
    }
}

class ElectricPowerSwitch {
    private LightBulb lightBulb;

    ElectricPowerSwitch(LightBulb lightBulb) {
        this.lightBulb = lightBulb;
    }

    void press() {
        // 控制灯泡的开关
    }
}
```

在这个例子中，ElectricPowerSwitch（电源开关）类直接依赖于具体的 LightBulb（灯泡）类，如果我们要控制其他的设备，比如风扇，那么我们就需要修改 ElectricPowerSwitch 类或者创建一个新的开关类，这违反了依赖反转原则。

#### 遵守 DIP 的例子

```java
interface Switchable {
    void turnOn();
    void turnOff();
}

class LightBulb implements Switchable {
    public void turnOn() {
        // 开灯
    }

    public void turnOff() {
        // 关灯
    }
}

class Fan implements Switchable {
    public void turnOn() {
        // 开风扇
    }

    public void turnOff() {
        // 关风扇
    }
}

class ElectricPowerSwitch {
    private Switchable device;

    ElectricPowerSwitch(Switchable device) {
        this.device = device;
    }

    void press() {
        // 控制设备的开关
    }
}
```

在遵守了 DIP 的例子中，我们定义了一个 Switchable 接口，然后让 LightBulb 类和 Fan 类都实现这个接口。ElectricPowerSwitch 类不再直接依赖于 LightBulb 类，而是依赖于 Switchable 接口。这样，我们就可以用 ElectricPowerSwitch 类来控制任何实现了 Switchable 接口的设备，无需修改 ElectricPowerSwitch 类的代码，这就遵守了依赖反转原则。

依赖反转原则可以减少类之间的耦合性，提高系统的稳定性，同时提高代码的可读性和可维护性，使得代码更易于测试和复用。
