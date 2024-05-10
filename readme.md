# DineHub - Restaurant Ordering System
DineHub is a comprehensive restaurant ordering system built using Spring Cloud. It provides functionalities for both ordinary users and administrators to manage orders, users, menus, and more.

## Microservices
The system is composed of several microservices:

Eureka Server: Service registry for microservices.
Config Server: Centralized configuration management.
Account Service: Manages user accounts and authentication.
Menu Service: Handles menu management operations.
Order Service: Manages order information.
User Service: Manages user information.
## Database
DineHub utilizes MySQL as its database management system with the following tables:

t_admin: Stores administrator information.
t_menu: Contains menu item details.
t_order: Stores order information.
t_type: Holds information about menu item types.
t_user: Stores user information.
## Functionality
### For Ordinary Users:
Register an account.
Place orders.
View order status.
### For Administrators:
Add, delete, or edit users.
Manage order statuses (e.g., mark orders as completed).
Add, edit, or delete menu items.
## Getting Started
### Clone the Repository:
git clone https://github.com/wulitina/DineHub.git
### Set up Configuration:
Update configuration files in the Config Server according to your environment.
### Build and Run Microservices:
Build and run each microservice using Maven or your preferred build tool.
Ensure proper communication between microservices.
### Database Setup:
Create a MySQL database and import the provided schema.
### Run the Application:
Start the Eureka Server.
Start the Config Server.
Start all other microservices.
### Accessing the Application:
Access the application through the provided login interface.
Ordinary users can register and place orders.
Administrators can manage users, orders, and menus.
## Contributors
YanXu (Tina)


Chinese Edition
# DineHub - 餐厅点餐系统
DineHub 是一个使用 Spring Cloud 构建的全面餐厅点餐系统。它提供了普通用户和管理员管理订单、用户、菜单等功能。

## 微服务
该系统由多个微服务组成：

eurekaserver： 用于微服务的服务注册。
configserver： 集中式配置管理。
account Service： 管理用户账户和身份验证。
Menu Service： 处理菜单管理操作。
Order Service： 管理订单信息。
User Service： 管理用户信息。
## 数据库
DineHub 使用 MySQL 作为其数据库管理系统，包含以下表格：

t_admin： 存储管理员信息。
t_menu： 包含菜单项的详细信息。
t_order： 存储订单信息。
t_type： 存储菜单项类型信息。
t_user： 存储用户信息。

## 功能
### 对于普通用户：
注册账户。
下订单。
查看订单状态。
### 对于管理员：
添加、删除或编辑用户。
管理订单状态（例如，标记订单为完成）。
添加、编辑或删除菜单项。
## 入门指南
### 克隆仓库：
bash
复制代码
git clone https://github.com/wulitina/DineHub.git
### 设置配置：
根据您的环境更新配置服务器中的配置文件。
### 构建和运行微服务：
使用 Maven 或您喜欢的构建工具构建和运行每个微服务。
确保微服务之间的正确通信。
### 数据库设置：
创建一个 MySQL 数据库并导入提供的模式。
### 运行应用程序：
启动 Eureka Server。
启动 Config Server。
启动所有其他微服务。
### 访问应用程序：
通过提供的登录界面访问应用程序。
普通用户可以注册并下订单。
管理员可以管理用户、订单和菜单。
## 贡献者
YanXu (Tina)