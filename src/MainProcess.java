import java.util.HashMap;
import java.util.Scanner;
import java.util.Map;

/**
 * 管理系统通用接口
 * 定义了基本的CRUD操作和系统退出功能
 *
 * @author 开发者姓名
 * @version 1.0
 */
interface Management {
    /**
     * 添加新项目
     * @param type 项目类型标识
     */
    void add(int type);

    /**
     * 删除指定ID的项目
     * @param ID 项目唯一标识符
     */
    void delete(int ID);

    /**
     * 更新指定ID的项目信息
     * @param ID 项目唯一标识符
     */
    void update(int ID);

    /**
     * 显示指定ID的项目详细信息
     * @param ID 项目唯一标识符
     */
    void show(int ID);

    /**
     * 退出系统
     */
    void exit();
}

/**
 * 汽车管理器类
 * 实现Management接口，提供汽车的增删改查功能
 * 支持三种车型：轿车(Car)、卡车(Truck)、皮卡(PickUp)
 */
class AutomobileManager implements Management {
    /** 汽车列表存储容器（已弃用，现使用全局常量类中的AUTO_LIST） */
    private HashMap<Integer, Automobile> automobileList;

    /**
     * 构造函数
     * 初始化汽车列表
     */
    public AutomobileManager() {
        automobileList = new HashMap<>();
    }

    /**
     * 添加新汽车到系统中
     * 支持添加轿车、卡车、皮卡三种类型
     *
     * @param type 汽车类型 (1-轿车, 2-卡车, 3-皮卡)
     */
    public void add(int type) {
        Scanner scanner = new Scanner(System.in);

        // 获取汽车品牌信息
        System.out.println("Enter brand:");
        String brand = scanner.nextLine();

        // 获取汽车价格信息
        System.out.println("Enter price:");
        double price = scanner.nextDouble();

        // 根据类型创建对应的汽车对象
        Automobile automobile;
        switch (type) {
            case 1: // 轿车
                automobile = new Car();
                break;
            case 2: // 卡车
                automobile = new Truck();
                break;
            case 3: // 皮卡
                automobile = new PickUp();
                break;
            default:
                System.out.println("Invalid type.");
                return;
        }

        // 设置汽车基本属性
        automobile.setBrand(brand);
        automobile.setPrice(price);

        // 将汽车添加到全局汽车列表中，并自动分配ID
        RentSysConst.AUTO_LIST.put(RentSysConst.AUTO_ID++, automobile);

        System.out.println("Car added successfully.");
    }

    /**
     * 删除指定ID的汽车
     *
     * @param ID 要删除的汽车ID
     */
    public void delete(int ID) {
        // 检查是否存在对应ID的汽车
        if (!RentSysConst.AUTO_LIST.containsKey(ID)) {
            System.out.println("Car with ID " + ID + " does not exist.");
            return;
        }

        // 从汽车列表中移除对应ID的汽车
        RentSysConst.AUTO_LIST.remove(ID);

        System.out.println("Car with ID " + ID + " deleted successfully.");
    }

