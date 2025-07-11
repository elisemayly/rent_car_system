# 租车管理系统 (Car Rental Management System)

## 项目概述

这是一个基于Java开发的控制台租车管理系统，采用面向对象设计模式，实现了汽车信息管理和租车业务流程的完整功能。系统支持多种车型管理，提供直观的用户交互界面。

## 功能特性

### 🚗 汽车管理系统
- **车辆信息管理**：支持添加、删除、修改、查询汽车信息
- **多车型支持**：轿车(Car)、卡车(Truck)、皮卡(PickUp)
- **属性管理**：品牌、价格、载人/载货容量等
- **数据持久化**：内存中的数据存储和管理

### 🏪 租车业务系统
- **车辆选择**：浏览可用车辆并进行选择
- **订单管理**：创建和查看租车订单
- **库存查看**：实时查看所有可用车辆

## 技术架构

### 设计模式
- **接口设计模式**：`Management`接口定义统一的管理操作
- **策略模式**：不同车型实现不同的功能接口
- **单例模式思想**：全局常量类管理共享数据

### 面向对象特性
- **继承**：`Car`、`Truck`、`PickUp`继承自`Automobile`基类
- **多态**：通过接口`CanCarryPeople`和`CanCarryCargo`实现多态
- **封装**：私有属性配合公共方法实现数据封装
- **抽象**：接口抽象出通用的管理和功能方法

### 类结构图
```
Automobile (基类)
├── Car (轿车) implements CanCarryPeople
├── Truck (卡车) implements CanCarryCargo  
└── PickUp (皮卡) implements CanCarryPeople, CanCarryCargo

Management (管理接口)
└── AutomobileManager (汽车管理器)

RentSystem (租车系统)
RentSysConst (全局常量类)
```

## 技术栈

- **编程语言**：Java 8+
- **开发工具**：IntelliJ IDEA
- **数据结构**：HashMap、Scanner
- **设计模式**：接口模式、继承、多态

## 系统架构

### 核心模块

1. **汽车实体模块**
   - `Automobile`：汽车基类
   - `Car`、`Truck`、`PickUp`：具体车型实现

2. **功能接口模块**
   - `CanCarryPeople`：载人功能接口
   - `CanCarryCargo`：载货功能接口
   - `Management`：管理功能接口

3. **业务逻辑模块**
   - `AutomobileManager`：汽车管理器
   - `RentSystem`：租车系统

4. **数据管理模块**
   - `RentSysConst`：全局常量和数据存储

## 使用说明

### 运行环境
- Java 8 或更高版本
- 控制台环境

### 启动方式
```bash
# 编译
javac src/MainProcess.java

# 运行
java -cp src MainProcess
```

### 操作流程

1. **启动系统**：选择进入汽车管理系统或租车系统
2. **汽车管理**：
   - 添加车辆：选择车型并输入品牌、价格
   - 管理车辆：删除、修改、查询车辆信息
3. **租车业务**：
   - 浏览车辆：查看所有可用车辆
   - 创建订单：选择车辆加入租车订单
   - 订单管理：查看当前订单详情

## 项目亮点

### 技术亮点
- **面向对象设计**：完整的OOP设计，体现继承、多态、封装特性
- **接口编程**：通过接口实现功能的模块化和可扩展性
- **设计模式应用**：合理运用多种设计模式提高代码质量
- **异常处理**：完善的输入验证和错误处理机制

### 业务亮点
- **双系统架构**：汽车管理和租车业务分离，职责清晰
- **多车型支持**：灵活的车型扩展机制
- **用户友好**：直观的菜单系统和操作提示

## 代码质量

- **注释完整**：JavaDoc规范注释，代码可读性强
- **结构清晰**：模块化设计，层次分明
- **命名规范**：遵循Java命名约定
- **错误处理**：完善的异常处理和用户提示

## 扩展性

系统具有良好的扩展性，可以轻松添加：
- 新的车型类别
- 更多的车辆属性
- 复杂的租车业务逻辑
- 数据库持久化功能
- Web界面支持

## 开发信息

- **开发时间**：2024年
- **代码行数**：约700行
- **文件结构**：
  ```
  src/
  ├── MainProcess.java    # 主程序和所有类定义
  └── Main.java          # 测试文件
  ```

## 总结

这个租车管理系统展示了Java面向对象编程的核心概念和最佳实践，通过合理的架构设计和代码组织，实现了一个功能完整、结构清晰的管理系统。项目体现了软件工程中的模块化思想和可维护性原则。
