English Edition

1. Create a startup class.
2. Create a controller, entity, and repository.
3. Inject database statements from various repositories into the UserRepository.
4. Inject the dynamically generated proxy object created by the Mapper, scan the declared proxies of the Mapper into the IOC container, and add a @MapperScan annotation.
5. Use the @PostMapping annotation in the controller to communicate between microservices via REST. Encapsulate the data into JSON format, then use @RequestBody to convert the JSON data into a User object, and finally call the save method of the UserRepository to save the user information.
6. Use the @EnableFeignClients annotation to enable the Feign client for calling.



Chinese Edition 
1. 创建启动类。
2. 创建控制器（Controller）、实体类（Entity）、仓库（Repository）。
3. 注入各种 Repository 中的 UserRepository 数据库语句。
4. 注入 Mapper 创建的动态代理对象，将 Mapper 声明的代理扫描到 IOC 容器中，并添加一个 @MapperScan 注解。
5. 在控制器中使用 @PostMapping 注解，通过 REST 方式进行微服务之间的沟通，将数据封装成 JSON，然后使用 @RequestBody 将 JSON 格式的数据转化成 User 对象，最后调用 UserRepository 的 save 方法保存用户信息。
6. 使用 @EnableFeignClients 注解表示启用 Feign 客户端进行调用。