    /**
     * 更新指定ID汽车的信息
     * 支持更新品牌和价格
     *
     * @param ID 要更新的汽车ID
     */
    public void update(int ID) {
        // 检查是否存在对应ID的汽车
        if (!RentSysConst.AUTO_LIST.containsKey(ID)) {
            System.out.println("Car with ID " + ID + " does not exist.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter 1 to update brand, 2 to update price:");
        int choice = scanner.nextInt();

        // 获取要更新的汽车对象
        Automobile automobile = RentSysConst.AUTO_LIST.get(ID);

        switch (choice) {
            case 1: // 更新品牌
                System.out.println("Enter new brand:");
                String newBrand = scanner.next();
                automobile.setBrand(newBrand);
                break;
            case 2: // 更新价格
                System.out.println("Enter new price:");
                double newPrice = scanner.nextDouble();
                automobile.setPrice(newPrice);
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        System.out.println("Car with ID " + ID + " updated successfully.");
    }

    /**
     * 显示指定ID汽车的详细信息
     *
     * @param ID 要显示的汽车ID
     */
    public void show(int ID) {
        // 检查是否存在对应ID的汽车
        if (!RentSysConst.AUTO_LIST.containsKey(ID)) {
            System.out.println("Car with ID " + ID + " does not exist.");
            return;
        }

        // 获取汽车对象并显示详细信息
        Automobile automobile = RentSysConst.AUTO_LIST.get(ID);

        System.out.println("Car ID: " + ID);
        System.out.println("Brand: " + automobile.getBrand());
        System.out.println("Price: " + automobile.getPrice());
    }

    /**
     * 退出汽车管理系统
     */
    public void exit() {
        System.out.println("Exiting the program...");
        System.exit(0);
    }

    /**
     * 初始化汽车管理系统
     * 创建全局数据结构并设置初始值
     */
    public void init() {
        RentSysConst.AUTO_LIST = new HashMap<>();
        RentSysConst.AUTO_ID = 1;
        RentSysConst.input = new Scanner(System.in);
        System.out.println("Car management system initialized.");
    }

    /**
     * 显示汽车管理系统主菜单
     */
    public void showMenu() {
        System.out.println("Car Management System Menu:");
        System.out.println("1. Add Car");
        System.out.println("2. Delete Car");
        System.out.println("3. Update Car");
        System.out.println("4. Show Car");
        System.out.println("5. Show All Cars");
        System.out.println("6. Exit");
        System.out.println("Please enter your choice:");
    }

    /**
     * 获取用户输入并处理相应的操作
     * 主要的用户交互循环，处理菜单选择
     */
    void getUserInput() {
        int choice;
        do {
            showMenu();
            choice = RentSysConst.input.nextInt();
            switch (choice) {
                case 1: // 添加汽车
                    System.out.println("Enter car type (1 for Car, 2 for Truck, 3 for PickUp):");
                    int type = RentSysConst.input.nextInt();
                    add(type);
                    break;
                case 2: // 删除汽车
                    System.out.println("Enter car ID to delete:");
                    int deleteID = RentSysConst.input.nextInt();
                    delete(deleteID);
                    break;
                case 3: // 更新汽车信息
                    System.out.println("Enter car ID to update:");
                    int updateID = RentSysConst.input.nextInt();
                    update(updateID);
                    break;
                case 4: // 显示单个汽车信息
                    System.out.println("Enter car ID to show:");
                    int showID = RentSysConst.input.nextInt();
                    show(showID);
                    break;
                case 5: // 显示所有汽车
                    showCar();
                    break;
                case 6: // 退出系统
                    exit();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 6);
    }

    /**
     * 显示所有汽车的详细信息
     * 遍历汽车列表并打印每辆车的信息
     */
    private void showCar() {
        System.out.println("All Cars:");
        // 遍历全局汽车列表
        for (Map.Entry<Integer, Automobile> entry : RentSysConst.AUTO_LIST.entrySet()) {
            int ID = entry.getKey();
            Automobile automobile = entry.getValue();
            System.out.println("Car ID: " + ID);
            System.out.println("Brand: " + automobile.getBrand());
            System.out.println("Price: " + automobile.getPrice());
            System.out.println(); // 空行分隔
        }
    }
}

/**
 * 租车系统常量类
 * 存储全局共享的数据结构和常量
 */
class RentSysConst {
    /** 全局汽车列表，存储所有汽车信息 */
    public static HashMap<Integer, Automobile> AUTO_LIST;
    /** 汽车ID计数器，用于自动分配唯一ID */
    public static int AUTO_ID;
    /** 全局输入扫描器 */
    public static Scanner input;
}

/**
 * 载货能力接口
 * 定义了载货相关的方法
 */
interface CanCarryCargo {
    /**
     * 设置载货容量
     * @param capacity 载货容量
     */
    void setCargoCapacity(double capacity);

    /**
     * 获取载货容量
     * @return 载货容量
     */
    double getCargoCapacity();
}

/**
 * 载人能力接口
 * 定义了载人相关的方法
 */
interface CanCarryPeople {
    /**
     * 设置载人容量
     * @param capacity 载人容量
     */
    void setPeopleCapacity(double capacity);

    /**
     * 获取载人容量
     * @return 载人容量
     */
    double getPeopleCapacity();
}

/**
 * 汽车基类
 * 定义了所有汽车的基本属性和方法
 */
class Automobile {
    /** 汽车唯一标识符 */
    private int ID;
    /** 汽车品牌 */
    private String brand;
    /** 汽车价格 */
    private double price;

    /**
     * 设置汽车品牌
     * @param brand 汽车品牌
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * 设置汽车价格
     * @param price 汽车价格
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * 设置汽车ID
     * @param ID 汽车唯一标识符
     */
    public void setID(int ID){
        this.ID = ID; // 修复bug：原来错误地设置为price
    }

    /**
     * 获取汽车品牌
     * @return 汽车品牌
     */
    public String getBrand() {
        return brand;
    }

    /**
     * 获取汽车价格
     * @return 汽车价格
     */
    public double getPrice() {
        return price;
    }

    /**
     * 获取汽车ID
     * @return 汽车唯一标识符
     */
    public int getID() {
        return ID;
    }
}

/**
 * 轿车类
 * 继承自Automobile，实现载人功能
 * 主要用于载客运输
 */
class Car extends Automobile implements CanCarryPeople {
    /** 载人容量 */
    private double peopleCapacity;

    /**
     * 设置载人容量
     * @param capacity 载人容量
     */
    public void setPeopleCapacity(double capacity) {
        this.peopleCapacity = capacity;
    }

    /**
     * 获取载人容量
     * @return 载人容量
     */
    public double getPeopleCapacity() {
        return peopleCapacity;
    }
}

/**
 * 卡车类
 * 继承自Automobile，实现载货功能
 * 主要用于货物运输
 */
class Truck extends Automobile implements CanCarryCargo {
    /** 载货容量 */
    private double cargoCapacity;

    /**
     * 设置载货容量
     * @param capacity 载货容量
     */
    public void setCargoCapacity(double capacity) {
        this.cargoCapacity = capacity;
    }

    /**
     * 获取载货容量
     * @return 载货容量
     */
    public double getCargoCapacity() {
        return cargoCapacity;
    }
}

/**
 * 皮卡类
 * 继承自Automobile，同时实现载货和载人功能
 * 具有多功能运输能力
 */
class PickUp extends Automobile implements CanCarryCargo, CanCarryPeople {
    /** 载货容量 */
    private double cargoCapacity;
    /** 载人容量 */
    private double peopleCapacity;

    /**
     * 设置载货容量
     * @param capacity 载货容量
     */
    public void setCargoCapacity(double capacity) {
        this.cargoCapacity = capacity;
    }

    /**
     * 获取载货容量
     * @return 载货容量
     */
    public double getCargoCapacity() {
        return cargoCapacity;
    }

    /**
     * 设置载人容量
     * @param capacity 载人容量
     */
    public void setPeopleCapacity(double capacity) {
        this.peopleCapacity = capacity;
    }

    /**
     * 获取载人容量
     * @return 载人容量
     */
    public double getPeopleCapacity() {
        return peopleCapacity;
    }
}

/**
 * 租车系统类
 * 提供汽车租赁功能，包括选择汽车、管理订单等
 * 支持多车型租赁和订单管理
 */
class RentSystem {
    /** 订单列表，存储订单ID到汽车及数量的映射 */
    private HashMap<Integer, HashMap<Automobile, Integer>> orders;
    /** 订单ID计数器，用于自动分配唯一订单ID */
    private int ID = 1;

    /**
     * 构造函数
     * 初始化订单列表
     */
    public RentSystem() {
        orders = new HashMap<>();
    }

    /**
     * 显示租车系统主菜单
     */
    public void showMenu() {
        System.out.println("Rent System Menu:");
        System.out.println("1. Choose Automobiles");
        System.out.println("2. Show Order");
        System.out.println("3. Show All Cars");
        System.out.println("4. Exit");
        System.out.println("Please enter your choice:");
    }

    /**
     * 选择汽车进行租赁
     * 显示可用汽车列表，允许用户选择并添加到订单中
     */
    public void chooseAutomobiles() {
        Scanner scanner = new Scanner(System.in);

        // 显示所有可用汽车信息供用户选择
        System.out.println("Available Cars:");
        for (Map.Entry<Integer, Automobile> entry : RentSysConst.AUTO_LIST.entrySet()) {
            int ID = entry.getKey();
            Automobile automobile = entry.getValue();
            System.out.println("Car ID: " + ID);
            System.out.println("Brand: " + automobile.getBrand());
            System.out.println("Price: " + automobile.getPrice());
            System.out.println();
        }

        // 获取用户选择的汽车ID
        System.out.println("Enter Car ID to rent:");
        int carID = scanner.nextInt();

        // 验证汽车ID是否存在
        if (!RentSysConst.AUTO_LIST.containsKey(carID)) {
            System.out.println("Car with ID " + carID + " does not exist.");
            return;
        }

        // 将选择的汽车添加到当前订单中
        Automobile automobile = RentSysConst.AUTO_LIST.get(carID);
        if (!orders.containsKey(ID)) {
            orders.put(ID, new HashMap<>());
        }
        orders.get(ID).put(automobile, 1); // 默认租赁数量为1

        System.out.println("Car with ID " + carID + " added to the order successfully.");
    }

    /**
     * 显示所有订单信息
     * 遍历并打印所有订单的详细内容
     */
    public void showOrder() {
        if (orders.isEmpty()) {
            System.out.println("No orders.");
            return;
        }

        System.out.println("Orders:");
        // 遍历所有订单
        for (Map.Entry<Integer, HashMap<Automobile, Integer>> entry : orders.entrySet()) {
            int orderID = entry.getKey();
            HashMap<Automobile, Integer> orderDetails = entry.getValue();

            System.out.println("Order ID: " + orderID);
            // 遍历订单中的每个汽车项目
            for (Map.Entry<Automobile, Integer> orderEntry : orderDetails.entrySet()) {
                Automobile automobile = orderEntry.getKey();
                int quantity = orderEntry.getValue();

                System.out.println("Car ID: " + automobile.getID());
                System.out.println("Brand: " + automobile.getBrand());
                System.out.println("Price: " + automobile.getPrice());
                System.out.println("Quantity: " + quantity);
                System.out.println();
            }
        }
    }

    /**
     * 显示所有可用汽车
     * 展示系统中所有汽车的基本信息
     */
    public void showAllCars() {
        System.out.println("Available Cars:");
        for (Map.Entry<Integer, Automobile> entry : RentSysConst.AUTO_LIST.entrySet()) {
            int ID = entry.getKey();
            Automobile automobile = entry.getValue();
            System.out.println("Car ID: " + ID);
            System.out.println("Brand: " + automobile.getBrand());
            System.out.println("Price: " + automobile.getPrice());
            System.out.println();
        }
    }

    /**
     * 初始化租车系统
     * 重置订单列表和ID计数器
     */
    public void init() {
        orders = new HashMap<>();
        ID = 1;
        System.out.println("Renting system initialized.");
    }

    /**
     * 退出租车系统
     */
    public void exit() {
        System.out.println("Exiting the program...");
        System.exit(0);
    }
    /**
     * 获取用户输入并处理租车系统操作
     * 主要的用户交互循环，处理租车相关的菜单选择
     */
    public void getUserInput() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            showMenu();
            choice = scanner.nextInt();
            switch (choice) {
                case 1: // 选择汽车进行租赁
                    chooseAutomobiles();
                    break;
                case 2: // 显示当前订单
                    showOrder();
                    break;
                case 3: // 显示所有可用汽车
                    showAllCars();
                    break;
                case 4: // 退出租车系统
                    exit();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 4); // 修复bug：应该是4而不是3
    }
}

/**
 * 主程序类
 * 系统入口点，提供汽车管理系统和租车系统的选择界面
 *
 * @author 开发者姓名
 * @version 1.0
 * @since 2024
 */
public class MainProcess {
    /**
     * 程序主入口方法
     * 初始化两个子系统并提供系统选择菜单
     *
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        // 创建并初始化汽车管理系统
        AutomobileManager automobileManager = new AutomobileManager();
        automobileManager.init();

        // 创建并初始化租车系统
        RentSystem rentSystem = new RentSystem();
        rentSystem.init();

        Scanner scanner = new Scanner(System.in);
        int choice;

        // 主系统选择循环
        do {
            System.out.println("Select system to enter:");
            System.out.println("1. Car Management System");
            System.out.println("2. Rent System");
            System.out.println("3. Exit");
            choice = scanner.nextInt();

            switch (choice) {
                case 1: // 进入汽车管理系统
                    automobileManager.getUserInput();
                    break;
                case 2: // 进入租车系统
                    rentSystem.getUserInput();
                    break;
                case 3: // 退出整个程序
                    System.out.println("Exiting the program...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 3);

        // 关闭扫描器资源
        scanner.close();
    }
}